package co.edu.uniquindio.empresaalojamiento.repositorios.interfaces;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Usuario;

import java.util.List;

public interface IUsuarioRepositorio {
    public void agregarUsuario(Usuario usuario);

    public void eliminarUsuario(Usuario usuario);

    public Usuario buscarUsuario(String id);

    public Usuario buscarUsuarioCorreo(String correo);

    public List<Usuario> listarUsuarios();

}
