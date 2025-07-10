// Catalina Rosales->rataxikita
package com.edutech.edutech_api.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;  
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.edutech.edutech_api.model.Alumno;
import com.edutech.edutech_api.model.GerenteCursos;
import com.edutech.edutech_api.model.Soporte;
import com.edutech.edutech_api.model.Rol;
import com.edutech.edutech_api.service.AdministradorService;
import com.edutech.edutech_api.dto.GerenteCursosDTO;
import com.edutech.edutech_api.dto.AlumnoListDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
class AdministradorControllerTest {

    @Mock
    private AdministradorService administradorService;

    @InjectMocks
    private AdministradorController administradorController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    private Alumno alumno;
    private GerenteCursos gerente;
    private Soporte soporte;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(administradorController).build();
        objectMapper = new ObjectMapper();

        alumno = new Alumno();
        alumno.setId(1L);
        alumno.setNombre("Juan");
        alumno.setApellidos("Pérez");
        alumno.setCorreo("juan@test.com");
        alumno.setRol(Rol.ESTUDIANTE);
        alumno.setEstado(true);

        gerente = new GerenteCursos();
        gerente.setId(1L);
        gerente.setNombre("Carlos");
        gerente.setCorreo("carlos@test.com");
        gerente.setRol(Rol.GERENTE_CURSOS);
        gerente.setEstado(true);

        soporte = new Soporte();
        soporte.setId(1L);
        soporte.setNombreSoporte("Ana Soporte");
    }

    @Test
    void testCrearAlumno_Success() throws Exception {
        // Arrange
        Alumno alumnoCompleto = new Alumno();
        alumnoCompleto.setNombre("Juan");
        alumnoCompleto.setApellidos("Pérez");
        alumnoCompleto.setCorreo("juan@test.com");
        alumnoCompleto.setClave("password123");
        alumnoCompleto.setRol(Rol.ESTUDIANTE);
        alumnoCompleto.setEstado(true);

        when(administradorService.crearAlumno(any(Alumno.class))).thenReturn(alumno);

        // Act & Assert
        mockMvc.perform(post("/api/administrador/alumnos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(alumnoCompleto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Juan"))
                .andExpect(jsonPath("$.correo").value("juan@test.com"));

        verify(administradorService).crearAlumno(any(Alumno.class));
    }

    @Test
    void testListarAlumnos_Success() throws Exception {
        // Arrange
        AlumnoListDTO alumnoDTO = new AlumnoListDTO(1L, "Juan", "Pérez", "juan@test.com", true, "ESTUDIANTE");
        List<AlumnoListDTO> alumnos = Arrays.asList(alumnoDTO);
        when(administradorService.listarAlumnos()).thenReturn(alumnos);

        // Act & Assert
        mockMvc.perform(get("/api/administrador/alumnos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nombre").value("Juan"))
                .andExpect(jsonPath("$[0].correo").value("juan@test.com"));

        verify(administradorService).listarAlumnos();
    }

    @Test
    void testBuscarAlumnoPorId_Success() throws Exception {
        // Arrange
        when(administradorService.buscarAlumnoPorId(1L)).thenReturn(alumno);

        // Act & Assert
        mockMvc.perform(get("/api/administrador/alumnos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Juan"));

        verify(administradorService).buscarAlumnoPorId(1L);
    }

    @Test
    void testActualizarAlumno_Success() throws Exception {
        // Arrange
        Alumno alumnoActualizado = new Alumno();
        alumnoActualizado.setNombre("Juan Actualizado");
        alumnoActualizado.setApellidos("Pérez Actualizado");
        alumnoActualizado.setCorreo("juan.nuevo@test.com");
        alumnoActualizado.setClave("password123");
        alumnoActualizado.setRol(Rol.ESTUDIANTE);
        alumnoActualizado.setEstado(true);

        when(administradorService.actualizarAlumno(eq(1L), any(Alumno.class))).thenReturn(alumnoActualizado);

        // Act & Assert
        mockMvc.perform(put("/api/administrador/alumnos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(alumnoActualizado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Juan Actualizado"));

        verify(administradorService).actualizarAlumno(eq(1L), any(Alumno.class));
    }

    @Test
    void testCrearGerenteCursos_Success() throws Exception {
        // Arrange
        GerenteCursosDTO gerenteDTO = new GerenteCursosDTO();
        gerenteDTO.setNombre("Carlos");
        gerenteDTO.setCorreo("carlos@test.com");
        gerenteDTO.setClave("password123");
        gerenteDTO.setEstado(true);

        when(administradorService.crearGerenteCursos(any(GerenteCursos.class))).thenReturn(gerente);

        // Act & Assert
        mockMvc.perform(post("/api/administrador/gerentes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(gerenteDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Carlos"));

        verify(administradorService).crearGerenteCursos(any(GerenteCursos.class));
    }

    @Test
    void testCrearSoporte_Success() throws Exception {
        // Arrange
        when(administradorService.crearSoporte(any(Soporte.class))).thenReturn(soporte);

        // Act & Assert
        mockMvc.perform(post("/api/administrador/soporte")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(soporte)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombreSoporte").value("Ana Soporte"));

        verify(administradorService).crearSoporte(any(Soporte.class));
    }

    @Test
    void testActualizarSoporte_Success() throws Exception {
        // Arrange
        Soporte soporteActualizado = new Soporte();
        soporteActualizado.setNombreSoporte("Ana Actualizada");

        when(administradorService.actualizarSoporte(eq(1L), any(Soporte.class))).thenReturn(soporteActualizado);

        // Act & Assert
        mockMvc.perform(put("/api/administrador/soporte/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(soporteActualizado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreSoporte").value("Ana Actualizada"));

        verify(administradorService).actualizarSoporte(eq(1L), any(Soporte.class));
    }

    @Test
    void testEliminarAlumno_Success() throws Exception {
        // Act & Assert
        mockMvc.perform(delete("/api/administrador/alumnos/1"))
                .andExpect(status().isNoContent());

        verify(administradorService).eliminarAlumno(1L);
    }

    @Test
    void testEliminarGerenteCursos_Success() throws Exception {
        // Act & Assert
        mockMvc.perform(delete("/api/administrador/gerentes/1"))
                .andExpect(status().isNoContent());

        verify(administradorService).eliminarGerenteCursos(1L);
    }

    @Test
    void testEliminarSoporte_Success() throws Exception {
        // Act & Assert
        mockMvc.perform(delete("/api/administrador/soporte/1"))
                .andExpect(status().isNoContent());

        verify(administradorService).eliminarSoporte(1L);
    }

    @Test
    void testCrearAlumno_MissingRequiredFields() throws Exception {
        // Arrange
        Alumno alumnoIncompleto = new Alumno();
        alumnoIncompleto.setApellidos("Perez");
        alumnoIncompleto.setCorreo("juan@test.com");
        alumnoIncompleto.setClave("password123");
        // Nombre es nulo, lo que viola la validación @NotNull

        // Act & Assert
        mockMvc.perform(post("/api/administrador/alumnos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(alumnoIncompleto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testCrearAlumno_ServiceThrowsException() throws Exception {
        // Arrange
        when(administradorService.crearAlumno(any(Alumno.class))).thenThrow(new IllegalArgumentException("Error de prueba"));
        
        Alumno alumnoCompleto = new Alumno();
        alumnoCompleto.setNombre("Juan");
        alumnoCompleto.setApellidos("Pérez");
        alumnoCompleto.setCorreo("juan@test.com");
        alumnoCompleto.setClave("password123");
        alumnoCompleto.setRol(Rol.ESTUDIANTE);
        alumnoCompleto.setEstado(true);


        // Act & Assert
        mockMvc.perform(post("/api/administrador/alumnos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(alumnoCompleto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testCrearAlumno_InvalidEmail() throws Exception {
        // Arrange
        Alumno alumnoInvalido = new Alumno();
        alumnoInvalido.setNombre("Juan");
        alumnoInvalido.setCorreo("email-invalido"); // Email inválido
        alumnoInvalido.setClave("password123");

        // Act & Assert
        mockMvc.perform(post("/api/administrador/alumnos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(alumnoInvalido)))
                .andExpect(status().isBadRequest());
    }
} 