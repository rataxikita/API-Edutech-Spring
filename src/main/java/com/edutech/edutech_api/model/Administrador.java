// Catalina Rosales->rataxikita
package com.edutech.edutech_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;

@Entity
@DiscriminatorValue("ADMINISTRADOR")
public class Administrador extends Usuario {
    public Administrador() { super(); }
}
// Catalina Rosales->rataxikita
