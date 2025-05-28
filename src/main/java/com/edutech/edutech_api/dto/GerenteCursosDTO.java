package com.edutech.edutech_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class GerenteCursosDTO {
    
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String nombre;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El formato del correo no es válido")
    private String correo;

    @NotBlank(message = "La clave es obligatoria")
    @Size(min = 6, message = "La clave debe tener al menos 6 caracteres")
    private String clave;

    @NotBlank(message = "El área de especialización es obligatoria")
    private String areaEspecializacion;

    public GerenteCursosDTO() {}

    public GerenteCursosDTO(String nombre, String correo, String clave, String areaEspecializacion) {
        this.nombre = nombre;
        this.correo = correo;
        this.clave = clave;
        this.areaEspecializacion = areaEspecializacion;
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

    public String getAreaEspecializacion() {
        return areaEspecializacion;
    }

    public void setAreaEspecializacion(String areaEspecializacion) {
        this.areaEspecializacion = areaEspecializacion;
    }
} 