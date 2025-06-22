// Catalina Rosales->rataxikita
package com.edutech.edutech_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edutech.edutech_api.model.Pregunta;

public interface PreguntaRepository extends JpaRepository<Pregunta, Long> {
    List<Pregunta> findByCursoSigla(String cursoSigla);
    List<Pregunta> findByInstructorId(Long instructorId);
    List<Pregunta> findByAlumnoId(Long alumnoId);
    List<Pregunta> findByInstructorIdAndCursoSigla(Long instructorId, String cursoSigla);
    List<Pregunta> findByAlumnoIdAndCursoSigla(Long alumnoId, String cursoSigla);
}
// Catalina Rosales->rataxikita
