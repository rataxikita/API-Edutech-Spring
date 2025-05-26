//Catalina Rosales->rataxikita
package com.edutech.edutech_api.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edutech.edutech_api.model.Curso;
import com.edutech.edutech_api.model.Evaluacion;
import com.edutech.edutech_api.repository.CursoRepository;
import com.edutech.edutech_api.repository.EvaluacionRepository;

@RestController
@RequestMapping("/instructores")
public class EvaluacionController {

    @Autowired
    private EvaluacionRepository evaluacionRepo;

    @Autowired
    private CursoRepository cursoRepo;

    @PostMapping("/{id}/evaluaciones")
    public ResponseEntity<?> crearEvaluacion(@PathVariable Long id, @RequestBody Evaluacion ev) {
        Curso curso = cursoRepo.findById(ev.getCurso().getId()).orElse(null);
        if (curso == null || !curso.getInstructor().getId().equals(id)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No puedes crear evaluación en este curso");
        }
            return ResponseEntity.ok(evaluacionRepo.save(ev));
    }

    @GetMapping("/{id}/evaluaciones")
    public ResponseEntity<?> verEvaluaciones(@PathVariable Long id) {
        List<Evaluacion> lista = evaluacionRepo.findAll();
        List<Evaluacion> propias = new ArrayList<>();

        for (Evaluacion e : lista) {
            if (e.getCurso().getInstructor().getId().equals(id)) {
                propias.add(e);
            }
        }

        return ResponseEntity.ok(propias);
    }
}
//Catalina Rosales->rataxikita
