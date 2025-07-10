package com.edutech.edutech_api.dto;

public class ResenaListDTO {
    private Long id;
    private String comentario;
    private int calificacion;
    private String fecha;
    private Long alumnoId;
    private String cursoSigla;

    public ResenaListDTO() {}

    public ResenaListDTO(Long id, String comentario, int calificacion, String fecha, Long alumnoId, String cursoSigla) {
        this.id = id;
        this.comentario = comentario;
        this.calificacion = calificacion;
        this.fecha = fecha;
        this.alumnoId = alumnoId;
        this.cursoSigla = cursoSigla;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }

    public int getCalificacion() { return calificacion; }
    public void setCalificacion(int calificacion) { this.calificacion = calificacion; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }

    public Long getAlumnoId() { return alumnoId; }
    public void setAlumnoId(Long alumnoId) { this.alumnoId = alumnoId; }

    public String getCursoSigla() { return cursoSigla; }
    public void setCursoSigla(String cursoSigla) { this.cursoSigla = cursoSigla; }
} 