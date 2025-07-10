package com.edutech.edutech_api.dto;

public class EvaluacionListDTO {
    private Long id;
    private String titulo;
    private String descripcion;
    private String fechaPublicacion;
    private String cursoSigla;

    public EvaluacionListDTO() {}

    public EvaluacionListDTO(Long id, String titulo, String descripcion, String fechaPublicacion, String cursoSigla) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaPublicacion = fechaPublicacion;
        this.cursoSigla = cursoSigla;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getFechaPublicacion() { return fechaPublicacion; }
    public void setFechaPublicacion(String fechaPublicacion) { this.fechaPublicacion = fechaPublicacion; }

    public String getCursoSigla() { return cursoSigla; }
    public void setCursoSigla(String cursoSigla) { this.cursoSigla = cursoSigla; }
} 