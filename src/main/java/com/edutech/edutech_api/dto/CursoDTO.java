//Anais Llancapan-peitou1
package com.edutech.edutech_api.dto;

import com.edutech.edutech_api.model.Curso;

public class CursoDTO {
    private String sigla;
    private String nombre;
    private String descripcion;
    private boolean estado;
    private String valor;

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

    public boolean getEstado() {
        return estado;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public boolean isEstado() {
        return estado;
    }
    public void setEstado(boolean estado) {
        this.estado = estado;
    
    }
    public String getValor() {
        return valor;
    }
    public void setValor(String valor) {
        this.valor = valor;
    }

public Curso toEntity() {
    Curso curso = new Curso();
    curso.setSigla(this.sigla);
    curso.setNombre(this.nombre);
    curso.setDescripcion(this.descripcion);
    curso.setValor(this.valor);
    curso.setEstado(this.estado);
    return curso;

}

}