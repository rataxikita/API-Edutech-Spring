// Catalina Rosales->rataxikita
package com.edutech.edutech_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edutech.edutech_api.model.Usuario;
import com.edutech.edutech_api.model.Rol;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByCorreo(String correo);
    Usuario findByCorreoAndClave(String correo, String clave);
    List<Usuario> findByRol(Rol rol);
    boolean existsByCorreo(String correo);
}

// Catalina Rosales->rataxikita
