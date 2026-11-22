package com.docugestor.dao;

import com.docugestor.model.Rol;
import java.util.List;

public interface RolDAO {
    boolean crear(Rol rol);
    List<Rol> listarTodos();
    Rol buscarPorId(int idRol);
    boolean actualizar(Rol rol);
    boolean cambiarEstado(int idRol, boolean estado);
}