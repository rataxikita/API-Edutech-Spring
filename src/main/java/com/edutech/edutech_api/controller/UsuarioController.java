package com.edutech.edutech_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.edutech.edutech_api.dto.UsuarioDTO;
import com.edutech.edutech_api.model.Usuario;
import com.edutech.edutech_api.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<?> crearUsuario(@RequestBody @Valid UsuarioDTO dto) {
        Usuario nuevo = new Usuario();
        nuevo.setNombre(dto.getNombre());
        nuevo.setCorreo(dto.getCorreo());
        nuevo.setClave(dto.getClave());
        nuevo.setRol(dto.getRol());
        nuevo.setActivo(true);

        Usuario creado = usuarioService.crearUsuario(nuevo);
        if (creado == null) {
            return ResponseEntity.badRequest().body("El correo ya está registrado.");
        }
        return ResponseEntity.ok(creado);
    }

    @GetMapping
    public List<Usuario> obtenerUsuarios() {
        return usuarioService.obtenerUsuarios();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable Long id, @RequestBody @Valid UsuarioDTO dto) {
        Usuario datos = new Usuario();
        datos.setNombre(dto.getNombre());
        datos.setCorreo(dto.getCorreo());
        datos.setClave(dto.getClave());
        datos.setRol(dto.getRol());

        Usuario actualizado = usuarioService.actualizarUsuario(id, datos);
        if (actualizado != null) {
            return ResponseEntity.ok(actualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/desactivar")
    public ResponseEntity<?> desactivarUsuario(@PathVariable Long id) {
        Usuario desactivado = usuarioService.desactivarUsuario(id);
        if (desactivado != null) {
            return ResponseEntity.ok(desactivado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
