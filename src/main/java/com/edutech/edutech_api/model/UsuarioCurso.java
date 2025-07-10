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
import jakarta.persistence.Column;

@Entity
@Table(name = "usuario_curso")
public class UsuarioCurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Campos para diferentes tipos de usuarios - todos nullable
    @ManyToOne
    @JoinColumn(name = "administrador_id", nullable = true)
    @JsonIgnore
    private Administrador administrador;

    @ManyToOne
    @JoinColumn(name = "instructor_id", nullable = true)
    @JsonIgnore
    private Instructor instructor;

    @ManyToOne
    @JoinColumn(name = "alumno_id", nullable = true)
    private Alumno alumno;

    @ManyToOne
    @JoinColumn(name = "gerente_cursos_id", nullable = true)
    @JsonIgnore
    private GerenteCursos gerenteCursos;

    @ManyToOne
    @JoinColumn(name = "curso_sigla", nullable = false)
    private Curso curso;

    @Column(nullable = false)
    private String fechaInscripcion;
    
    @Column(nullable = false)
    private String progreso;

    public UsuarioCurso() {
    }

    public UsuarioCurso(Long id, Curso curso, String fechaInscripcion, String progreso) {
        this.id = id;
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

    // Métodos para obtener el usuario independientemente del tipo
    public Object getUsuario() {
        if (administrador != null) return administrador;
        if (instructor != null) return instructor;
        if (alumno != null) return alumno;
        if (gerenteCursos != null) return gerenteCursos;
        return null;
    }

    public void setUsuario(Object usuario) {
        // Limpiar todas las referencias
        this.administrador = null;
        this.instructor = null;
        this.alumno = null;
        this.gerenteCursos = null;

        // Establecer la referencia correcta según el tipo
        if (usuario instanceof Administrador) {
            this.administrador = (Administrador) usuario;
        } else if (usuario instanceof Instructor) {
            this.instructor = (Instructor) usuario;
        } else if (usuario instanceof Alumno) {
            this.alumno = (Alumno) usuario;
        } else if (usuario instanceof GerenteCursos) {
            this.gerenteCursos = (GerenteCursos) usuario;
        }
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public GerenteCursos getGerenteCursos() {
        return gerenteCursos;
    }

    public void setGerenteCursos(GerenteCursos gerenteCursos) {
        this.gerenteCursos = gerenteCursos;
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