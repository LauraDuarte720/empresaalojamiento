package co.edu.uniquindio.empresaalojamiento.repositorios;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Usuario;
import co.edu.uniquindio.empresaalojamiento.repositorios.interfaces.IUsuarioRepositorio;
import co.edu.uniquindio.empresaalojamiento.utilidades.Constantes;
import co.edu.uniquindio.empresaalojamiento.utilidades.Persistencia;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepositorio implements IUsuarioRepositorio {

    private final List<Usuario> usuarios;

    public UsuarioRepositorio() {
        this.usuarios = leerDatos();
    }

    @Override
    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
        guardarDatos(usuarios);
    }

    @Override
    public void eliminarUsuario(Usuario usuario) {
        usuarios.remove(usuario);
        guardarDatos(usuarios);
    }

    @Override
    public Usuario buscarUsuario(String id) {
        return usuarios.stream().filter(c -> id.equalsIgnoreCase(c.getCedula())).findFirst().orElse(null);
    }

    @Override
    public Usuario buscarUsuarioCorreo(String correo) {
        return usuarios.stream().filter(c -> correo.equalsIgnoreCase(c.getEmail())).findFirst().orElse(null);
    }


    @Override
    public List<Usuario> listarUsuarios() {
        return usuarios;
    }

    public void guardarDatos(List<Usuario> usuarios) {
        try {
            Persistencia.serializarObjeto(Constantes.RUTA_USUARIOS, usuarios);
        } catch (IOException e) {
            System.err.println("Error guardando pacientes: " + e.getMessage());
        }
    }


    public List<Usuario> leerDatos() {
        try {
            Object datos = Persistencia.deserializarObjeto(Constantes.RUTA_USUARIOS);
            if (datos != null) {
                return (List<Usuario>) datos;
            }
        } catch (Exception e) {
            System.err.println("Error cargando pacientes: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    public void activarUsuario(Usuario usuario){
        usuario.setActivo(true);
        guardarDatos(usuarios);
    }

}
