// Catalina Rosales->rataxikita

package com.edutech.edutech_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.edutech.edutech_api.model.UsuarioCurso;
import com.edutech.edutech_api.model.Alumno;
import com.edutech.edutech_api.repository.UsuarioCursoRepository;
import com.edutech.edutech_api.repository.AdministradorRepository;
import com.edutech.edutech_api.repository.InstructorRepository;
import com.edutech.edutech_api.repository.AlumnoRepository;
import com.edutech.edutech_api.repository.GerenteCursosRepository;

import java.util.*;

@RestController
@RequestMapping("/api/usuario-cursos")
public class UsuarioCursoController {

    @Autowired
    private UsuarioCursoRepository usuarioCursoRepo;

    @Autowired
    private AdministradorRepository administradorRepo;

    @Autowired
    private InstructorRepository instructorRepo;

    @Autowired
    private AlumnoRepository alumnoRepo;

    @Autowired
    private GerenteCursosRepository gerenteCursosRepo;

    @PostMapping("/inscribir")
    public ResponseEntity<?> inscribir(@RequestBody UsuarioCurso uc) {
        // Buscar el usuario en todas las entidades
        Object usuario = buscarUsuarioPorId(uc);
        
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Usuario no encontrado");
        }
            
        // Validar que sea estudiante (Alumno)
        if (!(usuario instanceof Alumno)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body("Solo los estudiantes pueden inscribirse en cursos");
        }

        // Validar que no esté ya inscrito en el mismo curso
        List<UsuarioCurso> existentes = usuarioCursoRepo.findByAlumnoIdAndCursoSigla(
            ((Alumno) usuario).getId(),
            uc.getCurso().getSigla()
        );

        if (!existentes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya estás inscrito en este curso");
        }

        // Crear nueva inscripción
        UsuarioCurso nuevaInscripcion = new UsuarioCurso();
        nuevaInscripcion.setAlumno((Alumno) usuario);
        nuevaInscripcion.setCurso(uc.getCurso());
        nuevaInscripcion.setFechaInscripcion(new Date().toString());
        nuevaInscripcion.setProgreso("0%");
        
        return ResponseEntity.ok(usuarioCursoRepo.save(nuevaInscripcion));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<?> cursosDeUsuario(@PathVariable Long usuarioId) {
        // Validar que el usuario existe
        if (!usuarioExiste(usuarioId)) {
            return ResponseEntity.notFound().build();
        }

        List<UsuarioCurso> lista = usuarioCursoRepo.findByUsuarioId(usuarioId);
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/usuario/{usuarioId}/progreso")
    public ResponseEntity<?> progresoDeUsuario(@PathVariable Long usuarioId) {
        // Validar que el usuario existe
        if (!usuarioExiste(usuarioId)) {
            return ResponseEntity.notFound().build();
        }

        List<UsuarioCurso> lista = usuarioCursoRepo.findByUsuarioId(usuarioId);
        List<Map<String, String>> progreso = new ArrayList<>();

        for (UsuarioCurso uc : lista) {
            Map<String, String> item = new HashMap<>();
            item.put("curso", uc.getCurso().getNombre());
            item.put("progreso", uc.getProgreso());
            progreso.add(item);
        }

        return ResponseEntity.ok(progreso);
    }

    private Object buscarUsuarioPorId(UsuarioCurso uc) {
        // Buscar en todas las entidades de usuario
        if (uc.getAdministrador() != null) {
            return administradorRepo.findById(uc.getAdministrador().getId()).orElse(null);
        }
        if (uc.getInstructor() != null) {
            return instructorRepo.findById(uc.getInstructor().getId()).orElse(null);
        }
        if (uc.getAlumno() != null) {
            return alumnoRepo.findById(uc.getAlumno().getId()).orElse(null);
        }
        if (uc.getGerenteCursos() != null) {
            return gerenteCursosRepo.findById(uc.getGerenteCursos().getId()).orElse(null);
        }
        return null;
    }

    private boolean usuarioExiste(Long usuarioId) {
        return administradorRepo.existsById(usuarioId) ||
               instructorRepo.existsById(usuarioId) ||
               alumnoRepo.existsById(usuarioId) ||
               gerenteCursosRepo.existsById(usuarioId);
    }
}
// Catalina Rosales->rataxikita

