// Catalina Rosales->rataxikita
package com.edutech.edutech_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.edutech.edutech_api.model.Evaluacion;

public interface EvaluacionRepository extends JpaRepository<Evaluacion, Long> {

    @Query("SELECT e FROM Evaluacion e WHERE e.curso.instructor.id = :instructorId")
    List<Evaluacion> findByCursoInstructorId(@Param("instructorId") Long instructorId);
}
//Catalina Rosales->rataxikita
//Anais Llancapan - peitou1
