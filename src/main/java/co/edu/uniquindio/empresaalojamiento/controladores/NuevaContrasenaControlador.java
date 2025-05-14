package co.edu.uniquindio.empresaalojamiento.controladores;

import co.edu.uniquindio.empresaalojamiento.servicios.EmpresaAlojamientoServicio;
import co.edu.uniquindio.empresaalojamiento.singleton.Sesion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Control;
import javafx.scene.control.PasswordField;

public class NuevaContrasenaControlador {

    @FXML
    private PasswordField txtNuevaContrasena;

    @FXML
    private PasswordField txtNuevaContrasenaCambiar;

    private final EmpresaAlojamientoServicio empresaAlojamientoServicio = ControladorPrincipal.getInstancia().getEmpresaAlojamiento();

    @FXML
    void cambiarContrasena(ActionEvent event) {
        try{
            empresaAlojamientoServicio.cambiarContrasena(Sesion.getInstancia().getUsuario(), txtNuevaContrasena.getText(), txtNuevaContrasenaCambiar.getText());
            ControladorPrincipal.crearAlerta("Se ha cambiado la contraseña correctamente", Alert.AlertType.INFORMATION);
            ControladorPrincipal.navegarVentana("/co/edu/uniquindio/empresaalojamiento/inicioSesion.fxml", "Inicio Sesion", txtNuevaContrasena, getClass());
        }
        catch(Exception e){
            ControladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

}
