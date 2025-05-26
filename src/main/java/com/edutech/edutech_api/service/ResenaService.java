// Anais Llancapan- peitou1
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

    public Resena crearResena(ResenaDTO dto) {
        UsuarioCurso usuarioCurso = usuarioCursoRepository.findById(dto.getUsuarioCursoId())
        .orElseThrow(() -> new RuntimeException("UsuarioCurso no encontrado"));
            String fechaActual = LocalDateTime.now().toString(); // Convertimos a String
            Resena resena = new Resena(
            null,dto.getDescripcion(),fechaActual,usuarioCurso);

        return resenaRepository.save(resena);
    }

    public List<Resena> listarResenas() {
        return resenaRepository.findAll();
    }
    
    public boolean eliminarResena(Long id) {
    if (resenaRepository.existsById(id)) { // Verifica si la reseña existe
        resenaRepository.deleteById(id);   // Si existe, la elimina
        return true; // eliminó correctamente
    }
    return false; //no fue encontrada
}

}
