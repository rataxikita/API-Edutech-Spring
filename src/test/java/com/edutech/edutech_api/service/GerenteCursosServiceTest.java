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

import com.edutech.edutech_api.model.Curso;
import com.edutech.edutech_api.model.Instructor;
import com.edutech.edutech_api.model.GerenteCursos;
import com.edutech.edutech_api.model.Rol;
import com.edutech.edutech_api.model.UsuarioCurso;
import com.edutech.edutech_api.repository.CursoRepository;
import com.edutech.edutech_api.repository.InstructorRepository;
import com.edutech.edutech_api.repository.GerenteCursosRepository;

@ExtendWith(MockitoExtension.class)
public class GerenteCursosServiceTest {

    /*crea las maquetas de los repositorios
    */
    @Mock
    private CursoRepository cursoRepository;

    @Mock
    private InstructorRepository instructorRepository;

    @Mock
    private GerenteCursosRepository gerenteCursosRepository;

    @InjectMocks
    private GerenteCursosService gerenteCursosService;

    private GerenteCursos gerente;
    private Curso curso;
    private Instructor instructor;

    @BeforeEach
    void setUp() {
        gerente = new GerenteCursos();
        gerente.setId(1L);
        gerente.setNombre("Gerente Test");
        gerente.setCorreo("gerente@test.com");
        gerente.setRol(Rol.GERENTE_CURSOS);
        gerente.setEstado(true);

        curso = new Curso();
        curso.setSigla("MAT101");
        curso.setNombre("Matemáticas I");
        curso.setDescripcion("Curso de matemáticas básicas");
        curso.setEstado(true);
        curso.setValor(150000.0);

        instructor = new Instructor();
        instructor.setId(1L);
        instructor.setNombre("Juan");
        instructor.setApellido("Pérez");
        instructor.setCorreo("juan@test.com");
        instructor.setRut("12345678-9");
        instructor.setRol(Rol.INSTRUCTOR);
        instructor.setEstado(true);
    }

    @Test
    void testCrearCurso_Success() {
        // Arrange
        when(gerenteCursosRepository.findById(1L)).thenReturn(Optional.of(gerente));
        when(cursoRepository.save(any(Curso.class))).thenReturn(curso);

        // Act
        gerenteCursosService.setUsuarioAutenticado(1L);
        Curso resultado = gerenteCursosService.crearCurso(curso);

        // Assert
        assertNotNull(resultado);
        assertEquals("MAT101", resultado.getSigla());
        verify(gerenteCursosRepository).findById(1L);
        verify(cursoRepository).save(curso);
    }

    @Test
    void testCrearCurso_SinPermisos() {
        // Arrange
        gerente.setRol(Rol.ESTUDIANTE);
        when(gerenteCursosRepository.findById(1L)).thenReturn(Optional.of(gerente));

        // Act & Assert
        gerenteCursosService.setUsuarioAutenticado(1L);
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            gerenteCursosService.crearCurso(curso);
        });

        assertEquals("Acceso denegado. Solo los gerentes de cursos pueden realizar esta operación.", exception.getMessage());
        verify(gerenteCursosRepository).findById(1L);
        verify(cursoRepository, never()).save(any(Curso.class));
    }

    @Test
    void testActualizarCurso_Success() {
        // Arrange
        Curso cursoActualizado = new Curso();
        cursoActualizado.setNombre("Matemáticas I Actualizado");
        cursoActualizado.setDescripcion("Descripción actualizada");
        cursoActualizado.setEstado(false);
        cursoActualizado.setValor(200000.0);

        when(gerenteCursosRepository.findById(1L)).thenReturn(Optional.of(gerente));
        when(cursoRepository.findById("MAT101")).thenReturn(Optional.of(curso));
        when(cursoRepository.save(any(Curso.class))).thenReturn(cursoActualizado);

        // Act
        gerenteCursosService.setUsuarioAutenticado(1L);
        Curso resultado = gerenteCursosService.actualizarCurso("MAT101", cursoActualizado);

        // Assert
        assertNotNull(resultado);
        assertEquals("Matemáticas I Actualizado", resultado.getNombre());
        verify(gerenteCursosRepository).findById(1L);
        verify(cursoRepository).findById("MAT101");
        verify(cursoRepository).save(any(Curso.class));
    }

    @Test
    void testEliminarCurso_Success() {
        // Arrange
        when(gerenteCursosRepository.findById(1L)).thenReturn(Optional.of(gerente));
        when(cursoRepository.existsById("MAT101")).thenReturn(true);

        // Act
        gerenteCursosService.setUsuarioAutenticado(1L);
        gerenteCursosService.eliminarCurso("MAT101");

        // Assert
        verify(gerenteCursosRepository).findById(1L);
        verify(cursoRepository).existsById("MAT101");
        verify(cursoRepository).deleteById("MAT101");
    }

    @Test
    void testCrearInstructor_Success() {
        // Arrange
        when(gerenteCursosRepository.findById(1L)).thenReturn(Optional.of(gerente));
        when(instructorRepository.findByRut(anyString())).thenReturn(null);
        when(instructorRepository.findByCorreo(anyString())).thenReturn(null);
        when(instructorRepository.save(any(Instructor.class))).thenReturn(instructor);

        // Act
        gerenteCursosService.setUsuarioAutenticado(1L);
        Instructor resultado = gerenteCursosService.crearInstructor(instructor);

        // Assert
        assertNotNull(resultado);
        assertEquals(Rol.INSTRUCTOR, resultado.getRol());
        assertTrue(resultado.isEstado());
        verify(gerenteCursosRepository).findById(1L);
        verify(instructorRepository).findByRut(instructor.getRut());
        verify(instructorRepository).findByCorreo(instructor.getCorreo());
        verify(instructorRepository).save(instructor);
    }

    @Test
    void testCrearInstructor_RutDuplicado() {
        // Arrange
        when(gerenteCursosRepository.findById(1L)).thenReturn(Optional.of(gerente));
        when(instructorRepository.findByRut(anyString())).thenReturn(instructor);

        // Act & Assert
        gerenteCursosService.setUsuarioAutenticado(1L);
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            gerenteCursosService.crearInstructor(instructor);
        });

        assertEquals("Ya existe un instructor con el RUT: " + instructor.getRut(), exception.getMessage());
        verify(gerenteCursosRepository).findById(1L);
        verify(instructorRepository).findByRut(instructor.getRut());
        verify(instructorRepository, never()).save(any(Instructor.class));
    }

    @Test
    void testAsignarInstructor_Success() {
        // Arrange
        when(gerenteCursosRepository.findById(1L)).thenReturn(Optional.of(gerente));
        when(cursoRepository.findById("MAT101")).thenReturn(Optional.of(curso));
        when(instructorRepository.findById(1L)).thenReturn(Optional.of(instructor));
        when(cursoRepository.save(any(Curso.class))).thenReturn(curso);

        // Act
        gerenteCursosService.setUsuarioAutenticado(1L);
        Instructor resultado = gerenteCursosService.asignarInstructor("MAT101", 1L);

        // Assert
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        verify(gerenteCursosRepository).findById(1L);
        verify(cursoRepository).findById("MAT101");
        verify(instructorRepository).findById(1L);
        verify(cursoRepository).save(any(Curso.class));
    }

    @Test
    void testGenerarReporteInscripciones_Success() {
        // Arrange
        List<UsuarioCurso> inscritos = Arrays.asList(new UsuarioCurso(), new UsuarioCurso());
        curso.setInscritos(inscritos);
        
        when(gerenteCursosRepository.findById(1L)).thenReturn(Optional.of(gerente));
        when(cursoRepository.findById("MAT101")).thenReturn(Optional.of(curso));

        // Act
        gerenteCursosService.setUsuarioAutenticado(1L);
        var resultado = gerenteCursosService.generarReporteInscripciones("MAT101");

        // Assert
        assertNotNull(resultado);
        assertEquals("MAT101", resultado.get("sigla"));
        assertEquals("Matemáticas I", resultado.get("nombreCurso"));
        assertEquals(2, resultado.get("totalInscritos"));
        assertNotNull(resultado.get("fechaGeneracion"));
        verify(gerenteCursosRepository).findById(1L);
        verify(cursoRepository).findById("MAT101");
    }

    @Test
    void testAprobarContenido_Success() {
        // Arrange
        when(gerenteCursosRepository.findById(1L)).thenReturn(Optional.of(gerente));
        when(cursoRepository.findById("MAT101")).thenReturn(Optional.of(curso));
        when(cursoRepository.save(any(Curso.class))).thenReturn(curso);

        // Act
        gerenteCursosService.setUsuarioAutenticado(1L);
        gerenteCursosService.aprobarContenido("MAT101");

        // Assert
        verify(gerenteCursosRepository).findById(1L);
        verify(cursoRepository).findById("MAT101");
        verify(cursoRepository).save(any(Curso.class));
    }

    @Test
    void testRechazarContenido_Success() {
        // Arrange
        when(gerenteCursosRepository.findById(1L)).thenReturn(Optional.of(gerente));
        when(cursoRepository.findById("MAT101")).thenReturn(Optional.of(curso));
        when(cursoRepository.save(any(Curso.class))).thenReturn(curso);

        // Act
        gerenteCursosService.setUsuarioAutenticado(1L);
        gerenteCursosService.rechazarContenido("MAT101", "Motivo de rechazo");

        // Assert
        verify(gerenteCursosRepository).findById(1L);
        verify(cursoRepository).findById("MAT101");
        verify(cursoRepository).save(any(Curso.class));
    }
} 