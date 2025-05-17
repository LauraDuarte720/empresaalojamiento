package co.edu.uniquindio.empresaalojamiento.controladores;

import co.edu.uniquindio.empresaalojamiento.HelloApplication;
import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Alojamiento;
import co.edu.uniquindio.empresaalojamiento.servicios.EmpresaAlojamientoServicio;
import co.edu.uniquindio.empresaalojamiento.singleton.AlojamientoSingleton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

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

    @FXML
    private VBox vBoxAlojamientos;

    private static EmpresaAlojamientoServicio empresaAlojamientoServicio = ControladorPrincipal.getInstancia().getEmpresaAlojamiento();

    @FXML
    void initialize() {
        for (Alojamiento alojamiento : empresaAlojamientoServicio.obtenerAlojamientosAleatorios()) {
            Parent raiz = crearItem(alojamiento);
            vBoxAlojamientos.getChildren().add(raiz);
        }


    }

    public Parent crearItem(Alojamiento alojamiento) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("itemAlojamiento.fxml"));
            Parent raiz = fxmlLoader.load();
            ItemAlojamientoControlador controlador = fxmlLoader.getController();
            controlador.setAlojamiento(alojamiento);
            return raiz;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
