package com.edutech.edutech_api.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.edutech.edutech_api.model.Pregunta;
import com.edutech.edutech_api.service.PreguntaService;

@RestController
@RequestMapping("/api/preguntas")
public class PreguntaController {

    @Autowired
    private PreguntaService preguntaService;

    /**
     * Crear una pregunta (solo alumnos)
     */
    @PostMapping
    public ResponseEntity<?> crearPregunta(@RequestBody Map<String, Object> request) {
        try {
            Long alumnoId = Long.valueOf(request.get("alumnoId").toString());
            Long instructorId = Long.valueOf(request.get("instructorId").toString());
            String cursoSigla = request.get("cursoSigla").toString();
            String contenido = request.get("contenido").toString();

            Pregunta pregunta = preguntaService.crearPregunta(alumnoId, instructorId, cursoSigla, contenido);
            return ResponseEntity.status(HttpStatus.CREATED).body(pregunta);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al crear pregunta: " + e.getMessage());
        }
    }

    /**
     * Obtener preguntas de un instructor
     */
    @GetMapping("/instructor/{instructorId}")
    public ResponseEntity<?> obtenerPreguntasPorInstructor(@PathVariable Long instructorId) {
        try {
            List<Pregunta> preguntas = preguntaService.obtenerPreguntasPorInstructor(instructorId);
            return ResponseEntity.ok(preguntas);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al obtener preguntas: " + e.getMessage());
        }
    }

    /**
     * Obtener preguntas de un alumno
     */
    @GetMapping("/alumno/{alumnoId}")
    public ResponseEntity<?> obtenerPreguntasPorAlumno(@PathVariable Long alumnoId) {
        try {
            List<Pregunta> preguntas = preguntaService.obtenerPreguntasPorAlumno(alumnoId);
            return ResponseEntity.ok(preguntas);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al obtener preguntas: " + e.getMessage());
        }
    }

    /**
     * Obtener preguntas de un curso
     */
    @GetMapping("/curso/{cursoSigla}")
    public ResponseEntity<?> obtenerPreguntasPorCurso(@PathVariable String cursoSigla) {
        try {
            List<Pregunta> preguntas = preguntaService.obtenerPreguntasPorCurso(cursoSigla);
            return ResponseEntity.ok(preguntas);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al obtener preguntas: " + e.getMessage());
        }
    }

    /**
     * Responder una pregunta (solo instructores)
     */
    @PutMapping("/{preguntaId}/responder")
    public ResponseEntity<?> responderPregunta(
            @PathVariable Long preguntaId,
            @RequestBody Map<String, Object> request) {
        try {
            Long instructorId = Long.valueOf(request.get("instructorId").toString());
            String respuesta = request.get("respuesta").toString();

            Pregunta pregunta = preguntaService.responderPregunta(preguntaId, instructorId, respuesta);
            return ResponseEntity.ok(pregunta);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al responder pregunta: " + e.getMessage());
        }
    }

    /**
     * Eliminar una pregunta (solo el alumno que la hizo)
     */
    @DeleteMapping("/{preguntaId}")
    public ResponseEntity<?> eliminarPregunta(
            @PathVariable Long preguntaId,
            @RequestBody Map<String, Object> request) {
        try {
            Long alumnoId = Long.valueOf(request.get("alumnoId").toString());
            preguntaService.eliminarPregunta(preguntaId, alumnoId);
            return ResponseEntity.ok("Pregunta eliminada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al eliminar pregunta: " + e.getMessage());
        }
    }
} 