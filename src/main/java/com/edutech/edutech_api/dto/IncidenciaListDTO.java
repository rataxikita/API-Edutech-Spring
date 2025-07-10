package com.edutech.edutech_api.dto;

public class IncidenciaListDTO {
    private Long id;
    private String titulo;
    private String descripcion;
    private String respuesta;
    private Long alumnoId;
    private Long soporteId;

    public IncidenciaListDTO() {}

    public IncidenciaListDTO(Long id, String titulo, String descripcion, String respuesta, Long alumnoId, Long soporteId) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.respuesta = respuesta;
        this.alumnoId = alumnoId;
        this.soporteId = soporteId;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getRespuesta() { return respuesta; }
    public void setRespuesta(String respuesta) { this.respuesta = respuesta; }

    public Long getAlumnoId() { return alumnoId; }
    public void setAlumnoId(Long alumnoId) { this.alumnoId = alumnoId; }

    public Long getSoporteId() { return soporteId; }
    public void setSoporteId(Long soporteId) { this.soporteId = soporteId; }
} 