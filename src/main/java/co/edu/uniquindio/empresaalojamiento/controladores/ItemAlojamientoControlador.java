package co.edu.uniquindio.empresaalojamiento.controladores;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Alojamiento;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;

public class ItemAlojamientoControlador {

    public Label tituloAlojamiento;

    public void reservar(ActionEvent actionEvent) {
        ControladorPrincipal.navegarVentana("/co/edu/uniquindio/empresaalojamiento/reservarAlojamiento.fxml", "Reservar Alojamiento", tituloAlojamiento, getClass());
    }

    public void setAlojamiento(Alojamiento a) {
        tituloAlojamiento.setText(a.getNombre());
    }
}
