package co.edu.uniquindio.empresaalojamiento.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

import java.util.ResourceBundle;

public class InicioSesion2Controlador {


    @FXML
    private StackPane panleReservas;

    @FXML
    void initialize() {
        Parent node = ControladorPrincipal.cargarPanel("/co/edu/uniquindio/empresaalojamiento/panelAlojamiento.fxml", getClass());
        panleReservas.getChildren().setAll(node);
    }

    @FXML
    void irIniciarSesion(ActionEvent event) {
        ControladorPrincipal.navegarVentana("/co/edu/uniquindio/empresaalojamiento/iniciarSesion.fxml", "Iniciar Sesion", panleReservas, getClass());
    }

    @FXML
    void irRegistrarse(ActionEvent event) {
        ControladorPrincipal.navegarVentana("/co/edu/uniquindio/empresaalojamiento/registrarUsuario.fxml", "Registrar Usuario", panleReservas, getClass());

    }

}
