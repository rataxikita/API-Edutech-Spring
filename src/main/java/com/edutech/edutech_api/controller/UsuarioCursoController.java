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
import com.edutech.edutech_api.dto.UsuarioCursoDTO;

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
        // Debug de los datos recibidos
        System.out.println("UsuarioCurso recibido: " + uc);
        System.out.println("Alumno: " + (uc.getAlumno() != null ? uc.getAlumno().getId() : "null"));
        System.out.println("Curso: " + (uc.getCurso() != null ? uc.getCurso().getSigla() : "null"));

        // Validar que se proporcionen los datos necesarios
        if (uc.getAlumno() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("No se proporcionó información del alumno");
        }

        if (uc.getAlumno().getId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Se requiere un ID de alumno válido");
        }
        
        if (uc.getCurso() == null || uc.getCurso().getSigla() == null || uc.getCurso().getSigla().trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Se requiere una sigla de curso válida");
        }

        Long alumnoId = uc.getAlumno().getId();
        
        // Buscar el alumno directamente
        Alumno alumno = alumnoRepo.findById(alumnoId).orElse(null);
        
        if (alumno == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Alumno con ID " + alumnoId + " no encontrado");
        }
            
        // Validar que el alumno esté activo
        if (!alumno.isEstado()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body("El alumno está deshabilitado");
        }

        // Validar que no esté ya inscrito en el mismo curso
        List<UsuarioCurso> existentes = usuarioCursoRepo.findByAlumnoIdAndCursoSigla(
            alumnoId,
            uc.getCurso().getSigla()
        );

        if (!existentes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("El alumno ya está inscrito en el curso " + uc.getCurso().getSigla());
        }

        // Crear nueva inscripción
        UsuarioCurso nuevaInscripcion = new UsuarioCurso();
        nuevaInscripcion.setAlumno(alumno);
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
        List<UsuarioCursoDTO> dtos = new ArrayList<>();

        for (UsuarioCurso uc : lista) {
            UsuarioCursoDTO dto = new UsuarioCursoDTO();
            dto.setId(uc.getId());
            dto.setNombreAlumno(uc.getAlumno() != null ? 
                uc.getAlumno().getNombre() + " " + uc.getAlumno().getApellidos() : "");
            dto.setSiglaCurso(uc.getCurso().getSigla());
            dto.setNombreCurso(uc.getCurso().getNombre());
            dto.setFechaInscripcion(uc.getFechaInscripcion());
            dto.setProgreso(uc.getProgreso());
            dtos.add(dto);
        }

        return ResponseEntity.ok(dtos);
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

    private Object buscarUsuarioPorId(Long usuarioId) {
        // Buscar en todas las entidades de usuario
        Object usuario = administradorRepo.findById(usuarioId).orElse(null);
        if (usuario != null) return usuario;

        usuario = instructorRepo.findById(usuarioId).orElse(null);
        if (usuario != null) return usuario;

        usuario = alumnoRepo.findById(usuarioId).orElse(null);
        if (usuario != null) return usuario;

        usuario = gerenteCursosRepo.findById(usuarioId).orElse(null);
        return usuario;
    }

    private boolean usuarioExiste(Long usuarioId) {
        return administradorRepo.existsById(usuarioId) ||
               instructorRepo.existsById(usuarioId) ||
               alumnoRepo.existsById(usuarioId) ||
               gerenteCursosRepo.existsById(usuarioId);
    }
}
// Catalina Rosales->rataxikita

