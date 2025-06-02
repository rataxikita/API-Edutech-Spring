package com.edutech.edutech_api.dto;


public class IncidenciaDetalleDto {
        private Long id;
    private String titulo;
    private String descripcion;
    private String respuesta;
    private String alumnoCorreo;
    private String soporteCorreo;
    private Long alumnoId;
    private Long soporteId;
    
    public IncidenciaDetalleDto() {
    }

    public IncidenciaDetalleDto(Long id, String titulo, String descripcion, String respuesta, String alumnoCorreo,
            String soporteCorreo, Long alumnoId, Long soporteId) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.respuesta = respuesta;
        this.alumnoCorreo = alumnoCorreo;
        this.soporteCorreo = soporteCorreo;
        this.alumnoId = alumnoId;
        this.soporteId = soporteId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getAlumnoCorreo() {
        return alumnoCorreo;
    }

    public void setAlumnoCorreo(String alumnoCorreo) {
        this.alumnoCorreo = alumnoCorreo;
    }

    public String getSoporteCorreo() {
        return soporteCorreo;
    }

    public void setSoporteCorreo(String soporteCorreo) {
        this.soporteCorreo = soporteCorreo;
    }

    public Long getAlumnoId() {
        return alumnoId;
    }

    public void setAlumnoId(Long alumnoId) {
        this.alumnoId = alumnoId;
    }

    public Long getSoporteId() {
        return soporteId;
    }

    public void setSoporteId(Long soporteId) {
        this.soporteId = soporteId;
    }


}
