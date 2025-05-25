// Catalina Rosales->rataxikita

package com.edutech.edutech_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edutech.edutech_api.model.UsuarioCurso;

public interface UsuarioCursoRepository extends JpaRepository<UsuarioCurso, Long> {
    List<UsuarioCurso> findByUsuarioId(Long usuarioId);
    List<UsuarioCurso> findByUsuarioIdAndCursoId(Long usuarioId, Long cursoId);
}
// Catalina Rosales->rataxikita
