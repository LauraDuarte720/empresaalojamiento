package co.edu.uniquindio.empresaalojamiento.controladores;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Alojamiento;
import co.edu.uniquindio.empresaalojamiento.servicios.EmpresaAlojamientoServicio;
import co.edu.uniquindio.empresaalojamiento.singleton.AlojamientoSingleton;
import co.edu.uniquindio.empresaalojamiento.singleton.Sesion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.StackPane;

public class MenuAdministradorControlador {

    @FXML
    private StackPane panelPrincipal;

    private final EmpresaAlojamientoServicio controladorPrincipal = ControladorPrincipal.getInstancia().getEmpresaAlojamiento();
    private final Sesion sesion = Sesion.getInstancia();
    private final Alojamiento alojamiento = AlojamientoSingleton.getInstancia().getAlojamiento();


    @FXML
    void cambiarContrasena(ActionEvent event) {
        ControladorPrincipal.navegarVentana("/co/edu/uniquindio/empresaalojamiento/cambiarContrasenaCorreo.fxml","Cambiar Contraseña Administrador",panelPrincipal,getClass());
    }

    @FXML
    void cerrarSesion(ActionEvent event) {
        ControladorPrincipal.navegarVentana("/co/edu/uniquindio/empresaalojamiento/inicioSesion.fxml", "Inicio Sesion", panelPrincipal, getClass());
        ControladorPrincipal.crearAlerta("¡Hasta luego " + Sesion.getInstancia().getUsuario().getNombre() + "!", Alert.AlertType.INFORMATION);
        Sesion.getInstancia().cerrarSesion();
    }

    @FXML
    void gestionarAlojamiento(ActionEvent event) {
        Parent node = ControladorPrincipal.cargarPanel("/co/edu/uniquindio/empresaalojamiento/gestionAlojamiento.fxml", getClass());
        panelPrincipal.getChildren().setAll(node);
    }

    @FXML
    void gestionarOferta(ActionEvent event) {
        Parent node = ControladorPrincipal.cargarPanel("/co/edu/uniquindio/empresaalojamiento/panelOferta.fxml", getClass());
        panelPrincipal.getChildren().setAll(node);
    }

}

