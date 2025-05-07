package co.edu.uniquindio.empresaalojamiento.repositorios;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Usuario;

import java.util.ArrayList;
import java.util.List;

public interface IUsuarioRepositorio {
    public void agregar(Usuario usuario);

    public void eliminar(Usuario usuario);

    public void actualizar(Usuario usuario);

    public Usuario buscarPorId(String id);

    public List<Usuario> listarTodos();

}
