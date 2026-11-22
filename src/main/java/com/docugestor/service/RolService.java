package com.docugestor.service;

import com.docugestor.dao.RolDAO;
import com.docugestor.dao.impl.RolDAOImpl;
import com.docugestor.model.Rol;

import java.util.List;

public class RolService {
    private final RolDAO rolDAO = new RolDAOImpl();

    public boolean registrarRol(String nombre, String descripcion, boolean estado) {
        if (nombre == null || nombre.trim().isEmpty()) {
            System.out.println("El nombre del rol es obligatorio.");
            return false;
        }
        Rol rol = new Rol(nombre.trim(), descripcion != null ? descripcion.trim() : "", estado);
        return rolDAO.crear(rol);
    }

    public List<Rol> listarRoles() {
        return rolDAO.listarTodos();
    }

    public Rol buscarRolPorId(int idRol) {
        return rolDAO.buscarPorId(idRol);
    }

    public boolean actualizarRol(int idRol, String nombre, String descripcion, boolean estado) {
        if (idRol <= 0 || nombre == null || nombre.trim().isEmpty()) {
            System.out.println("Datos inválidos para actualizar el rol.");
            return false;
        }
        Rol rol = new Rol(idRol, nombre.trim(), descripcion != null ? descripcion.trim() : "", estado);
        return rolDAO.actualizar(rol);
    }

    public boolean cambiarEstadoRol(int idRol, boolean estado) {
        return rolDAO.cambiarEstado(idRol, estado);
    }
}