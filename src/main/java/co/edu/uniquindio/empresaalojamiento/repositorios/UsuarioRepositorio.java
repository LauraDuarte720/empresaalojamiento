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

    @Override
    public void activarUsuario(Usuario usuario) {
        usuario.setActivo(true);
        guardarDatos(usuarios);
    }

    @Override
    public void cambiarContrasena(Usuario usuario, String contrasena) {
        usuario.setContrasena(contrasena);
        guardarDatos(usuarios);
    }

    @Override
    public void cambiarCodigoEnviado(Usuario usuario, String codigoEnviado) {
        usuario.setCodigoEnviado(codigoEnviado);
        guardarDatos(usuarios);
    }

    public void actualizarUsuario(String cedulaAntiguo,  String cedulaNueva, String nombre, String apellido, String telefono, String email){
        Usuario usuario = buscarUsuario(cedulaAntiguo);
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setTelefono(telefono);
        usuario.setEmail(email);
        usuario.setCedula(cedulaNueva);
        guardarDatos(usuarios);
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

}
