package com.edutech.edutech_api.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.edutech.edutech_api.model.Curso;
import com.edutech.edutech_api.model.Instructor;
import com.edutech.edutech_api.service.GerenteCursosService;

@RestController
@RequestMapping("/api/gerente-cursos")
public class GerenteCursosController {

    @Autowired
    private GerenteCursosService gerenteCursosService;

    // Gestión de Cursos
    @PostMapping("/cursos")
    public ResponseEntity<?> crearCurso(@RequestBody Curso curso) {
        try {
            Curso nuevoCurso = gerenteCursosService.crearCurso(curso);
            return ResponseEntity.ok(nuevoCurso);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al crear curso: " + e.getMessage());
        }
    }

    @PutMapping("/cursos/{id}")
    public ResponseEntity<?> actualizarCurso(@PathVariable Long id, @RequestBody Curso curso) {
        try {
            Curso cursoActualizado = gerenteCursosService.actualizarCurso(id, curso);
            return ResponseEntity.ok(cursoActualizado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al actualizar curso: " + e.getMessage());
        }
    }

    @DeleteMapping("/cursos/{id}")
    public ResponseEntity<?> eliminarCurso(@PathVariable Long id) {
        try {
            gerenteCursosService.eliminarCurso(id);
            return ResponseEntity.ok("Curso eliminado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al eliminar curso: " + e.getMessage());
        }
    }

    // Gestión de Instructores
    @PostMapping("/cursos/{cursoId}/instructores/{instructorId}")
    public ResponseEntity<?> asignarInstructor(
            @PathVariable Long cursoId,
            @PathVariable Long instructorId) {
        try {
            Instructor instructor = gerenteCursosService.asignarInstructor(cursoId, instructorId);
            return ResponseEntity.ok(instructor);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al asignar instructor: " + e.getMessage());
        }
    }

    // Reportes
    @GetMapping("/cursos/{cursoId}/reporte-inscripciones")
    public ResponseEntity<?> generarReporteInscripciones(@PathVariable Long cursoId) {
        try {
            Map<String, Object> reporte = gerenteCursosService.generarReporteInscripciones(cursoId);
            return ResponseEntity.ok(reporte);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al generar reporte: " + e.getMessage());
        }
    }

    @GetMapping("/cursos/{cursoId}/reporte-rendimiento")
    public ResponseEntity<?> generarReporteRendimiento(@PathVariable Long cursoId) {
        try {
            Map<String, Object> reporte = gerenteCursosService.generarReporteRendimiento(cursoId);
            return ResponseEntity.ok(reporte);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al generar reporte: " + e.getMessage());
        }
    }

    // Validación de Contenido
    @PostMapping("/cursos/{cursoId}/aprobar")
    public ResponseEntity<?> aprobarContenido(@PathVariable Long cursoId) {
        try {
            gerenteCursosService.aprobarContenido(cursoId);
            return ResponseEntity.ok("Contenido aprobado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al aprobar contenido: " + e.getMessage());
        }
    }

    @PostMapping("/cursos/{cursoId}/rechazar")
    public ResponseEntity<?> rechazarContenido(
            @PathVariable Long cursoId,
            @RequestBody Map<String, String> motivo) {
        try {
            gerenteCursosService.rechazarContenido(cursoId, motivo.get("motivo"));
            return ResponseEntity.ok("Contenido rechazado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al rechazar contenido: " + e.getMessage());
        }
    }
} 