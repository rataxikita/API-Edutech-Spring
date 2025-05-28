package com.edutech.edutech_api.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Soporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombreSoporte;
    private String respuesta;

    @OneToMany(mappedBy = "soporte")
    private List<Incidencia> incidencias = new ArrayList<>();

    public Soporte() {
        this.id = (long)0;
        this.nombreSoporte = "";
        this.respuesta = "";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombreSoporte() {
        return nombreSoporte;
    }

    public void setNombreSoporte(String nombreSoporte) {
        this.nombreSoporte = nombreSoporte;
    }

    public List<Incidencia> getIncidencias() {
        return incidencias;
    }

    public void setIncidencias(List<Incidencia> incidencias) {
        this.incidencias = incidencias;
    }


    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
    
    
}
