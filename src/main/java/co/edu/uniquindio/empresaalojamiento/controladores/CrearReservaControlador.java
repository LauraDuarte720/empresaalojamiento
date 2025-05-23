package co.edu.uniquindio.empresaalojamiento.controladores;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Alojamiento;
import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Habitacion;
import co.edu.uniquindio.empresaalojamiento.servicios.EmpresaAlojamientoServicio;
import co.edu.uniquindio.empresaalojamiento.singleton.AlojamientoSingleton;
import co.edu.uniquindio.empresaalojamiento.singleton.HabitacionSingleton;
import co.edu.uniquindio.empresaalojamiento.singleton.Sesion;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CrearReservaControlador {

    @FXML
    private DatePicker datePickFechaIngreso;

    @FXML
    private DatePicker datePickFechaSalida;

    @FXML
    private ImageView imgFechaIngreso;

    @FXML
    private ImageView imgFechaSalida;

    @FXML
    private ImageView imgNumHuesped;

    @FXML
    private TextField txtNumHuesped;

    @FXML
    private Label lblPrecio;

    private final EmpresaAlojamientoServicio controladorPrincipal = ControladorPrincipal.getInstancia().getEmpresaAlojamiento();
    private final Sesion sesion = Sesion.getInstancia();
    private final Alojamiento alojamiento = AlojamientoSingleton.getInstancia().getAlojamiento();
    private final Habitacion habitacion = HabitacionSingleton.getInstancia().getHabitacion();

    @FXML
    void initialize() {
        ChangeListener<Object> calcularPrecioListener = (obs, oldVal, newVal) -> calcularPrecioParcial();

        datePickFechaIngreso.valueProperty().addListener(calcularPrecioListener);
        datePickFechaSalida.valueProperty().addListener(calcularPrecioListener);
    }

    @FXML
    void crearReserva(ActionEvent event) {
        try {
            controladorPrincipal.registrarReserva(datePickFechaIngreso.getValue(), datePickFechaSalida.getValue(), txtNumHuesped.getText(), alojamiento.getId(), sesion.getUsuario().getCedula(), habitacion == null ? "" : habitacion.getId());
            ControladorPrincipal.crearAlerta("Se ha creado con exito la reserva", Alert.AlertType.INFORMATION);
            AlojamientoSingleton.getInstancia().setAlojamiento(null);
            HabitacionSingleton.getInstancia().setHabitacion(null);
            ControladorPrincipal.navegarVentana("/co/edu/uniquindio/empresaalojamiento/menuCliente.fxml", "Usuario", txtNumHuesped, getClass());
            controladorPrincipal.enviarNotificacion("Tu reserva en " + alojamiento.getNombre() + " ha sido creada con exito", sesion.getUsuario().getCedula());
        } catch (Exception e) {
            ControladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    void regresar(ActionEvent event) {
        ControladorPrincipal.navegarVentana("/co/edu/uniquindio/empresaalojamiento/menuCliente.fxml", "Usuario", txtNumHuesped, getClass());
        AlojamientoSingleton.getInstancia().setAlojamiento(null);
        HabitacionSingleton.getInstancia().setHabitacion(null);
    }

    private void calcularPrecioParcial() {
        LocalDate inicio = datePickFechaIngreso.getValue();
        LocalDate fin = datePickFechaSalida.getValue();

        if (inicio != null && fin != null && !fin.isBefore(inicio)) {
            long noches = ChronoUnit.DAYS.between(inicio, fin);
            if (alojamiento.getPrecioPorNoche() == 0) {
                double precio = noches * habitacion.getPrecioPorNoche();
                lblPrecio.setText(String.format("$%.2f", precio));
            } else {
                double precio = noches * alojamiento.getPrecioPorNoche() + alojamiento.getCostoAdicional();
                lblPrecio.setText(String.format("$%.2f", precio));
            }
        } else {
            lblPrecio.setText("—");
        }
    }
}

