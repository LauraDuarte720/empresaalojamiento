package co.edu.uniquindio.empresaalojamiento.controladores;

import co.edu.uniquindio.empresaalojamiento.HelloApplication;
import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Alojamiento;
import co.edu.uniquindio.empresaalojamiento.modelo.enums.Ciudad;
import co.edu.uniquindio.empresaalojamiento.modelo.enums.TipoAlojamiento;
import co.edu.uniquindio.empresaalojamiento.servicios.EmpresaAlojamientoServicio;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.List;

public class PanelAlojamientoControlador {

    @FXML
    private ComboBox<Ciudad> cmbFiltroCiudad;

    @FXML
    private ComboBox<TipoAlojamiento> cmbFiltroTipoAlojamiento;

    @FXML
    private ListView<Alojamiento> listAlojamientos;

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
        txtFiltroPrecioMin.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtFiltroPrecioMin.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        txtFiltroPrecioMax.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtFiltroPrecioMax.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });


        cmbFiltroCiudad.setOnAction(e -> aplicarFiltros());
        cmbFiltroTipoAlojamiento.setOnAction(e -> aplicarFiltros());

        txtFiltroNombre.textProperty().addListener((obs, oldVal, newVal) -> aplicarFiltros());
        txtFiltroPrecioMin.textProperty().addListener((obs, oldVal, newVal) -> aplicarFiltros());
        txtFiltroPrecioMax.textProperty().addListener((obs, oldVal, newVal) -> aplicarFiltros());

        cmbFiltroCiudad.getItems().setAll(Ciudad.values());
        cmbFiltroTipoAlojamiento.getItems().setAll(TipoAlojamiento.values());

        listAlojamientos.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (!listAlojamientos.getItems().isEmpty()) {
                listAlojamientos.getSelectionModel().clearSelection();
            }
        });


        listAlojamientos.setCellFactory(param -> new ListCell<Alojamiento>() {
            @Override
            protected void updateItem(Alojamiento alojamiento, boolean empty) {
                super.updateItem(alojamiento, empty);

                if (empty || alojamiento == null) {
                    setGraphic(null);
                } else {
                    try {
                        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("itemAlojamiento.fxml"));
                        Parent root = loader.load();
                        ItemAlojamientoControlador ctrl = loader.getController();
                        ctrl.setAlojamiento(alojamiento);
                        setGraphic(root);
                        setStyle("-fx-background-color: transparent;");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        // Cargar los alojamientos iniciales
        listAlojamientos.setItems(FXCollections.observableArrayList(
                empresaAlojamientoServicio.obtenerAlojamientosAleatorios()
        ));
    }

    private void aplicarFiltros() {
        try {
            String nombre = txtFiltroNombre.getText();
            TipoAlojamiento tipo = cmbFiltroTipoAlojamiento.getValue();
            Ciudad ciudad = cmbFiltroCiudad.getValue();

            String precioMin = txtFiltroPrecioMin.getText();
            String precioMax = txtFiltroPrecioMax.getText();

            List<Alojamiento> filtrados = empresaAlojamientoServicio.obtenerAlojamientosFiltrados(nombre, tipo, ciudad, precioMin, precioMax);
            System.out.println(filtrados);
            listAlojamientos.setItems(FXCollections.observableArrayList(filtrados));

        } catch (Exception e) {
            ControladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);

        }
    }
}
