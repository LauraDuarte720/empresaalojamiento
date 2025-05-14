package co.edu.uniquindio.empresaalojamiento.controladores;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Usuario;
import co.edu.uniquindio.empresaalojamiento.servicios.EmpresaAlojamientoServicio;
import co.edu.uniquindio.empresaalojamiento.singleton.Sesion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CambioContrasenaControlador {

    @FXML
    private Label lblCodigo;

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtCorreo;

    private final EmpresaAlojamientoServicio empresaAlojamientoServicio = ControladorPrincipal.getInstancia().getEmpresaAlojamiento();

    @FXML
    void initialize() {
        lblCodigo.setVisible(false);
        txtCodigo.setVisible(false);
    }

    @FXML
    void enviarCodigo(ActionEvent event) {
        empresaAlojamientoServicio.enviarCodigo(txtCorreo.getText());
        txtCodigo.setVisible(true);
        lblCodigo.setVisible(true);
    }

    @FXML
    void cancelar(ActionEvent event) {
        ControladorPrincipal.navegarVentana("/co/edu/uniquindio/empresaalojamiento/inicarSesion.fxml", "Inicio Sesion", lblCodigo, getClass());
    }

    @FXML
    void aceptar(ActionEvent event) {
        try{
            empresaAlojamientoServicio.validarCambioContrasena(txtCodigo.getText(), txtCorreo.getText());
            Usuario usuario = empresaAlojamientoServicio.buscarUsuarioCorreo(txtCorreo.getText());
            Sesion.getInstancia().setUsuario(usuario);
            ControladorPrincipal.navegarVentana("/co/edu/uniquindio/empresaalojamiento/nuevaContrasena.fxml", "Inicio Sesion", lblCodigo, getClass());

        } catch (Exception e) {
            ControladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }


    }

}
