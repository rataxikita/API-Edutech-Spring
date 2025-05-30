package com.edutech.edutech_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RespuestaIncidenciaDto {
    @NotNull(message = "El ID de la incidencia es obligatorio")
    private Long incidenciaId;
    @NotNull(message = "El ID del soporte es obligatorio")
    private Long soporteId;
    @NotBlank(message = "La respuesta no puede estar vacía")
    private String respuesta;

    public RespuestaIncidenciaDto() {}

    public RespuestaIncidenciaDto(Long incidenciaId, Long soporteId, String respuesta) {
        this.incidenciaId = incidenciaId;
        this.soporteId = soporteId;
        this.respuesta = respuesta;
    }

    public Long getIncidenciaId() {
        return incidenciaId;
    }

    public void setIncidenciaId(Long incidenciaId) {
        this.incidenciaId = incidenciaId;
    }

    public Long getSoporteId() {
        return soporteId;
    }

    public void setSoporteId(Long soporteId) {
        this.soporteId = soporteId;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
}


