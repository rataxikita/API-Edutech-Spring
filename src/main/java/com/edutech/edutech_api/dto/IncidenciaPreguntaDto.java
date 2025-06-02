package com.edutech.edutech_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class IncidenciaPreguntaDto {
    @NotNull(message = "El ID del alumno es obligatorio")
    private Long alumnoId;
    @NotBlank(message = "El título no puede estar vacío")
    private String titulo;
    @NotBlank(message = "La descripción no puede estar vacía")
    private String descripcion;
    
    public IncidenciaPreguntaDto() {
    }

    public IncidenciaPreguntaDto(Long alumnoId, String titulo, String descripcion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.alumnoId = alumnoId;
    }

    public Long getAlumnoId() {
        return alumnoId;
    }

    public void setAlumnoId(Long alumnoId) {
        this.alumnoId = alumnoId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
//Diego Sotelo G.