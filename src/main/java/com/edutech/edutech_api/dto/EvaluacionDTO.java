//Anais Llancapan-peitou1

package com.edutech.edutech_api.dto;

public class EvaluacionDTO {

    private Long id;
    private Long cursoId;
    private String titulo;
    private String descripcion;
    private String fechaPublicacion;

    public EvaluacionDTO() {
        this.cursoId = 0L;
        this.titulo = "";
        this.descripcion = "";
        this.fechaPublicacion = "";
    }

    public EvaluacionDTO(Long cursoId, String titulo, String descripcion, String fechaPublicacion) {
        this.cursoId = cursoId;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaPublicacion = fechaPublicacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCursoId() {
        return cursoId;
    }

    public void setCursoId(Long cursoId) {
        this.cursoId = cursoId;
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


