// Catalina Rosales->rataxikita
package com.edutech.edutech_api.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.edutech_api.model.Instructor;
import com.edutech.edutech_api.model.Curso;
import com.edutech.edutech_api.model.Evaluacion;
import com.edutech.edutech_api.model.Contenido;
import com.edutech.edutech_api.model.Pregunta;
import com.edutech.edutech_api.model.Administrador;
import com.edutech.edutech_api.model.Alumno;
import com.edutech.edutech_api.model.GerenteCursos;
import com.edutech.edutech_api.repository.InstructorRepository;
import com.edutech.edutech_api.repository.CursoRepository;
import com.edutech.edutech_api.repository.EvaluacionRepository;
import com.edutech.edutech_api.repository.ContenidoRepository;
import com.edutech.edutech_api.repository.PreguntaRepository;
import com.edutech.edutech_api.dto.InstructorListDTO;

@Service
public class InstructorService {
    @Autowired
    private InstructorRepository instructorRepository;

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
    public List<InstructorListDTO> obtenerTodos(){
        return instructorRepository.findAll()
            .stream()
            .map(this::convertirAInstructorDTO)
            .collect(java.util.stream.Collectors.toList());
    } 
    public Instructor obtenerPorId(Long id){
        return instructorRepository.findById(id).orElse(null);
    }
    public void eliminarInstructor(Long id){
        instructorRepository.deleteById(id); 
    }

    // Gestión de Contenido
    public Contenido crearContenido(String cursoId, Contenido contenido) {
        validarPermisosInstructor(cursoId);
        contenido.setCurso(cursoRepository.findBySigla(cursoId).orElseThrow());
        return contenidoRepository.save(contenido);
    }

    public Contenido actualizarContenido(String cursoId, Long contenidoId, Contenido contenidoActualizado) {
        validarPermisosInstructor(cursoId);
        Contenido contenido = contenidoRepository.findById(contenidoId)
            .orElseThrow(() -> new RuntimeException("Contenido no encontrado"));
        
        contenido.setTitulo(contenidoActualizado.getTitulo());
        contenido.setDescripcion(contenidoActualizado.getDescripcion());
        contenido.setMaterial(contenidoActualizado.getMaterial());
        
        return contenidoRepository.save(contenido);
    }

    // Gestión de Evaluaciones
    public Evaluacion crearEvaluacion(String cursoId, Evaluacion evaluacion) {
        validarPermisosInstructor(cursoId);
        evaluacion.setCurso(cursoRepository.findBySigla(cursoId).orElseThrow());
        return evaluacionRepository.save(evaluacion);
    }

    public Evaluacion actualizarEvaluacion(String cursoId, Long evaluacionId, Evaluacion evaluacionActualizada) {
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
        
        validarPermisosInstructor(pregunta.getCurso().getSigla());
        
        pregunta.setRespuesta(respuesta);
        pregunta.setFechaRespuesta(java.time.LocalDateTime.now().toString());
        
        return preguntaRepository.save(pregunta);
    }

    // Monitoreo de Progreso
    public Map<String, Object> obtenerProgresoEstudiantes(String cursoId) {
        validarPermisosInstructor(cursoId);
        
        Curso curso = cursoRepository.findBySigla(cursoId)
            .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
            
        return Map.of(
            "sigla", curso.getSigla(),
            "nombreCurso", curso.getNombre(),
            "totalEstudiantes", curso.getInscritos().size(),
            "estudiantesActivos", curso.getInscritos().stream()
                .filter(inscripcion -> {
                    Object usuario = inscripcion.getUsuario();
                    if (usuario instanceof Administrador) return ((Administrador) usuario).isEstado();
                    if (usuario instanceof Instructor) return ((Instructor) usuario).isEstado();
                    if (usuario instanceof Alumno) return ((Alumno) usuario).isEstado();
                    if (usuario instanceof GerenteCursos) return ((GerenteCursos) usuario).isEstado();
                    return false;
                })
                .count(),
            "promedioProgreso", calcularPromedioProgreso(curso)
        );
    }

    // Métodos privados de ayuda
    private void validarPermisosInstructor(String cursoId) {
        // Curso curso = cursoRepository.findById(cursoId)
        //     .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
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

    // Método de conversión a DTO
    private InstructorListDTO convertirAInstructorDTO(Instructor instructor) {
        return new InstructorListDTO(
            instructor.getId(),
            instructor.getNombre(),
            instructor.getApellido(),
            instructor.getCorreo(),
            instructor.getRut(),
            instructor.isEstado(),
            instructor.getRol().toString()
        );
    }
}
// Catalina Rosales->rataxikita
