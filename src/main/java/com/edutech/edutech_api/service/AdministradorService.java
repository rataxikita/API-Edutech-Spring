// Catalina Rosales->rataxikita
package com.edutech.edutech_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.edutech_api.model.Administrador;
import com.edutech.edutech_api.model.Alumno;
import com.edutech.edutech_api.model.GerenteCursos;
import com.edutech.edutech_api.model.Soporte;
import com.edutech.edutech_api.model.Rol;
import com.edutech.edutech_api.repository.AdministradorRepository;
import com.edutech.edutech_api.repository.AlumnoRepository;
import com.edutech.edutech_api.repository.GerenteCursosRepository;
import com.edutech.edutech_api.repository.SoporteRepository;
import com.edutech.edutech_api.dto.AlumnoListDTO;
import com.edutech.edutech_api.dto.GerenteCursosDTO;
import com.edutech.edutech_api.dto.SoporteListDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdministradorService {

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private GerenteCursosRepository gerenteCursosRepository;

    @Autowired
    private SoporteRepository soporteRepository;

    // Variable para simular el usuario autenticado (en producción esto vendría del contexto de seguridad)
    private Long usuarioAutenticadoId;

    // Método para establecer el usuario autenticado (simulación)
    public void setUsuarioAutenticado(Long usuarioId) {
        this.usuarioAutenticadoId = usuarioId;
    }

    // SOLO ADMINISTRADOR PUEDE CREAR ALUMNOS
    public Alumno crearAlumno(Alumno alumno) {
        validarPermisosAdministrador();
        
        // Validar que no exista un alumno con el mismo correo
        if (alumnoRepository.findByCorreo(alumno.getCorreo()) != null) {
            throw new RuntimeException("Ya existe un alumno con el correo: " + alumno.getCorreo());
        }
        
        // Asegurar que el rol sea ESTUDIANTE
        alumno.setRol(Rol.ESTUDIANTE);
        alumno.setEstado(true);
        
        return alumnoRepository.save(alumno);
    }

    // SOLO ADMINISTRADOR PUEDE CREAR GERENTES DE CURSOS
    public GerenteCursos crearGerenteCursos(GerenteCursos gerente) {
        validarPermisosAdministrador();
        
        // Validar que no exista un gerente con el mismo correo
        if (gerenteCursosRepository.findByCorreo(gerente.getCorreo()) != null) {
            throw new RuntimeException("Ya existe un gerente con el correo: " + gerente.getCorreo());
        }
        
        // Asegurar que el rol sea GERENTE_CURSOS
        gerente.setRol(Rol.GERENTE_CURSOS);
        gerente.setEstado(true);
        
        return gerenteCursosRepository.save(gerente);
    }

    // SOLO ADMINISTRADOR PUEDE CREAR SOPORTE
    public Soporte crearSoporte(Soporte soporte) {
        validarPermisosAdministrador();
        
        // La entidad Soporte solo tiene nombreSoporte, no hay validaciones adicionales
        return soporteRepository.save(soporte);
    }

    // Gestión de usuarios existentes
    public List<AlumnoListDTO> listarAlumnos() {
        validarPermisosAdministrador();
        return alumnoRepository.findAll()
            .stream()
            .map(this::convertirAAlumnoDTO)
            .collect(Collectors.toList());
    }

    public List<GerenteCursosDTO> listarGerentes() {
        validarPermisosAdministrador();
        return gerenteCursosRepository.findAll()
            .stream()
            .map(this::convertirAGerenteDTO)
            .collect(Collectors.toList());
    }

    public List<SoporteListDTO> listarSoporte() {
        validarPermisosAdministrador();
        return soporteRepository.findAll()
            .stream()
            .map(this::convertirASoporteDTO)
            .collect(Collectors.toList());
    }

    public Alumno buscarAlumnoPorId(Long id) {
        validarPermisosAdministrador();
        return alumnoRepository.findById(id).orElse(null);
    }

    public GerenteCursos buscarGerentePorId(Long id) {
        validarPermisosAdministrador();
        return gerenteCursosRepository.findById(id).orElse(null);
    }

    public Soporte buscarSoportePorId(Long id) {
        validarPermisosAdministrador();
        return soporteRepository.findById(id).orElse(null);
    }

    public Alumno actualizarAlumno(Long id, Alumno alumnoActualizado) {
        validarPermisosAdministrador();
        
        Alumno alumno = alumnoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));
        
        // Validar que el nuevo correo no esté en uso por otro alumno
        if (!alumno.getCorreo().equals(alumnoActualizado.getCorreo())) {
            if (alumnoRepository.findByCorreo(alumnoActualizado.getCorreo()) != null) {
                throw new RuntimeException("Ya existe un alumno con el correo: " + alumnoActualizado.getCorreo());
            }
        }
        
        alumno.setNombre(alumnoActualizado.getNombre());
        alumno.setApellidos(alumnoActualizado.getApellidos());
        alumno.setCorreo(alumnoActualizado.getCorreo());
        alumno.setEstado(alumnoActualizado.isEstado());
        
        if (alumnoActualizado.getClave() != null && !alumnoActualizado.getClave().isEmpty()) {
            alumno.setClave(alumnoActualizado.getClave());
        }
        
        return alumnoRepository.save(alumno);
    }

    public GerenteCursos actualizarGerente(Long id, GerenteCursos gerenteActualizado) {
        validarPermisosAdministrador();
        
        GerenteCursos gerente = gerenteCursosRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Gerente no encontrado"));
        
        // Validar que el nuevo correo no esté en uso por otro gerente
        if (!gerente.getCorreo().equals(gerenteActualizado.getCorreo())) {
            if (gerenteCursosRepository.findByCorreo(gerenteActualizado.getCorreo()) != null) {
                throw new RuntimeException("Ya existe un gerente con el correo: " + gerenteActualizado.getCorreo());
            }
        }
        
        gerente.setNombre(gerenteActualizado.getNombre());
        gerente.setCorreo(gerenteActualizado.getCorreo());
        gerente.setEstado(gerenteActualizado.isEstado());
        
        if (gerenteActualizado.getClave() != null && !gerenteActualizado.getClave().isEmpty()) {
            gerente.setClave(gerenteActualizado.getClave());
        }
        
        return gerenteCursosRepository.save(gerente);
    }

    public Soporte actualizarSoporte(Long id, Soporte soporteActualizado) {
        validarPermisosAdministrador();
        
        Soporte soporte = soporteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Soporte no encontrado"));
        
        // Solo actualizar el nombreSoporte
        soporte.setNombreSoporte(soporteActualizado.getNombreSoporte());
        
        return soporteRepository.save(soporte);
    }

    public void eliminarAlumno(Long id) {
        validarPermisosAdministrador();
        alumnoRepository.deleteById(id);
    }

    public void eliminarGerenteCursos(Long id) {
        validarPermisosAdministrador();
        gerenteCursosRepository.deleteById(id);
    }

    public void eliminarSoporte(Long id) {
        validarPermisosAdministrador();
        soporteRepository.deleteById(id);
    }

    // Métodos privados de ayuda
    private void validarPermisosAdministrador() {
        if (usuarioAutenticadoId == null) {
            throw new RuntimeException("Usuario no autenticado");
        }
        
        Administrador administrador = administradorRepository.findById(usuarioAutenticadoId)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        if (!Rol.ADMINISTRADOR.equals(administrador.getRol())) {
            throw new RuntimeException("Acceso denegado. Solo los administradores pueden realizar esta operación.");
        }
        
        if (!administrador.isEstado()) {
            throw new RuntimeException("Usuario deshabilitado");
        }
    }


    public Administrador crearAdministrador(String correo, String clave, String nombre) {
        Administrador administrador = new Administrador();
        administrador.setCorreo(correo);
        administrador.setNombre(nombre);
        administrador.setClave(clave);
        administrador.setRol(Rol.ADMINISTRADOR);
        return administradorRepository.save(administrador);
    }
    
    public Administrador traerCualquierAdministrador(){
        return administradorRepository.findAll().get(0);
    }

    public boolean hayAdministradores() {
        return !administradorRepository.findAll().isEmpty();
    }

    // Métodos de conversión a DTOs
    private AlumnoListDTO convertirAAlumnoDTO(Alumno alumno) {
        return new AlumnoListDTO(
            alumno.getId(),
            alumno.getNombre(),
            alumno.getApellidos(),
            alumno.getCorreo(),
            alumno.isEstado(),
            alumno.getRol().toString()
        );
    }

    private GerenteCursosDTO convertirAGerenteDTO(GerenteCursos gerente) {
        return new GerenteCursosDTO(
            gerente.getNombre(),
            gerente.getCorreo(),
            gerente.getClave(),
            gerente.isEstado()
        );
    }

    private SoporteListDTO convertirASoporteDTO(Soporte soporte) {
        return new SoporteListDTO(
            soporte.getId(),
            soporte.getNombreSoporte()
        );
    }
} 