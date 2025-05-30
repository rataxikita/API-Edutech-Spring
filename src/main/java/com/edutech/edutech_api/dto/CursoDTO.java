//Anais Llancapan-peitou1
package com.edutech.edutech_api.dto;


public class CursoDTO {
    private String sigla;
    private String nombre;
    private String descripcion;
    private boolean estado;
    private double valor;

public CursoDTO(){
    this.sigla = "";
    this.nombre= "";
    this.estado= false;
    this.descripcion= "";
    this.valor= 0.0;
}   

public CursoDTO(String sigla,String nombre, String descripcion, boolean estado, double valor) {
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

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

}
