package co.edu.uniquindio.empresaalojamiento.repositorios;

import co.edu.uniquindio.empresaalojamiento.conexion.ConexionDB;
import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Usuario;
import co.edu.uniquindio.empresaalojamiento.modelo.enums.Rol;
import co.edu.uniquindio.empresaalojamiento.repositorios.interfaces.IUsuarioRepositorio;
import co.edu.uniquindio.empresaalojamiento.utilidades.Constantes;
import co.edu.uniquindio.empresaalojamiento.utilidades.Persistencia;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepositorio implements IUsuarioRepositorio {

    private final List<Usuario> usuarios;

    public UsuarioRepositorio() {
        this.usuarios = leerDatos();
    }

    @Override
    public void agregarUsuario(Usuario usuario) {
        String sql = """
                INSERT into usuarios(id, cedula, nombre, apellido, telefono, email, contrasena, rol, activo, codigo_enviado) VALUES (?,?,?,?,?,?,?,?,?,?);
                """;
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getId());
            stmt.setString(2, usuario.getCedula());
            stmt.setString(3, usuario.getNombre());
            stmt.setString(4, usuario.getApellido());
            stmt.setString(5, usuario.getTelefono());
            stmt.setString(6, usuario.getEmail());
            stmt.setString(7, usuario.getContrasena());
            stmt.setString(8, usuario.getRol().getNombre());
            stmt.setBoolean(9, usuario.getActivo());
            stmt.setString(10, usuario.getCodigoEnviado());
            BilleteraRepositorio.agregarBilletera(usuario.getBilletera());
            stmt.executeUpdate();
            System.out.println("Usuario agregado exitosamente");

        } catch (SQLException e) {
            throw new RuntimeException("El usuario con cédula " + usuario.getCedula() + " no se pudo agregar correctamente: " + e.getMessage());
        }
    }

    @Override
    public void eliminarUsuario(Usuario usuario) {
        String sql = "DELETE FROM usuarios WHERE id = ?;";
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getId());
            stmt.executeUpdate();
            System.out.println("Usuario eliminado exitosamente");
            BilleteraRepositorio.eliminarBilletera(usuario.getBilletera());
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar el usuario con cedula " + usuario.getCedula() + ": " + e.getMessage());
        }

    }

    @Override
    public Usuario buscarUsuario(String id) {
        String sql = "SELECT * FROM usuarios WHERE id = ?;";
        Usuario usuario = null;
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                usuario = Usuario.builder()
                        .id(rs.getString("id"))
                        .cedula(rs.getString("cedula"))
                        .nombre(rs.getString("nombre"))
                        .apellido(rs.getString("apellido"))
                        .telefono(rs.getString("telefono"))
                        .email(rs.getString("email"))
                        .contrasena(rs.getString("contrasena"))
                        .billetera(BilleteraRepositorio.buscarBilletera(id))
                        .build();
            }
            return usuario;
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar el usuario con id " + id + ": " + e.getMessage());
        }
    }

    @Override
    public Usuario buscarUsuarioCorreo(String correo) {
        String sql = "SELECT * FROM usuarios WHERE email = ?;";
        Usuario usuario = null;
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, correo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                usuario = Usuario.builder()
                        .id(rs.getString("id"))
                        .cedula(rs.getString("cedula"))
                        .nombre(rs.getString("nombre"))
                        .apellido(rs.getString("apellido"))
                        .telefono(rs.getString("telefono"))
                        .email(rs.getString("email"))
                        .contrasena(rs.getString("contrasena"))
                        .billetera(BilleteraRepositorio.buscarBilletera(rs.getString("id")))
                        .rol(Rol.valueOf(rs.getString("rol")))
                        .activo(rs.getBoolean("activo"))
                        .codigoEnviado(rs.getString("codigoEnviado"))
                        .build();
            }
            return usuario;
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar el usuario con email " + correo + ": " + e.getMessage());
        }
    }


    @Override
    public List<Usuario> listarUsuarios() {
        String sql = "SELECT * FROM usuarios;";
        List<Usuario> usuarios = new ArrayList<>();
        Usuario usuario = null;
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                usuario = Usuario.builder()
                        .id(rs.getString("id"))
                        .cedula(rs.getString("cedula"))
                        .nombre(rs.getString("nombre"))
                        .apellido(rs.getString("apellido"))
                        .telefono(rs.getString("telefono"))
                        .email(rs.getString("email"))
                        .contrasena(rs.getString("contrasena"))
                        .billetera(BilleteraRepositorio.buscarBilletera(rs.getString("id")))
                        .rol(Rol.valueOf(rs.getString("rol")))
                        .activo(rs.getBoolean("activo"))
                        .codigoEnviado(rs.getString("codigoEnviado"))
                        .build();
                usuarios.add(usuario);
            }
            return usuarios;
        }
        catch (SQLException e) {
            throw new RuntimeException("Error al listar el usuarios: " + e.getMessage());
        }
    }


    public void actualizarUsuario(String cedulaAntiguo, String cedulaNueva, String nombre, String apellido, String telefono, String email) {
        String sql = """
                UPDATE usuarios SET cedula = ?, nombre = ?, apellido = ?, telefono = ?, 
                                        email = ?, contrasena = ?, rol = ?,
                                        activo = ?, codigo_enviado = ? WHERE id = ?;
                """;

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, cedulaAntiguo);
            // Falta arreglar la logica del id del usuario para poder actualizarlo correctamente

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el alojamiento: " + e.getMessage());
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


    public void guardarDatos(List<Usuario> usuarios) {
        try {
            Persistencia.serializarObjeto(Constantes.RUTA_USUARIOS, usuarios);
        } catch (IOException e) {
            System.err.println("Error guardando usuarios: " + e.getMessage());
        }
    }
}
