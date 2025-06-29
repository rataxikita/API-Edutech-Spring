// Catalina Rosales->rataxikita
package com.edutech.edutech_api.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "instructores")
public class Instructor {

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
    private Rol rol = Rol.INSTRUCTOR;

    @NotBlank(message = "El RUT es obligatorio")
    @Column(unique = true)
    private String rut;

    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;

    @JsonIgnore
    @OneToMany(mappedBy = "instructor")
    private List<Curso> cursos;

    @JsonIgnore
    @OneToMany(mappedBy = "instructor")
    private List<UsuarioCurso> inscripciones;

    public Instructor() {}

    public Instructor(Long id, String nombre, String correo, String clave, boolean estado, Rol rol, String rut, String apellido) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.clave = clave;
        this.estado = estado;
        this.rol = rol;
        this.rut = rut;
        this.apellido = apellido;
    }

    // Constructor simplificado para creación
    public Instructor(String nombre, String correo, String clave, String rut, String apellido) {
        this.nombre = nombre;
        this.correo = correo;
        this.clave = clave;
        this.rut = rut;
        this.apellido = apellido;
        this.estado = true;
        this.rol = Rol.INSTRUCTOR;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getClave() { return clave; }
    public void setClave(String clave) { this.clave = clave; }

    public boolean isEstado() { return estado; }
    public void setEstado(boolean estado) { this.estado = estado; }

    public Rol getRol() { return rol; }
    public void setRol(Rol rol) { this.rol = rol; }

    public String getRut() { return rut; }
    public void setRut(String rut) { this.rut = rut; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public List<Curso> getCursos() { return cursos; }
    public void setCursos(List<Curso> cursos) { this.cursos = cursos; }

    public List<UsuarioCurso> getInscripciones() { return inscripciones; }
    public void setInscripciones(List<UsuarioCurso> inscripciones) { this.inscripciones = inscripciones; }

    public boolean esAdministrador() {
        return Rol.ADMINISTRADOR.equals(this.rol);
    }

    public boolean esInstructor() {
        return Rol.INSTRUCTOR.equals(this.rol);
    }

    public boolean esEstudiante() {
        return Rol.ESTUDIANTE.equals(this.rol);
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", rut='" + rut + '\'' +
                ", apellido='" + apellido + '\'' +
                ", estado=" + estado +
                ", rol=" + rol +
                '}';
    }
}
// Catalina Rosales->rataxikita
