//Anais Llancapan-peitou1
package com.edutech.edutech_api.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.edutech_api.model.Curso;
import com.edutech.edutech_api.repository.CursoRepository;

@Service
public class CursoService {

    @Autowired
    private CursoRepository CursoRepository;

    public List<Curso> listar() {
        return CursoRepository.findAll();
    }

    public Curso guardar(Curso curso) {
        return CursoRepository.save(curso);
    }

    //puede devolver un objeto Curso o Optional.empty() si no se encuentra.
    public Optional<Curso> buscar(Long id) {
        return CursoRepository.findById(id);
    }

    public void eliminar(Long id) {
        CursoRepository.deleteById(id);
    }
}

