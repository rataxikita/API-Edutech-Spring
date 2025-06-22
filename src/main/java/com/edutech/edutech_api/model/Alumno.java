package com.edutech.edutech_api.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "alumnos")
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String nombre;
    
    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El formato del correo no es válido")
    @Column(unique = true)
    private String correo;
    
    @NotBlank(message = "La clave es obligatoria")
    @Size(min = 6, message = "La clave debe tener al menos 6 caracteres")
    private String clave;
    
    @Column(name = "activo")
    private boolean estado = true;
    
    @NotNull(message = "El rol es obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(name = "rol")
    private Rol rol = Rol.ESTUDIANTE;

    @NotBlank(message = "El Apellido no puede estar vacío")
    private String apellidos;
    
    @JsonIgnore
    @OneToMany(mappedBy = "alumno")
    private List<Incidencia> incidencias = new ArrayList<>();

    @OneToMany(mappedBy = "alumno")
    private List<UsuarioCurso> inscripciones;

    public Alumno() {
        this.apellidos = "";
    }

    public Alumno(Long id, String nombre, String correo, String clave, boolean estado, Rol rol, String apellidos) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.clave = clave;
        this.estado = estado;
        this.rol = rol;
        this.apellidos = apellidos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public List<Incidencia> getIncidencias() {
        return incidencias;
    }

    public void setIncidencias(List<Incidencia> incidencias) {
        this.incidencias = incidencias;
    }

    public List<UsuarioCurso> getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(List<UsuarioCurso> inscripciones) {
        this.inscripciones = inscripciones;
    }

    public boolean esAdministrador() {
        return Rol.ADMINISTRADOR.equals(this.rol);
    }

    public boolean esInstructor() {
        return Rol.INSTRUCTOR.equals(this.rol);
    }

    public boolean esEstudiante() {
        return Rol.ESTUDIANTE.equals(this.rol);
    }
}

//Diego Sotelo G.