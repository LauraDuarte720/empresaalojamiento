package co.edu.uniquindio.empresaalojamiento.repositorios;

import co.edu.uniquindio.empresaalojamiento.conexion.ConexionDB;
import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Billetera;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BilleteraRepositorio {
    public BilleteraRepositorio() {

    }

    public static void agregarBilletera(Billetera billetera) {
        String sql = "INSERT INTO billeteras(id, saldo, usuario_id) VALUES (?,?,?);";
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, billetera.getId());
            stmt.setDouble(2, billetera.getSaldo());
            stmt.setString(3, billetera.getIdUsuario());
            stmt.executeUpdate();
            System.out.println("Billetera agregada con exito");
        } catch (SQLException e) {
            throw new RuntimeException("Error al agregar el billetera del usuario con id " + billetera.getIdUsuario() + ": " + billetera.getId());
        }
    }

    public static void eliminarBilletera(Billetera billetera) {
        String sql = "DELETE FROM billeteras WHERE id = ?;";
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, billetera.getId());
            stmt.executeUpdate();
            System.out.println("Billetera eliminada con exito");
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar la billetera con id " + billetera.getId() + ": " + e.getMessage());
        }

    }
}
