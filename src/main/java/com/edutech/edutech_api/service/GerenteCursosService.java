// Catalina Rosales->rataxikita
package com.edutech.edutech_api.service;

import org.springframework.stereotype.Service;

import com.edutech.edutech_api.model.Curso;
import com.edutech.edutech_api.model.Instructor;
import com.edutech.edutech_api.repository.CursoRepository;
import com.edutech.edutech_api.repository.InstructorRepository;
import java.util.Map;

@Service
public class GerenteCursosService {

    private CursoRepository cursoRepository;

    private InstructorRepository instructorRepository;

    // Gestión de Cursos
    public Curso crearCurso(Curso curso) {
        validarPermisosGerente();
        return cursoRepository.save(curso);
    }

    public Curso actualizarCurso(String id, Curso cursoActualizado) {
        validarPermisosGerente();
        Curso curso = cursoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
        
        // Actualizar campos del curso
        curso.setNombre(cursoActualizado.getNombre());
        curso.setDescripcion(cursoActualizado.getDescripcion());
        curso.setEstado(cursoActualizado.isEstado());
        curso.setSigla(cursoActualizado.getSigla());
        curso.setValor(cursoActualizado.getValor());
        
        return cursoRepository.save(curso);
    }

    public void eliminarCurso(String id) {
        validarPermisosGerente();
        if (!cursoRepository.existsById(id)) {
            throw new RuntimeException("Curso no encontrado");
        }
        cursoRepository.deleteById(id);
    }

    // Gestión de Instructores
    public Instructor asignarInstructor(String cursoId, Long instructorId) {
        validarPermisosGerente();
        Curso curso = cursoRepository.findById(cursoId)
            .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
        Instructor instructor = instructorRepository.findById(instructorId)
            .orElseThrow(() -> new RuntimeException("Instructor no encontrado"));
        curso.setInstructor(instructor);
        cursoRepository.save(curso);
        return instructor;
    }

    // Reportes
    public Map<String, Object> generarReporteInscripciones(String cursoId) {
        validarPermisosGerente();
        
        Curso curso = cursoRepository.findById(cursoId)
            .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
            
        return Map.of(
            "sigla", curso.getSigla(),
            "nombreCurso", curso.getNombre(),
            "totalInscritos", curso.getInscritos().size(),
            "fechaGeneracion", java.time.LocalDateTime.now().toString()
        );
    }

    public Map<String, Object> generarReporteRendimiento(String cursoId) {
        validarPermisosGerente();
        
        Curso curso = cursoRepository.findById(cursoId)
            .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
            
        return Map.of(
            "sigla", curso.getSigla(),
            "nombreCurso", curso.getNombre(),
            "promedioAprobacion", calcularPromedioAprobacion(curso),
            "fechaGeneracion", java.time.LocalDateTime.now().toString()
        );
    }

    // Validación de Contenido
    public void aprobarContenido(String cursoId) {
        validarPermisosGerente();
        
        Curso curso = cursoRepository.findById(cursoId)
            .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
            
        curso.setEstado(true);
        cursoRepository.save(curso);
    }

    public void rechazarContenido(String cursoId, String motivo) {
        validarPermisosGerente();
        
        Curso curso = cursoRepository.findById(cursoId)
            .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
            
        curso.setEstado(false);
        cursoRepository.save(curso);
    }

    // Métodos privados de ayuda
    private void validarPermisosGerente() {
        // Implementar validación de permisos
        // Este método debería ser llamado al inicio de cada operación
    }

    private double calcularPromedioAprobacion(Curso curso) {
        // Implementar lógica para calcular el promedio de aprobación
        return 0.0; // Placeholder
    }
}
// Catalina Rosales->rataxikita 