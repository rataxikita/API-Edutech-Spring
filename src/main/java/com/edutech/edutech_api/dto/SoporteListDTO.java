package com.edutech.edutech_api.dto;

public class SoporteListDTO {
    private Long id;
    private String nombreSoporte;

    public SoporteListDTO() {}

    public SoporteListDTO(Long id, String nombreSoporte) {
        this.id = id;
        this.nombreSoporte = nombreSoporte;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombreSoporte() { return nombreSoporte; }
    public void setNombreSoporte(String nombreSoporte) { this.nombreSoporte = nombreSoporte; }
} 