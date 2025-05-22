package co.edu.uniquindio.empresaalojamiento.controladores;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Alojamiento;
import co.edu.uniquindio.empresaalojamiento.servicios.EmpresaAlojamientoServicio;
import co.edu.uniquindio.empresaalojamiento.singleton.AlojamientoSingleton;
import co.edu.uniquindio.empresaalojamiento.singleton.Sesion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuClienteControlador implements Initializable {
    public StackPane panelPrincipal;

    private final EmpresaAlojamientoServicio controladorPrincipal = ControladorPrincipal.getInstancia().getEmpresaAlojamiento();
    private final Sesion sesion = Sesion.getInstancia();
    private final Alojamiento alojamiento = AlojamientoSingleton.getInstancia().getAlojamiento();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Parent node = ControladorPrincipal.cargarPanel("/co/edu/uniquindio/empresaalojamiento/panelAlojamiento.fxml", getClass());
        panelPrincipal.getChildren().setAll(node);
    }

    @FXML
    void eliminarCuenta(ActionEvent event) {
        try{
            controladorPrincipal.eliminarUsuario(sesion.getUsuario().getCedula());
            ControladorPrincipal.crearAlerta("Cuenta eliminada con exito", Alert.AlertType.INFORMATION);
            ControladorPrincipal.navegarVentana("/co/edu/uniquindio/empresaalojamiento/inicioSesion.fxml", "Inicio Sesion", panelPrincipal, getClass());
        }catch(Exception e){
            ControladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    void recargarBilletera(ActionEvent event) {
        Parent node = ControladorPrincipal.cargarPanel("/co/edu/uniquindio/empresaalojamiento/recargarBilletera2.fxml", getClass());
        panelPrincipal.getChildren().setAll(node);

    }

    @FXML
    void verAlojamientos(ActionEvent event) {
        Parent node = ControladorPrincipal.cargarPanel("/co/edu/uniquindio/empresaalojamiento/panelAlojamiento.fxml", getClass());
        panelPrincipal.getChildren().setAll(node);
    }

    @FXML
    void verReservas(ActionEvent event) {
        Parent node = ControladorPrincipal.cargarPanel("/co/edu/uniquindio/empresaalojamiento/panelReserva.fxml", getClass());
        panelPrincipal.getChildren().setAll(node);

    }


    @FXML
    void actualizarDatos(ActionEvent event) {
        Parent node = ControladorPrincipal.cargarPanel("/co/edu/uniquindio/empresaalojamiento/actualizarUsuario.fxml", getClass());
        panelPrincipal.getChildren().setAll(node);

    }


    @FXML
    void cerrarSesion(ActionEvent event) {
        ControladorPrincipal.navegarVentana("/co/edu/uniquindio/empresaalojamiento/inicioSesion.fxml", "Inicio Sesion", panelPrincipal, getClass());
        ControladorPrincipal.crearAlerta("¡Hasta luego " + Sesion.getInstancia().getUsuario().getNombre() + "!", Alert.AlertType.INFORMATION);
        Sesion.getInstancia().cerrarSesion();
    }


    @FXML
    void irNotificaciones(ActionEvent event) {
        ControladorPrincipal.navegarVentana("/co/edu/uniquindio/empresaalojamiento/notificaciones.fxml", "Notificaciones", panelPrincipal, getClass());
    }

}
