package com.edutech.edutech_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.edutech_api.model.Instructor;
import com.edutech.edutech_api.repository.InstructorRepository;

@Service
public class InstructorService {
    @Autowired
    private InstructorRepository instructorRepository;

    public Instructor crearInstructor(Instructor instructor){
        return instructorRepository.save(instructor);
    }
    public List<Instructor> obtenerTodos(){
        return instructorRepository.findAll();
    } 
    public Instructor obtenerPorId(Long id){
        return instructorRepository.findById(id).orElse(null);
    }
    public void eliminarInstructor(Long id){
        instructorRepository.deleteById(id); 
    }
}
