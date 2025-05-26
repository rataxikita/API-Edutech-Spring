//Anais Llancapan- peitou1
package com.edutech.edutech_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Resena {

    @Id
    private String id; 
    private String descripcion;
    private String fecha;

    @ManyToOne
    @JoinColumn(name = "usuario_curso_id")
    private UsuarioCurso usuarioCurso;

    public Resena() {
        this.id = "";
        this.descripcion = "";
        this.fecha = "";
    }

    public Resena(String id, String descripcion, String fecha, UsuarioCurso usuarioCurso) {
        this.id = id;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.usuarioCurso = usuarioCurso;
    }

    // Getters y setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public UsuarioCurso getUsuarioCurso() {
        return usuarioCurso;
    }

    public void setUsuarioCurso(UsuarioCurso usuarioCurso) {
        this.usuarioCurso = usuarioCurso;
    }
}