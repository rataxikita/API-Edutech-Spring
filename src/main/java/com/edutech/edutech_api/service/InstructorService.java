// Catalina Rosales->rataxikita
package com.edutech.edutech_api.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.edutech_api.model.Instructor;
import com.edutech.edutech_api.model.Usuario;
import com.edutech.edutech_api.model.Curso;
import com.edutech.edutech_api.model.Evaluacion;
import com.edutech.edutech_api.model.Contenido;
import com.edutech.edutech_api.model.Pregunta;
import com.edutech.edutech_api.repository.InstructorRepository;
import com.edutech.edutech_api.repository.UsuarioRepository;
import com.edutech.edutech_api.repository.CursoRepository;
import com.edutech.edutech_api.repository.EvaluacionRepository;
import com.edutech.edutech_api.repository.ContenidoRepository;
import com.edutech.edutech_api.repository.PreguntaRepository;

@Service
public class InstructorService {
    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private EvaluacionRepository evaluacionRepository;

    @Autowired
    private ContenidoRepository contenidoRepository;

    @Autowired
    private PreguntaRepository preguntaRepository;

    public Instructor crearInstructor(Instructor instructor){
        return instructorRepository.save(instructor);
    }
    public List<Instructor> obtenerTodos(){
        return instructorRepository.findAll();
    } 
    public Instructor obtenerPorId(Long id){
        return instructorRepository.findById(id).orElse(null);
    }
    public void eliminarInstructor(Long id){
        instructorRepository.deleteById(id); 
    }

    // Gestión de Contenido
    public Contenido crearContenido(Long cursoId, Contenido contenido) {
        validarPermisosInstructor(cursoId);
        contenido.setCurso(cursoRepository.findById(cursoId).orElseThrow());
        return contenidoRepository.save(contenido);
    }

    public Contenido actualizarContenido(Long cursoId, Long contenidoId, Contenido contenidoActualizado) {
        validarPermisosInstructor(cursoId);
        Contenido contenido = contenidoRepository.findById(contenidoId)
            .orElseThrow(() -> new RuntimeException("Contenido no encontrado"));
        
        contenido.setTitulo(contenidoActualizado.getTitulo());
        contenido.setDescripcion(contenidoActualizado.getDescripcion());
        contenido.setMaterial(contenidoActualizado.getMaterial());
        
        return contenidoRepository.save(contenido);
    }

    // Gestión de Evaluaciones
    public Evaluacion crearEvaluacion(Long cursoId, Evaluacion evaluacion) {
        validarPermisosInstructor(cursoId);
        evaluacion.setCurso(cursoRepository.findById(cursoId).orElseThrow());
        return evaluacionRepository.save(evaluacion);
    }

    public Evaluacion actualizarEvaluacion(Long cursoId, Long evaluacionId, Evaluacion evaluacionActualizada) {
        validarPermisosInstructor(cursoId);
        Evaluacion evaluacion = evaluacionRepository.findById(evaluacionId)
            .orElseThrow(() -> new RuntimeException("Evaluación no encontrada"));
        
        evaluacion.setTitulo(evaluacionActualizada.getTitulo());
        evaluacion.setDescripcion(evaluacionActualizada.getDescripcion());
        evaluacion.setPreguntas(evaluacionActualizada.getPreguntas());
        
        return evaluacionRepository.save(evaluacion);
    }

    // Interacción con Estudiantes
    public Pregunta responderPregunta(Long preguntaId, String respuesta) {
        Pregunta pregunta = preguntaRepository.findById(preguntaId)
            .orElseThrow(() -> new RuntimeException("Pregunta no encontrada"));
        
        validarPermisosInstructor(pregunta.getCurso().getId());
        
        pregunta.setRespuesta(respuesta);
        pregunta.setFechaRespuesta(java.time.LocalDateTime.now().toString());
        
        return preguntaRepository.save(pregunta);
    }

    // Monitoreo de Progreso
    public Map<String, Object> obtenerProgresoEstudiantes(Long cursoId) {
        validarPermisosInstructor(cursoId);
        
        Curso curso = cursoRepository.findById(cursoId)
            .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
            
        return Map.of(
            "cursoId", curso.getId(),
            "nombreCurso", curso.getNombre(),
            "totalEstudiantes", curso.getInscritos().size(),
            "estudiantesActivos", curso.getInscritos().stream()
                .filter(inscripcion -> inscripcion.getUsuario().isEstado())
                .count(),
            "promedioProgreso", calcularPromedioProgreso(curso)
        );
    }

    // Métodos privados de ayuda
    private void validarPermisosInstructor(Long cursoId) {
        Curso curso = cursoRepository.findById(cursoId)
            .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
            
        // Aquí se debería implementar la validación del instructor actual
        // Por ejemplo, verificar que el instructor actual sea el asignado al curso
    }

    private double calcularPromedioProgreso(Curso curso) {
        // Implementar lógica para calcular el promedio de progreso
        return curso.getInscritos().stream()
            .mapToDouble(inscripcion -> Double.parseDouble(inscripcion.getProgreso().replace("%", "")))
            .average()
            .orElse(0.0);
    }
}
// Catalina Rosales->rataxikita
