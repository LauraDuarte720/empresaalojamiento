package co.edu.uniquindio.empresaalojamiento.controladores;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Alojamiento;
import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Reserva;
import co.edu.uniquindio.empresaalojamiento.servicios.EmpresaAlojamientoServicio;
import co.edu.uniquindio.empresaalojamiento.singleton.AlojamientoSingleton;
import co.edu.uniquindio.empresaalojamiento.singleton.Sesion;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

import java.time.LocalDate;

import static javafx.collections.FXCollections.observableList;

public class PanelReservaControlador {

    @FXML
    private TableColumn<Reserva, String> tbcAlojamiento;

    @FXML
    private TableColumn<Reserva, String> tbcFechaIngreso;

    @FXML
    private TableColumn<Reserva, String> tbcFechaSalida;

    @FXML
    private TableColumn<Reserva, String> tbcNumeroHuespedes;

    @FXML
    private TableColumn<Reserva, String> tbcPrecio;

    @FXML
    private TableView<Reserva> tblReservas;


    private final EmpresaAlojamientoServicio controladorPrincipal = ControladorPrincipal.getInstancia().getEmpresaAlojamiento();
    private final Sesion sesion = Sesion.getInstancia();
    private final Alojamiento alojamiento = AlojamientoSingleton.getInstancia().getAlojamiento();

    @FXML
    private void initialize() {

        tbcAlojamiento.setCellValueFactory(cellData ->new SimpleStringProperty(controladorPrincipal.obtenerAlojamientoPorId(cellData.getValue().getIdAlojamiento()).getNombre()));
        tbcFechaIngreso.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getFechaInicio().toString()));
        tbcFechaSalida.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getFechaFinal().toString()));
        tbcNumeroHuespedes.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getNumeroHuespedes())));
        tbcPrecio.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getFactura().getTotal())));
        tblReservas.setItems(observableList(controladorPrincipal.obtenerReservasUsuario(sesion.getUsuario().getCedula())));
    }
    @FXML
    void agregarResena(ActionEvent event) {
        Reserva reserva= onSeleccionarReserva(event);
        if (reserva.getFechaFinal().isBefore(LocalDate.now())){
            ControladorPrincipal.navegarVentana("/co/edu/uniquindio/empresaalojamiento/crearResena.fxml", "Crear Resena", tblReservas, getClass());

        }else{
            ControladorPrincipal.crearAlerta("No puede agregar una reseña sin que halla pasado la reseña", Alert.AlertType.ERROR);
        }

    }

    @FXML
    private Reserva onSeleccionarReserva(ActionEvent e) {
        Reserva reservaSeleccionada = tblReservas.getSelectionModel().getSelectedItem();

        if (reservaSeleccionada == null) {
            ControladorPrincipal.crearAlerta("Selecciona una reserva primero", Alert.AlertType.ERROR);
        }
        Alojamiento alojamiento = controladorPrincipal.obtenerAlojamientoPorId(reservaSeleccionada.getIdAlojamiento());
        return reservaSeleccionada;
    }

    @FXML
    void cancelarReserva(ActionEvent event) {
        Reserva reserva= onSeleccionarReserva(event);
        try{
            if (reserva.getFechaInicio().isAfter(LocalDate.now())){
                controladorPrincipal.cancelarReserva(reserva.getId());
                ControladorPrincipal.crearAlerta("Reserva cancelada con exito", Alert.AlertType.INFORMATION);
                tblReservas.setItems(observableList(controladorPrincipal.obtenerReservasUsuario(sesion.getUsuario().getCedula())));
            }else{
                ControladorPrincipal.crearAlerta("No puede cancelar una reserva si ya paso la fecha", Alert.AlertType.ERROR);
            }
        } catch (Exception e) {
            ControladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

}

