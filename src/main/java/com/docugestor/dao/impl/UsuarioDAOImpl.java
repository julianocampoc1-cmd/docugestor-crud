package com.docugestor.dao.impl;

import com.docugestor.config.ConexionBD;
import com.docugestor.dao.UsuarioDAO;
import com.docugestor.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOImpl implements UsuarioDAO {

    @Override
    public boolean crear(Usuario usuario) {
        String sql = "INSERT INTO usuario (documento, nombre_completo, correo, clave, id_rol, estado) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, usuario.getDocumento());
            ps.setString(2, usuario.getNombreCompleto());
            ps.setString(3, usuario.getCorreo());
            ps.setString(4, usuario.getClave());
            ps.setInt(5, usuario.getIdRol());
            ps.setBoolean(6, usuario.isEstado());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al crear usuario: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Usuario> listarTodosConRol() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT u.id_usuario, u.documento, u.nombre_completo, u.correo, u.clave, u.id_rol, r.nombre AS nombre_rol, u.estado " +
                     "FROM usuario u INNER JOIN rol r ON u.id_rol = r.id_rol ORDER BY u.id_usuario";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Usuario u = new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("documento"),
                        rs.getString("nombre_completo"),
                        rs.getString("correo"),
                        rs.getString("clave"),
                        rs.getInt("id_rol"),
                        rs.getBoolean("estado")
                );
                u.setNombreRol(rs.getString("nombre_rol"));
                lista.add(u);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar usuarios: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public Usuario buscarPorId(int idUsuario) {
        String sql = "SELECT u.id_usuario, u.documento, u.nombre_completo, u.correo, u.clave, u.id_rol, r.nombre AS nombre_rol, u.estado " +
                     "FROM usuario u INNER JOIN rol r ON u.id_rol = r.id_rol WHERE u.id_usuario = ?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Usuario u = new Usuario(
                            rs.getInt("id_usuario"),
                            rs.getString("documento"),
                            rs.getString("nombre_completo"),
                            rs.getString("correo"),
                            rs.getString("clave"),
                            rs.getInt("id_rol"),
                            rs.getBoolean("estado")
                    );
                    u.setNombreRol(rs.getString("nombre_rol"));
                    return u;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar usuario: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean actualizar(Usuario usuario) {
        String sql = "UPDATE usuario SET documento = ?, nombre_completo = ?, correo = ?, clave = ?, id_rol = ?, estado = ? WHERE id_usuario = ?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, usuario.getDocumento());
            ps.setString(2, usuario.getNombreCompleto());
            ps.setString(3, usuario.getCorreo());
            ps.setString(4, usuario.getClave());
            ps.setInt(5, usuario.getIdRol());
            ps.setBoolean(6, usuario.isEstado());
            ps.setInt(7, usuario.getIdUsuario());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar usuario: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean cambiarEstado(int idUsuario, boolean estado) {
        String sql = "UPDATE usuario SET estado = ? WHERE id_usuario = ?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setBoolean(1, estado);
            ps.setInt(2, idUsuario);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al cambiar estado del usuario: " + e.getMessage());
            return false;
        }
    }
}