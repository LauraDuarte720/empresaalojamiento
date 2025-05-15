package co.edu.uniquindio.empresaalojamiento.controladores;

import co.edu.uniquindio.empresaalojamiento.servicios.EmpresaAlojamientoServicio;
import co.edu.uniquindio.empresaalojamiento.singleton.Sesion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

public class NuevaContrasenaControlador {

    @FXML
    private Button btnConfirmarContrasena;

    @FXML
    private Button btnContrasena;

    @FXML
    private PasswordField txtNuevaContrasena;

    @FXML
    private PasswordField txtNuevaContrasenaConfirmar;

    @FXML
    private TextField txtNuevaContrasenaConfirmarField;

    @FXML
    private TextField txtNuevaContrasenaField;

    private boolean mostrandoTexto1;
    private boolean mostrandoTexto2;


    private final EmpresaAlojamientoServicio empresaAlojamientoServicio = ControladorPrincipal.getInstancia().getEmpresaAlojamiento();

    @FXML
    void initialize() {
        ControladorPrincipal.cambiarEfectoHooverBoton(btnContrasena, "/co/edu/uniquindio/empresaalojamiento/Imagenes/ojoCerradoOscuro.png");
        ControladorPrincipal.cambiarEfectoHooverBoton(btnConfirmarContrasena, "/co/edu/uniquindio/empresaalojamiento/Imagenes/ojoCerradoOscuro.png");
        mostrandoTexto1 = false;
        mostrandoTexto2 = false;
    }

    @FXML
    void cambiarContrasena(ActionEvent event) {
        try{
            empresaAlojamientoServicio.cambiarContrasena(Sesion.getInstancia().getUsuario(), txtNuevaContrasena.getText(), txtNuevaContrasenaConfirmar.getText());
            ControladorPrincipal.crearAlerta("Se ha cambiado la contraseña correctamente", Alert.AlertType.INFORMATION);
            ControladorPrincipal.navegarVentana("/co/edu/uniquindio/empresaalojamiento/inicioSesion.fxml", "Inicio Sesion", txtNuevaContrasena, getClass());
        }
        catch(Exception e){
            ControladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    void verContrasena(ActionEvent event) {
        if (mostrandoTexto1) {
            txtNuevaContrasenaField.setVisible(false);
            txtNuevaContrasenaField.setManaged(false);
            txtNuevaContrasena.setVisible(true);
            txtNuevaContrasena.setManaged(true);
            mostrandoTexto1 = false;
        } else {
            txtNuevaContrasenaField.setVisible(true);
            txtNuevaContrasenaField.setManaged(true);
            txtNuevaContrasena.setVisible(false);
            txtNuevaContrasena.setManaged(false);
            mostrandoTexto1 = true;
        }
    }

    @FXML
    void verContrasenaConfirmacion(ActionEvent event) {
        if (mostrandoTexto2) {
            txtNuevaContrasenaConfirmarField.setVisible(false);
            txtNuevaContrasenaConfirmarField.setManaged(false);
            txtNuevaContrasenaConfirmar.setVisible(true);
            txtNuevaContrasenaConfirmar.setManaged(true);
            mostrandoTexto2 = false;
        } else {
            txtNuevaContrasenaConfirmarField.setVisible(true);
            txtNuevaContrasenaConfirmarField.setManaged(true);
            txtNuevaContrasenaConfirmar.setVisible(false);
            txtNuevaContrasenaConfirmar.setManaged(false);
            mostrandoTexto2 = true;
        }

    }

}
