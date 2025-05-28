// Catalina Rosales->rataxikita
package com.edutech.edutech_api.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.edutech.edutech_api.model.Usuario;
import com.edutech.edutech_api.model.Curso;
import com.edutech.edutech_api.model.Rol;
import com.edutech.edutech_api.repository.UsuarioRepository;
import com.edutech.edutech_api.repository.CursoRepository;

@Service
public class GerenteCursosService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    // Gestión de Cursos
    public Curso crearCurso(Curso curso) {
        validarPermisosGerente();
        return cursoRepository.save(curso);
    }

    public Curso actualizarCurso(Long id, Curso cursoActualizado) {
        validarPermisosGerente();
        Curso curso = cursoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
        
        // Actualizar campos del curso
        curso.setNombre(cursoActualizado.getNombre());
        curso.setDescripcion(cursoActualizado.getDescripcion());
        curso.setEstado(cursoActualizado.getEstado());
        curso.setSigla(cursoActualizado.getSigla());
        curso.setValor(cursoActualizado.getValor());
        
        return cursoRepository.save(curso);
    }

    public void eliminarCurso(Long id) {
        validarPermisosGerente();
        if (!cursoRepository.existsById(id)) {
            throw new RuntimeException("Curso no encontrado");
        }
        cursoRepository.deleteById(id);
    }

    // Gestión de Instructores
    public Usuario asignarInstructor(Long cursoId, Long instructorId) {
        validarPermisosGerente();
        
        Curso curso = cursoRepository.findById(cursoId)
            .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
            
        Usuario instructor = usuarioRepository.findById(instructorId)
            .orElseThrow(() -> new RuntimeException("Instructor no encontrado"));
            
        if (!instructor.esInstructor()) {
            throw new RuntimeException("El usuario no es un instructor");
        }
        
        curso.setInstructor(instructor);
        cursoRepository.save(curso);
        
        return instructor;
    }

    // Reportes
    public Map<String, Object> generarReporteInscripciones(Long cursoId) {
        validarPermisosGerente();
        
        Curso curso = cursoRepository.findById(cursoId)
            .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
            
        return Map.of(
            "id", curso.getId(),
            "sigla", curso.getSigla(),
            "nombreCurso", curso.getNombre(),
            "totalInscritos", curso.getInscritos().size(),
            "fechaGeneracion", java.time.LocalDateTime.now().toString()
        );
    }

    public Map<String, Object> generarReporteRendimiento(Long cursoId) {
        validarPermisosGerente();
        
        Curso curso = cursoRepository.findById(cursoId)
            .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
            
        return Map.of(
            "id", curso.getId(),
            "sigla", curso.getSigla(),
            "nombreCurso", curso.getNombre(),
            "promedioAprobacion", calcularPromedioAprobacion(curso),
            "fechaGeneracion", java.time.LocalDateTime.now().toString()
        );
    }

    // Validación de Contenido
    public void aprobarContenido(Long cursoId) {
        validarPermisosGerente();
        
        Curso curso = cursoRepository.findById(cursoId)
            .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
            
        curso.setEstado("APROBADO");
        cursoRepository.save(curso);
    }

    public void rechazarContenido(Long cursoId, String motivo) {
        validarPermisosGerente();
        
        Curso curso = cursoRepository.findById(cursoId)
            .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
            
        curso.setEstado("RECHAZADO");
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