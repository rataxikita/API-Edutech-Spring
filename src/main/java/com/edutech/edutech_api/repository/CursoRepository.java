// Anais Llancapan - peitou1
package com.edutech.edutech_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edutech.edutech_api.model.Curso;
import java.util.Optional;

@Repository
public interface CursoRepository extends JpaRepository<Curso, String> {
    Optional<Curso> findBySigla(String sigla);
}
    

