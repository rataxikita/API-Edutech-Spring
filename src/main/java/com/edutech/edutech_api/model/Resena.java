//Anais Llancapan- peitou1
package com.edutech.edutech_api.model;



import java.util.Optional;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;

@Entity

public class Resena {



    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String contenido;
    private int calificacion;
    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Curso curso;



    // Getters y Setters

    public String getContenido() {

        return contenido;

    }

    public void setContenido(String contenido) {

        this.contenido = contenido;

    }

    public int getCalificacion() {

        return calificacion;

    }

    public void setCalificacion(int calificacion) {

        this.calificacion = calificacion;

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

    public void setCurso(Curso curso) { // Cambia Optional<Curso> a Curso
        this.curso = curso;
    }

}

