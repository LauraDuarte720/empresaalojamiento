package co.edu.uniquindio.empresaalojamiento.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.VBox;

public class PanelReservaControlador {

    @FXML
    private TableColumn<?, ?> tbcAlojamiento;

    @FXML
    private TableColumn<?, ?> tbcFechaIngreso;

    @FXML
    private TableColumn<?, ?> tbcFechaSalida;

    @FXML
    private TableColumn<?, ?> tbcNumeroHuespedes;

    @FXML
    private TableColumn<?, ?> tbcPrecio;

    @FXML
    private VBox tblReservas;

    @FXML
    void agregarResena(ActionEvent event) {

    }

    @FXML
    void cancelarReserva(ActionEvent event) {

    }

}

