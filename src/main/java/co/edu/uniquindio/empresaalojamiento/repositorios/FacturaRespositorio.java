package co.edu.uniquindio.empresaalojamiento.repositorios;

import co.edu.uniquindio.empresaalojamiento.conexion.ConexionDB;
import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Factura;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FacturaRespositorio {
    public FacturaRespositorio() {}

    public static Factura obtenerFactura(String Codigo) {
        String sql = "SELECT * FROM facturas WHERE codigo = ?;";
        Factura factura = null;
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setString(1, Codigo);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                factura = new Factura(rs.getDouble("total"), rs.getDouble("subtotal"), rs.getDate("fecha").toLocalDate(), rs.getString("codigo") );
                System.out.println("Se ha encontrado la factura correctamente");
            }
            return factura;

        }
        catch (SQLException e){
            throw new RuntimeException("Se ha producido un error al buscar la factura: " + e);
        }
    }

    public static void eliminarFactura(String codigo) {
        String sql2 = "SELECT * FROM facturas WHERE codigo = ?;";

        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql2)) {
            stmt.setString(1, codigo);
            stmt.executeUpdate();
            System.out.println("Factura eliminada con exito");
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar la factura: " + e.getMessage());
        }
    }
}
