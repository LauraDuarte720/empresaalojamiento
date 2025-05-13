package co.edu.uniquindio.empresaalojamiento.controladores;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class InicioSesionControlador {

    @FXML
    private ImageView imgALojamiento1;

    @FXML
    private ImageView imgALojamiento2;

    @FXML
    private ImageView imgALojamiento3;

    @FXML
    private ImageView imgALojamiento4;

    @FXML
    private ImageView imgALojamiento5;

    @FXML
    private ImageView imgALojamiento6;

    @FXML
    private Label lblDescripcion1;

    @FXML
    private Label lblDescripcion2;

    @FXML
    private Label lblDescripcion3;

    @FXML
    private Label lblDescripcion4;

    @FXML
    private Label lblDescripcion5;

    @FXML
    private Label lblDescripcion6;

    @FXML
    private ControladorPrincipal controladorPrincipal;


    @FXML
    void initialize() {
        controladorPrincipal = ControladorPrincipal.getInstancia();
    }

    @FXML
    void irIniciarSesion(ActionEvent event) {
        ControladorPrincipal.navegarVentana("/co/edu/uniquindio/empresaalojamiento/iniciarSesion.fxml", "Iniciar Sesion", imgALojamiento1, getClass());
    }

    @FXML
    void irRegistrarse(ActionEvent event) {
        ControladorPrincipal.navegarVentana("/co/edu/uniquindio/empresaalojamiento/registrarUsuario.fxml", "Registrar Usuario", imgALojamiento1, getClass());

    }

}
