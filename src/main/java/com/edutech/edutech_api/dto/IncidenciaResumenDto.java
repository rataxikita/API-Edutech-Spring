
package com.edutech.edutech_api.dto;

public class IncidenciaResumenDto {
    private Long id;
    private String titulo;
    private String descripcion;
    private String respuesta;
    private Long alumnoId;
    private String alumnoNombre;
    private String alumnoCorreo;
    private Long soporteId;
    private String soporteNombre;
    
    public IncidenciaResumenDto() {
    }

    public boolean isResuelta() {
        return respuesta != null && !respuesta.isBlank();
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

    public void setResuelta(boolean resuelta) {
    }

    public Long getAlumnoId() {
        return alumnoId;
    }

    public void setAlumnoId(Long alumnoId) {
        this.alumnoId = alumnoId;
    }

    public String getAlumnoNombre() {
        return alumnoNombre;
    }

    public void setAlumnoNombre(String alumnoNombre) {
        this.alumnoNombre = alumnoNombre;
    }

    public String getAlumnoCorreo() {
        return alumnoCorreo;
    }

    public void setAlumnoCorreo(String alumnoCorreo) {
        this.alumnoCorreo = alumnoCorreo;
    }

    public Long getSoporteId() {
        return soporteId;
    }

    public void setSoporteId(Long soporteId) {
        this.soporteId = soporteId;
    }

    public String getSoporteNombre() {
        return soporteNombre;
    }

    public void setSoporteNombre(String soporteNombre) {
        this.soporteNombre = soporteNombre;
    }

}
//Diego Sotelo G.