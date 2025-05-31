package com.edutech.edutech_api.controller;
//Catalina Rosales->rataxikita
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edutech.edutech_api.model.Instructor;
import com.edutech.edutech_api.model.Pregunta;
import com.edutech.edutech_api.model.Respuesta;
import com.edutech.edutech_api.repository.InstructorRepository;
import com.edutech.edutech_api.repository.PreguntaRepository;
import com.edutech.edutech_api.repository.RespuestaRepository;
import com.edutech.edutech_api.model.Contenido;
import com.edutech.edutech_api.model.Evaluacion;
import com.edutech.edutech_api.service.InstructorService;
import com.edutech.edutech_api.repository.CursoRepository;
import com.edutech.edutech_api.model.Curso;

@RestController
@RequestMapping("/instructores")
public class InstructorController {

    @Autowired
    private InstructorRepository instructorRepo;

    @Autowired
    private CursoRepository cursoRepo;

    @Autowired
    private PreguntaRepository preguntaRepo;

    @Autowired
    private RespuestaRepository respuestaRepo;

    @Autowired
    private InstructorService instructorService;

    // CRUD básico

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Instructor i) {
    // Validar RUT
    Instructor existePorRut = instructorRepo.findByRut(i.getRut());
    if (existePorRut != null) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("RUT ya registrado");
    }

    // Validar correo
    Instructor existePorCorreo = instructorRepo.findByCorreo(i.getCorreo());
    if (existePorCorreo != null) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Correo ya registrado");
    }

    i.setEstado(true);

    return ResponseEntity.ok(instructorRepo.save(i));
}

    @GetMapping
    public List<Instructor> listar() {
        return instructorRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable Long id) {
        Instructor i = instructorRepo.findById(id).orElse(null);
        if (i == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(i);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Instructor datos) {
        Instructor i = instructorRepo.findById(id).orElse(null);
        if (i == null) return ResponseEntity.notFound().build();

        i.setNombre(datos.getNombre());
        i.setApellido(datos.getApellido());
        i.setEstado(datos.getEstado());
        return ResponseEntity.ok(instructorRepo.save(i));
    }


    // Ver cursos que dicta
    @GetMapping("/{id}/cursos")
    public ResponseEntity<?> cursosDelInstructor(@PathVariable Long id) {
        List<Curso> cursos = cursoRepo.findAll();
        List<Curso> cursosInstructor = new ArrayList<>();

        for (Curso c : cursos) {
            if (c.getInstructor().getId().equals(id)) {
                cursosInstructor.add(c);
            }
        }

        return ResponseEntity.ok(cursosInstructor);
    }

    // Ver preguntas de estudiantes
    @GetMapping("/{id}/preguntas")
    public ResponseEntity<?> verPreguntas(@PathVariable Long id) {
        List<Pregunta> preguntas = preguntaRepo.findAll();
        List<Pregunta> preguntasInstructor = new ArrayList<>();

        for (Pregunta p : preguntas) {
            if (p.getCurso().getInstructor().getId().equals(id)) {
                preguntasInstructor.add(p);
            }
        }

        return ResponseEntity.ok(preguntasInstructor);
    }

    // Responder preguntas
    @PostMapping("/{id}/responder")
    public ResponseEntity<?> responder(@PathVariable Long id, @RequestBody Respuesta r) {
        if (!r.getPregunta().getCurso().getInstructor().getId().equals(id)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No puedes responder esta pregunta");
        }
        return ResponseEntity.ok(respuestaRepo.save(r));
    }

    // Gestión de Contenido
    @PostMapping("/cursos/{cursoId}/contenido")
    public ResponseEntity<?> crearContenido(
            @PathVariable String cursoId,
            @RequestBody Contenido contenido) {
        try {
            Contenido nuevoContenido = instructorService.crearContenido(cursoId, contenido);
            return ResponseEntity.ok(nuevoContenido);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al crear contenido: " + e.getMessage());
        }
    }

    @PutMapping("/cursos/{cursoId}/contenido/{contenidoId}")
    public ResponseEntity<?> actualizarContenido(
            @PathVariable String cursoId,
            @PathVariable Long contenidoId,
            @RequestBody Contenido contenido) {
        try {
            Contenido contenidoActualizado = instructorService.actualizarContenido(cursoId, contenidoId, contenido);
            return ResponseEntity.ok(contenidoActualizado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al actualizar contenido: " + e.getMessage());
        }
    }

    // Gestión de Evaluaciones
    @PostMapping("/cursos/{cursoId}/evaluaciones")
    public ResponseEntity<?> crearEvaluacion(
            @PathVariable String cursoId,
            @RequestBody Evaluacion evaluacion) {
        try {
            Evaluacion nuevaEvaluacion = instructorService.crearEvaluacion(cursoId, evaluacion);
            return ResponseEntity.ok(nuevaEvaluacion);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al crear evaluación: " + e.getMessage());
        }
    }

    @PutMapping("/cursos/{cursoId}/evaluaciones/{evaluacionId}")
    public ResponseEntity<?> actualizarEvaluacion(
            @PathVariable String cursoId,
            @PathVariable Long evaluacionId,
            @RequestBody Evaluacion evaluacion) {
        try {
            Evaluacion evaluacionActualizada = instructorService.actualizarEvaluacion(cursoId, evaluacionId, evaluacion);
            return ResponseEntity.ok(evaluacionActualizada);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al actualizar evaluación: " + e.getMessage());
        }
    }

    // Interacción con Estudiantes
    @PostMapping("/preguntas/{preguntaId}/responder")
    public ResponseEntity<?> responderPregunta(
            @PathVariable Long preguntaId,
            @RequestBody Map<String, String> respuesta) {
        try {
            Pregunta preguntaRespondida = instructorService.responderPregunta(preguntaId, respuesta.get("respuesta"));
            return ResponseEntity.ok(preguntaRespondida);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al responder pregunta: " + e.getMessage());
        }
    }

    // Monitoreo de Progreso
    @GetMapping("/cursos/{cursoId}/progreso")
    public ResponseEntity<?> obtenerProgresoEstudiantes(@PathVariable String cursoId) {
        try {
            Map<String, Object> progreso = instructorService.obtenerProgresoEstudiantes(cursoId);
            return ResponseEntity.ok(progreso);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al obtener progreso: " + e.getMessage());
        }
    }
}


//Catalina Rosales->rataxikita