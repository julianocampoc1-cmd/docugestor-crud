package com.docugestor.model;

public class Usuario {
    private int idUsuario;
    private String documento;
    private String nombreCompleto;
    private String correo;
    private String clave;
    private int idRol;
    private String nombreRol;
    private boolean estado;

    public Usuario() {}

    public Usuario(int idUsuario, String documento, String nombreCompleto, String correo, String clave, int idRol, boolean estado) {
        this.idUsuario = idUsuario;
        this.documento = documento;
        this.nombreCompleto = nombreCompleto;
        this.correo = correo;
        this.clave = clave;
        this.idRol = idRol;
        this.estado = estado;
    }

    public Usuario(String documento, String nombreCompleto, String correo, String clave, int idRol, boolean estado) {
        this.documento = documento;
        this.nombreCompleto = nombreCompleto;
        this.correo = correo;
        this.clave = clave;
        this.idRol = idRol;
        this.estado = estado;
    }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getDocumento() { return documento; }
    public void setDocumento(String documento) { this.documento = documento; }

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getClave() { return clave; }
    public void setClave(String clave) { this.clave = clave; }

    public int getIdRol() { return idRol; }
    public void setIdRol(int idRol) { this.idRol = idRol; }

    public String getNombreRol() { return nombreRol; }
    public void setNombreRol(String nombreRol) { this.nombreRol = nombreRol; }

    public boolean isEstado() { return estado; }
    public void setEstado(boolean estado) { this.estado = estado; }

    @Override
    public String toString() {
        String rolMostrar = (nombreRol != null) ? nombreRol : ("ID Rol " + idRol);
        return String.format("ID: %d | Doc: %-12s | Nombre: %-22s | Correo: %-28s | Rol: %-18s | Estado: %s",
                idUsuario, documento, nombreCompleto, correo, rolMostrar, estado ? "Activo" : "Inactivo");
    }
}