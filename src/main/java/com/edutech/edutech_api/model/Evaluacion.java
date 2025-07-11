// Catalina Rosales->rataxikita
package com.edutech.edutech_api.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Evaluacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    @JsonIgnore
    private Curso curso;

    private String titulo;
    private String descripcion;
    private String fechaPublicacion;
    private Integer puntajeMaximo;

    @OneToMany
    private java.util.List<Pregunta> preguntas = new java.util.ArrayList<>();

    public Evaluacion() {
    }

    public Evaluacion(Long id, Curso curso, String titulo, String descripcion, String fechaPublicacion, Integer puntajeMaximo) {
        this.id = id;
        this.curso = curso;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaPublicacion = fechaPublicacion;
        this.puntajeMaximo = puntajeMaximo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
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

    public Integer getPuntajeMaximo() {
        return puntajeMaximo;
    }

    public void setPuntajeMaximo(Integer puntajeMaximo) {
        this.puntajeMaximo = puntajeMaximo;
    }

    public java.util.List<Pregunta> getPreguntas() { return preguntas; }
    public void setPreguntas(java.util.List<Pregunta> preguntas) { this.preguntas = preguntas; }
}
// Catalina Rosales->rataxikita