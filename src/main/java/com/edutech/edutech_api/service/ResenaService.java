// Anais Llancapan - peitou1
package com.edutech.edutech_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.edutech_api.dto.ResenaDTO;
import com.edutech.edutech_api.model.Curso;
import com.edutech.edutech_api.model.Resena;
import com.edutech.edutech_api.repository.CursoRepository;
import com.edutech.edutech_api.repository.ResenaRepository;
import com.edutech.edutech_api.repository.UsuarioRepository;
import com.edutech.edutech_api.model.Usuario;

@Service
public class ResenaService {

@Autowired
private UsuarioRepository usuarioRepository;
@Autowired
private ResenaRepository resenaRepository;
@Autowired
private CursoRepository cursoRepository;

public Resena crearResena(ResenaDTO dto) {
    Usuario usuario = usuarioRepository.findByCorreo(dto.getCorreoUsuario());
    Optional<Curso> cursoOpt = cursoRepository.findBySigla(dto.getSiglaCurso());
    
    if (usuario == null || !cursoOpt.isPresent()) {
    throw new RuntimeException("Usuario o curso no encontrado");
    }
    Curso curso = cursoOpt.get();

    Resena resena = new Resena();
    resena.setContenido(dto.getContenido());
    resena.setCalificacion(dto.getCalificacion());
    resena.setUsuario(usuario);
    resena.setCurso(curso);
    

    return resenaRepository.save(resena);
    }
    
    
    //Listar reseñas
    public List<Resena> listarResenas() {
        return resenaRepository.findAll();
    }

    //Eliminar
    public boolean eliminarResena(Long id) {
        return resenaRepository.findById(id)
            .map(resena -> {
                resenaRepository.delete(resena);
                return true;
            }).orElse(false);
    }
}
