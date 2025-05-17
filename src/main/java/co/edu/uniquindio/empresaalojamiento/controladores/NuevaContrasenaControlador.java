package co.edu.uniquindio.empresaalojamiento.controladores;

import co.edu.uniquindio.empresaalojamiento.servicios.EmpresaAlojamientoServicio;
import co.edu.uniquindio.empresaalojamiento.singleton.Sesion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

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
        txtNuevaContrasenaField.textProperty().bindBidirectional(txtNuevaContrasena.textProperty());
        txtNuevaContrasenaConfirmarField.textProperty().bindBidirectional(txtNuevaContrasenaConfirmar.textProperty());

        txtNuevaContrasenaField.textProperty().bindBidirectional(txtNuevaContrasena.textProperty());
        txtNuevaContrasenaConfirmarField.textProperty().bindBidirectional(txtNuevaContrasenaConfirmar.textProperty());


        ControladorPrincipal.setMostrarContrasena(false, btnContrasena, txtNuevaContrasena, txtNuevaContrasenaField, getClass());
        ControladorPrincipal.setMostrarContrasena(false, btnConfirmarContrasena, txtNuevaContrasenaConfirmar, txtNuevaContrasenaConfirmarField, getClass());
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
        mostrandoTexto1 = ControladorPrincipal.setMostrarContrasena(!mostrandoTexto1, btnContrasena, txtNuevaContrasena, txtNuevaContrasenaField, getClass());
    }

    @FXML
    void verContrasenaConfirmacion(ActionEvent event) {
        mostrandoTexto2 = ControladorPrincipal.setMostrarContrasena(!mostrandoTexto2, btnConfirmarContrasena, txtNuevaContrasenaConfirmar, txtNuevaContrasenaConfirmarField, getClass());
    }

}
