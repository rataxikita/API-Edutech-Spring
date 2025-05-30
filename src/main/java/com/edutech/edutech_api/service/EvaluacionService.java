//Anais Llancapan- peitou1
package com.edutech.edutech_api.service;
import java.util.List;
import java.util.Optional;

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
        Optional<Curso> cursoOpt = cursoRepository.findBySigla(DTO.getCursoSigla());
        if (!cursoOpt.isPresent()) {
            throw new RuntimeException("Curso no encontrado");
        }
        Curso curso = cursoOpt.get();
        Evaluacion eval = new Evaluacion(null, curso, DTO.getTitulo(), DTO.getDescripcion(), DTO.getFechaPublicacion());
        return evaluacionRepository.save(eval);
    }

    public List<Evaluacion> listarEvaluaciones() {
        return evaluacionRepository.findAll();
    }
}


