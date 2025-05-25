// Catalina Rosales->rataxikita

package com.edutech.edutech_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class UsuarioCurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Curso curso;

    private String fechaInscripcion;
    private String progreso;
    public UsuarioCurso() {
    }
    public UsuarioCurso(Long id, Usuario usuario, Curso curso, String fechaInscripcion, String progreso) {
        this.id = id;
        this.usuario = usuario;
        this.curso = curso;
        this.fechaInscripcion = fechaInscripcion;
        this.progreso = progreso;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Curso getCurso() {
        return curso;
    }
    public void setCurso(Curso curso) {
        this.curso = curso;
    }
    public String getFechaInscripcion() {
        return fechaInscripcion;
    }
    public void setFechaInscripcion(String fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }
    public String getProgreso() {
        return progreso;
    }
    public void setProgreso(String progreso) {
        this.progreso = progreso;
    }

    // Catalina Rosales->rataxikita

    
}