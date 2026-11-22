package com.docugestor.model;

public class Rol {
    private int idRol;
    private String nombre;
    private String descripcion;
    private boolean estado;

    public Rol() {}

    public Rol(int idRol, String nombre, String descripcion, boolean estado) {
        this.idRol = idRol;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public Rol(String nombre, String descripcion, boolean estado) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public int getIdRol() { return idRol; }
    public void setIdRol(int idRol) { this.idRol = idRol; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public boolean isEstado() { return estado; }
    public void setEstado(boolean estado) { this.estado = estado; }

    @Override
    public String toString() {
        return String.format("ID: %d | Rol: %-20s | Descripción: %-45s | Estado: %s",
                idRol, nombre, descripcion, estado ? "Activo" : "Inactivo");
    }
}