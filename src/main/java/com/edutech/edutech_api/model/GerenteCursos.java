package com.edutech.edutech_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.DiscriminatorValue;

@Entity
@DiscriminatorValue("GERENTE_CURSOS")
public class GerenteCursos extends Usuario {
    public GerenteCursos() { super(); }
} 