package co.edu.uniquindio.empresaalojamiento.controladores;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Label;

public class PantallaBienvenidaControlador {

    @FXML
    private Label lblTitutlo;

    @FXML
    void irPantallaInicio(ActionEvent event) {
        ControladorPrincipal.navegarVentana("/co/edu/uniquindio/empresaalojamiento/inicioSesion2.fxml", "Inicio Sesion", lblTitutlo, getClass());
    }

}

