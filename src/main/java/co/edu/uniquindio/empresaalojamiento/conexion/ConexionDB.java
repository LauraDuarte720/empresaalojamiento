package co.edu.uniquindio.empresaalojamiento.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/empresaalojamiento";
    private static final String USER = "root";
    private static final String PASS = "Joab2007*";

    private static Connection conexion = null;

    public static Connection getConexion() {
        if (conexion == null) {
            try {
                conexion = DriverManager.getConnection(URL, USER, PASS);
                System.out.println("✅ Conectado a la base de datos.");
            } catch (SQLException e) {
                System.out.println("❌ Error en la conexión.");
                e.printStackTrace();
            }
        }
        return conexion;
    }
}