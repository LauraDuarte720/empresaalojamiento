package co.edu.uniquindio.empresaalojamiento.controladores;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Alojamiento;
import co.edu.uniquindio.empresaalojamiento.servicios.EmpresaAlojamientoServicio;
import co.edu.uniquindio.empresaalojamiento.singleton.AlojamientoSingleton;
import co.edu.uniquindio.empresaalojamiento.singleton.Sesion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class CrearHabitacionControlador {

    @FXML
    private ImageView imgFoto;

    @FXML
    private Button regresar;

    @FXML
    private TextField txtCapacidadHuespedes;

    @FXML
    private TextArea txtDescripcion;

    @FXML
    private TextField txtNumHabitacion;

    @FXML
    private TextField txtPrecioNoche;

    private String rutaFoto;

    private final EmpresaAlojamientoServicio controladorPrincipal = ControladorPrincipal.getInstancia().getEmpresaAlojamiento();
    private final Sesion sesion = Sesion.getInstancia();
    private final Alojamiento alojamiento = AlojamientoSingleton.getInstancia().getAlojamiento();


    @FXML
    void crearHabitacion(ActionEvent event) {
        try{
            controladorPrincipal.registrarHabitacion(txtNumHabitacion.getText(),txtPrecioNoche.getText(),txtCapacidadHuespedes.getText(),txtDescripcion.getText(),alojamiento.getId(),rutaFoto);
            ControladorPrincipal.crearAlerta("Se ha creado con exito la habitacion", Alert.AlertType.INFORMATION);
            ControladorPrincipal.navegarVentana("/co/edu/uniquindio/empresaalojamiento/gestionHabitacion.fxml", "Gestor de Habitaciones", txtNumHabitacion, getClass());
        }catch(Exception e){
            ControladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }

    }

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
                try {

                    File directorioImagenes = new File("imagenes");
                    if (!directorioImagenes.exists()) {
                        directorioImagenes.mkdirs();
                    }

                    String nombreArchivo = archivoSeleccionado.getName();

                    File archivoDestino = new File(directorioImagenes, nombreArchivo);

                    Files.copy(archivoSeleccionado.toPath(), archivoDestino.toPath(), StandardCopyOption.REPLACE_EXISTING);

                    rutaFoto = archivoDestino.getAbsolutePath();

                    Image imagen = new Image(archivoDestino.toURI().toString());
                    imgFoto.setImage(imagen);

                } catch (IOException e) {
                    ControladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
                }
            }
        }


    @FXML
    void regresar(ActionEvent event) {
        ControladorPrincipal.navegarVentana("/co/edu/uniquindio/empresaalojamiento/gestionHabitacion.fxml", "Gestor de Habitaciones", txtNumHabitacion, getClass());
    }

}
