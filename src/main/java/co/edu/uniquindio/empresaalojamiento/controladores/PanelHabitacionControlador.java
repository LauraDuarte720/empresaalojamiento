package co.edu.uniquindio.empresaalojamiento.controladores;

import co.edu.uniquindio.empresaalojamiento.HelloApplication;
import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Alojamiento;
import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Habitacion;
import co.edu.uniquindio.empresaalojamiento.servicios.EmpresaAlojamientoServicio;
import co.edu.uniquindio.empresaalojamiento.singleton.AlojamientoSingleton;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;


public class PanelHabitacionControlador {

    @FXML
    private Label lblNoHay;

    @FXML
    private ListView<Habitacion> listHabitaciones;

    private final EmpresaAlojamientoServicio empresaAlojamientoServicio = ControladorPrincipal.getInstancia().getEmpresaAlojamiento();

    private final Alojamiento alojamiento = AlojamientoSingleton.getInstancia().getAlojamiento();

    @FXML
    void initialize() {
        listHabitaciones.setCellFactory(param -> new ListCell<Habitacion>() {
            @Override
            protected void updateItem(Habitacion habitacion, boolean empty) {
                super.updateItem(habitacion, empty);

                if (empty || habitacion == null) {
                    setGraphic(null);
                } else {
                    try {
                        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("itemHabitacion.fxml"));
                        Parent root = loader.load();
                        ItemHabitacionControlador ctrl = loader.getController();
                        ctrl.setHabitacion(habitacion);
                        setGraphic(root);
                        setStyle("-fx-background-color: transparent;");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        if (!empresaAlojamientoServicio.obtenerHabitacionesHotel(alojamiento.getId()).isEmpty()) {
            listHabitaciones.setItems(FXCollections.observableArrayList(
                    empresaAlojamientoServicio.obtenerHabitacionesHotel(alojamiento.getId())));
            listHabitaciones.setVisible(true);
            lblNoHay.setVisible(false);
        } else {
            listHabitaciones.setVisible(false);
            lblNoHay.setVisible(true);
        }
    }

}
