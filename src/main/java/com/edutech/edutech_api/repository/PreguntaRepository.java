// Catalina Rosales->rataxikita
package com.edutech.edutech_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edutech.edutech_api.model.Pregunta;

public interface PreguntaRepository extends JpaRepository<Pregunta, Long> {
    List<Pregunta> findByCursoSigla(String cursoSigla);
}
// Catalina Rosales->rataxikita
