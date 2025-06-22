package com.edutech.edutech_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edutech.edutech_api.model.Administrador;
import com.edutech.edutech_api.model.Rol;

public interface AdministradorRepository extends JpaRepository<Administrador, Long> {
    Administrador findByCorreo(String correo);
    Administrador findByCorreoAndClave(String correo, String clave);
    List<Administrador> findByRol(Rol rol);
} 