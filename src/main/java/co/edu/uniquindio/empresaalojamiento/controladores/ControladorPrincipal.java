package co.edu.uniquindio.empresaalojamiento.controladores;

import co.edu.uniquindio.empresaalojamiento.servicios.EmpresaAlojamientoServicio;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import lombok.Getter;

@Getter
public class ControladorPrincipal {

    private static ControladorPrincipal instancia;
    private final EmpresaAlojamientoServicio empresaAlojamiento;


    private ControladorPrincipal(){
        empresaAlojamiento = new EmpresaAlojamientoServicio();
    }


    public static ControladorPrincipal getInstancia(){
        if(instancia == null){
            instancia = new ControladorPrincipal();
        }
        return instancia;
    }

    public void cerrarVentana(Node node){
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }


    public void crearAlerta(String mensaje, Alert.AlertType tipo){
        Alert alert = new Alert(tipo);
        alert.setHeight(300);
        alert.setWidth(500);
        alert.setTitle("Alerta");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public static void navegarVentana(String fxml, String titulo, Node nodo, Class<?> clase) {
        try {
            FXMLLoader loader = new FXMLLoader(clase.getResource(fxml));
            Parent root = loader.load();
            Stage stage = (Stage) nodo.getScene().getWindow();
            stage.setTitle(titulo);
            stage.setScene(new Scene(root));
            stage.centerOnScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
