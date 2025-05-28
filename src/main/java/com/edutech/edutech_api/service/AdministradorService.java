// Catalina Rosales->rataxikita
package com.edutech.edutech_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.edutech.edutech_api.model.Usuario;
import com.edutech.edutech_api.model.Rol;
import com.edutech.edutech_api.repository.UsuarioRepository;

@Service
public class AdministradorService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario crearUsuario(Usuario usuario) {
        // Validar que el correo no exista
        if (usuarioRepository.findByCorreo(usuario.getCorreo()) != null) {
            throw new RuntimeException("El correo ya está registrado");
        }
        
        // Validar campos obligatorios
        if (usuario.getCorreo() == null || usuario.getClave() == null || usuario.getRol() == null) {
            throw new RuntimeException("Correo, clave y rol son obligatorios");
        }

        // Validar que el rol sea válido
        try {
            Rol.fromString(usuario.getRol().getValor());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Rol no válido: " + usuario.getRol());
        }

        usuario.setEstado(true);
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }

    public List<Usuario> obtenerUsuariosPorRol(Rol rol) {
        return usuarioRepository.findByRol(rol);
    }

    public Usuario obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public Usuario actualizarUsuario(Long id, Usuario usuarioActualizado) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Validar que el nuevo correo no esté en uso por otro usuario
        if (!usuario.getCorreo().equals(usuarioActualizado.getCorreo()) &&
            usuarioRepository.findByCorreo(usuarioActualizado.getCorreo()) != null) {
            throw new RuntimeException("El correo ya está en uso");
        }

        // Validar que el rol sea válido si se está actualizando
        if (usuarioActualizado.getRol() != null) {
            try {
                Rol.fromString(usuarioActualizado.getRol().getValor());
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Rol no válido: " + usuarioActualizado.getRol());
            }
        }

        usuario.setNombre(usuarioActualizado.getNombre());
        usuario.setCorreo(usuarioActualizado.getCorreo());
        if (usuarioActualizado.getClave() != null && !usuarioActualizado.getClave().isEmpty()) {
            usuario.setClave(usuarioActualizado.getClave());
        }
        if (usuarioActualizado.getRol() != null) {
            usuario.setRol(usuarioActualizado.getRol());
        }
        usuario.setEstado(usuarioActualizado.isEstado());

        return usuarioRepository.save(usuario);
    }

    public void desactivarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        // No permitir desactivar administradores
        if (usuario.esAdministrador()) {
            throw new RuntimeException("No se puede desactivar un administrador");
        }
        
        usuario.setEstado(false);
        usuarioRepository.save(usuario);
    }

    public void eliminarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            
        // No permitir eliminar administradores
        if (usuario.esAdministrador()) {
            throw new RuntimeException("No se puede eliminar un administrador");
        }
        
        usuarioRepository.deleteById(id);
    }

    public ResponseEntity<?> login(String correo, String clave) {
        Usuario usuario = usuarioRepository.findByCorreoAndClave(correo, clave);
        
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Credenciales incorrectas");
        }

        if (!usuario.isEstado()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body("Usuario inactivo");
        }

        // Verificar que sea administrador
        if (!usuario.esAdministrador()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body("Acceso denegado: se requieren privilegios de administrador");
        }

        return ResponseEntity.ok(usuario);
    }
}
// Catalina Rosales->rataxikita 