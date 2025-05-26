//Catalina Rosales->rataxikita
package com.edutech.edutech_api.dto;

import jakarta.validation.constraints.NotBlank;

public class UsuarioDTO {

    @NotBlank (message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "el correo es obligatorio")
    private String correo;

    @NotBlank(message = "La contraseña es obligatoria")
    private String clave;

    @NotBlank (message = "El rol es obligatorio")
    private String rol;

    public UsuarioDTO(){}

    public UsuarioDTO(@NotBlank(message = "El nombre es obligatorio") String nombre,
            @NotBlank(message = "el correo es obligatorio") String correo,
            @NotBlank(message = "La contraseña es obligatoria") String clave,
            @NotBlank(message = "El rol es obligatorio") String rol) {
        this.nombre = nombre;
        this.correo = correo;
        this.clave = clave;
        this.rol = rol;
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

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

        
}
//Catalina Rosales->rataxikita
