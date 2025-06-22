package com.edutech.edutech_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.edutech_api.model.Pregunta;
import com.edutech.edutech_api.model.Alumno;
import com.edutech.edutech_api.model.Instructor;
import com.edutech.edutech_api.model.Curso;
import com.edutech.edutech_api.repository.PreguntaRepository;
import com.edutech.edutech_api.repository.AlumnoRepository;
import com.edutech.edutech_api.repository.InstructorRepository;
import com.edutech.edutech_api.repository.CursoRepository;

@Service
public class PreguntaService {

    @Autowired
    private PreguntaRepository preguntaRepository;

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private CursoRepository cursoRepository;

    /**
     * Crear una pregunta de un alumno a un instructor
     */
    public Pregunta crearPregunta(Long alumnoId, Long instructorId, String cursoSigla, String contenido) {
        // Validar que el alumno existe
        Alumno alumno = alumnoRepository.findById(alumnoId)
            .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));

        // Validar que el instructor existe
        Instructor instructor = instructorRepository.findById(instructorId)
            .orElseThrow(() -> new RuntimeException("Instructor no encontrado"));

        // Validar que el curso existe
        Optional<Curso> cursoOpt = cursoRepository.findBySigla(cursoSigla);
        if (!cursoOpt.isPresent()) {
            throw new RuntimeException("Curso no encontrado");
        }
        Curso curso = cursoOpt.get();

        // Validar que el instructor es el asignado al curso
        if (!curso.getInstructor().getId().equals(instructorId)) {
            throw new RuntimeException("El instructor no está asignado a este curso");
        }

        // Crear la pregunta
        Pregunta pregunta = new Pregunta();
        pregunta.setAlumno(alumno);
        pregunta.setInstructor(instructor);
        pregunta.setCurso(curso);
        pregunta.setContenido(contenido);
        pregunta.setFecha(java.time.LocalDateTime.now().toString());

        return preguntaRepository.save(pregunta);
    }

    /**
     * Obtener todas las preguntas de un instructor
     */
    public List<Pregunta> obtenerPreguntasPorInstructor(Long instructorId) {
        return preguntaRepository.findByInstructorId(instructorId);
    }

    /**
     * Obtener todas las preguntas de un alumno
     */
    public List<Pregunta> obtenerPreguntasPorAlumno(Long alumnoId) {
        return preguntaRepository.findByAlumnoId(alumnoId);
    }

    /**
     * Obtener todas las preguntas de un curso
     */
    public List<Pregunta> obtenerPreguntasPorCurso(String cursoSigla) {
        return preguntaRepository.findByCursoSigla(cursoSigla);
    }

    /**
     * Responder una pregunta (solo instructores)
     */
    public Pregunta responderPregunta(Long preguntaId, Long instructorId, String respuesta) {
        Pregunta pregunta = preguntaRepository.findById(preguntaId)
            .orElseThrow(() -> new RuntimeException("Pregunta no encontrada"));

        // Validar que el instructor que responde es el mismo al que se hizo la pregunta
        if (!pregunta.getInstructor().getId().equals(instructorId)) {
            throw new RuntimeException("No tienes permisos para responder esta pregunta");
        }

        pregunta.setRespuesta(respuesta);
        pregunta.setFechaRespuesta(java.time.LocalDateTime.now().toString());

        return preguntaRepository.save(pregunta);
    }

    /**
     * Eliminar una pregunta (solo el alumno que la hizo)
     */
    public void eliminarPregunta(Long preguntaId, Long alumnoId) {
        Pregunta pregunta = preguntaRepository.findById(preguntaId)
            .orElseThrow(() -> new RuntimeException("Pregunta no encontrada"));

        // Validar que el alumno que elimina es el mismo que hizo la pregunta
        if (!pregunta.getAlumno().getId().equals(alumnoId)) {
            throw new RuntimeException("No tienes permisos para eliminar esta pregunta");
        }

        preguntaRepository.deleteById(preguntaId);
    }
} 