package co.edu.uniquindio.empresaalojamiento.controladores;

import co.edu.uniquindio.empresaalojamiento.HelloApplication;
import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Alojamiento;
import co.edu.uniquindio.empresaalojamiento.singleton.Sesion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MenuClienteControlador implements Initializable {
    public StackPane panelPrincipal;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        List<Alojamiento> lista = new ArrayList<>();
        lista.add(Alojamiento.builder().nombre("Hola 1").build());
        lista.add(Alojamiento.builder().nombre("Hola 2").build());
        lista.add(Alojamiento.builder().nombre("Hola 3").build());

        VBox vBox = new VBox();
        panelPrincipal.getChildren().add(vBox);

        for (Alojamiento a : lista) {
            Parent raiz = crearItem(a);
            vBox.getChildren().add(raiz);
        }

    }

    public Parent crearItem(Alojamiento a) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("itemAlojamiento.fxml"));
            Parent raiz = fxmlLoader.load();
            ItemAlojamientoControlador controlador = fxmlLoader.getController();
            controlador.setAlojamiento(a);
            Scene scene = new Scene(raiz);
            //scene.
            return raiz;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @FXML
    void actualizaDatos(ActionEvent event) {

    }

    @FXML
    void cerrarSesion(ActionEvent event) {
        ControladorPrincipal.navegarVentana("/co/edu/uniquindio/empresaalojamiento/inicioSesion.fxml", "Inicio Sesion", panelPrincipal, getClass());
        ControladorPrincipal.crearAlerta("¡Hasta luego " + Sesion.getInstancia().getUsuario().getNombre() + "!", Alert.AlertType.INFORMATION);
        Sesion.getInstancia().cerrarSesion();
    }

    @FXML
    void eliminarCuenta(ActionEvent event) {

    }

    @FXML
    void recargarBilletera(ActionEvent event) {

    }

    @FXML
    void verAlojamientos(ActionEvent event) {

    }

    @FXML
    void verReservas(ActionEvent event) {

    }
}
