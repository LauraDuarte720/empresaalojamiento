package co.edu.uniquindio.empresaalojamiento.controladores;


import co.edu.uniquindio.empresaalojamiento.singleton.Sesion;
import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class RegistrarUsuarioControlador {

    @FXML
    private ImageView imgIconoApellido;

    @FXML
    private ImageView imgIconoCedula;

    @FXML
    private ImageView imgIconoContrasena;

    @FXML
    private ImageView imgIconoCorreo;

    @FXML
    private ImageView imgIconoNombre;

    @FXML
    private ImageView imgIconoTelefono;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtCedula;

    @FXML
    private TextField txtContrasena;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtTelefono;

    @FXML
    private Button btnVerContrasena;

    @FXML
    private PasswordField txtPasContrasenaSesion;

    private boolean mostrandoTexto;


    @FXML
    void initialize() {
        ControladorPrincipal.cambiarEfectoHooverBoton(btnVerContrasena, "/co/edu/uniquindio/empresaalojamiento/imagenesIconos/ojoCerradoOscuro.png");
        txtContrasena.textProperty().bindBidirectional(txtPasContrasenaSesion.textProperty());
        txtPasContrasenaSesion.textProperty().bindBidirectional(txtContrasena.textProperty());


        ControladorPrincipal.setMostrarContrasena(false, btnVerContrasena, txtPasContrasenaSesion, txtContrasena, getClass());
    }

    @FXML
    void registrarUsuario(ActionEvent event) {
        try {
            Usuario usuario = ControladorPrincipal.getInstancia().getEmpresaAlojamiento().registrarUsuario(txtCedula.getText(), txtNombre.getText(), txtApellido.getText(), txtTelefono.getText(), txtCorreo.getText(), txtContrasena.getText());
            ControladorPrincipal.crearAlerta("Se ha registrado el usuario correctamente", Alert.AlertType.INFORMATION);
            ControladorPrincipal.getInstancia().getEmpresaAlojamiento().enviarCodigo(txtCorreo.getText());
            Sesion.getInstancia().setUsuario(usuario);
            ControladorPrincipal.navegarVentana("/co/edu/uniquindio/empresaalojamiento/codigoRegistro.fxml", "Inicio Sesion", txtTelefono, getClass());
        } catch (Exception e) {
            ControladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    void regresar(ActionEvent event) {
        ControladorPrincipal.navegarVentana("/co/edu/uniquindio/empresaalojamiento/inicioSesion.fxml", "Inicio Sesion", txtTelefono, getClass());
    }

    @FXML
    void verContrasena(ActionEvent event) {
        mostrandoTexto = ControladorPrincipal.setMostrarContrasena(!mostrandoTexto, btnVerContrasena, txtPasContrasenaSesion, txtContrasena, getClass());
    }

}



