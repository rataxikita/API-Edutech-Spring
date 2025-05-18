package com.edutech.edutech_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.edutech.edutech_api.dto.InstructorDTO;
import com.edutech.edutech_api.model.Instructor;
import com.edutech.edutech_api.service.InstructorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/instructores")
public class InstructorController {

    @Autowired
    private InstructorService instructorService;

    @PostMapping
    public Instructor crear(@RequestBody @Valid InstructorDTO dto) {
        Instructor instructor = new Instructor();
        instructor.setNombre(dto.getNombre());
        instructor.setEspecialidad(dto.getEspecialidad());
        instructor.setCorreo(dto.getCorreo());
        instructor.setClave(dto.getClave());
        return instructorService.crearInstructor(instructor);
    }

    @GetMapping
    public List<Instructor> obtenerTodos() {
        return instructorService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Instructor obtenerPorId(@PathVariable Long id) {
        return instructorService.obtenerPorId(id);
    }

    @PutMapping("/{id}")
    public Instructor actualizar(@PathVariable Long id, @RequestBody @Valid InstructorDTO dto) {
        Instructor instructor = instructorService.obtenerPorId(id);
        instructor.setNombre(dto.getNombre());
        instructor.setEspecialidad(dto.getEspecialidad());
        instructor.setCorreo(dto.getCorreo());
        instructor.setClave(dto.getClave());
        return instructorService.crearInstructor(instructor); 
    }

    
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        instructorService.eliminarInstructor(id);
    }
}
