// Catalina Rosales->rataxikita
package com.edutech.edutech_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.DiscriminatorColumn;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_usuario")
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    protected String nombre;
    
    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El formato del correo no es válido")
    @Column(unique = true)
    protected String correo;
    
    @NotBlank(message = "La clave es obligatoria")
    @Size(min = 6, message = "La clave debe tener al menos 6 caracteres")
    protected String clave;
    
    @Column(name = "activo")
    protected boolean estado = true;
    
    @NotNull(message = "El rol es obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(name = "rol")
    protected Rol rol;

    @OneToMany(mappedBy = "usuario")
    protected List<UsuarioCurso> inscripciones;

    public Usuario() {}

    public Usuario(Long id, String nombre, String correo, String clave, boolean estado, Rol rol) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.clave = clave;
        this.estado = estado;
        this.rol = rol;
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

    public boolean esAdministrador() {
        return Rol.ADMINISTRADOR.equals(this.rol);
    }

    public boolean esInstructor() {
        return Rol.INSTRUCTOR.equals(this.rol);
    }

    public boolean esEstudiante() {
        return Rol.ESTUDIANTE.equals(this.rol);
    }

    public List<UsuarioCurso> getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(List<UsuarioCurso> inscripciones) {
        this.inscripciones = inscripciones;
    }
}
