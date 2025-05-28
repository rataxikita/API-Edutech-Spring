// Catalina Rosales->rataxikita

package com.edutech.edutech_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.edutech.edutech_api.model.UsuarioCurso;
import com.edutech.edutech_api.repository.UsuarioCursoRepository;
import com.edutech.edutech_api.repository.UsuarioRepository;
import com.edutech.edutech_api.model.Usuario;

import java.util.*;

@RestController
@RequestMapping("/api/usuario-cursos")
public class UsuarioCursoController {

    @Autowired
    private UsuarioCursoRepository usuarioCursoRepo;

    @Autowired
    private UsuarioRepository usuarioRepo;

    @PostMapping("/inscribir")
    public ResponseEntity<?> inscribir(@RequestBody UsuarioCurso uc) {
        // Validar que el usuario existe y es estudiante
        Usuario usuario = usuarioRepo.findById(uc.getUsuario().getId())
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            
        if (!usuario.esEstudiante()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body("Solo los estudiantes pueden inscribirse en cursos");
        }

        // Validar que no esté ya inscrito en el mismo curso
        List<UsuarioCurso> existentes = usuarioCursoRepo.findByUsuarioIdAndCursoId(
            uc.getUsuario().getId(),
            uc.getCurso().getId()
        );

        if (!existentes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya estás inscrito en este curso");
        }

        uc.setFechaInscripcion(new Date().toString());
        uc.setProgreso("0%");
        return ResponseEntity.ok(usuarioCursoRepo.save(uc));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<?> cursosDeUsuario(@PathVariable Long usuarioId) {
        // Validar que el usuario existe
        if (!usuarioRepo.existsById(usuarioId)) {
            return ResponseEntity.notFound().build();
        }

        List<UsuarioCurso> lista = usuarioCursoRepo.findByUsuarioId(usuarioId);
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/usuario/{usuarioId}/progreso")
    public ResponseEntity<?> progresoDeUsuario(@PathVariable Long usuarioId) {
        // Validar que el usuario existe
        if (!usuarioRepo.existsById(usuarioId)) {
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
}
// Catalina Rosales->rataxikita

