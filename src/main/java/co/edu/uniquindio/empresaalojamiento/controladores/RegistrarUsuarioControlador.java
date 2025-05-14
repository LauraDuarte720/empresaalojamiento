package co.edu.uniquindio.empresaalojamiento.controladores;


import co.edu.uniquindio.empresaalojamiento.singleton.Sesion;
import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
    private PasswordField txtContrasena;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtTelefono;

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

}



