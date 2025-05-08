package co.edu.uniquindio.empresaalojamiento.repositorios;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Usuario;
import co.edu.uniquindio.empresaalojamiento.repositorios.interfaces.IUsuarioRepositorio;

import java.util.ArrayList;
import java.util.List;

public class UsuarioRepositorio implements IUsuarioRepositorio {

    private final ArrayList<Usuario> usuarios;

    public UsuarioRepositorio() {
        this.usuarios = new ArrayList<>();
    }

    @Override
    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    @Override
    public void eliminarUsuario(Usuario usuario) {
        usuarios.remove(usuario);
    }

    @Override
    public Usuario buscarUsuario(String id) {
        return usuarios.stream().filter(c -> id.equalsIgnoreCase(c.getCedula())).findFirst().orElse(null);
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarios;
    }
}
