package co.edu.uniquindio.empresaalojamiento.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/empresaalojamiento";
    private static final String USER = "root";
    private static final String PASS = "Joab2007*";

    public static Connection getConexion() throws SQLException {
        Connection conexion = DriverManager.getConnection(URL, USER, PASS);
        System.out.println("✅ Conectado a la base de datos.");
        return conexion;
    }
}