package co.edu.uniquindio.empresaalojamiento.controladores;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Alojamiento;
import co.edu.uniquindio.empresaalojamiento.modelo.enums.Ciudad;
import co.edu.uniquindio.empresaalojamiento.modelo.enums.TipoAlojamiento;
import co.edu.uniquindio.empresaalojamiento.servicios.EmpresaAlojamientoServicio;
import co.edu.uniquindio.empresaalojamiento.singleton.AlojamientoSingleton;
import co.edu.uniquindio.empresaalojamiento.singleton.Sesion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class CrearAlojamientoControlador {

    @FXML
    private CheckBox checkDesayuno;

    @FXML
    private CheckBox checkPiscina;

    @FXML
    private CheckBox checkWifi;

    @FXML
    private ComboBox<String> cmbCiudad;

    @FXML
    private ComboBox<String> cmbTipoAlojamiento;

    @FXML
    private HBox hBoxCapacidadHuesped;

    @FXML
    private HBox hBoxPrecioNoche;

    @FXML
    private ImageView imgCostoAdicional;

    @FXML
    private ImageView imgFoto;

    @FXML
    private Label lblCostoAdicional;

    @FXML
    private TextField txtCapacidadHuespedes;

    @FXML
    private TextField txtCostoadicional;

    @FXML
    private TextArea txtDescripcion;

    @FXML
    private TextField txtPrecioNoche;

    @FXML
    private TextField txtnombre;

    private String rutaFoto;

    @FXML
    private CheckBox checkGym;

    @FXML
    private CheckBox checkMascotas;

    @FXML
    private CheckBox checkParqueaderos;


    private final EmpresaAlojamientoServicio controladorPrincipal = ControladorPrincipal.getInstancia().getEmpresaAlojamiento();
    private final Sesion sesion = Sesion.getInstancia();
    private final Alojamiento alojamiento = AlojamientoSingleton.getInstancia().getAlojamiento();

    @FXML
    public void initialize(){
        cmbCiudad.getItems().addAll(Ciudad.getListaDeNombres());
        cmbTipoAlojamiento.getItems().addAll(TipoAlojamiento.getListaDeNombres());
        cmbTipoAlojamiento.setOnAction(event -> {
            actualizarVisibilidad(cmbTipoAlojamiento.getValue());
        });
    }

    @FXML
    void crearAlojamiento(ActionEvent event) {
        if (archivoTemporalSeleccionado != null) {
            try {
                rutaFoto = ControladorPrincipal.guardarImagenEnDirectorio(archivoTemporalSeleccionado);
            } catch (IOException e) {
                ControladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
                return;
            }
        }
        try{
            controladorPrincipal.registrarAlojamiento(TipoAlojamiento.getTipoAlojamientoDesdeNombre(cmbTipoAlojamiento.getValue()),txtnombre.getText(),txtDescripcion.getText(),
                    rutaFoto,txtPrecioNoche.getText(),txtCapacidadHuespedes.getText(),
                    checkPiscina.isSelected(),checkWifi.isSelected(),checkDesayuno.isSelected(),txtCostoadicional.getText(),
                    Ciudad.getCiudadDesdeNombre(cmbCiudad.getValue()),checkParqueaderos.isSelected(),checkMascotas.isSelected(),checkGym.isSelected());
            ControladorPrincipal.crearAlerta("Se ha creado con exito el alojamiento", Alert.AlertType.INFORMATION);
            ControladorPrincipal.navegarVentana("/co/edu/uniquindio/empresaalojamiento/menuAdministrador.fxml", "Menu Administrador", txtnombre, getClass());
        }catch (Exception e){
            ControladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    void regresar(ActionEvent event) {
        ControladorPrincipal.navegarVentana("/co/edu/uniquindio/empresaalojamiento/menuAdministrador.fxml", "Menu Administrador", txtnombre, getClass());
    }

    private File archivoTemporalSeleccionado;

    @FXML
    void seleccionarFoto(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar imagen");

        FileChooser.ExtensionFilter filtroImagenes = new FileChooser.ExtensionFilter(
                "Imágenes", "*.png", "*.jpg", "*.jpeg", "*.gif"
        );
        fileChooser.getExtensionFilters().add(filtroImagenes);

        File archivoSeleccionado = fileChooser.showOpenDialog(null);

        if (archivoSeleccionado != null) {
            archivoTemporalSeleccionado = archivoSeleccionado;

            Image imagen = new Image(archivoSeleccionado.toURI().toString());
            imgFoto.setImage(imagen);
        }
    }

    private void actualizarVisibilidad(String seleccion) {
        boolean mostrar1 = "Casa".equals(seleccion) || "Apartamentos".equals(seleccion);

        imgCostoAdicional.setVisible(mostrar1);
        lblCostoAdicional.setVisible(mostrar1);
        txtCostoadicional.setVisible(mostrar1);
        hBoxCapacidadHuesped.setVisible(mostrar1);
        hBoxPrecioNoche.setVisible(mostrar1);
        txtPrecioNoche.setVisible(mostrar1);
        txtCapacidadHuespedes.setVisible(mostrar1);

    }
}

