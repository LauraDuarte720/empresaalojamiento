package co.edu.uniquindio.empresaalojamiento.repositorios;

import co.edu.uniquindio.empresaalojamiento.conexion.ConexionDB;
import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Reserva;
import co.edu.uniquindio.empresaalojamiento.repositorios.interfaces.IReservaRepositorio;
import co.edu.uniquindio.empresaalojamiento.utilidades.Constantes;
import co.edu.uniquindio.empresaalojamiento.utilidades.Persistencia;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReservaRepositorio implements IReservaRepositorio {

    public ReservaRepositorio() {
    }

    @Override
    public void agregarReserva(Reserva reserva) {
        String sql = "INSERT INTO reservas(id, fecha_inicio, fecha_final, numero_huespedes, id_alojamiento, id_habitacion, codigo_factura, id_usuario) VALUES (?,?,?,?,?,?,?,?);";
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, reserva.getId());
            stmt.setString(2, reserva.getFechaInicio().toString());
            stmt.setString(3, reserva.getFechaFinal().toString());
            stmt.setInt(4, reserva.getNumeroHuespedes());
            stmt.setString(5, reserva.getIdAlojamiento());
            stmt.setString(6, reserva.getIdHabitacion());
            stmt.setString(7, reserva.getFactura().getCodigo());
            stmt.setString(8, reserva.getIdUsuario());
            stmt.executeUpdate();
            System.out.println("Reserva agregada con exito");

        } catch (SQLException e) {
            throw new RuntimeException("Error al agregar el reserva: " + e.getMessage());
        }

        String sql2 = "INSERT INTO facturas(codigo, subtotal, total, fecha) VALUES (?,?,?,?);";
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql2)) {
            stmt.setString(1, reserva.getFactura().getCodigo());
            stmt.setDouble(2, reserva.getFactura().getSubtotal());
            stmt.setDouble(3, reserva.getFactura().getTotal());
            stmt.setDate(4, Date.valueOf(reserva.getFactura().getFecha()));
            stmt.executeUpdate();
            System.out.println("Factura agregada con exito");
        } catch (SQLException e) {
            throw new RuntimeException("Error al agregar el factura: " + e.getMessage());
        }
    }

    @Override
    public Reserva buscarReserva(String id) {
        String sql = "SELECT * FROM reservas WHERE id = ?;";
        Reserva reserva = null;
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {

                reserva = Reserva.builder()
                        .id(rs.getString("id"))
                        .fechaInicio(rs.getDate("fecha_inicio").toLocalDate())
                        .fechaFinal(rs.getDate("fecha_final").toLocalDate())
                        .numeroHuespedes(rs.getInt("numero_huespedes"))
                        .idAlojamiento(rs.getString("id_alojamiento"))
                        .idHabitacion(rs.getString("id_habitacion"))
                        .factura(FacturaRespositorio.obtenerFactura(rs.getString("codigo_factura")))
                        .idUsuario(rs.getString("id_usuario"))
                        .build();
                System.out.println("Reserva encontrada con exito");
            }
            return reserva;
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar el reserva: " + e.getMessage());
        }
    }

    @Override
    public void eliminarReserva(Reserva reserva) {
        String sql = "DELETE FROM reservas WHERE id = ?;";
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, reserva.getId());
            stmt.executeUpdate();
            System.out.println("Reserva eliminada con exito");
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar la reserva: " + e.getMessage());
        }
        FacturaRespositorio.eliminarFactura(reserva.getFactura().getCodigo());
    }

    @Override
    public List<Reserva> obtenerReservas() {

        String sql = "SELECT * FROM reservas;";
        List<Reserva> reservas = new ArrayList<>();
        Reserva reserva;
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                reserva = Reserva.builder()
                        .id(rs.getString("id"))
                        .fechaInicio(rs.getDate("fecha_inicio").toLocalDate())
                        .fechaFinal(rs.getDate("fecha_final").toLocalDate())
                        .numeroHuespedes(rs.getInt("numero_huespedes"))
                        .idAlojamiento(rs.getString("id_alojamiento"))
                        .idHabitacion(rs.getString("id_habitacion"))
                        .factura(FacturaRespositorio.obtenerFactura(rs.getString("codigo_factura")))
                        .idUsuario(rs.getString("id_usuario"))
                        .build();
                reservas.add(reserva);
            }
            return reservas;

        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener todas las reservas: " + e.getMessage());
        }
    }

    @Override
    public List<Reserva> obtenerReservasUsuario(String idUsuario) {

        String sql = """
        SELECT r.id, r.fecha_inicio, r.fecha_final, r.numero_huespedes, r.id_alojamiento, r.id_habitacion, r.codigo_factura, r.id_usuario
        FROM reservas r
        INNER JOIN usuarios U ON r.id_usuario = u.id
        WHERE r.id_usuario = ?;
        """;

        List<Reserva> reservas = new ArrayList<>();
        Reserva reserva;
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idUsuario);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                reserva = Reserva.builder()
                        .id(rs.getString("id"))
                        .fechaInicio(rs.getDate("fecha_inicio").toLocalDate())
                        .fechaFinal(rs.getDate("fecha_final").toLocalDate())
                        .numeroHuespedes(rs.getInt("numero_huespedes"))
                        .idAlojamiento(rs.getString("id_alojamiento"))
                        .idHabitacion(rs.getString("id_habitacion"))
                        .factura(FacturaRespositorio.obtenerFactura(rs.getString("codigo_factura")))
                        .idUsuario(rs.getString("id_usuario"))
                        .build();
                reservas.add(reserva);
            }
            return reservas;

        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener todas las reservas: " + e.getMessage());
        }
    }

    @Override
    public List<Reserva> obtenerReservasAlojamiento(String idAlojamiento) {

        String sql = """
        SELECT r.id, r.fecha_inicio, r.fecha_final, r.numero_huespedes, r.id_alojamiento, r.id_habitacion, r.codigo_factura, r.id_usuario
        FROM reservas r
        INNER JOIN alojamientos a ON r.id_alojamiento = a.id
        WHERE r.id_alojamiento = ?;
        """;

        List<Reserva> reservas = new ArrayList<>();
        Reserva reserva;
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idAlojamiento);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                reserva = Reserva.builder()
                        .id(rs.getString("id"))
                        .fechaInicio(rs.getDate("fecha_inicio").toLocalDate())
                        .fechaFinal(rs.getDate("fecha_final").toLocalDate())
                        .numeroHuespedes(rs.getInt("numero_huespedes"))
                        .idAlojamiento(rs.getString("id_alojamiento"))
                        .idHabitacion(rs.getString("id_habitacion"))
                        .factura(FacturaRespositorio.obtenerFactura(rs.getString("codigo_factura")))
                        .idUsuario(rs.getString("id_usuario"))
                        .build();
                reservas.add(reserva);
            }
            return reservas;

        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener las reservas del alojamiento con id " + idAlojamiento + " : " + e.getMessage());
        }
    }

    @Override
    public List<Reserva> obtenerReservasHabitacion(String idHabitacion) {

        String sql = """
        SELECT r.id, r.fecha_inicio, r.fecha_final, r.numero_huespedes, r.id_alojamiento, r.id_habitacion, r.codigo_factura, r.id_usuario
        FROM reservas r
        INNER JOIN habitaciones h ON r.id_habitacion = h.id
        WHERE r.id_habitacion = ?;
        """;

        List<Reserva> reservas = new ArrayList<>();
        Reserva reserva;
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idHabitacion);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                reserva = Reserva.builder()
                        .id(rs.getString("id"))
                        .fechaInicio(rs.getDate("fecha_inicio").toLocalDate())
                        .fechaFinal(rs.getDate("fecha_final").toLocalDate())
                        .numeroHuespedes(rs.getInt("numero_huespedes"))
                        .idAlojamiento(rs.getString("id_alojamiento"))
                        .idHabitacion(rs.getString("id_habitacion"))
                        .factura(FacturaRespositorio.obtenerFactura(rs.getString("codigo_factura")))
                        .idUsuario(rs.getString("id_usuario"))
                        .build();
                reservas.add(reserva);
            }
            return reservas;

        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener las reservas de la habitació con id " + idHabitacion + " : " + e.getMessage());
        }
    }
}

