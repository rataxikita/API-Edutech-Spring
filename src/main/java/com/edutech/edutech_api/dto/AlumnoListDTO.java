package com.edutech.edutech_api.dto;

public class AlumnoListDTO {
    private Long id;
    private String nombre;
    private String apellidos;
    private String correo;
    private boolean estado;
    private String rol;

    public AlumnoListDTO() {}

    public AlumnoListDTO(Long id, String nombre, String apellidos, String correo, boolean estado, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
        this.estado = estado;
        this.rol = rol;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public boolean isEstado() { return estado; }
    public void setEstado(boolean estado) { this.estado = estado; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
} 