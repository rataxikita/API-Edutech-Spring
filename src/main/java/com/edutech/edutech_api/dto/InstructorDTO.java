//Catalina Rosales->rataxikita
package com.edutech.edutech_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class InstructorDTO {
    
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String nombre;
    
    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;
    
    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El formato del correo no es válido")
    private String correo;
    
    @NotBlank(message = "La clave es obligatoria")
    @Size(min = 6, message = "La clave debe tener al menos 6 caracteres")
    private String clave;
    
    @NotBlank(message = "El RUT es obligatorio")
    private String rut;
    
    private boolean estado = true;

    public InstructorDTO() {}

    public InstructorDTO(String nombre, String apellido, String correo, String clave, String rut) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.clave = clave;
        this.rut = rut;
        this.estado = true;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
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

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
//Catalina Rosales->rataxikita