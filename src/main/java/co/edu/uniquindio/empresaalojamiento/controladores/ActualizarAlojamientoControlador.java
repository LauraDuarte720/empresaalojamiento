package co.edu.uniquindio.empresaalojamiento.controladores;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Alojamiento;
import co.edu.uniquindio.empresaalojamiento.modelo.enums.Calificacion;
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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;

import static co.edu.uniquindio.empresaalojamiento.modelo.enums.Ciudad.getListaDeNombres;

public class ActualizarAlojamientoControlador {

    @FXML
    private ComboBox<String> cmbCiudad;

    @FXML
    private ImageView imgFoto;

    @FXML
    private TextField txtCapacidadHuespedes;

    @FXML
    private TextArea txtDescripcion;

    @FXML
    private TextField txtPrecioNoche;

    @FXML
    private TextField txtnombre;

    @FXML
    private CheckBox checkDesayuno;

    @FXML
    private CheckBox checkPiscina;

    @FXML
    private CheckBox checkWifi;

    @FXML
    private ImageView imgCostoAdicional;

    @FXML
    private Label lblCostoAdicional;

    @FXML
    private TextField txtCostoadicional;

    @FXML
    private Button btnHabitaciones;

    @FXML
    private Label tipoAlojamiento;

    @FXML
    private HBox hBoxCapacidadHuesped;

    @FXML
    private HBox hBoxPrecioNoche;

    @FXML
    private CheckBox checkGym;

    @FXML
    private CheckBox checkMascotas;

    @FXML
    private CheckBox checkParqueaderos;

    private String rutaFoto;

    private final EmpresaAlojamientoServicio controladorPrincipal = ControladorPrincipal.getInstancia().getEmpresaAlojamiento();
    private final Sesion sesion = Sesion.getInstancia();
    private Alojamiento alojamiento = AlojamientoSingleton.getInstancia().getAlojamiento();


    @FXML
    void initialize(){
        cmbCiudad.getItems().addAll(Ciudad.getListaDeNombres());
        cmbCiudad.getSelectionModel().select(alojamiento.getCiudad().toString());
        if (alojamiento.getRuta() != null && !alojamiento.getRuta().isEmpty()) {
            File file = new File(alojamiento.getRuta());
            if (file.exists()) {
                Image imagen = new Image(new File(alojamiento.getRuta()).toURI().toString());
                imgFoto.setImage(imagen);
            } else {
                System.out.println("No se encontró la imagen: " + alojamiento.getRuta());
                imgFoto.setImage(null);
            }
        } else {
            imgFoto.setImage(null);
        }
        tipoAlojamiento.setText(alojamiento.getTipoAlojamiento().toString());
        txtCapacidadHuespedes.setText(String.valueOf(alojamiento.getCapacidadMaximaHuespedes()));
        txtDescripcion.setText(alojamiento.getDescripcion());
        txtPrecioNoche.setText(String.valueOf(alojamiento.getPrecioPorNoche()));
        txtnombre.setText(alojamiento.getNombre());
        checkDesayuno.setSelected(alojamiento.isDesayuno());
        checkPiscina.setSelected(alojamiento.isPiscina());
        checkWifi.setSelected(alojamiento.isWifi());
        checkParqueaderos.setSelected(alojamiento.isParqueadro());
        checkGym.setSelected(alojamiento.isGym());
        checkMascotas.setSelected(alojamiento.isMascotasPermitidas());
        txtCostoadicional.setText(String.valueOf(alojamiento.getCostoAdicional()));
        actualizarVisibilidad(alojamiento.getTipoAlojamiento().toString());

    }
    @FXML
    void actualizarAlojamiento(ActionEvent event) {
        try{
            if (archivoTemporalSeleccionado != null) {
                try {
                    rutaFoto = ControladorPrincipal.guardarImagenEnDirectorio(archivoTemporalSeleccionado);
                } catch (IOException e) {
                    ControladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
                    return;
                }
            }
            else {
                rutaFoto = alojamiento.getRuta();
            }
            System.out.println(rutaFoto);
            controladorPrincipal.actualizarAlojamiento(
                    alojamiento.getId(),
                    txtnombre.getText(),
                    txtDescripcion.getText(),
                    rutaFoto,txtPrecioNoche.getText(),
                    txtCapacidadHuespedes.getText(),
                    checkPiscina.isSelected(),
                    checkWifi.isSelected(),
                    checkDesayuno.isSelected(),
                    txtCostoadicional.getText(),
                    Ciudad.getCiudadDesdeNombre(cmbCiudad.getValue()),
                    checkParqueaderos.isSelected(),
                    checkMascotas.isSelected(),
                    checkGym.isSelected());
            ControladorPrincipal.crearAlerta("Se ha actualizado con exito el alojamiento", Alert.AlertType.INFORMATION);
            ControladorPrincipal.navegarVentana("/co/edu/uniquindio/empresaalojamiento/menuAdministrador.fxml", "Menu Administrador", txtnombre, getClass());
            AlojamientoSingleton.getInstancia().setAlojamiento(null);
        }catch(Exception e){
            ControladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }

    }

    @FXML
    void regresar(ActionEvent event) {
        ControladorPrincipal.navegarVentana("/co/edu/uniquindio/empresaalojamiento/menuAdministrador.fxml", "Menu Administrador", txtnombre, getClass());
        AlojamientoSingleton.getInstancia().setAlojamiento(null);
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
        boolean mostrar2 = "Hotel".equals(seleccion);
        imgCostoAdicional.setVisible(mostrar1);
        lblCostoAdicional.setVisible(mostrar1);
        txtCostoadicional.setVisible(mostrar1);
        btnHabitaciones.setVisible(mostrar2);
        hBoxCapacidadHuesped.setVisible(mostrar1);
        hBoxPrecioNoche.setVisible(mostrar1);
        txtPrecioNoche.setVisible(mostrar1);
        txtCapacidadHuespedes.setVisible(mostrar1);

    }


    @FXML
    void irGestionHabitacion(ActionEvent event) {
        ControladorPrincipal.navegarVentana("/co/edu/uniquindio/empresaalojamiento/gestionHabitacion.fxml", "Gestor de Habitaciones", txtnombre, getClass());
    }
}
