package com.edutech.edutech_api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.edutech_api.dto.IncidenciaListaRespuestasDto;
import com.edutech.edutech_api.dto.IncidenciaPreguntaDto;
import com.edutech.edutech_api.dto.IncidenciaRespuestaDto;
import com.edutech.edutech_api.model.Incidencia;
import com.edutech.edutech_api.model.Usuario;
import com.edutech.edutech_api.repository.IncidenciaRepository;
import com.edutech.edutech_api.repository.UsuarioRepository;

@Service
public class IncidenciaService {

    @Autowired
    private IncidenciaRepository incidenciaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public IncidenciaListaRespuestasDto crearIncidencia(Long usuarioId, IncidenciaPreguntaDto dto) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Incidencia incidencia = new Incidencia();
        incidencia.setTitulo(dto.getTitulo());
        incidencia.setDescripcion(dto.getDescripcion());
        incidencia.setUsuario(usuario);
        incidencia = incidenciaRepository.save(incidencia);
        return toDTO(incidencia);
    }

    public IncidenciaListaRespuestasDto responderIncidencia(Long incidenciaId, Long gerenteId, IncidenciaRespuestaDto dto) {
        Incidencia incidencia = incidenciaRepository.findById(incidenciaId)
                .orElseThrow(() -> new RuntimeException("Incidencia no encontrada"));
        Usuario gerente = usuarioRepository.findById(gerenteId)
                .orElseThrow(() -> new RuntimeException("Gerente no encontrado"));

        incidencia.setRespuesta(dto.getRespuesta());
        incidencia.setGerente(gerente);

        incidencia = incidenciaRepository.save(incidencia);

        return toDTO(incidencia);
    }

    public List<IncidenciaListaRespuestasDto> listarTodas() {
        return incidenciaRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private IncidenciaListaRespuestasDto toDTO(Incidencia incidencia) {
        IncidenciaListaRespuestasDto dto = new IncidenciaListaRespuestasDto();
        dto.setId(incidencia.getId());
        dto.setTitulo(incidencia.getTitulo());
        dto.setDescripcion(incidencia.getDescripcion());
        dto.setRespuesta(incidencia.getRespuesta());
        dto.setUsuarioId(incidencia.getUsuario().getId());
        if (incidencia.getGerente() != null) {
            dto.setGerenteId(incidencia.getGerente().getId());
        }
        return dto;
    }
}
