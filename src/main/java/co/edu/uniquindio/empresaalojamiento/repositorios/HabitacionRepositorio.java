package co.edu.uniquindio.empresaalojamiento.repositorios;

import co.edu.uniquindio.empresaalojamiento.conexion.ConexionDB;
import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Habitacion;
import co.edu.uniquindio.empresaalojamiento.repositorios.interfaces.IHabitacionRepositorio;
import co.edu.uniquindio.empresaalojamiento.utilidades.Constantes;
import co.edu.uniquindio.empresaalojamiento.utilidades.Persistencia;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class HabitacionRepositorio implements IHabitacionRepositorio {
    private final HashSet<Habitacion> habitaciones;

    public HabitacionRepositorio() {
        habitaciones = new HashSet<>();
    }

    @Override
    public void agregarHabitacion(Habitacion habitacion) {
        String sql = "INSERT INTO habitaciones (id, numero, precio_por_noche, capacidad_huespedes, ruta_imagen, descripcion, id_hotel) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try(Connection conn = ConexionDB.getConexion();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, habitacion.getId());
            stmt.setInt(2, habitacion.getNumero());
            stmt.setDouble(3, habitacion.getPrecioPorNoche());
            stmt.setInt(4, habitacion.getCapacidadHuespedes());
            stmt.setString(5, habitacion.getRutaImagen());
            stmt.setString(6, habitacion.getDescripcion());
            stmt.setString(7, habitacion.getIdHotel());
        } catch (SQLException e) {
            throw new RuntimeException("Error al agregar una habitación:  " + e);
        }
        habitaciones.add(habitacion);

    }

    @Override
    public void eliminarHabitacion(Habitacion habitacion) {
        habitaciones.remove(habitacion);
    }

    @Override
    public Habitacion buscarHabitacion(String id) {
        return habitaciones.stream().filter(c -> c.getId().equalsIgnoreCase(id)).findFirst().orElse(null);
    }

    @Override
    public List<Habitacion> obtenerHabitacionesHotel(String idHotel) {
        return habitaciones
                .stream()
                .filter(c -> idHotel.equalsIgnoreCase(c.getIdHotel()))
                .collect(Collectors.toList()
                );
    }

    public List<Habitacion> obtenerHabitaciones() {
        return new ArrayList<>(habitaciones);
    }


}
