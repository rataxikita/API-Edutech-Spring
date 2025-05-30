// Catalina Rosales->rataxikita
package com.edutech.edutech_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edutech.edutech_api.model.Evaluacion;

public interface EvaluacionRepository extends JpaRepository<Evaluacion, Long> {
    List<Evaluacion> findByCursoSigla(String cursoSigla);
}
// Catalina Rosales->rataxikita
