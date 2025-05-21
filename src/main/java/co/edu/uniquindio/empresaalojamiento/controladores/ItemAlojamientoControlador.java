package co.edu.uniquindio.empresaalojamiento.controladores;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Alojamiento;
import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Resena;
import co.edu.uniquindio.empresaalojamiento.modelo.enums.TipoAlojamiento;
import co.edu.uniquindio.empresaalojamiento.servicios.EmpresaAlojamientoServicio;
import co.edu.uniquindio.empresaalojamiento.singleton.AlojamientoSingleton;
import co.edu.uniquindio.empresaalojamiento.singleton.Sesion;
import co.edu.uniquindio.empresaalojamiento.utilidades.Utilidades;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class ItemAlojamientoControlador {

    @FXML
    private HBox hBoxSiResena;

    @FXML
    private ImageView imgAlojamiento;

    @FXML
    private ImageView imgPrecio;

    @FXML
    private Label lblCalificacion;

    @FXML
    private Label lblCalificacionIndividual;

    @FXML
    private Label lblDescripcion;

    @FXML
    private Label lblNoResena;

    @FXML
    private Label lblOpinion;

    @FXML
    private Label lblPrecioPorNoche;

    @FXML
    private Label lblTituloAlojamiento;

    @FXML
    private Label lblUsuario;

    @FXML
    private VBox vBoxCamposOpcionales;

    @FXML
    private Button btnReservar;


    private int indexResena;

    private List<Resena> listaResenas;

    private Alojamiento alojamiento;
    private final EmpresaAlojamientoServicio empresaAlojamientoServicio = ControladorPrincipal.getInstancia().getEmpresaAlojamiento();

    public void setAlojamiento(Alojamiento alojamiento) {
        this.alojamiento = alojamiento;
        System.out.println(alojamiento.getId());
        indexResena = 0;
        lblTituloAlojamiento.setText(alojamiento.getNombre());
        lblDescripcion.setText(alojamiento.getDescripcion());
        lblCalificacion.setText(alojamiento.getCalificacionPromedio() + " estrellas");
        lblPrecioPorNoche.setText("$" + Utilidades.obtenerValorCadena(alojamiento.getPrecioPorNoche()) + "por noche");
        imgAlojamiento.setImage(new Image(new File(alojamiento.getRuta().substring(1)).toURI().toString()));
        llenarCamposAdicionales();

        if(alojamiento.getTipoAlojamiento().equals(TipoAlojamiento.HOTEL)){
            imgPrecio.setVisible(false);
            lblPrecioPorNoche.setVisible(false);
            btnReservar.setMaxWidth(170);
            btnReservar.setText("Seleccionar Alojamiento");
        }

        try {
            listaResenas = empresaAlojamientoServicio.obtenerResenasAlojamiento(alojamiento.getId());
        } catch (Exception e) {
            listaResenas = List.of();
        }

        llenarResena();
    }

    public void reservar(ActionEvent actionEvent) {
        AlojamientoSingleton.getInstancia().setAlojamiento(alojamiento);
        if (!alojamiento.getTipoAlojamiento().equals(TipoAlojamiento.HOTEL)) {
            if (Sesion.getInstancia().getUsuario() != null) {
                ControladorPrincipal.navegarVentana("/co/edu/uniquindio/empresaalojamiento/crearReserva.fxml", "Reservar Alojamiento", lblTituloAlojamiento, getClass());
            } else {
                ControladorPrincipal.crearAlerta("Para reservar debe iniciar sesión", Alert.AlertType.INFORMATION);
                ControladorPrincipal.navegarVentana("/co/edu/uniquindio/empresaalojamiento/iniciarSesion.fxml", "Reservar Alojamiento", lblTituloAlojamiento, getClass());
            }
        } else {
            mostrarPopupAlojamiento(alojamiento);
        }


    }

    public void llenarCamposAdicionales() {
        try {
            for (String campoOpcional : empresaAlojamientoServicio.obtenerCamposOpcionales(alojamiento.getId())) {
                HBox hbox = new HBox();
                vBoxCamposOpcionales.getChildren().add(hbox);
                hbox.getChildren().add(new Label(campoOpcional));
                ImageView imageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/co/edu/uniquindio/empresaalojamiento/imagenesIconos/chulito.png"))));
                imageView.setFitHeight(15);
                imageView.setFitWidth(15);
                hbox.getChildren().add(imageView);
                hbox.setAlignment(Pos.CENTER);
                hbox.setSpacing(10);

            }
        } catch (Exception e) {
            ((Pane) vBoxCamposOpcionales.getParent()).getChildren().remove(vBoxCamposOpcionales);
        }
    }

    public void llenarResena() {
        if (listaResenas == null || listaResenas.isEmpty()) {
            // No hay reseñas
            hBoxSiResena.setVisible(false);
            lblNoResena.setVisible(true); // Este label indica "No hay reseñas"
        } else {
            // Mostrar reseña actual
            Resena resenaActual = listaResenas.get(indexResena);
            lblCalificacionIndividual.setText(resenaActual.getCalificacion() + " estrellas");
            lblOpinion.setText(resenaActual.getValoracion());
            lblUsuario.setText(empresaAlojamientoServicio.buscarUsuario(resenaActual.getIdUsuario()).getNombre());

            hBoxSiResena.setVisible(true);
            lblNoResena.setVisible(false);
        }
    }


    @FXML
    void verResena(ActionEvent event) {
        {
            if (listaResenas != null && indexResena + 1 < listaResenas.size()) {
                indexResena++;
                llenarResena();
            }
        }

    }

    @FXML
    void verResenaAnterior(ActionEvent event) {
        {
            if (listaResenas != null && indexResena > 0) {
                indexResena--;
                llenarResena();
            }
        }

    }

    private void mostrarPopupAlojamiento(Alojamiento alojamiento) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/empresaalojamiento/panelHabitacion.fxml"));
            Parent root = loader.load();

            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setTitle("Habitaciones del alojamiento");
            popupStage.setScene(new Scene(root));
            popupStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}