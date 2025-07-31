package co.edu.uniquindio.empresaalojamiento.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

public class InicioSesionControlador {


    @FXML
    private StackPane panelReservas;

    @FXML
    void initialize() {
        Parent node = ControladorPrincipal.cargarPanel("/co/edu/uniquindio/empresaalojamiento/panelAlojamiento.fxml", getClass());
        panelReservas.getChildren().setAll(node);
    }

    @FXML
    void irIniciarSesion(ActionEvent event) {
        ControladorPrincipal.navegarVentana("/co/edu/uniquindio/empresaalojamiento/iniciarSesion.fxml", "Iniciar Sesion", panelReservas, getClass());
    }

    @FXML
    void irRegistrarse(ActionEvent event) {
        ControladorPrincipal.navegarVentana("/co/edu/uniquindio/empresaalojamiento/registrarUsuario.fxml", "Registrar Usuario", panelReservas, getClass());

    }

}
