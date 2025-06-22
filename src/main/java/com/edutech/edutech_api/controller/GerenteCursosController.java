package com.edutech.edutech_api.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.edutech.edutech_api.model.Curso;
import com.edutech.edutech_api.model.Instructor;
import com.edutech.edutech_api.service.GerenteCursosService;
import com.edutech.edutech_api.dto.InstructorDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/gerente-cursos")
public class GerenteCursosController {

    @Autowired
    private GerenteCursosService gerenteCursosService;

    // Gestión de Cursos
    @PostMapping("/cursos")
    public ResponseEntity<?> crearCurso(@RequestBody Curso curso) {
        try {
            // Simular usuario autenticado (en producción esto vendría del contexto de seguridad)
            gerenteCursosService.setUsuarioAutenticado(1L); // ID del gerente autenticado
            
            Curso nuevoCurso = gerenteCursosService.crearCurso(curso);
            return ResponseEntity.ok(nuevoCurso);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al crear curso: " + e.getMessage());
        }
    }

    @PutMapping("/cursos/{id}")
    public ResponseEntity<?> actualizarCurso(@PathVariable String id, @RequestBody Curso curso) {
        try {
            gerenteCursosService.setUsuarioAutenticado(1L);
            Curso cursoActualizado = gerenteCursosService.actualizarCurso(id, curso);
            return ResponseEntity.ok(cursoActualizado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al actualizar curso: " + e.getMessage());
        }
    }

    @DeleteMapping("/cursos/{id}")
    public ResponseEntity<?> eliminarCurso(@PathVariable String id) {
        try {
            gerenteCursosService.setUsuarioAutenticado(1L);
            gerenteCursosService.eliminarCurso(id);
            return ResponseEntity.ok("Curso eliminado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al eliminar curso: " + e.getMessage());
        }
    }

    // SOLO GERENTE DE CURSOS PUEDE CREAR INSTRUCTORES
    @PostMapping("/instructores")
    public ResponseEntity<?> crearInstructor(@Valid @RequestBody InstructorDTO instructorDTO) {
        try {
            gerenteCursosService.setUsuarioAutenticado(1L);
            
            // Crear instructor desde DTO
            Instructor instructor = new Instructor();
            instructor.setNombre(instructorDTO.getNombre());
            instructor.setApellido(instructorDTO.getApellido());
            instructor.setCorreo(instructorDTO.getCorreo());
            instructor.setClave(instructorDTO.getClave());
            instructor.setRut(instructorDTO.getRut());
            instructor.setEstado(instructorDTO.isEstado());
            
            Instructor instructorGuardado = gerenteCursosService.crearInstructor(instructor);
            return ResponseEntity.ok(instructorGuardado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al crear instructor: " + e.getMessage());
        }
    }

    // Gestión de Instructores
    @PostMapping("/cursos/{cursoId}/instructores/{instructorId}")
    public ResponseEntity<?> asignarInstructor(
            @PathVariable String cursoId,
            @PathVariable Long instructorId) {
        try {
            gerenteCursosService.setUsuarioAutenticado(1L);
            Instructor instructor = gerenteCursosService.asignarInstructor(cursoId, instructorId);
            return ResponseEntity.ok(instructor);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al asignar instructor: " + e.getMessage());
        }
    }

    // Reportes
    @GetMapping("/cursos/{cursoId}/reporte-inscripciones")
    public ResponseEntity<?> generarReporteInscripciones(@PathVariable String cursoId) {
        try {
            gerenteCursosService.setUsuarioAutenticado(1L);
            Map<String, Object> reporte = gerenteCursosService.generarReporteInscripciones(cursoId);
            return ResponseEntity.ok(reporte);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al generar reporte: " + e.getMessage());
        }
    }

    @GetMapping("/cursos/{cursoId}/reporte-rendimiento")
    public ResponseEntity<?> generarReporteRendimiento(@PathVariable String cursoId) {
        try {
            gerenteCursosService.setUsuarioAutenticado(1L);
            Map<String, Object> reporte = gerenteCursosService.generarReporteRendimiento(cursoId);
            return ResponseEntity.ok(reporte);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al generar reporte: " + e.getMessage());
        }
    }

    // Validación de Contenido
    @PostMapping("/cursos/{cursoId}/aprobar")
    public ResponseEntity<?> aprobarContenido(@PathVariable String cursoId) {
        try {
            gerenteCursosService.setUsuarioAutenticado(1L);
            gerenteCursosService.aprobarContenido(cursoId);
            return ResponseEntity.ok("Contenido aprobado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al aprobar contenido: " + e.getMessage());
        }
    }

    @PostMapping("/cursos/{cursoId}/rechazar")
    public ResponseEntity<?> rechazarContenido(
            @PathVariable String cursoId,
            @RequestBody Map<String, String> motivo) {
        try {
            gerenteCursosService.setUsuarioAutenticado(1L);
            gerenteCursosService.rechazarContenido(cursoId, motivo.get("motivo"));
            return ResponseEntity.ok("Contenido rechazado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al rechazar contenido: " + e.getMessage());
        }
    }
} 