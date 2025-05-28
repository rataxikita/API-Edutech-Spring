package com.edutech.edutech_api.model;

public enum Rol {
    ADMINISTRADOR("ADMIN"),
    GERENTE_CURSOS("GERENTE"),
    INSTRUCTOR("INSTRUCTOR"),
    ESTUDIANTE("ESTUDIANTE");

    private final String valor;

    Rol(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    public static Rol fromString(String texto) {
        for (Rol rol : Rol.values()) {
            if (rol.valor.equalsIgnoreCase(texto)) {
                return rol;
            }
        }
        throw new IllegalArgumentException("Rol no válido: " + texto);
    }

    public boolean puedeGestionarCursos() {
        return this == ADMINISTRADOR || this == GERENTE_CURSOS;
    }

    public boolean puedeGestionarInstructores() {
        return this == ADMINISTRADOR || this == GERENTE_CURSOS;
    }

    public boolean puedeCrearContenido() {
        return this == INSTRUCTOR;
    }

    public boolean puedeEvaluarEstudiantes() {
        return this == INSTRUCTOR;
    }

    public boolean puedeAccederAReportes() {
        return this == ADMINISTRADOR || this == GERENTE_CURSOS;
    }
} 