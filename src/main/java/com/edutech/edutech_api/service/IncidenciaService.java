package com.edutech.edutech_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.edutech_api.dto.RespuestaIncidenciaDto;
import com.edutech.edutech_api.model.Incidencia;
import com.edutech.edutech_api.model.Soporte;
import com.edutech.edutech_api.repository.IncidenciaRepository;
import com.edutech.edutech_api.repository.SoporteRepository;

@Service
public class IncidenciaService {
    @Autowired
    private IncidenciaRepository incidenciaRepository;
    @Autowired
    private SoporteRepository soporteRepository;
    //@Autowired
    //private AlumnoRepository alumnoRepository;

    public Incidencia crearIncidencia(Incidencia incidencia){
        return incidenciaRepository.save(incidencia);
    }

    public List<Incidencia> listar(){
        return incidenciaRepository.findAll();
    }

    public Incidencia responderIncidencia(Long id, RespuestaIncidenciaDto respuestaDto){
        Incidencia incidencia = incidenciaRepository.findById(id).orElseThrow(() -> new RuntimeException("Incidencia no encontrada"));
        if (incidencia.getRespuesta() != null){
            throw new RuntimeException("La incidencia ya ha sido respondida.");
        }
        Soporte soporte = soporteRepository.findById(respuestaDto.getSoporteId()).orElseThrow(() -> new RuntimeException("Soporte no encontrado"));
        incidencia.setRespuesta(respuestaDto.getRespuesta());
        incidencia.setSoporte(soporte);
        return incidenciaRepository.save(incidencia);
    }

}
//Diego Sotelo G.