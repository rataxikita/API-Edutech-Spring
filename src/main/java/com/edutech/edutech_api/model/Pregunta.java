// Catalina Rosales->rataxikita
package com.edutech.edutech_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "preguntas")
public class Pregunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Solo relación con Alumno (quien hace la pregunta) e Instructor (quien responde)
    @ManyToOne
    @JoinColumn(name = "alumno_id")
    @JsonIgnore
    private Alumno alumno;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    @JsonIgnore
    private Instructor instructor;

    @ManyToOne
    @JsonIgnore
    private Curso curso;

    private String contenido;
    private String fecha;
    private String respuesta;
    private String fechaRespuesta;

    public Pregunta() {
    }

    public Pregunta(Long id, Alumno alumno, Instructor instructor, Curso curso, String contenido, String fecha) {
        this.id = id;
        this.alumno = alumno;
        this.instructor = instructor;
        this.curso = curso;
        this.contenido = contenido;
        this.fecha = fecha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getFechaRespuesta() {
        return fechaRespuesta;
    }

    public void setFechaRespuesta(String fecha) {
        this.fechaRespuesta = fecha;
    }
}
// Catalina Rosales->rataxikita