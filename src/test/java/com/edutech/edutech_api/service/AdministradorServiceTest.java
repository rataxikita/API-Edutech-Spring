// Catalina Rosales->rataxikita
package com.edutech.edutech_api.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.edutech.edutech_api.model.Administrador;
import com.edutech.edutech_api.model.Alumno;
import com.edutech.edutech_api.model.GerenteCursos;
import com.edutech.edutech_api.model.Soporte;
import com.edutech.edutech_api.model.Rol;
import com.edutech.edutech_api.repository.AdministradorRepository;
import com.edutech.edutech_api.repository.AlumnoRepository;
import com.edutech.edutech_api.repository.GerenteCursosRepository;
import com.edutech.edutech_api.repository.SoporteRepository;

@ExtendWith(MockitoExtension.class)
class AdministradorServiceTest {

    @Mock
    private AdministradorRepository administradorRepository;

    @Mock
    private AlumnoRepository alumnoRepository;

    @Mock
    private GerenteCursosRepository gerenteCursosRepository;

    @Mock
    private SoporteRepository soporteRepository;

    @InjectMocks
    private AdministradorService administradorService;

    private Administrador administrador;
    private Alumno alumno;
    private GerenteCursos gerente;
    private Soporte soporte;

    @BeforeEach
    void setUp() {
        administrador = new Administrador();
        administrador.setId(1L);
        administrador.setNombre("Admin Test");
        administrador.setCorreo("admin@test.com");
        administrador.setRol(Rol.ADMINISTRADOR);
        administrador.setEstado(true);

        alumno = new Alumno();
        alumno.setId(1L);
        alumno.setNombre("Juan");
        alumno.setApellidos("Pérez");
        alumno.setCorreo("juan@test.com");
        alumno.setClave("123456");
        alumno.setRol(Rol.ESTUDIANTE);
        alumno.setEstado(true);

        gerente = new GerenteCursos();
        gerente.setId(1L);
        gerente.setNombre("Carlos");
        gerente.setCorreo("carlos@test.com");
        gerente.setClave("123456");
        gerente.setRol(Rol.GERENTE_CURSOS);
        gerente.setEstado(true);

        soporte = new Soporte();
        soporte.setId(1L);
        soporte.setNombreSoporte("Ana Soporte");
    }

    @Test
    void testCrearAlumno_Success() {
        // Arrange
        when(administradorRepository.findById(1L)).thenReturn(Optional.of(administrador));
        when(alumnoRepository.findByCorreo(anyString())).thenReturn(null);
        when(alumnoRepository.save(any(Alumno.class))).thenReturn(alumno);

        // Act
        administradorService.setUsuarioAutenticado(1L);
        Alumno resultado = administradorService.crearAlumno(alumno);

        // Assert
        assertNotNull(resultado);
        assertEquals(Rol.ESTUDIANTE, resultado.getRol());
        assertTrue(resultado.isEstado());
        verify(alumnoRepository).findByCorreo(alumno.getCorreo());
        verify(alumnoRepository).save(alumno);
    }

    @Test
    void testCrearAlumno_CorreoDuplicado() {
        // Arrange
        when(administradorRepository.findById(1L)).thenReturn(Optional.of(administrador));
        when(alumnoRepository.findByCorreo(anyString())).thenReturn(alumno);

        // Act & Assert
        administradorService.setUsuarioAutenticado(1L);
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            administradorService.crearAlumno(alumno);
        });

        assertEquals("Ya existe un alumno con el correo: " + alumno.getCorreo(), exception.getMessage());
        verify(alumnoRepository).findByCorreo(alumno.getCorreo());
        verify(alumnoRepository, never()).save(any(Alumno.class));
    }

    @Test
    void testCrearGerenteCursos_Success() {
        // Arrange
        when(administradorRepository.findById(1L)).thenReturn(Optional.of(administrador));
        when(gerenteCursosRepository.findByCorreo(anyString())).thenReturn(null);
        when(gerenteCursosRepository.save(any(GerenteCursos.class))).thenReturn(gerente);

        // Act
        administradorService.setUsuarioAutenticado(1L);
        GerenteCursos resultado = administradorService.crearGerenteCursos(gerente);

        // Assert
        assertNotNull(resultado);
        assertEquals(Rol.GERENTE_CURSOS, resultado.getRol());
        assertTrue(resultado.isEstado());
        verify(gerenteCursosRepository).findByCorreo(gerente.getCorreo());
        verify(gerenteCursosRepository).save(gerente);
    }

    @Test
    void testCrearSoporte_Success() {
        // Arrange
        when(administradorRepository.findById(1L)).thenReturn(Optional.of(administrador));
        when(soporteRepository.save(any(Soporte.class))).thenReturn(soporte);

        // Act
        administradorService.setUsuarioAutenticado(1L);
        Soporte resultado = administradorService.crearSoporte(soporte);

        // Assert
        assertNotNull(resultado);
        assertEquals("Ana Soporte", resultado.getNombreSoporte());
        verify(soporteRepository).save(soporte);
    }

    @Test
    void testListarAlumnos_Success() {
        // Arrange
        List<Alumno> alumnos = Arrays.asList(alumno);
        when(administradorRepository.findById(1L)).thenReturn(Optional.of(administrador));
        when(alumnoRepository.findAll()).thenReturn(alumnos);

        // Act
        administradorService.setUsuarioAutenticado(1L);
        List<Alumno> resultado = administradorService.listarAlumnos();

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        verify(administradorRepository).findById(1L);
        verify(alumnoRepository).findAll();
    }

    @Test
    void testListarAlumnos_SinPermisos() {
        // Arrange
        administrador.setRol(Rol.ESTUDIANTE);
        when(administradorRepository.findById(1L)).thenReturn(Optional.of(administrador));

        // Act & Assert
        administradorService.setUsuarioAutenticado(1L);
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            administradorService.listarAlumnos();
        });

        assertEquals("Acceso denegado. Solo los administradores pueden realizar esta operación.", exception.getMessage());
        verify(administradorRepository).findById(1L);
        verify(alumnoRepository, never()).findAll();
    }

    @Test
    void testBuscarAlumnoPorId_Success() {
        // Arrange
        when(administradorRepository.findById(1L)).thenReturn(Optional.of(administrador));
        when(alumnoRepository.findById(1L)).thenReturn(Optional.of(alumno));

        // Act
        administradorService.setUsuarioAutenticado(1L);
        Alumno resultado = administradorService.buscarAlumnoPorId(1L);

        // Assert
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        verify(administradorRepository).findById(1L);
        verify(alumnoRepository).findById(1L);
    }

    @Test
    void testActualizarAlumno_Success() {
        // Arrange
        Alumno alumnoActualizado = new Alumno();
        alumnoActualizado.setNombre("Juan Actualizado");
        alumnoActualizado.setCorreo("juan.nuevo@test.com");
        alumnoActualizado.setClave("654321");

        when(administradorRepository.findById(1L)).thenReturn(Optional.of(administrador));
        when(alumnoRepository.findById(1L)).thenReturn(Optional.of(alumno));
        when(alumnoRepository.findByCorreo(anyString())).thenReturn(null);
        when(alumnoRepository.save(any(Alumno.class))).thenReturn(alumnoActualizado);

        // Act
        administradorService.setUsuarioAutenticado(1L);
        Alumno resultado = administradorService.actualizarAlumno(1L, alumnoActualizado);

        // Assert
        assertNotNull(resultado);
        verify(administradorRepository).findById(1L);
        verify(alumnoRepository).findById(1L);
        verify(alumnoRepository).findByCorreo(alumnoActualizado.getCorreo());
        verify(alumnoRepository).save(any(Alumno.class));
    }

    @Test
    void testActualizarSoporte_Success() {
        // Arrange
        Soporte soporteActualizado = new Soporte();
        soporteActualizado.setNombreSoporte("Ana Actualizada");

        when(administradorRepository.findById(1L)).thenReturn(Optional.of(administrador));
        when(soporteRepository.findById(1L)).thenReturn(Optional.of(soporte));
        when(soporteRepository.save(any(Soporte.class))).thenReturn(soporteActualizado);

        // Act
        administradorService.setUsuarioAutenticado(1L);
        Soporte resultado = administradorService.actualizarSoporte(1L, soporteActualizado);

        // Assert
        assertNotNull(resultado);
        assertEquals("Ana Actualizada", resultado.getNombreSoporte());
        verify(administradorRepository).findById(1L);
        verify(soporteRepository).findById(1L);
        verify(soporteRepository).save(any(Soporte.class));
    }

    @Test
    void testValidarPermisosAdministrador_UsuarioNoAutenticado() {
        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            administradorService.listarAlumnos();
        });

        assertEquals("Usuario no autenticado", exception.getMessage());
        verify(administradorRepository, never()).findById(any());
    }
} 