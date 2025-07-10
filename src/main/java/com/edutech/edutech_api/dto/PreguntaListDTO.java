package com.edutech.edutech_api.dto;

public class PreguntaListDTO {
    private Long id;
    private String contenido;
    private String fecha;
    private String respuesta;
    private String fechaRespuesta;
    private Long alumnoId;
    private Long instructorId;
    private String cursoSigla;

    public PreguntaListDTO() {}

    public PreguntaListDTO(Long id, String contenido, String fecha, String respuesta, String fechaRespuesta, 
                          Long alumnoId, Long instructorId, String cursoSigla) {
        this.id = id;
        this.contenido = contenido;
        this.fecha = fecha;
        this.respuesta = respuesta;
        this.fechaRespuesta = fechaRespuesta;
        this.alumnoId = alumnoId;
        this.instructorId = instructorId;
        this.cursoSigla = cursoSigla;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getContenido() { return contenido; }
    public void setContenido(String contenido) { this.contenido = contenido; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }

    public String getRespuesta() { return respuesta; }
    public void setRespuesta(String respuesta) { this.respuesta = respuesta; }

    public String getFechaRespuesta() { return fechaRespuesta; }
    public void setFechaRespuesta(String fechaRespuesta) { this.fechaRespuesta = fechaRespuesta; }

    public Long getAlumnoId() { return alumnoId; }
    public void setAlumnoId(Long alumnoId) { this.alumnoId = alumnoId; }

    public Long getInstructorId() { return instructorId; }
    public void setInstructorId(Long instructorId) { this.instructorId = instructorId; }

    public String getCursoSigla() { return cursoSigla; }
    public void setCursoSigla(String cursoSigla) { this.cursoSigla = cursoSigla; }
} 