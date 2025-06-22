// Catalina Rosales->rataxikita

package com.edutech.edutech_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.edutech.edutech_api.model.UsuarioCurso;

public interface UsuarioCursoRepository extends JpaRepository<UsuarioCurso, Long> {
    
    // Métodos para buscar por diferentes tipos de usuarios
    List<UsuarioCurso> findByAdministradorId(Long administradorId);
    List<UsuarioCurso> findByInstructorId(Long instructorId);
    List<UsuarioCurso> findByAlumnoId(Long alumnoId);
    List<UsuarioCurso> findByGerenteCursosId(Long gerenteCursosId);
    
    // Métodos combinados con curso
    List<UsuarioCurso> findByAdministradorIdAndCursoSigla(Long administradorId, String cursoSigla);
    List<UsuarioCurso> findByInstructorIdAndCursoSigla(Long instructorId, String cursoSigla);
    List<UsuarioCurso> findByAlumnoIdAndCursoSigla(Long alumnoId, String cursoSigla);
    List<UsuarioCurso> findByGerenteCursosIdAndCursoSigla(Long gerenteCursosId, String cursoSigla);
    
    // Método genérico para buscar por cualquier tipo de usuario
    @Query("SELECT uc FROM UsuarioCurso uc WHERE " +
           "uc.administrador.id = :usuarioId OR " +
           "uc.instructor.id = :usuarioId OR " +
           "uc.alumno.id = :usuarioId OR " +
           "uc.gerenteCursos.id = :usuarioId")
    List<UsuarioCurso> findByUsuarioId(@Param("usuarioId") Long usuarioId);
    
    @Query("SELECT uc FROM UsuarioCurso uc WHERE " +
           "(uc.administrador.id = :usuarioId OR " +
           "uc.instructor.id = :usuarioId OR " +
           "uc.alumno.id = :usuarioId OR " +
           "uc.gerenteCursos.id = :usuarioId) AND " +
           "uc.curso.sigla = :cursoSigla")
    List<UsuarioCurso> findByUsuarioIdAndCursoSigla(@Param("usuarioId") Long usuarioId, @Param("cursoSigla") String cursoSigla);
}
// Catalina Rosales->rataxikita
