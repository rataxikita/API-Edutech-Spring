// Anais Llancapan- peitou1

package com.edutech.edutech_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;
import java.util.List;

@Entity
@Table(name = "cursos")
public class Curso {
    @Id
    private String sigla;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String nombre;

    @Column(length = 500)
    private String descripcion;

    @NotNull(message = "El estado es obligatorio")
    private boolean estado;

    @NotNull(message = "El valor es obligatorio")
    private Double valor;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    @JsonIgnore
    private Instructor instructor;

    @OneToMany(mappedBy = "curso")
    @JsonIgnore
    private List<Evaluacion> evaluaciones;

    @OneToMany(mappedBy = "curso")
    @JsonIgnore
    private List<Pregunta> preguntas;

    @OneToMany(mappedBy = "curso")
    @JsonIgnore
    private List<UsuarioCurso> inscritos;

    public Curso() {}

    public Curso(String sigla, String nombre, String descripcion, boolean estado, Double valor) {
        this.sigla = sigla;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.valor = valor;
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

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
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
