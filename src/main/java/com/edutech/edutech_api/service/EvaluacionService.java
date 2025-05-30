//Anais Llancapan- peitou1
package com.edutech.edutech_api.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.edutech_api.dto.EvaluacionDTO;
import com.edutech.edutech_api.model.Curso;
import com.edutech.edutech_api.model.Evaluacion;
import com.edutech.edutech_api.repository.CursoRepository;
import com.edutech.edutech_api.repository.EvaluacionRepository;

@Service
public class EvaluacionService {

    @Autowired
    private EvaluacionRepository evaluacionRepository;
    @Autowired
    private CursoRepository cursoRepository;

    public Evaluacion crearEvaluacion(EvaluacionDTO DTO) {
        Curso curso = cursoRepository.findById(DTO.getCursoId())
        .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
        Evaluacion eval = new Evaluacion(null, curso, DTO.getTitulo(), DTO.getDescripcion(), DTO.getFechaPublicacion());
        return evaluacionRepository.save(eval);
    }

    public List<Evaluacion> listarEvaluaciones() {
        return evaluacionRepository.findAll();
    }
}


