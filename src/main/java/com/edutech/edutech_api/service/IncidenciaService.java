package com.edutech.edutech_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.edutech_api.dto.IncidenciaPreguntaDto;
import com.edutech.edutech_api.dto.IncidenciaRespuestaDto;
import com.edutech.edutech_api.model.Alumno;
import com.edutech.edutech_api.model.Incidencia;
import com.edutech.edutech_api.model.Soporte;
import com.edutech.edutech_api.repository.AlumnoRepository;
import com.edutech.edutech_api.repository.IncidenciaRepository;
import com.edutech.edutech_api.repository.SoporteRepository;

@Service
public class IncidenciaService {
    @Autowired
    private IncidenciaRepository incidenciaRepository;
    @Autowired
    private SoporteRepository soporteRepository;
    @Autowired
    private AlumnoRepository alumnoRepository;

    public Incidencia crearIncidencia(IncidenciaPreguntaDto dto) {
    Alumno alumno = alumnoRepository.findById(dto.getAlumnoId())
        .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));

    Incidencia incidencia = new Incidencia();
    incidencia.setTitulo(dto.getTitulo());
    incidencia.setDescripcion(dto.getDescripcion());
    incidencia.setAlumno(alumno);

    return incidenciaRepository.save(incidencia);
}

    public List<Incidencia> listar(){
        return incidenciaRepository.findAll();
    }

public Incidencia responderIncidencia(Long id, IncidenciaRespuestaDto respuestaDto){
    System.out.println("ID incidencia: " + id);
    System.out.println("DTO incidenciaId: " + respuestaDto.getIncidenciaId());
    System.out.println("DTO soporteId: " + respuestaDto.getSoporteId());
    System.out.println("DTO respuesta: " + respuestaDto.getRespuesta());

    Incidencia incidencia = incidenciaRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Incidencia no encontrada"));

    if (incidencia.getRespuesta() != null && !incidencia.getRespuesta().isBlank()){
        throw new RuntimeException("La incidencia ya ha sido respondida.");
    }

    Soporte soporte = soporteRepository.findById(respuestaDto.getSoporteId())
        .orElseThrow(() -> new RuntimeException("Soporte no encontrado"));

    incidencia.setRespuesta(respuestaDto.getRespuesta());
    incidencia.setSoporte(soporte);

    return incidenciaRepository.save(incidencia);
}


}
//Diego Sotelo G.