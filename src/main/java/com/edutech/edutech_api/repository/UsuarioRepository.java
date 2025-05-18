package com.edutech.edutech_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edutech.edutech_api.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByCorreo(String correo);
    }

