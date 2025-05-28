package com.edutech.edutech_api.dto;


public class IncidenciaListaRespuestasDto {
        private Long id;
    private String titulo;
    private String descripcion;
    private String respuesta;
    private String usuarioCorreo;
    private String gerenteCorreo;
    private Long usuarioId;
    private Long gerenteId;
    
    public IncidenciaListaRespuestasDto() {
    }

    public IncidenciaListaRespuestasDto(Long id, String titulo, String descripcion, String respuesta,
            String usuarioCorreo, String gerenteCorreo) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.respuesta = respuesta;
        this.usuarioCorreo = usuarioCorreo;
        this.gerenteCorreo = gerenteCorreo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getUsuarioCorreo() {
        return usuarioCorreo;
    }

    public void setUsuarioCorreo(String usuarioCorreo) {
        this.usuarioCorreo = usuarioCorreo;
    }

    public String getGerenteCorreo() {
        return gerenteCorreo;
    }

    public void setGerenteCorreo(String gerenteCorreo) {
        this.gerenteCorreo = gerenteCorreo;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getGerenteId() {
        return gerenteId;
    }

    public void setGerenteId(Long gerenteId) {
        this.gerenteId = gerenteId;
    }
}
