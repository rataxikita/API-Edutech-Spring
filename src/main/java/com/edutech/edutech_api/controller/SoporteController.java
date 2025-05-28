package com.edutech.edutech_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edutech.edutech_api.model.Soporte;
import com.edutech.edutech_api.service.SoporteService;

@RestController
@RequestMapping("/soportes")
public class SoporteController {
    @Autowired
    private SoporteService soporteService;

    @PostMapping
    public String almacenar(@RequestBody Soporte soporte){
        return soporteService.crearSoporte(soporte);
    }

    @GetMapping
    public List<Soporte> listar(){
        return soporteService.listar();
    }
}

//Diego Sotelo G.