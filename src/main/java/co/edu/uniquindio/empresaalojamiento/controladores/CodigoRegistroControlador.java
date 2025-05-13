package co.edu.uniquindio.empresaalojamiento.controladores;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Sesion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class CodigoRegistroControlador {

    @FXML
    private TextField txtCodigoVerificacion;

    @FXML
    void aceptar(ActionEvent event){
        try {
            ControladorPrincipal.getInstancia().getEmpresaAlojamiento().activarUsuario(Sesion.getInstancia().getUsuario().getCedula(), txtCodigoVerificacion.getText());
            ControladorPrincipal.crearAlerta("Se ha registrado el usuario correctamente", Alert.AlertType.INFORMATION);
            Sesion.getInstancia().setUsuario(null);
            ControladorPrincipal.navegarVentana("/co/edu/uniquindio/empresaalojamiento/inicioSesion.fxml", "Inicio Sesion", txtCodigoVerificacion, getClass());
        } catch (Exception e) {
            ControladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    void cancelar(ActionEvent event) {

    }

}
