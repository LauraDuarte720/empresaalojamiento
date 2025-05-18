package co.edu.uniquindio.empresaalojamiento.controladores;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Alojamiento;
import co.edu.uniquindio.empresaalojamiento.singleton.Sesion;
import co.edu.uniquindio.empresaalojamiento.modelo.enums.Calificacion;
import co.edu.uniquindio.empresaalojamiento.servicios.EmpresaAlojamientoServicio;
import co.edu.uniquindio.empresaalojamiento.singleton.AlojamientoSingleton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

public class CrearResenaControlador {

    @FXML
    private ComboBox<String> cmbCalificacion;

    @FXML
    private TextArea txtValoracion;

    private final EmpresaAlojamientoServicio controladorPrincipal = ControladorPrincipal.getInstancia().getEmpresaAlojamiento();
    private final Sesion sesion = Sesion.getInstancia();
    private final Alojamiento alojamiento = AlojamientoSingleton.getInstancia().getAlojamiento();


    @FXML
    public void initialize() {
        cmbCalificacion.getItems().addAll(Arrays.stream(Calificacion.values())
                .map(Calificacion::getNombreLegible)
                .toList());
    }

    @FXML
    void crearResena(ActionEvent event) {
        try {
            controladorPrincipal.crearResena(txtValoracion.getText(),Calificacion.getCalificacion(cmbCalificacion.getValue()),sesion.getUsuario().getCedula(),alojamiento.getId());
            ControladorPrincipal.crearAlerta("Su reseña ha sido creada", Alert.AlertType.INFORMATION);
            ControladorPrincipal.navegarVentana("/co/edu/uniquindio/empresaalojamiento/menuCliente.fxml", "Usuario", txtValoracion, getClass());
        }catch (Exception e){
            ControladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    void regresar(ActionEvent event) {
        ControladorPrincipal.navegarVentana("/co/edu/uniquindio/empresaalojamiento/menuCliente.fxml", "Usuario", txtValoracion, getClass());
    }
}
