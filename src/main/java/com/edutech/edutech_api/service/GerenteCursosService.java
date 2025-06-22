// Catalina Rosales->rataxikita
package com.edutech.edutech_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.edutech_api.model.Curso;
import com.edutech.edutech_api.model.Instructor;
import com.edutech.edutech_api.model.GerenteCursos;
import com.edutech.edutech_api.model.Rol;
import com.edutech.edutech_api.repository.CursoRepository;
import com.edutech.edutech_api.repository.InstructorRepository;
import com.edutech.edutech_api.repository.GerenteCursosRepository;
import java.util.Map;

@Service
public class GerenteCursosService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private GerenteCursosRepository gerenteCursosRepository;

    // Variable para simular el usuario autenticado (en producción esto vendría del contexto de seguridad)
    private Long usuarioAutenticadoId;

    // Método para establecer el usuario autenticado (simulación)
    public void setUsuarioAutenticado(Long usuarioId) {
        this.usuarioAutenticadoId = usuarioId;
    }

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

    // Gestión de Instructores - SOLO GERENTE DE CURSOS PUEDE CREAR
    public Instructor crearInstructor(Instructor instructor) {
        validarPermisosGerente();
        
        // Validar que no exista un instructor con el mismo RUT o correo
        if (instructorRepository.findByRut(instructor.getRut()) != null) {
            throw new RuntimeException("Ya existe un instructor con el RUT: " + instructor.getRut());
        }
        
        if (instructorRepository.findByCorreo(instructor.getCorreo()) != null) {
            throw new RuntimeException("Ya existe un instructor con el correo: " + instructor.getCorreo());
        }
        
        // Asegurar que el rol sea INSTRUCTOR
        instructor.setRol(Rol.INSTRUCTOR);
        instructor.setEstado(true);
        
        return instructorRepository.save(instructor);
    }

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
        if (usuarioAutenticadoId == null) {
            throw new RuntimeException("Usuario no autenticado");
        }
        
        GerenteCursos gerente = gerenteCursosRepository.findById(usuarioAutenticadoId)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        if (!Rol.GERENTE_CURSOS.equals(gerente.getRol())) {
            throw new RuntimeException("Acceso denegado. Solo los gerentes de cursos pueden realizar esta operación.");
        }
        
        if (!gerente.isEstado()) {
            throw new RuntimeException("Usuario deshabilitado");
        }
    }

    private double calcularPromedioAprobacion(Curso curso) {
        // Implementar lógica para calcular el promedio de aprobación
        return 0.0; // Placeholder
    }
}
// Catalina Rosales->rataxikita 