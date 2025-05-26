//Anais Llancapan-peitou1

package com.edutech.edutech_api.dto;

public class EvaluacionDTO {


    private String cursoSigla;
    private String titulo;
    private String descripcion;
    private String fechaPublicacion;


    public EvaluacionDTO() {
        this.cursoSigla = "";
        this.titulo = "";
        this.descripcion = "";
        this.fechaPublicacion ="";
    }

    public EvaluacionDTO(String cursoSigla, String titulo, String descripcion,String fechaPublicacion) {
        this.cursoSigla = cursoSigla;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getCursoSigla() {
        return cursoSigla;
    }

    public void setCursoSigla(String cursoSigla) {
        this.cursoSigla = cursoSigla;
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

}


