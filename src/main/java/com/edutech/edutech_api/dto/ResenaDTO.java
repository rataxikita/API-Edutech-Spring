//Anais Llancapan-peitou1

package com.edutech.edutech_api.dto;

public class ResenaDTO {

    private String descripcion;
    private Long usuarioCursoId;

    public ResenaDTO() {
        this.descripcion = "";
        this.usuarioCursoId = 0L; // valor por defecto Long válido
    }

    public ResenaDTO(String descripcion, Long usuarioCursoId) {
        this.descripcion = descripcion;
        this.usuarioCursoId = usuarioCursoId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getUsuarioCursoId() {
        return usuarioCursoId;
    }

    public void setUsuarioCursoId(Long usuarioCursoId) {
        this.usuarioCursoId = usuarioCursoId;
    }
}
