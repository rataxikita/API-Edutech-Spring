package com.edutech.edutech_api.controller;
//Catalina Rosales->rataxikita
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.edutech.edutech_api.model.Usuario;
import com.edutech.edutech_api.repository.UsuarioRepository;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepo;

    // REGISTRO
    @PostMapping("/registro")
    public ResponseEntity<?> registrar(@RequestBody Usuario usuario) {
        if (usuario.getCorreo() == null || usuario.getClave() == null) {
            return ResponseEntity.badRequest().body("Faltan correo o clave");
        }

        Usuario existente = usuarioRepo.findByCorreo(usuario.getCorreo());
        if (existente != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Correo ya registrado");
        }
        usuario.setEstado(true);
        Usuario usuarioGuardado = usuarioRepo.save(usuario);

        return ResponseEntity.ok(Map.of("msg", "Alumno registrado correctamente", "idUsuario", usuarioGuardado.getId()));
    }

    // LOGIN UNIFICADO
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> datos) {
        String correo = datos.get("correo");
        String clave = datos.get("clave");

        if (correo == null || clave == null) {
            return ResponseEntity.badRequest().body("Faltan datos de acceso");
        }

        Usuario usuario = usuarioRepo.findByCorreoAndClave(correo, clave);

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        }

        if (!usuario.isEstado()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Usuario inactivo");
        }

        // Validación de rol para administradores
        if (datos.containsKey("admin") && Boolean.parseBoolean(datos.get("admin"))) {
            if (!usuario.esAdministrador()) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Acceso denegado: se requieren privilegios de administrador");
            }
        }

        // Devolver el tipo real de usuario
        String tipo = usuario.getClass().getSimpleName();
        return ResponseEntity.ok(Map.of(
            "usuario", usuario,
            "tipo", tipo
        ));
    }

    // LISTAR TODOS
    @GetMapping("/listar")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        return ResponseEntity.ok(usuarioRepo.findAll());
    }

    // OBTENER UNO
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        Usuario usuario = usuarioRepo.findById(id).orElse(null);
        if (usuario == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(usuario);
    }

    // ACTUALIZAR CLAVE Y ESTADO
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Usuario datos) {
        Usuario usuario = usuarioRepo.findById(id).orElse(null);
        if (usuario == null) return ResponseEntity.notFound().build();

        if (datos.getClave() != null) usuario.setClave(datos.getClave());
        usuario.setEstado(datos.isEstado()); // siempre actualiza el estado

        return ResponseEntity.ok(usuarioRepo.save(usuario));
    }

    // DESHABILITAR USUARIO (estado = false)
    @PutMapping("/{id}/deshabilitar")
    public ResponseEntity<?> deshabilitar(@PathVariable Long id) {
        Usuario usuario = usuarioRepo.findById(id).orElse(null);
        if (usuario == null) return ResponseEntity.notFound().build();

        usuario.setEstado(false);
        return ResponseEntity.ok(usuarioRepo.save(usuario));
    }

    // ELIMINAR
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        if (!usuarioRepo.existsById(id)) return ResponseEntity.notFound().build();

        usuarioRepo.deleteById(id);
        return ResponseEntity.ok("Usuario eliminado");
    }

    // ACTUALIZAR NOMBRE Y CORREO
    @PutMapping("/{id}/perfil")
    public ResponseEntity<?> actualizarPerfil(@PathVariable Long id, @RequestBody Usuario datos) {
        Usuario usuario = usuarioRepo.findById(id).orElse(null);
        if (usuario == null) return ResponseEntity.notFound().build();

        if (datos.getNombre() != null) usuario.setNombre(datos.getNombre());
        if (datos.getCorreo() != null) usuario.setCorreo(datos.getCorreo());

        return ResponseEntity.ok(usuarioRepo.save(usuario));
    }
}
//Catalina Rosales->rataxikita
