package co.edu.uniquindio.empresaalojamiento.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;

public class PanelAlojamientoControlador {

    @FXML
    private ComboBox<?> cmbFiltroCiudad;

    @FXML
    private ComboBox<?> cmbFiltroTipoAlojamiento;

    @FXML
    private ScrollPane scrPareserva;

    @FXML
    private TextField txtFiltroNombre;

    @FXML
    private TextField txtFiltroPrecioMax;

    @FXML
    private TextField txtFiltroPrecioMin;

}
