// Catalina Rosales->rataxikita
package com.edutech.edutech_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edutech.edutech_api.model.Instructor;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {
    Instructor findByRut(String rut);
    Instructor findByCorreo(String correo);
}

// Catalina Rosales->rataxikita
