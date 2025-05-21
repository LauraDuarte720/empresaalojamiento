package co.edu.uniquindio.empresaalojamiento.controladores;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Alojamiento;
import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Habitacion;
import co.edu.uniquindio.empresaalojamiento.servicios.EmpresaAlojamientoServicio;
import co.edu.uniquindio.empresaalojamiento.singleton.HabitacionSingleton;
import co.edu.uniquindio.empresaalojamiento.singleton.Sesion;
import co.edu.uniquindio.empresaalojamiento.utilidades.Utilidades;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

import static javafx.collections.FXCollections.observableList;

public class ItemHabitacionControlador {


    @FXML
    private ComboBox<Integer> cmbNumeroHabitacion;

    @FXML
    private ImageView imgHabitacion;

    @FXML
    private Label lblCapacidad;

    @FXML
    private Label lblDescripcion;

    @FXML
    private Label lblPrecioPorNoche;

    private Habitacion habitacion;

    private final EmpresaAlojamientoServicio empresaAlojamientoServicio = ControladorPrincipal.getInstancia().getEmpresaAlojamiento();

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
        lblDescripcion.setText(habitacion.getDescripcion());
        lblCapacidad.setText(habitacion.getCapacidadHuespedes() + "");
        lblPrecioPorNoche.setText("$" + Utilidades.obtenerValorCadena(habitacion.getPrecioPorNoche()) + "por noche");
        imgHabitacion.setImage(new Image(new File(habitacion.getRutaImagen().substring(1)).toURI().toString()));
        cmbNumeroHabitacion.setItems(observableList(empresaAlojamientoServicio.obtenerNumerosHabitaciones(habitacion.getId())));
    }

    @FXML
    void reservar(ActionEvent event) {
        HabitacionSingleton.getInstancia().setHabitacion(habitacion);
        if (Sesion.getInstancia().getUsuario() != null) {
            ControladorPrincipal.navegarVentana("/co/edu/uniquindio/empresaalojamiento/crearReserva.fxml", "Reservar Alojamiento", lblCapacidad, getClass());
        } else {
            ControladorPrincipal.crearAlerta("Para reservar debe iniciar sesión", Alert.AlertType.INFORMATION);
            ControladorPrincipal.navegarVentana("/co/edu/uniquindio/empresaalojamiento/iniciarSesion.fxml", "Reservar Alojamiento", lblCapacidad, getClass());
        }
    }

}
