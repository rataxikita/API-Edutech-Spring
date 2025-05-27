package com.edutech.edutech_api.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.edutech.edutech_api.model.Instructor;

public class AdministradorController {

    @PostMapping("/login")
public ResponseEntity<?> login(@RequestBody Map<String, String> datos) {
    String correo = datos.get("correo");
    String clave = datos.get("clave");

    if (correo == null || clave == null) {
        return ResponseEntity.badRequest().body("Faltan datos de acceso");
    }

    Instructor instructor = instructorRepo.findByCorreoAndClave(correo, clave);

    if (instructor == null) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
    }

    if (!"activo".equalsIgnoreCase(instructor.getEstado())) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Instructor inactivo");
    }

    return ResponseEntity.ok(instructor);
}
@DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        if (!instructorRepo.existsById(id)) return ResponseEntity.notFound().build();

        instructorRepo.deleteById(id);
        return ResponseEntity.ok("Instructor eliminado");
    }

    //crear, modificar, actualizar, desactivar
    // revisar caso full stack
}
