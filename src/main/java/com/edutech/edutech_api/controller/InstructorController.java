package com.edutech.edutech_api.controller;
//Catalina Rosales->rataxikita
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.edutech.edutech_api.dto.InstructorListDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/instructores")
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

    // CRUD básico - SOLO LECTURA Y ACTUALIZACIÓN
    // LA CREACIÓN DE INSTRUCTORES SOLO LA PUEDE HACER EL GERENTE DE CURSOS

    /*
    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody InstructorDTO instructorDTO) {
        // ESTE ENDPOINT HA SIDO DESHABILITADO
        // SOLO EL GERENTE DE CURSOS PUEDE CREAR INSTRUCTORES
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
            .body("Acceso denegado. Solo los gerentes de cursos pueden crear instructores. Use /api/gerente-cursos/instructores");
    }
    */

    @GetMapping
    public ResponseEntity<List<InstructorListDTO>> listar() {
        try {
            List<InstructorListDTO> instructores = instructorService.obtenerTodos();
            return ResponseEntity.ok(instructores);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable Long id) {
        try {
            Instructor instructor = instructorRepo.findById(id).orElse(null);
            if (instructor == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Instructor con ID " + id + " no encontrado");
            }
            return ResponseEntity.ok(instructor);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al obtener instructor: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody Instructor datos) {
        try {
            Instructor instructor = instructorRepo.findById(id).orElse(null);
            if (instructor == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Instructor con ID " + id + " no encontrado");
            }

            // Validar que el nuevo correo no esté en uso por otro instructor
            if (!instructor.getCorreo().equals(datos.getCorreo())) {
                Instructor existePorCorreo = instructorRepo.findByCorreo(datos.getCorreo());
                if (existePorCorreo != null) {
                    return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Error: El correo " + datos.getCorreo() + " ya está en uso");
                }
            }

            // Validar que el nuevo RUT no esté en uso por otro instructor
            if (!instructor.getRut().equals(datos.getRut())) {
                Instructor existePorRut = instructorRepo.findByRut(datos.getRut());
                if (existePorRut != null) {
                    return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Error: El RUT " + datos.getRut() + " ya está en uso");
                }
            }

            // Actualizar campos
            instructor.setNombre(datos.getNombre());
            instructor.setApellido(datos.getApellido());
            instructor.setCorreo(datos.getCorreo());
            instructor.setRut(datos.getRut());
            instructor.setEstado(datos.isEstado());
            
            // Solo actualizar clave si se proporciona una nueva
            if (datos.getClave() != null && !datos.getClave().isEmpty()) {
                instructor.setClave(datos.getClave());
            }

            Instructor instructorActualizado = instructorRepo.save(instructor);
            return ResponseEntity.ok(instructorActualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Error al actualizar instructor: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            Instructor instructor = instructorRepo.findById(id).orElse(null);
            if (instructor == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Instructor con ID " + id + " no encontrado");
            }

            // Verificar si tiene cursos asignados
            if (instructor.getCursos() != null && !instructor.getCursos().isEmpty()) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("No se puede eliminar el instructor porque tiene cursos asignados");
            }

            instructorRepo.deleteById(id);
            return ResponseEntity.ok("Instructor eliminado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al eliminar instructor: " + e.getMessage());
        }
    }

    // Ver cursos que dicta
    @GetMapping("/{id}/cursos")
    public ResponseEntity<?> cursosDelInstructor(@PathVariable Long id) {
        try {
            Instructor instructor = instructorRepo.findById(id).orElse(null);
            if (instructor == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Instructor con ID " + id + " no encontrado");
            }

            List<Curso> cursos = cursoRepo.findAll();
            List<Curso> cursosInstructor = new ArrayList<>();

            for (Curso c : cursos) {
                if (c.getInstructor() != null && c.getInstructor().getId().equals(id)) {
                    cursosInstructor.add(c);
                }
            }

            return ResponseEntity.ok(cursosInstructor);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al obtener cursos del instructor: " + e.getMessage());
        }
    }

    // Ver preguntas de estudiantes
    @GetMapping("/{id}/preguntas")
    public ResponseEntity<?> verPreguntas(@PathVariable Long id) {
        try {
            Instructor instructor = instructorRepo.findById(id).orElse(null);
            if (instructor == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Instructor con ID " + id + " no encontrado");
            }

            List<Pregunta> preguntas = preguntaRepo.findByInstructorId(id);
            return ResponseEntity.ok(preguntas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al obtener preguntas: " + e.getMessage());
        }
    }

    // Responder preguntas
    @PostMapping("/{id}/responder")
    public ResponseEntity<?> responder(@PathVariable Long id, @RequestBody Respuesta r) {
        try {
            if (!r.getPregunta().getInstructor().getId().equals(id)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("No tienes permisos para responder esta pregunta");
            }
            Respuesta respuestaGuardada = respuestaRepo.save(r);
            return ResponseEntity.ok(respuestaGuardada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Error al responder pregunta: " + e.getMessage());
        }
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