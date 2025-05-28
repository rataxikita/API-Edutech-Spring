// Anais Llancapan- peitou1

package com.edutech.edutech_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.util.List;

@Entity
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sigla;
    private String nombre;
    private String descripcion;
    private String estado;
    private String valor;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @OneToMany(mappedBy = "curso")
    private List<Evaluacion> evaluaciones;

    @OneToMany(mappedBy = "curso")
    private List<Pregunta> preguntas;

    @OneToMany(mappedBy = "curso")
    private List<UsuarioCurso> inscritos;

    public Curso(){
        this.sigla = "";
        this.nombre = "";
        this.descripcion = "";
        this.estado = "";
        this.valor = "";
    }

    public Curso(String sigla, String nombre, String descripcion, String estado, String valor) {
        this.sigla = sigla;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public List<Evaluacion> getEvaluaciones() {
        return evaluaciones;
    }

    public void setEvaluaciones(List<Evaluacion> evaluaciones) {
        this.evaluaciones = evaluaciones;
    }

    public List<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }

    public List<UsuarioCurso> getInscritos() {
        return inscritos;
    }

    public void setInscritos(List<UsuarioCurso> inscritos) {
        this.inscritos = inscritos;
    }
}
