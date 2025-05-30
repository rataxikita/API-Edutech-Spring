// Anais Llancapan - peitou1
package com.edutech.edutech_api.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.edutech_api.dto.ResenaDTO;
import com.edutech.edutech_api.model.Resena;
import com.edutech.edutech_api.model.UsuarioCurso;
import com.edutech.edutech_api.repository.ResenaRepository;
import com.edutech.edutech_api.repository.UsuarioCursoRepository;

@Service
public class ResenaService {

    @Autowired
    private ResenaRepository resenaRepository;

    @Autowired
    private UsuarioCursoRepository usuarioCursoRepository;

    //Crear reseña
    public Resena crearResena(ResenaDTO dto) {
        UsuarioCurso usuarioCurso = usuarioCursoRepository.findById(dto.getUsuarioCursoId())
            .orElseThrow(() -> new RuntimeException("UsuarioCurso no encontrado"));

        String fechaActual = LocalDateTime.now().toString();

        Resena resena = new Resena(
            null, // ID autogenerado
            dto.getDescripcion(),
            fechaActual,
            usuarioCurso
        );

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
