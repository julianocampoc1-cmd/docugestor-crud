package com.docugestor.service;

import com.docugestor.dao.UsuarioDAO;
import com.docugestor.dao.impl.UsuarioDAOImpl;
import com.docugestor.model.Usuario;

import java.util.List;

public class UsuarioService {
    private final UsuarioDAO usuarioDAO = new UsuarioDAOImpl();

    public boolean registrarUsuario(String documento, String nombreCompleto, String correo, String clave, int idRol, boolean estado) {
        if (documento == null || documento.trim().isEmpty()
                || nombreCompleto == null || nombreCompleto.trim().isEmpty()
                || correo == null || correo.trim().isEmpty()
                || clave == null || clave.trim().isEmpty()
                || idRol <= 0) {
            System.out.println("Todos los campos del usuario y el rol son obligatorios.");
            return false;
        }
        Usuario usuario = new Usuario(
                documento.trim(),
                nombreCompleto.trim(),
                correo.trim(),
                clave.trim(),
                idRol,
                estado
        );
        return usuarioDAO.crear(usuario);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioDAO.listarTodosConRol();
    }

    public Usuario buscarUsuarioPorId(int idUsuario) {
        return usuarioDAO.buscarPorId(idUsuario);
    }

    public boolean actualizarUsuario(int idUsuario, String documento, String nombreCompleto, String correo, String clave, int idRol, boolean estado) {
        if (idUsuario <= 0 || documento == null || documento.trim().isEmpty()
                || nombreCompleto == null || nombreCompleto.trim().isEmpty()
                || correo == null || correo.trim().isEmpty()
                || clave == null || clave.trim().isEmpty()
                || idRol <= 0) {
            System.out.println("Datos inválidos para actualizar el usuario.");
            return false;
        }
        Usuario usuario = new Usuario(
                idUsuario,
                documento.trim(),
                nombreCompleto.trim(),
                correo.trim(),
                clave.trim(),
                idRol,
                estado
        );
        return usuarioDAO.actualizar(usuario);
    }

    public boolean cambiarEstadoUsuario(int idUsuario, boolean estado) {
        return usuarioDAO.cambiarEstado(idUsuario, estado);
    }
}