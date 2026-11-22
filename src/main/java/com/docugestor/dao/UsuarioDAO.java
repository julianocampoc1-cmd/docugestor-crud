package com.docugestor.dao;

import com.docugestor.model.Usuario;
import java.util.List;

public interface UsuarioDAO {
    boolean crear(Usuario usuario);
    List<Usuario> listarTodosConRol();
    Usuario buscarPorId(int idUsuario);
    boolean actualizar(Usuario usuario);
    boolean cambiarEstado(int idUsuario, boolean estado);
}