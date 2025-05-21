package co.edu.uniquindio.empresaalojamiento.controladores;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Alojamiento;
import co.edu.uniquindio.empresaalojamiento.modelo.enums.Ciudad;
import co.edu.uniquindio.empresaalojamiento.servicios.EmpresaAlojamientoServicio;
import co.edu.uniquindio.empresaalojamiento.singleton.AlojamientoSingleton;
import co.edu.uniquindio.empresaalojamiento.singleton.Sesion;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.List;

import static javafx.collections.FXCollections.observableList;

public class GestionalojamientoControlador {

    @FXML
    private ComboBox<Ciudad> cmbCiudad;

    @FXML
    private TableColumn<Alojamiento, String> tbcCiudad;

    @FXML
    private TableColumn<Alojamiento, String> tbcMaxHuesped;

    @FXML
    private TableColumn<Alojamiento, String> tbcNombre;

    @FXML
    private TableColumn<Alojamiento, String> tbcPrecioPorNoche;

    @FXML
    private TableColumn<Alojamiento, String> tbcTIpoAlojamiento;

    @FXML
    private TableView<Alojamiento> tblAlojamientos;


    private final EmpresaAlojamientoServicio controladorPrincipal = ControladorPrincipal.getInstancia().getEmpresaAlojamiento();
    private final Sesion sesion = Sesion.getInstancia();
    private final Alojamiento alojamiento = AlojamientoSingleton.getInstancia().getAlojamiento();
    private Alojamiento alojamientoSeleccionado;

    @FXML
    public void initialize() {
        cmbCiudad.getItems().addAll(Ciudad.values());
        tbcNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        tbcCiudad.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCiudad().toString()));
        tbcMaxHuesped.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getCapacidadMaximaHuespedes())));
        tbcPrecioPorNoche.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPrecioPorNoche())));
        tbcTIpoAlojamiento.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTipoAlojamiento().toString()));
        setAlojamiento();
        tblAlojamientos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            alojamientoSeleccionado=newValue;
        });

    }
    @FXML
    void eliminarAlojamiento(ActionEvent event) {
        try {
            controladorPrincipal.eliminarAlojamiento(alojamientoSeleccionado.getId());
            ControladorPrincipal.crearAlerta("Se ha eliminado con exito el alojamiento", Alert.AlertType.INFORMATION);
            setAlojamiento();
        }catch (Exception e){
            ControladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    void filtrarAlojamientosRentables(ActionEvent event) {
        try {
            Ciudad ciudadSeleccionada = cmbCiudad.getValue();
            if (ciudadSeleccionada == null) {
                throw new IllegalArgumentException("Debe seleccionar una ciudad antes de filtrar.");
            }
            List<Alojamiento> alojamientosPopulares = controladorPrincipal.ordenarAlojamientosPopularesCiudad(ciudadSeleccionada);
            setAlojamiento();
        }catch (Exception e){
            ControladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    void irActualizarAlojamiento(ActionEvent event) {
        if (alojamientoSeleccionado != null) {
            Alojamiento alojamientoSeleccionado = tblAlojamientos.getSelectionModel().getSelectedItem();
            AlojamientoSingleton.getInstancia().setAlojamiento(alojamientoSeleccionado);
            ControladorPrincipal.navegarVentana("/co/edu/uniquindio/empresaalojamiento/actualizarAlojamiento.fxml", "Actualizar Alojamiento", tblAlojamientos, getClass());
        }else {
            ControladorPrincipal.crearAlerta("Selecciona un alojamiento primero", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void irAlojamientoRentable(ActionEvent event) {
        ControladorPrincipal.navegarVentana("/co/edu/uniquindio/empresaalojamiento/alojamientosRentables.fxml", "Alojamiento Rentable", tblAlojamientos, getClass());
    }

    @FXML
    void irCrearOferta(ActionEvent event) {
        ControladorPrincipal.navegarVentana("/co/edu/uniquindio/empresaalojamiento/crearOferta.fxml", "Crear Oferta", tblAlojamientos, getClass());
    }

    @FXML
    void irNuevoAlojamiento(ActionEvent event) {
        ControladorPrincipal.navegarVentana("/co/edu/uniquindio/empresaalojamiento/crearAlojamiento.fxml", "Nuevo Alojamiento", tblAlojamientos, getClass());
    }

    @FXML
    void irVerEstadistuca(ActionEvent event) {

        if (alojamientoSeleccionado != null) {
            Alojamiento alojamientoSeleccionado = tblAlojamientos.getSelectionModel().getSelectedItem();
            AlojamientoSingleton.getInstancia().setAlojamiento(alojamientoSeleccionado);
            ControladorPrincipal.navegarVentana("/co/edu/uniquindio/empresaalojamiento/estadisticaAlojamiento.fxml", "Actualizar Alojamiento", tblAlojamientos, getClass());
        }else {
            ControladorPrincipal.crearAlerta("Selecciona un alojamiento primero", Alert.AlertType.ERROR);
        }
    }

    public void setAlojamiento(){
        tblAlojamientos.setItems(observableList(controladorPrincipal.obtenerAlojamientos()));
        tblAlojamientos.refresh();
    }

}

