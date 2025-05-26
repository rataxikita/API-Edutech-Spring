// Catalina Rosales->rataxikita

package com.edutech.edutech_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.edutech_api.model.Usuario;
import com.edutech.edutech_api.repository.UsuarioRepository;

@Service
public class usuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario crearUsuario(Usuario usuario) {
        if (usuarioRepository.findByCorreo(usuario.getCorreo()) != null) {
            return null;
        }
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> obtenerUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario actualizarUsuario(Long id, Usuario datosActualizados) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario != null) {
            usuario.setCorreo(datosActualizados.getCorreo());
            usuario.setClave(datosActualizados.getClave());
            usuario.setEstado(datosActualizados.isEstado());
            return usuarioRepository.save(usuario);
        }
        return null;
    }

    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}

// Catalina Rosales->rataxikita

