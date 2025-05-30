package com.edutech.edutech_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edutech.edutech_api.model.Alumno;

public interface AlumnoRepository extends JpaRepository<Alumno, Long>{
    Alumno findByCorreo(String correo);
}
