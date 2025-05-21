package co.edu.uniquindio.empresaalojamiento.controladores;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Alojamiento;
import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Habitacion;
import co.edu.uniquindio.empresaalojamiento.servicios.EmpresaAlojamientoServicio;
import co.edu.uniquindio.empresaalojamiento.singleton.AlojamientoSingleton;
import co.edu.uniquindio.empresaalojamiento.singleton.Sesion;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import static javafx.collections.FXCollections.observableList;

public class GestionHabitacionControlador {

    @FXML
    private TableView<Habitacion> tbHabitacion;

    @FXML
    private TableColumn<Habitacion, String> tbcCapacidad;

    @FXML
    private TableColumn<Habitacion, String> tbcNumHabitacion;

    @FXML
    private TableColumn<Habitacion, String> tbcPrecio;

    private final EmpresaAlojamientoServicio controladorPrincipal = ControladorPrincipal.getInstancia().getEmpresaAlojamiento();
    private final Sesion sesion = Sesion.getInstancia();
    private final Alojamiento alojamiento = AlojamientoSingleton.getInstancia().getAlojamiento();
    private Habitacion habitacionSeleccionado;

    @FXML
    void initialize(){
        tbcCapacidad.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getCapacidadHuespedes())));
        tbcNumHabitacion.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getNumero())));
        tbcPrecio.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPrecioPorNoche())));
        setHabitaciones();
        tbHabitacion.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            habitacionSeleccionado=newValue;
        });

    }

    @FXML
    void crear(ActionEvent event) {
        ControladorPrincipal.navegarVentana("/co/edu/uniquindio/empresaalojamiento/crearHabitacion.fxml", "Crear Habitacion", tbHabitacion, getClass());
    }

    @FXML
    void eliminar(ActionEvent event) {
        if(habitacionSeleccionado!=null){
            try {
                controladorPrincipal.eliminarHabitacion(habitacionSeleccionado.getId());
                ControladorPrincipal.crearAlerta("Se ha eliminado con exito la habitacion", Alert.AlertType.INFORMATION);
                setHabitaciones();
            }catch (Exception e){
                ControladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
            }
        }
        else{
            ControladorPrincipal.crearAlerta("Debe seleccionar una habitacion antes de eliminarla", Alert.AlertType.ERROR);
        }
    }


    @FXML
    void regresar(ActionEvent event) {
        ControladorPrincipal.navegarVentana("/co/edu/uniquindio/empresaalojamiento/gestionAlojamiento.fxml", "Gestiones Alojamiento", tbHabitacion, getClass());
        AlojamientoSingleton.getInstancia().setAlojamiento(null);
    }

    public void setHabitaciones(){
        tbHabitacion.setItems(observableList(controladorPrincipal.obtenerHabitacionesHotel(alojamiento.getId())));
        tbHabitacion.refresh();
    }

}
