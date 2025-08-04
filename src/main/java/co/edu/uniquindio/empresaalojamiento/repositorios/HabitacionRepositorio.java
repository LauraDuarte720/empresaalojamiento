package co.edu.uniquindio.empresaalojamiento.repositorios;

import co.edu.uniquindio.empresaalojamiento.conexion.ConexionDB;
import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Habitacion;
import co.edu.uniquindio.empresaalojamiento.repositorios.interfaces.IHabitacionRepositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HabitacionRepositorio implements IHabitacionRepositorio {

    @Override
    public void agregarHabitacion(Habitacion habitacion) {
        String sql = "INSERT INTO habitaciones (id, numero, precio_por_noche, capacidad_huespedes, ruta_imagen, descripcion, id_hotel) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, habitacion.getId());
            stmt.setInt(2, habitacion.getNumero());
            stmt.setDouble(3, habitacion.getPrecioPorNoche());
            stmt.setInt(4, habitacion.getCapacidadHuespedes());
            stmt.setString(5, habitacion.getRutaImagen());
            stmt.setString(6, habitacion.getDescripcion());
            stmt.setString(7, habitacion.getIdHotel());
            stmt.executeUpdate();
            System.out.println("Habitación agregada con éxito");
        } catch (SQLException e) {
            throw new RuntimeException("Error al agregar una habitación:  " + e);
        }

    }

    @Override
    public void eliminarHabitacion(Habitacion habitacion) {
        String sql = "DELETE FROM habitaciones WHERE id = ?;";
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, habitacion.getId());
            stmt.executeUpdate();
            System.out.println("Habitacion elimnada con exito");
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar el alojamiento:  " + e);
        }
    }

    @Override
    public Habitacion buscarHabitacion(String id) {
        String sql = "SELECT * FROM habitaciones WHERE id = ?;";
        Habitacion habitacion = null;
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                habitacion = Habitacion.builder()
                        .id(rs.getString("id"))
                        .numero(rs.getInt("numero"))
                        .precioPorNoche(rs.getDouble("precio_por_noche"))
                        .capacidadHuespedes(rs.getInt("capacidad_huespedes"))
                        .rutaImagen(rs.getString("ruta_imagen"))
                        .descripcion(rs.getString("descripcion"))
                        .idHotel(rs.getString("id_hotel"))
                        .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar el habitacion:  " + e);
        }
        return habitacion;
    }

    @Override
    public List<Habitacion> obtenerHabitacionesHotel(String idHotel) {
        Habitacion habitacion = null;
        ArrayList<Habitacion> habitacionesHotel = new ArrayList<>();
        String sql = """
                SELECT h.id, h.numero, h.precio_por_noche, h.capacidad_huespedes, h.ruta_imagen, h.descripcion, h.id_hotel
                FROM habitaciones h
                INNER JOIN alojamientos ht ON h.id_hotel = ht.id
                WHERE ht.id = ?;
                """;
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idHotel);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                habitacion = Habitacion.builder()
                        .id(rs.getString("id"))
                        .numero(rs.getInt("numero"))
                        .precioPorNoche(rs.getDouble("precio_por_noche"))
                        .capacidadHuespedes(rs.getInt("capacidad_huespedes"))
                        .rutaImagen(rs.getString("ruta_imagen"))
                        .descripcion(rs.getString("descripcion"))
                        .idHotel(rs.getString("id_hotel"))
                        .build();
                habitacionesHotel.add(habitacion);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error obteninedo las habitaciones del hotel " + e);
        }
        return habitacionesHotel;

    }

    public List<Habitacion> obtenerHabitaciones() {
        String sql = "SELECT * FROM habitaciones;";
        Habitacion habitacion;
        ArrayList<Habitacion> habitaciones = new ArrayList<>();
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement stmt = con.prepareStatement(sql)
        ) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                habitacion = Habitacion.builder()
                        .id(rs.getString("id"))
                        .numero(rs.getInt("numero"))
                        .precioPorNoche(rs.getDouble("precio_por_noche"))
                        .capacidadHuespedes(rs.getInt("capacidad_huespedes"))
                        .rutaImagen(rs.getString("ruta_imagen"))
                        .descripcion(rs.getString("descripcion"))
                        .idHotel(rs.getString("id_hotel"))
                        .build();
                habitaciones.add(habitacion);
            }
            return habitaciones;
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener los alojamientos: " + e.getMessage());
        }
    }


}
