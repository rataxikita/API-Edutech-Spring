package com.duoc.Edutech.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duoc.Edutech.dto.IncidenciaListaRespuestasDto;
import com.duoc.Edutech.dto.IncidenciaPreguntaDto;
import com.duoc.Edutech.dto.IncidenciaRespuestaDto;
import com.duoc.Edutech.service.IncidenciaService;

@RestController
@RequestMapping("/api/incidencias")
public class IncidenciaController {
    @Autowired
    private IncidenciaService incidenciaService;

    @PostMapping("/crear/{usuarioId}")
    public IncidenciaListaRespuestasDto crear(@PathVariable Long usuarioId, @RequestBody IncidenciaPreguntaDto dto) {
        return incidenciaService.crearIncidencia(usuarioId, dto);
    }

    @PutMapping("/responder/{incidenciaId}/{gerenteId}")
    public IncidenciaListaRespuestasDto responder(@PathVariable Long incidenciaId, @PathVariable Long gerenteId, @RequestBody IncidenciaRespuestaDto dto){
        return incidenciaService.responderIncidencia(incidenciaId, gerenteId, dto);
    }

    @GetMapping
    public List<IncidenciaListaRespuestasDto> listar() {
        return incidenciaService.listarTodas();
    }
}
