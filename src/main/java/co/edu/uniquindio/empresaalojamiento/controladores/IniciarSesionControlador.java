package co.edu.uniquindio.empresaalojamiento.controladores;

import co.edu.uniquindio.empresaalojamiento.singleton.Sesion;
import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Usuario;
import co.edu.uniquindio.empresaalojamiento.servicios.EmpresaAlojamientoServicio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class IniciarSesionControlador {

    @FXML
    private Button btnVerContrasena;

    @FXML
    private ImageView imgIconoCorreo;

    @FXML
    private TextField txtContrasenaSesion;

    @FXML
    private TextField txtCorreoIniciarSesion;

    @FXML
    private PasswordField txtPasContrasenaSesion;

    private boolean mostrandoTexto;


    private final EmpresaAlojamientoServicio empresaAlojamientoServicio = ControladorPrincipal.getInstancia().getEmpresaAlojamiento();

    @FXML
    void initialize() {
        ControladorPrincipal.cambiarEfectoHooverBoton(btnVerContrasena, "/co/edu/uniquindio/empresaalojamiento/Imagenes/ojoCerradoOscuro.png");
        txtContrasenaSesion.textProperty().bindBidirectional(txtPasContrasenaSesion.textProperty());
        txtPasContrasenaSesion.textProperty().bindBidirectional(txtContrasenaSesion.textProperty());


        ControladorPrincipal.setMostrarContrasena(false, btnVerContrasena, txtPasContrasenaSesion, txtContrasenaSesion, getClass());
    }

    @FXML
    void cambiarContrasena(ActionEvent event) {
        ControladorPrincipal.navegarVentana("/co/edu/uniquindio/empresaalojamiento/cambiarContrasenaCorreo.fxml", "Usuario", txtCorreoIniciarSesion, getClass());
    }

    @FXML
    void iniciarSesion(ActionEvent event) {
        try{
            Usuario usuario = empresaAlojamientoServicio.iniciarSesion(txtCorreoIniciarSesion.getText(), txtContrasenaSesion.getText());
            Sesion.getInstancia().setUsuario(usuario);
            ControladorPrincipal.crearAlerta("Bienvenido " +usuario.getNombre(), Alert.AlertType.INFORMATION);
            ControladorPrincipal.navegarVentana("/co/edu/uniquindio/empresaalojamiento/menuCliente.fxml", "Usuario", txtCorreoIniciarSesion, getClass());
        } catch (IllegalAccessException e) {
            ControladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
            Usuario usuario = empresaAlojamientoServicio.buscarUsuarioCorreo(txtCorreoIniciarSesion.getText());
            Sesion.getInstancia().setUsuario(usuario);
            empresaAlojamientoServicio.enviarCodigo(usuario.getEmail());
            ControladorPrincipal.navegarVentana("/co/edu/uniquindio/empresaalojamiento/codigoRegistro.fxml", "Codigo registro", txtCorreoIniciarSesion, getClass());
        }

        catch(Exception e){
            ControladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }

    }

    @FXML
    void regresar(ActionEvent event) {
        ControladorPrincipal.navegarVentana("/co/edu/uniquindio/empresaalojamiento/inicioSesion.fxml", "Inicio Sesion", txtContrasenaSesion, getClass());
    }

    @FXML
    void verContrasena(ActionEvent event) {
        mostrandoTexto = ControladorPrincipal.setMostrarContrasena(!mostrandoTexto, btnVerContrasena, txtPasContrasenaSesion, txtContrasenaSesion, getClass());
    }

}

