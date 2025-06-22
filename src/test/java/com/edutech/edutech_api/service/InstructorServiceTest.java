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

import com.edutech.edutech_api.model.Instructor;
import com.edutech.edutech_api.model.Curso;
import com.edutech.edutech_api.model.Evaluacion;
import com.edutech.edutech_api.model.Contenido;
import com.edutech.edutech_api.model.Pregunta;
import com.edutech.edutech_api.repository.InstructorRepository;
import com.edutech.edutech_api.repository.CursoRepository;
import com.edutech.edutech_api.repository.EvaluacionRepository;
import com.edutech.edutech_api.repository.ContenidoRepository;
import com.edutech.edutech_api.repository.PreguntaRepository;

@ExtendWith(MockitoExtension.class)
class InstructorServiceTest {

    @Mock
    private InstructorRepository instructorRepository;

    @Mock
    private CursoRepository cursoRepository;

    @Mock
    private EvaluacionRepository evaluacionRepository;

    @Mock
    private ContenidoRepository contenidoRepository;

    @Mock
    private PreguntaRepository preguntaRepository;

    @InjectMocks
    private InstructorService instructorService;

    private Instructor instructor;
    private Curso curso;
    private Evaluacion evaluacion;
    private Contenido contenido;
    private Pregunta pregunta;

    @BeforeEach
    void setUp() {
        instructor = new Instructor();
        instructor.setId(1L);
        instructor.setNombre("Juan");
        instructor.setApellido("Pérez");
        instructor.setCorreo("juan@test.com");
        instructor.setRut("12345678-9");

        curso = new Curso();
        curso.setSigla("MAT101");
        curso.setNombre("Matemáticas I");
        curso.setInstructor(instructor);

        evaluacion = new Evaluacion();
        evaluacion.setId(1L);
        evaluacion.setTitulo("Evaluación 1");
        evaluacion.setDescripcion("Primera evaluación del curso");
        evaluacion.setCurso(curso);

        contenido = new Contenido();
        contenido.setId(1L);
        contenido.setTitulo("Introducción a las Matemáticas");
        contenido.setDescripcion("Conceptos básicos");
        contenido.setMaterial("PDF de introducción");
        contenido.setCurso(curso);

        pregunta = new Pregunta();
        pregunta.setId(1L);
        pregunta.setContenido("¿Cómo resolver ecuaciones?");
        pregunta.setCurso(curso);
        pregunta.setInstructor(instructor);
    }

    @Test
    void testCrearInstructor_Success() {
        // Arrange
        when(instructorRepository.save(any(Instructor.class))).thenReturn(instructor);

        // Act
        Instructor resultado = instructorService.crearInstructor(instructor);

        // Assert
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Juan", resultado.getNombre());
        verify(instructorRepository).save(instructor);
    }

    @Test
    void testObtenerTodos_Success() {
        // Arrange
        List<Instructor> instructores = Arrays.asList(instructor);
        when(instructorRepository.findAll()).thenReturn(instructores);

        // Act
        List<Instructor> resultado = instructorService.obtenerTodos();

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(instructor, resultado.get(0));
        verify(instructorRepository).findAll();
    }

    @Test
    void testObtenerPorId_Success() {
        // Arrange
        when(instructorRepository.findById(1L)).thenReturn(Optional.of(instructor));

        // Act
        Instructor resultado = instructorService.obtenerPorId(1L);

        // Assert
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        verify(instructorRepository).findById(1L);
    }

    @Test
    void testObtenerPorId_NotFound() {
        // Arrange
        when(instructorRepository.findById(999L)).thenReturn(Optional.empty());

        // Act
        Instructor resultado = instructorService.obtenerPorId(999L);

        // Assert
        assertNull(resultado);
        verify(instructorRepository).findById(999L);
    }

    @Test
    void testEliminarInstructor_Success() {
        // Act
        instructorService.eliminarInstructor(1L);

        // Assert
        verify(instructorRepository).deleteById(1L);
    }

    @Test
    void testCrearContenido_Success() {
        // Arrange
        when(cursoRepository.findBySigla("MAT101")).thenReturn(Optional.of(curso));
        when(contenidoRepository.save(any(Contenido.class))).thenReturn(contenido);

        // Act
        Contenido resultado = instructorService.crearContenido("MAT101", contenido);

        // Assert
        assertNotNull(resultado);
        assertEquals("Introducción a las Matemáticas", resultado.getTitulo());
        verify(cursoRepository).findBySigla("MAT101");
        verify(contenidoRepository).save(contenido);
    }

    @Test
    void testActualizarContenido_Success() {
        // Arrange
        Contenido contenidoActualizado = new Contenido();
        contenidoActualizado.setTitulo("Título Actualizado");
        contenidoActualizado.setDescripcion("Descripción actualizada");
        contenidoActualizado.setMaterial("Material actualizado");

        when(contenidoRepository.findById(1L)).thenReturn(Optional.of(contenido));
        when(contenidoRepository.save(any(Contenido.class))).thenReturn(contenidoActualizado);

        // Act
        Contenido resultado = instructorService.actualizarContenido("MAT101", 1L, contenidoActualizado);

        // Assert
        assertNotNull(resultado);
        assertEquals("Título Actualizado", resultado.getTitulo());
        verify(contenidoRepository).findById(1L);
        verify(contenidoRepository).save(any(Contenido.class));
    }

    @Test
    void testCrearEvaluacion_Success() {
        // Arrange
        when(cursoRepository.findBySigla("MAT101")).thenReturn(Optional.of(curso));
        when(evaluacionRepository.save(any(Evaluacion.class))).thenReturn(evaluacion);

        // Act
        Evaluacion resultado = instructorService.crearEvaluacion("MAT101", evaluacion);

        // Assert
        assertNotNull(resultado);
        assertEquals(curso, resultado.getCurso());
        verify(cursoRepository).findBySigla("MAT101");
        verify(evaluacionRepository).save(evaluacion);
    }

    @Test
    void testActualizarEvaluacion_Success() {
        // Arrange
        Evaluacion evaluacionActualizada = new Evaluacion();
        evaluacionActualizada.setTitulo("Evaluación Actualizada");
        evaluacionActualizada.setDescripcion("Descripción actualizada");

        when(evaluacionRepository.findById(1L)).thenReturn(Optional.of(evaluacion));
        when(evaluacionRepository.save(any(Evaluacion.class))).thenReturn(evaluacionActualizada);

        // Act
        Evaluacion resultado = instructorService.actualizarEvaluacion("MAT101", 1L, evaluacionActualizada);

        // Assert
        assertNotNull(resultado);
        assertEquals("Evaluación Actualizada", resultado.getTitulo());
        verify(evaluacionRepository).findById(1L);
        verify(evaluacionRepository).save(any(Evaluacion.class));
    }

    @Test
    void testResponderPregunta_Success() {
        // Arrange
        when(preguntaRepository.findById(1L)).thenReturn(Optional.of(pregunta));
        when(preguntaRepository.save(any(Pregunta.class))).thenReturn(pregunta);

        // Act
        Pregunta resultado = instructorService.responderPregunta(1L, "Respuesta del instructor");

        // Assert
        assertNotNull(resultado);
        assertEquals("Respuesta del instructor", resultado.getRespuesta());
        verify(preguntaRepository).findById(1L);
        verify(preguntaRepository).save(pregunta);
    }
} 