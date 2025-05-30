package com.edutech.edutech_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edutech.edutech_api.dto.RespuestaIncidenciaDto;
import com.edutech.edutech_api.model.Incidencia;
import com.edutech.edutech_api.service.IncidenciaService;

@RestController
@RequestMapping("/incidencias")
public class IncidenciaController {
    @Autowired
    private IncidenciaService incidenciaService;

    @PostMapping
    public Incidencia almacenar(@RequestBody Incidencia incidencia){
        return incidenciaService.crearIncidencia(incidencia);
    }

    @GetMapping
    public List<Incidencia> listyar(){
        return incidenciaService.listar();
    }

    @PutMapping("/{id}/respuesta")
    public Incidencia responder(@PathVariable Long id, @RequestBody RespuestaIncidenciaDto resDto){
        return incidenciaService.responderIncidencia(id, resDto);
    }
}

//Diego Sotelo G.