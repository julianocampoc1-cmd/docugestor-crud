package com.docugestor.dao.impl;

import com.docugestor.config.ConexionBD;
import com.docugestor.dao.RolDAO;
import com.docugestor.model.Rol;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RolDAOImpl implements RolDAO {

    @Override
    public boolean crear(Rol rol) {
        String sql = "INSERT INTO rol (nombre, descripcion, estado) VALUES (?, ?, ?)";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, rol.getNombre());
            ps.setString(2, rol.getDescripcion());
            ps.setBoolean(3, rol.isEstado());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al crear rol: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Rol> listarTodos() {
        List<Rol> lista = new ArrayList<>();
        String sql = "SELECT id_rol, nombre, descripcion, estado FROM rol ORDER BY id_rol";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Rol(
                        rs.getInt("id_rol"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getBoolean("estado")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar roles: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public Rol buscarPorId(int idRol) {
        String sql = "SELECT id_rol, nombre, descripcion, estado FROM rol WHERE id_rol = ?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idRol);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Rol(
                            rs.getInt("id_rol"),
                            rs.getString("nombre"),
                            rs.getString("descripcion"),
                            rs.getBoolean("estado")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar rol: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean actualizar(Rol rol) {
        String sql = "UPDATE rol SET nombre = ?, descripcion = ?, estado = ? WHERE id_rol = ?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, rol.getNombre());
            ps.setString(2, rol.getDescripcion());
            ps.setBoolean(3, rol.isEstado());
            ps.setInt(4, rol.getIdRol());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar rol: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean cambiarEstado(int idRol, boolean estado) {
        String sql = "UPDATE rol SET estado = ? WHERE id_rol = ?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setBoolean(1, estado);
            ps.setInt(2, idRol);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al cambiar estado del rol: " + e.getMessage());
            return false;
        }
    }
}