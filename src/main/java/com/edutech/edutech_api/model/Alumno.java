package com.edutech.edutech_api.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.DiscriminatorValue;

@Entity
@DiscriminatorValue("ALUMNO")
public class Alumno extends Usuario {
    private String apellidos;
    
    @OneToMany(mappedBy = "alumno")
    private List<Incidencia> incidencias = new ArrayList<>();

    public Alumno() {
        super();
        this.apellidos = "";
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public List<Incidencia> getIncidencias() {
        return incidencias;
    }

    public void setIncidencias(List<Incidencia> incidencias) {
        this.incidencias = incidencias;
    }
}
