package com.edutech.edutech_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.edutech.edutech_api.model.Alumno;
import com.edutech.edutech_api.service.AlumnoService;

@RestController
@RequestMapping("/alumnos")
public class AlumnoController {
    @Autowired
    private AlumnoService alumnoService;

    
    @PostMapping("/crear")
    public String crearAlumno(@RequestBody Alumno alumno){
        return alumnoService.crearAlumno(alumno);
    }   
    
    @GetMapping
    public List<Alumno> listar(){
        return alumnoService.listar();
    }

    @PutMapping("/{id}/deshabilitar")
    public String deshabilitar(@PathVariable Long id) {
        Alumno alumno = alumnoService.buscarPorId(id);
        if (alumno == null) {
            return "Alumno no encontrado";
        }
        alumno.setEstado(false);
        alumnoService.guardar(alumno);
        return "Alumno deshabilitado correctamente";
    }
}

//Diego Sotelo G.