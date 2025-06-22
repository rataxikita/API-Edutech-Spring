package com.edutech.edutech_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edutech.edutech_api.model.GerenteCursos;
import com.edutech.edutech_api.model.Rol;

public interface GerenteCursosRepository extends JpaRepository<GerenteCursos, Long> {
    GerenteCursos findByCorreo(String correo);
    GerenteCursos findByCorreoAndClave(String correo, String clave);
    List<GerenteCursos> findByRol(Rol rol);
} 