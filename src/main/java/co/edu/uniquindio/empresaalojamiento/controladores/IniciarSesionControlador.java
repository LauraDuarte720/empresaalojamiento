package co.edu.uniquindio.empresaalojamiento.controladores;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Sesion;
import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Usuario;
import co.edu.uniquindio.empresaalojamiento.servicios.EmpresaAlojamientoServicio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class IniciarSesionControlador {

    @FXML
    private ImageView imgIconoCorreo;

    @FXML
    private TextField txtContrasenaSesion;

    @FXML
    private TextField txtCorreoIniciarSesion;


    private final EmpresaAlojamientoServicio empresaAlojamientoServicio = ControladorPrincipal.getInstancia().getEmpresaAlojamiento();

    @FXML
    void cambiarContrasena(ActionEvent event) {

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
            empresaAlojamientoServicio.enviarCodigo(usuario.getCedula());
            ControladorPrincipal.navegarVentana("/co/edu/uniquindio/empresaalojamiento/codigoRegistro.fxml", "Codigo registro", txtCorreoIniciarSesion, getClass());

        }
        catch(Exception e){
            ControladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }

    }

}

