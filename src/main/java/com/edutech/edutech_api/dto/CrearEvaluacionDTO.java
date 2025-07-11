package com.edutech.edutech_api.dto;

public class CrearEvaluacionDTO {
    private String titulo;
    private String descripcion;
    private String fechaPublicacion;
    private String cursoSigla;
    private Integer puntajeMaximo;

    public CrearEvaluacionDTO() {
    }

    public CrearEvaluacionDTO(String titulo, String descripcion, String fechaPublicacion, String cursoSigla, Integer puntajeMaximo) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaPublicacion = fechaPublicacion;
        this.cursoSigla = cursoSigla;
        this.puntajeMaximo = puntajeMaximo;
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

    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getCursoSigla() {
        return cursoSigla;
    }

    public void setCursoSigla(String cursoSigla) {
        this.cursoSigla = cursoSigla;
    }

    public Integer getPuntajeMaximo() {
        return puntajeMaximo;
    }

    public void setPuntajeMaximo(Integer puntajeMaximo) {
        this.puntajeMaximo = puntajeMaximo;
    }
} 