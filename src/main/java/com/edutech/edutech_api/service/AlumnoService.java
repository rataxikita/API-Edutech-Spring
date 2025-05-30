package com.edutech.edutech_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.edutech_api.model.Alumno;
import com.edutech.edutech_api.repository.AlumnoRepository;

@Service
public class AlumnoService {
    @Autowired
    private AlumnoRepository alumnoRepository;

    public String crearAlumno(Alumno alumno){

        Alumno validacion = alumnoRepository.findByCorreo(alumno.getCorreo());

        if(validacion == null){
            alumnoRepository.save(alumno);
        return "Alumno creado correctamente";
        } else{
            return "Alumno con correo " + alumno.getCorreo() + " ya se encuentra registrado";
        }

    }

    public List<Alumno> listar(){
        return alumnoRepository.findAll();
    }

    public Alumno buscarPorId(Long id) {
        return alumnoRepository.findById(id).orElse(null);
    }

    public Alumno guardar(Alumno alumno) {
        return alumnoRepository.save(alumno);
    }
}
//Diego Sotelo G.