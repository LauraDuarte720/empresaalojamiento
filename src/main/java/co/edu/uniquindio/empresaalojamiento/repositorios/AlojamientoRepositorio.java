package co.edu.uniquindio.empresaalojamiento.repositorios;

import co.edu.uniquindio.empresaalojamiento.conexion.ConexionDB;
import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Alojamiento;
import co.edu.uniquindio.empresaalojamiento.modelo.enums.Ciudad;
import co.edu.uniquindio.empresaalojamiento.modelo.enums.TipoAlojamiento;
import co.edu.uniquindio.empresaalojamiento.repositorios.interfaces.IAlojamientoRepositorio;
import co.edu.uniquindio.empresaalojamiento.utilidades.Constantes;
import co.edu.uniquindio.empresaalojamiento.utilidades.Persistencia;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class AlojamientoRepositorio implements IAlojamientoRepositorio {

    public AlojamientoRepositorio() {
    }


    @Override
    public void agregarAlojamiento(Alojamiento alojamiento) {
        String sql = "INSERT INTO alojamientos (" +
                "id, tipo_alojamiento, nombre, descripcion, ruta, ciudad, " +
                "precio_por_noche, capacidad_maxima_huespedes, calificacion_promedio, " +
                "costo_adicional, piscina, wifi, desayuno, parqueadero, mascotas_permitidas, gym" +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, alojamiento.getId());
            stmt.setString(2, alojamiento.getTipoAlojamiento().name());
            stmt.setString(3, alojamiento.getNombre());
            stmt.setString(4, alojamiento.getDescripcion());
            stmt.setString(5, alojamiento.getRuta());
            stmt.setString(6, alojamiento.getCiudad().name());
            stmt.setDouble(7, alojamiento.getPrecioPorNoche());
            stmt.setInt(8, alojamiento.getCapacidadMaximaHuespedes());
            stmt.setFloat(9, alojamiento.getCalificacionPromedio());
            stmt.setDouble(10, alojamiento.getCostoAdicional());
            stmt.setBoolean(11, alojamiento.isPiscina());
            stmt.setBoolean(12, alojamiento.isWifi());
            stmt.setBoolean(13, alojamiento.isDesayuno());
            stmt.setBoolean(14, alojamiento.isParqueadro());
            stmt.setBoolean(15, alojamiento.isMascotasPermitidas());
            stmt.setBoolean(16, alojamiento.isGym());

            stmt.executeUpdate();
            System.out.println("Alojamiento insertado con éxito");
        } catch (SQLException e) {
            System.err.println("Error al insertar alojamiento: " + e.getMessage());
        }

    }

    @Override
    public void eliminarAlojamiento(Alojamiento alojamiento) {
        String sql = "DELETE FROM alojamientos WHERE id = ?;";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, alojamiento.getId());
            stmt.executeUpdate();
            System.out.println("Alojamiento eliminado con éxito");
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar el alojamiento" + e.getMessage());
        }
    }

    @Override
    public Alojamiento buscarAlojamiento(String id) {
        String sql = "SELECT * FROM alojamientos WHERE id = ?;";
        Alojamiento alojamiento = null;
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                alojamiento = Alojamiento.builder().tipoAlojamiento(TipoAlojamiento.valueOf(rs.getString("tipo_alojamiento")))
                        .id(rs.getString("id"))
                        .nombre(rs.getString("nombre"))
                        .descripcion(rs.getString("descripcion"))
                        .ruta(rs.getString("ruta"))
                        .ciudad(Ciudad.valueOf(rs.getString("ciudad")))
                        .precioPorNoche(rs.getDouble("precio_por_noche"))
                        .capacidadMaximaHuespedes(rs.getInt("capacidad_maxima_huespedes"))
                        .calificacionPromedio(rs.getFloat("calificacion_promedio"))
                        .costoAdicional(rs.getDouble("costo_adicional"))
                        .piscina(rs.getBoolean("piscina"))
                        .wifi(rs.getBoolean("wifi"))
                        .desayuno(rs.getBoolean("desayuno"))
                        .parqueadro(rs.getBoolean("parqueadero"))
                        .mascotasPermitidas(rs.getBoolean("mascotas_permitidas"))
                        .gym(rs.getBoolean("gym")).build();
                        System.out.println("Alojamiento encontrado con éxito");


            } else {
                System.out.println("No se encontró el alojamiento con id: " + id);
            }
            return alojamiento;
        } catch (SQLException e) {
            throw new RuntimeException("Se produjo un error al realizar la busqueda: " + e);
        }
    }

    @Override
    public List<Alojamiento> obtenerAlojamientos() {
        String sql = "SELECT * FROM alojamientos;";
        Alojamiento alojamiento;
        ArrayList<Alojamiento> alojamientos = new ArrayList<>();

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement stmt = con.prepareStatement(sql)
        ) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                alojamiento = Alojamiento.builder().tipoAlojamiento(TipoAlojamiento.valueOf(rs.getString("tipo_alojamiento")))
                        .id(rs.getString("id"))
                        .nombre(rs.getString("nombre"))
                        .descripcion(rs.getString("descripcion"))
                        .ruta(rs.getString("ruta"))
                        .ciudad(Ciudad.valueOf(rs.getString("ciudad")))
                        .precioPorNoche(rs.getDouble("precio_por_noche"))
                        .capacidadMaximaHuespedes(rs.getInt("capacidad_maxima_huespedes"))
                        .calificacionPromedio(rs.getFloat("calificacion_promedio"))
                        .costoAdicional(rs.getDouble("costo_adicional"))
                        .piscina(rs.getBoolean("piscina"))
                        .wifi(rs.getBoolean("wifi"))
                        .desayuno(rs.getBoolean("desayuno"))
                        .parqueadro(rs.getBoolean("parqueadero"))
                        .mascotasPermitidas(rs.getBoolean("mascotas_permitidas"))
                        .gym(rs.getBoolean("gym")).build();
                alojamientos.add(alojamiento);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener los alojamientos: " + e.getMessage());
        }
        return alojamientos;
    }


    @Override
    public List<Alojamiento> obtenerAlojamientoPorTipo(TipoAlojamiento tipo) {
        String sql = "SELECT * FROM alojamientos WHERE tipo_alojamiento = ?;";
        List<Alojamiento> alojamientosTipo = new ArrayList<>();
        Alojamiento alojamiento;
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement stmt = con.prepareStatement(sql)
        ) {
            stmt.setString(1, tipo.toString());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                alojamiento = Alojamiento.builder().tipoAlojamiento(TipoAlojamiento.valueOf(rs.getString("tipo_alojamiento")))
                        .id(rs.getString("id"))
                        .nombre(rs.getString("nombre"))
                        .descripcion(rs.getString("descripcion"))
                        .ruta(rs.getString("ruta"))
                        .ciudad(Ciudad.valueOf(rs.getString("ciudad")))
                        .precioPorNoche(rs.getDouble("precio_por_noche"))
                        .capacidadMaximaHuespedes(rs.getInt("capacidad_maxima_huespedes"))
                        .calificacionPromedio(rs.getFloat("calificacion_promedio"))
                        .costoAdicional(rs.getDouble("costo_adicional"))
                        .piscina(rs.getBoolean("piscina"))
                        .wifi(rs.getBoolean("wifi"))
                        .desayuno(rs.getBoolean("desayuno"))
                        .parqueadro(rs.getBoolean("parqueadero"))
                        .mascotasPermitidas(rs.getBoolean("mascotas_permitidas"))
                        .gym(rs.getBoolean("gym")).build();
                alojamientosTipo.add(alojamiento);
            }
            return alojamientosTipo;
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener los alojamientos por tipo: " + e.getMessage());
        }
    }


    public void actualizarAlojamiento(String idAlojamiento, String nombre, String descripcion, String ruta,
                                      double precioPorNoche, int capacidadMaximaHuespedes, boolean piscina, boolean wifi, boolean desayuno, double costoAdicional, Ciudad ciudad, boolean parqueadero, boolean mascotasPermitidas, boolean gym) {
        String sql = """
                UPDATE alojamientos SET nombre = ?, descripcion = ?, ruta = ?, precio_por_noche = ?, 
                                        capacidad_maxima_huespedes = ?, piscina = ?, wifi = ?,
                                        desayuno = ?, costo_adicional = ?, ciudad = ?, parqueadero = ?,
                                        mascotas_permitidas = ?, gym = ? WHERE id = ?;
                """;

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, nombre);
            stmt.setString(2, descripcion);
            stmt.setString(3, ruta);
            stmt.setDouble(4, precioPorNoche);
            stmt.setInt(5, capacidadMaximaHuespedes);
            stmt.setBoolean(6, piscina);
            stmt.setBoolean(7, wifi);
            stmt.setBoolean(8, desayuno);
            stmt.setDouble(9, costoAdicional);
            stmt.setString(10, ciudad.toString());
            stmt.setBoolean(11, parqueadero);
            stmt.setBoolean(12, mascotasPermitidas);
            stmt.setBoolean(13, gym);

            // Id del alojamiento obuscado
            stmt.setString(14, idAlojamiento);


            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el alojamiento: " + e.getMessage());
        }
    }

    public List<String> obtenerCamposOpcionales(Alojamiento alojamiento) {
        List<String> presentes = new ArrayList<>();

        if (alojamiento.isPiscina()) {
            presentes.add("Piscina");
        }

        if (alojamiento.isWifi()) {
            presentes.add("Wifi");
        }
        if (alojamiento.isDesayuno()) {
            presentes.add("Desayuno");
        }
        if (alojamiento.isParqueadro()) {
            presentes.add("Parqueadro");
        }
        if (alojamiento.isMascotasPermitidas()) {
            presentes.add("Mascotas permitidas");
        }
        if (alojamiento.isGym()) {
            presentes.add("Gimnasio");
        }
        return presentes;
    }

}
