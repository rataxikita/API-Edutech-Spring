// Anais Llancapan
package com.edutech.edutech_api.controller;

import java.util.List;

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

import com.edutech.edutech_api.dto.CursoDTO;
import com.edutech.edutech_api.model.Curso;
import com.edutech.edutech_api.service.CursoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    //Crear curso
    @PostMapping
    public ResponseEntity<Curso> crearCurso(@Valid @RequestBody CursoDTO dto) {
        Curso nuevo = cursoService.crearCurso(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    //Listar cursos
    @GetMapping
    public ResponseEntity<List<CursoDTO>> listarCursos() {
        return ResponseEntity.ok(cursoService.listarCursos());
    }

    //Obtener curso por sigla
    @GetMapping("/{sigla}")
    public ResponseEntity<Curso> obtenerCurso(@PathVariable String sigla) {
        Curso curso = cursoService.buscarPorSigla(sigla);
        return curso != null ? ResponseEntity.ok(curso) : ResponseEntity.notFound().build();
    }

    //Actualizar curso
    @PutMapping("/{sigla}")
    public ResponseEntity<Curso> actualizarCurso(@PathVariable String sigla, @Valid @RequestBody CursoDTO dto) {
        Curso actualizado = cursoService.actualizarCurso(sigla, dto);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    //Eliminar curso
    @DeleteMapping("/{sigla}")
    public ResponseEntity<String> eliminarCurso(@PathVariable String sigla) {
        if (!cursoService.existsById(sigla)) {
            return ResponseEntity.notFound().build();
        }
        cursoService.deleteById(sigla);
        return ResponseEntity.ok("Curso eliminado correctamente");
    }
}