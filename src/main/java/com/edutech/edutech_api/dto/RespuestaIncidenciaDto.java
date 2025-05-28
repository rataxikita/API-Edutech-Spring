package com.edutech.edutech_api.dto;

public class RespuestaIncidenciaDto {
    private Long id;
    private String nombreSoporte;
    private String respuesta;
    
    public RespuestaIncidenciaDto(Long id, String nombreSoporte, String respuesta) {
        this.id = id;
        this.nombreSoporte = nombreSoporte;
        this.respuesta = respuesta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreSoporte() {
        return nombreSoporte;
    }

    public void setNombreSoporte(String nombreSoporte) {
        this.nombreSoporte = nombreSoporte;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    
}
