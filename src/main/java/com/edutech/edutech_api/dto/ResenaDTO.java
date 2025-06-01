//Anais Llancapan-peitou1

package com.edutech.edutech_api.dto;

public class ResenaDTO {
    private Long id;
    private String contenido;
    private int calificacion;
    private String correoUsuario;
    private String siglaCurso;
    
    public int getCalificacion() {
        return calificacion;
    }
    public String getContenido() {
        return contenido;
    }
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }
    public String getCorreoUsuario() {
        return correoUsuario;
    }
    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }
    public String getSiglaCurso() {
        return siglaCurso;
    }
    public void setSiglaCurso(String siglaCurso) {
        this.siglaCurso = siglaCurso;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    
}