package com.duoc.Edutech.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Incidencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descripcion;
    private String respuesta;

    @ManyToOne
    @JoinColumn(name = "usuasrio_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "gerente_id")
    private Usuario gerente;

    public Incidencia() {
    }

    public Incidencia(Long id, String titulo, String descripcion, String respuesta, Usuario usuario, Usuario gerente) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.respuesta = respuesta;
        this.usuario = usuario;
        this.gerente = gerente;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getGerente() {
        return gerente;
    }

    public void setGerente(Usuario gerente) {
        this.gerente = gerente;
    }

    
}
