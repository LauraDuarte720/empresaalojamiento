package co.edu.uniquindio.empresaalojamiento.repositorios;

import co.edu.uniquindio.empresaalojamiento.conexion.ConexionDB;
import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Oferta;
import co.edu.uniquindio.empresaalojamiento.repositorios.interfaces.IOfertaRepositorio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class OfertaRepositorio implements IOfertaRepositorio {


    public OfertaRepositorio() {

    }


    @Override
    public void agregarOferta(Oferta oferta) {
        String sql = "INSERT INTO ofertas (id, fecha_inicio, fecha_final, valor_porcentaje, id_alojamiento, descripcion)" +
                " VALUES (?,?,?,?,?,?);";
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, oferta.getId());
            stmt.setDate(2, Date.valueOf(oferta.getFechaInicio()));
            stmt.setDate(3, Date.valueOf(oferta.getFechaFinal()));
            stmt.setDouble(4, oferta.getValorPorcentaje());
            stmt.setString(5, oferta.getIdAlojamiento());
            stmt.setString(6, oferta.getDescripcion());
            stmt.executeUpdate();
            System.out.println("Se agrego el oferta correctamente");
        } catch (SQLException e) {
            throw new RuntimeException("Error al agregar la oferta: " + e);

        }

    }

    @Override
    public void eliminarOferta(Oferta oferta) {
        String sql = "DELETE FROM ofertas WHERE id = ?;";
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, oferta.getId());
            stmt.executeUpdate();
            System.out.println("Se eliminó el oferta correctamente");
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar la oferta: " + e);
        }

    }

    @Override
    public Oferta buscarOferta(String id) {
        String sql = "SELECT * FROM ofertas WHERE id = ?;";
        Oferta oferta = null;
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                oferta = Oferta.builder()
                        .id(rs.getString("id"))
                        .fechaInicio((rs.getDate("fecha_inicio")).toLocalDate())
                        .fechaFinal((rs.getDate("fecha_final")).toLocalDate())
                        .valorPorcentaje(rs.getDouble("valor_porcentaje"))
                        .idAlojamiento(rs.getString("id_alojamiento"))
                        .descripcion(rs.getString("descripcion"))
                        .build();
                System.out.println("Oferta encontrada con éxito");
            } else {
                System.out.println("No existe la oferta con id: " + id);
            }
            return oferta;

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar la oferta: " + e);
        }
    }

    @Override
    public List<Oferta> obtenerOfertasAlojamiento(String idAlojamiento) {

        String sql = """
                SELECT o.id, o.fecha_inicio, o.fecha_final, o.valor_porcentaje,
                o.id_alojamiento, o.descripcion 
                FROM ofertas o
                INNER JOIN alojamientos al ON al.id = o.id_alojamiento
                WHERE al.id = ?;
                """;
        List<Oferta> ofertas = new ArrayList<>();
        Oferta oferta;

        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idAlojamiento);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                oferta = Oferta.builder()
                        .id(rs.getString("id"))
                        .fechaInicio((rs.getDate("fecha_inicio")).toLocalDate())
                        .fechaFinal((rs.getDate("fecha_final")).toLocalDate())
                        .valorPorcentaje(rs.getDouble("valor_porcentaje"))
                        .idAlojamiento(rs.getString("id_alojamiento"))
                        .descripcion(rs.getString("descripcion"))
                        .build();
                ofertas.add(oferta);
            }
            return ofertas;


        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener todas las ofertas: " + e);
        }
    }

    public List<Oferta> obtenerOfertas() {
        String sql = "SELECT * FROM ofertas;";
        List<Oferta> ofertas = new ArrayList<>();
        Oferta oferta;

        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                oferta = Oferta.builder()
                        .id(rs.getString("id"))
                        .fechaInicio((rs.getDate("fecha_inicio")).toLocalDate())
                        .fechaFinal((rs.getDate("fecha_final")).toLocalDate())
                        .valorPorcentaje(rs.getDouble("valor_porcentaje"))
                        .idAlojamiento(rs.getString("id_alojamiento"))
                        .descripcion(rs.getString("descripcion"))
                        .build();
                ofertas.add(oferta);
            }
            return ofertas;


        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener todas las ofertas: " + e);
        }
    }
}
