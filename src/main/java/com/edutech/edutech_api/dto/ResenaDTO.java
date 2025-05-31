//Anais Llancapan-peitou1

package com.edutech.edutech_api.dto;

public class ResenaDTO {
    private Long id;
    private String descripcion;
    private int calificacion;
    private String correoUsuario;
    private String siglaCurso;
    
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public int getCalificacion() {
        return calificacion;
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