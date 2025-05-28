// Catalina Rosales->rataxikita
package com.edutech.edutech_api.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.edutech.edutech_api.model.Usuario;
import com.edutech.edutech_api.service.AdministradorService;

@RestController
@RequestMapping("/api/administrador")
public class AdministradorController {

    @Autowired
    private AdministradorService administradorService;

    // Crear nuevo usuario
    @PostMapping("/usuarios")
    public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario) {
        try {
            Usuario nuevoUsuario = administradorService.crearUsuario(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al crear usuario: " + e.getMessage());
        }
    }

    // Obtener todos los usuarios
    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        return ResponseEntity.ok(administradorService.obtenerTodosLosUsuarios());
    }

    // Obtener usuario por ID
    @GetMapping("/usuarios/{id}")
    public ResponseEntity<?> obtenerUsuario(@PathVariable Long id) {
        try {
            Usuario usuario = administradorService.obtenerUsuarioPorId(id);
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Actualizar usuario
    @PutMapping("/usuarios/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        try {
            Usuario usuarioActualizado = administradorService.actualizarUsuario(id, usuario);
            return ResponseEntity.ok(usuarioActualizado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al actualizar usuario: " + e.getMessage());
        }
    }

    // Desactivar usuario
    @PutMapping("/usuarios/{id}/desactivar")
    public ResponseEntity<?> desactivarUsuario(@PathVariable Long id) {
        try {
            administradorService.desactivarUsuario(id);
            return ResponseEntity.ok("Usuario desactivado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al desactivar usuario: " + e.getMessage());
        }
    }

    // Eliminar usuario
    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long id) {
        try {
            administradorService.eliminarUsuario(id);
            return ResponseEntity.ok("Usuario eliminado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al eliminar usuario: " + e.getMessage());
        }
    }

    // Login de administrador
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credenciales) {
        try {
            String correo = credenciales.get("correo");
            String clave = credenciales.get("clave");
            
            if (correo == null || clave == null) {
                return ResponseEntity.badRequest().body("Faltan credenciales");
            }

            return administradorService.login(correo, clave);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error en la autenticación");
        }
    }
}
// Catalina Rosales->rataxikita
