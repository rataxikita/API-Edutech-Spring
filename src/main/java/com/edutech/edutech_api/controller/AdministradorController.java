// Catalina Rosales->rataxikita
package com.edutech.edutech_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.edutech.edutech_api.model.Alumno;
import com.edutech.edutech_api.model.GerenteCursos;
import com.edutech.edutech_api.model.Soporte;
import com.edutech.edutech_api.model.Rol;
import com.edutech.edutech_api.service.AdministradorService;
import com.edutech.edutech_api.dto.GerenteCursosDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/administrador")
public class AdministradorController {

    @Autowired
    private AdministradorService administradorService;

    // SOLO ADMINISTRADOR PUEDE CREAR ALUMNOS
    @PostMapping("/alumnos")
    public ResponseEntity<?> crearAlumno(@Valid @RequestBody Alumno alumno) {
        try {
            // Simular usuario autenticado (en producción esto vendría del contexto de seguridad)
            administradorService.setUsuarioAutenticado(1L); // ID del administrador autenticado
            
            Alumno nuevoAlumno = administradorService.crearAlumno(alumno);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoAlumno);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Error al crear alumno: " + e.getMessage());
        }
    }

    // SOLO ADMINISTRADOR PUEDE CREAR GERENTES DE CURSOS
    @PostMapping("/gerentes")
    public ResponseEntity<?> crearGerente(@Valid @RequestBody GerenteCursosDTO gerenteDTO) {
        try {
            // Simular usuario autenticado (en producción esto vendría del contexto de seguridad)
            administradorService.setUsuarioAutenticado(1L); // ID del administrador autenticado
            
            // Crear gerente desde DTO
            GerenteCursos gerente = new GerenteCursos();
            gerente.setNombre(gerenteDTO.getNombre());
            gerente.setCorreo(gerenteDTO.getCorreo());
            gerente.setClave(gerenteDTO.getClave());
            gerente.setEstado(gerenteDTO.isEstado());
            
            GerenteCursos nuevoGerente = administradorService.crearGerenteCursos(gerente);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoGerente);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Error al crear gerente: " + e.getMessage());
        }
    }

    // SOLO ADMINISTRADOR PUEDE CREAR SOPORTE
    @PostMapping("/soporte")
    public ResponseEntity<?> crearSoporte(@Valid @RequestBody Soporte soporte) {
        try {
            // Simular usuario autenticado (en producción esto vendría del contexto de seguridad)
            administradorService.setUsuarioAutenticado(1L); // ID del administrador autenticado
            
            Soporte nuevoSoporte = administradorService.crearSoporte(soporte);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoSoporte);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Error al crear soporte: " + e.getMessage());
        }
    }

    // Gestión de usuarios existentes
    @GetMapping("/alumnos")
    public ResponseEntity<?> listarAlumnos() {
        try {
            administradorService.setUsuarioAutenticado(1L);
            List<Alumno> alumnos = administradorService.listarAlumnos();
            return ResponseEntity.ok(alumnos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body("Error de permisos: " + e.getMessage());
        }
    }

    @GetMapping("/gerentes")
    public ResponseEntity<?> listarGerentes() {
        try {
            administradorService.setUsuarioAutenticado(1L);
            List<GerenteCursos> gerentes = administradorService.listarGerentes();
            return ResponseEntity.ok(gerentes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body("Error de permisos: " + e.getMessage());
        }
    }

    @GetMapping("/soporte")
    public ResponseEntity<?> listarSoporte() {
        try {
            administradorService.setUsuarioAutenticado(1L);
            List<Soporte> soporte = administradorService.listarSoporte();
            return ResponseEntity.ok(soporte);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body("Error de permisos: " + e.getMessage());
        }
    }

    @GetMapping("/alumnos/{id}")
    public ResponseEntity<?> obtenerAlumno(@PathVariable Long id) {
        try {
            administradorService.setUsuarioAutenticado(1L);
            Alumno alumno = administradorService.buscarAlumnoPorId(id);
            if (alumno == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Alumno no encontrado");
            }
            return ResponseEntity.ok(alumno);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body("Error de permisos: " + e.getMessage());
        }
    }

    @GetMapping("/gerentes/{id}")
    public ResponseEntity<?> obtenerGerente(@PathVariable Long id) {
        try {
            administradorService.setUsuarioAutenticado(1L);
            GerenteCursos gerente = administradorService.buscarGerentePorId(id);
            if (gerente == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Gerente no encontrado");
            }
            return ResponseEntity.ok(gerente);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body("Error de permisos: " + e.getMessage());
        }
    }

    @GetMapping("/soporte/{id}")
    public ResponseEntity<?> obtenerSoporte(@PathVariable Long id) {
        try {
            administradorService.setUsuarioAutenticado(1L);
            Soporte soporte = administradorService.buscarSoportePorId(id);
            if (soporte == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Soporte no encontrado");
            }
            return ResponseEntity.ok(soporte);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body("Error de permisos: " + e.getMessage());
        }
    }

    @PutMapping("/alumnos/{id}")
    public ResponseEntity<?> actualizarAlumno(@PathVariable Long id, @Valid @RequestBody Alumno alumno) {
        try {
            administradorService.setUsuarioAutenticado(1L);
            Alumno alumnoActualizado = administradorService.actualizarAlumno(id, alumno);
            return ResponseEntity.ok(alumnoActualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Error al actualizar alumno: " + e.getMessage());
        }
    }

    @PutMapping("/gerentes/{id}")
    public ResponseEntity<?> actualizarGerente(@PathVariable Long id, @Valid @RequestBody GerenteCursosDTO gerenteDTO) {
        try {
            administradorService.setUsuarioAutenticado(1L);
            
            // Crear gerente desde DTO
            GerenteCursos gerente = new GerenteCursos();
            gerente.setNombre(gerenteDTO.getNombre());
            gerente.setCorreo(gerenteDTO.getCorreo());
            gerente.setClave(gerenteDTO.getClave());
            gerente.setEstado(gerenteDTO.isEstado());
            
            GerenteCursos gerenteActualizado = administradorService.actualizarGerente(id, gerente);
            return ResponseEntity.ok(gerenteActualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Error al actualizar gerente: " + e.getMessage());
        }
    }

    @PutMapping("/soporte/{id}")
    public ResponseEntity<?> actualizarSoporte(@PathVariable Long id, @Valid @RequestBody Soporte soporte) {
        try {
            administradorService.setUsuarioAutenticado(1L);
            Soporte soporteActualizado = administradorService.actualizarSoporte(id, soporte);
            return ResponseEntity.ok(soporteActualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Error al actualizar soporte: " + e.getMessage());
        }
    }

    // Métodos de eliminación
    @DeleteMapping("/alumnos/{id}")
    public ResponseEntity<?> eliminarAlumno(@PathVariable Long id) {
        try {
            administradorService.setUsuarioAutenticado(1L);
            administradorService.eliminarAlumno(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Error al eliminar alumno: " + e.getMessage());
        }
    }

    @DeleteMapping("/gerentes/{id}")
    public ResponseEntity<?> eliminarGerente(@PathVariable Long id) {
        try {
            administradorService.setUsuarioAutenticado(1L);
            administradorService.eliminarGerenteCursos(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Error al eliminar gerente: " + e.getMessage());
        }
    }

    @DeleteMapping("/soporte/{id}")
    public ResponseEntity<?> eliminarSoporte(@PathVariable Long id) {
        try {
            administradorService.setUsuarioAutenticado(1L);
            administradorService.eliminarSoporte(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Error al eliminar soporte: " + e.getMessage());
        }
    }
} 