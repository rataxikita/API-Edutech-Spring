// Catalina Rosales->rataxikita
package com.edutech.edutech_api.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InstructorDTOTest {

    private InstructorDTO instructorDTO;

    @BeforeEach
    void setUp() {
        instructorDTO = new InstructorDTO();
    }

    @Test
    void testConstructorVacio() {
        // Act & Assert
        assertNotNull(instructorDTO);
        assertNull(instructorDTO.getNombre());
        assertNull(instructorDTO.getApellido());
        assertNull(instructorDTO.getCorreo());
        assertNull(instructorDTO.getClave());
        assertNull(instructorDTO.getRut());
        assertTrue(instructorDTO.isEstado());
    }

    @Test
    void testConstructorConParametros() {
        // Arrange
        String nombre = "Juan";
        String apellido = "Pérez";
        String correo = "juan@test.com";
        String clave = "123456";
        String rut = "12345678-9";

        // Act
        InstructorDTO dto = new InstructorDTO(nombre, apellido, correo, clave, rut);

        // Assert
        assertEquals(nombre, dto.getNombre());
        assertEquals(apellido, dto.getApellido());
        assertEquals(correo, dto.getCorreo());
        assertEquals(clave, dto.getClave());
        assertEquals(rut, dto.getRut());
        assertTrue(dto.isEstado());
    }

    @Test
    void testSettersYGetters() {
        // Arrange
        String nombre = "María";
        String apellido = "González";
        String correo = "maria@test.com";
        String clave = "654321";
        String rut = "87654321-0";
        boolean estado = false;

        // Act
        instructorDTO.setNombre(nombre);
        instructorDTO.setApellido(apellido);
        instructorDTO.setCorreo(correo);
        instructorDTO.setClave(clave);
        instructorDTO.setRut(rut);
        instructorDTO.setEstado(estado);

        // Assert
        assertEquals(nombre, instructorDTO.getNombre());
        assertEquals(apellido, instructorDTO.getApellido());
        assertEquals(correo, instructorDTO.getCorreo());
        assertEquals(clave, instructorDTO.getClave());
        assertEquals(rut, instructorDTO.getRut());
        assertEquals(estado, instructorDTO.isEstado());
    }

    @Test
    void testEstadoPorDefecto() {
        // Act & Assert
        assertTrue(instructorDTO.isEstado());
    }

    @Test
    void testCambiarEstado() {
        // Act
        instructorDTO.setEstado(false);

        // Assert
        assertFalse(instructorDTO.isEstado());
    }

    @Test
    void testDatosCompletos() {
        // Arrange
        instructorDTO.setNombre("Carlos");
        instructorDTO.setApellido("Rodríguez");
        instructorDTO.setCorreo("carlos@test.com");
        instructorDTO.setClave("password123");
        instructorDTO.setRut("11111111-1");
        instructorDTO.setEstado(true);

        // Assert
        assertEquals("Carlos", instructorDTO.getNombre());
        assertEquals("Rodríguez", instructorDTO.getApellido());
        assertEquals("carlos@test.com", instructorDTO.getCorreo());
        assertEquals("password123", instructorDTO.getClave());
        assertEquals("11111111-1", instructorDTO.getRut());
        assertTrue(instructorDTO.isEstado());
    }
} 