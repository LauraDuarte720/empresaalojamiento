package co.edu.uniquindio.empresaalojamiento.controladores;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Sesion;
import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Usuario;
import co.edu.uniquindio.empresaalojamiento.servicios.EmpresaAlojamientoServicio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class ActualizarUsuarioControlador {

    @FXML
    private ImageView imgIconoApellidoAct;

    @FXML
    private ImageView imgIconoCedulaAct;

    @FXML
    private ImageView imgIconoCorreoAct;

    @FXML
    private ImageView imgIconoNombreAct;

    @FXML
    private ImageView imgIconoTelefonoAct;

    @FXML
    private TextField txtApellidoAct;

    @FXML
    private TextField txtCedulaAct;

    @FXML
    private TextField txtCorreoAct;

    @FXML
    private TextField txtNombreAct;

    @FXML
    private TextField txtTelefonoAct;

    private EmpresaAlojamientoServicio empresaAlojamientoServicio;
    private final Sesion sesion = Sesion.getInstancia();
    Usuario usuario;
    private final ControladorPrincipal controladorPrincipal = ControladorPrincipal.getInstancia();

    public void initialize() {
        usuario = sesion.getUsuario();
        txtCedulaAct.setText(usuario.getCedula());
        txtNombreAct.setText(usuario.getNombre());
        txtApellidoAct.setText(usuario.getApellido());
        txtTelefonoAct.setText(usuario.getTelefono());
        txtCorreoAct.setText(usuario.getEmail());
    }
    @FXML
    void actualizarCliente(ActionEvent event) {
        try{
            empresaAlojamientoServicio.actualizarUsuario(usuario.getCedula(),
                    txtCedulaAct.getText(),
                    txtNombreAct.getText(),
                    txtApellidoAct.getText(),
                    txtTelefonoAct.getText(),
                    txtCorreoAct.getText());
            ControladorPrincipal.crearAlerta("El usuario "+txtNombreAct+" ha sido actualizado correctamente", Alert.AlertType.INFORMATION);
        }catch (Exception e){
            ControladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

}
