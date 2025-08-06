package co.edu.uniquindio.empresaalojamiento.repositorios;

import co.edu.uniquindio.empresaalojamiento.conexion.ConexionDB;
import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Resena;
import co.edu.uniquindio.empresaalojamiento.repositorios.interfaces.IResenaRepositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResenaRepositorio implements IResenaRepositorio {

    public ResenaRepositorio() {
    }

    @Override
    public void agregarResena(Resena resena) {
        String sql = "INSERT INTO resenas(id, valoracion, calificacion, id_usuario, id_alojamiento) VALUES(?,?,?,?,?);";
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, resena.getId());
            stmt.setString(2, resena.getValoracion());
            stmt.setInt(3, resena.getCalificacion());
            stmt.setString(4, resena.getIdUsuario());
            stmt.setString(5, resena.getIdAlojamiento());
            stmt.executeUpdate();
            System.out.println("Resena agregada con exito");

        } catch (SQLException e) {
            throw new RuntimeException("Se ha producido un error al agregar la reseña: " + e.getMessage());
        }

    }

    @Override
    public void elimnarResena(Resena resena) {
        String sql = "DELETE FROM resenas WHERE id = ?;";
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, resena.getId());
            stmt.executeUpdate();
            System.out.println("Resena eliminada con exito");
        } catch (SQLException e) {
            throw new RuntimeException("Se ha producido un error al eliminar la resena: " + e.getMessage());
        }
    }

    @Override
    public Resena buscarResena(String id) {
        String sql = "SELECT * FROM resenas WHERE id = ?;";
        Resena resena = null;
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                resena = new Resena(rs.getString("id"), rs.getString("valoracion"),
                        rs.getInt("calificacion"), rs.getString("id_usuario"), rs.getString("id_alojamiento"));
            }
            return resena;

        } catch (SQLException e) {
            throw new RuntimeException("Se ha producido un error al buscar la resena: " + e.getMessage());
        }
    }

    @Override
    public List<Resena> obtenerResenasAlojamiento(String idAlojamiento) {
        String sql = """
                SELECT r.id, r.valoracion, r.calificacion, r.id_usuario, r.id_alojamiento
                FROM resenas r
                INNER JOIN alojamientos a ON r.id_alojamiento = a.id
                WHERE r.id_alojamiento = ?;
                """;
        List<Resena> resenas = new ArrayList<>();
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idAlojamiento);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Resena resena = new Resena(rs.getString("id"), rs.getString("valoracion"),
                        rs.getInt("calificacion"), rs.getString("id_usuario"), rs.getString("id_alojamiento"));
                resenas.add(resena);
            }
            return resenas;
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener las resenas del alojamiento con id " + idAlojamiento + ": " + e.getMessage());
        }
    }

    public List<Resena> obtenerResenas() {
        String sql = "SELECT * FROM resenas;";
        List<Resena> resenas = new ArrayList<>();
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Resena resena = new Resena(rs.getString("id"), rs.getString("valoracion"),
                        rs.getInt("calificacion"), rs.getString("id_usuario"), rs.getString("id_alojamiento"));
                resenas.add(resena);
            }
            return resenas;
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener todas las resenas: " + e.getMessage());
        }
    }
}
