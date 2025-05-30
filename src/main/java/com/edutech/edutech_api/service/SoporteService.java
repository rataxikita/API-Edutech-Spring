package com.edutech.edutech_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.edutech_api.model.Soporte;
import com.edutech.edutech_api.repository.SoporteRepository;

@Service
public class SoporteService {
    @Autowired
    private SoporteRepository soporteRepository;

    public String crearSoporte(Soporte soporte){
        soporteRepository.save(soporte);
        return "soporte creado correctamente";
    }

    public List<Soporte> listar(){
        return soporteRepository.findAll();
    }
}
//Diego Sotelo G.