package com.edutech.edutech_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edutech.edutech_api.dto.IncidenciaPreguntaDto;
import com.edutech.edutech_api.dto.IncidenciaRespuestaDto;
import com.edutech.edutech_api.model.Incidencia;
import com.edutech.edutech_api.service.IncidenciaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/incidencias")
public class IncidenciaController {
    @Autowired
    private IncidenciaService incidenciaService;

        @PostMapping
    public ResponseEntity<Incidencia> crearIncidencia(@Valid @RequestBody IncidenciaPreguntaDto dto) {
        Incidencia nueva = incidenciaService.crearIncidencia(dto);
        return ResponseEntity.ok(nueva);
    }

    @GetMapping
    public List<Incidencia> listyar(){
        return incidenciaService.listar();
    }

@PutMapping("/respuesta")
public ResponseEntity<?> responder(@RequestBody IncidenciaRespuestaDto resDto){
    try {
        Incidencia respuesta = incidenciaService.responderIncidencia(resDto.getIncidenciaId(), resDto);
        return ResponseEntity.ok(respuesta);
    } catch (Exception e) {
        e.printStackTrace(); // Esto te mostrará el error real en la consola
        return ResponseEntity.status(500).body("Error: " + e.getMessage());
    }
}

}

//Diego Sotelo G.