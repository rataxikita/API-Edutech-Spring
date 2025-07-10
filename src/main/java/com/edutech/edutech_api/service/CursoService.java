// Anais Llancapan - peitou1
package com.edutech.edutech_api.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.edutech_api.dto.CursoDTO;
import com.edutech.edutech_api.model.Curso;
import com.edutech.edutech_api.repository.CursoRepository;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    // Listar todos los cursos
    public List<CursoDTO> listarCursos() {
        return cursoRepository.findAll()
            .stream()
            .map(this::convertirACursoDTO)
            .collect(Collectors.toList());
    }

    // Crear curso desde DTO
    public Curso crearCurso(CursoDTO dto) {
        Curso curso = new Curso(
            dto.getSigla(),
            dto.getNombre(),
            dto.getDescripcion(),
            dto.getEstado(),
            dto.getValor()
        );
        return cursoRepository.save(curso);
    }

    // Buscar curso por sigla
    public Curso buscarPorSigla(String sigla) {
        return cursoRepository.findById(sigla).orElse(null);
    }

    // Verificar si existe un curso por sigla
    public boolean existsById(String sigla) {
        return cursoRepository.existsById(sigla);
    }

    // Eliminar un curso por sigla
    public void deleteById(String sigla) {
        cursoRepository.deleteById(sigla);
    }

    // Actualizar curso usando DTO
    public Curso actualizarCurso(String sigla, CursoDTO dto) {
        Optional<Curso> opcional = cursoRepository.findById(sigla);
        if (opcional.isPresent()) {
            Curso curso = opcional.get();
            curso.setNombre(dto.getNombre());
            curso.setDescripcion(dto.getDescripcion());
            curso.setEstado(dto.getEstado());
            curso.setValor(dto.getValor());
            return cursoRepository.save(curso);
        }
        return null;
    }

    private CursoDTO convertirACursoDTO(Curso curso) {
        CursoDTO dto = new CursoDTO();
        dto.setSigla(curso.getSigla());
        dto.setNombre(curso.getNombre());
        dto.setDescripcion(curso.getDescripcion());
        dto.setEstado(curso.isEstado());
        dto.setValor(curso.getValor());
        return dto;
    }
}

