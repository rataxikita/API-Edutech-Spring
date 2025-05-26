// Anais Llancapan- peitou1

package com.edutech.edutech_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class Curso {

    @Id
    private String sigla;
    private String nombre;
    private String descripcion;
    private String estado;
    private String valor;

public Curso(){
    this.sigla="";
    this.nombre="";
    this.descripcion="";
    this.estado="";
    this.valor="";
}

public Curso(String sigla, String nombre, String descripcion, String estado, String valor) {
    this.sigla = sigla;
    this.nombre = nombre;
    this.descripcion= descripcion;
    this.estado= estado;
    this.valor= valor;
}

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Object getInstructor() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
