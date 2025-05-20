package co.edu.uniquindio.empresaalojamiento.controladores;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Alojamiento;
import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Resena;
import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Usuario;
import co.edu.uniquindio.empresaalojamiento.servicios.EmpresaAlojamientoServicio;
import co.edu.uniquindio.empresaalojamiento.singleton.AlojamientoSingleton;
import co.edu.uniquindio.empresaalojamiento.singleton.Sesion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.Objects;

public class ItemAlojamientoControlador {

    @FXML
    private HBox hBoxSiResena;

    @FXML
    private ImageView imgAlojamiento;

    @FXML
    private Label lblCalificacion;

    @FXML
    private Label lblDescripcion;

    @FXML
    private Label lblNoResena;

    @FXML
    private Label lblOpinion;

    @FXML
    private Label lblTituloAlojamiento;

    @FXML
    private Label lblUsuario;

    @FXML
    private Label lblCalificacionIndividual;

    @FXML
    private VBox vBoxCamposOpcionales;


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
        imgAlojamiento.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(alojamiento.getRuta()))));
        llenarCamposAdicionales();

        try {
            listaResenas = empresaAlojamientoServicio.obtenerResenasAlojamiento(alojamiento.getId());
        } catch (Exception e) {
            listaResenas = List.of();
        }

        llenarResena();
    }

    public void reservar(ActionEvent actionEvent) {
        AlojamientoSingleton.getInstancia().setAlojamiento(alojamiento);
        if(Sesion.getInstancia().getUsuario() != null){
            ControladorPrincipal.navegarVentana("/co/edu/uniquindio/empresaalojamiento/crearReserva.fxml", "Reservar Alojamiento", lblTituloAlojamiento, getClass());
        }
        else{
            ControladorPrincipal.crearAlerta("Para reservar debe iniciar sesión", Alert.AlertType.INFORMATION);
            ControladorPrincipal.navegarVentana("/co/edu/uniquindio/empresaalojamiento/iniciarSesion.fxml", "Reservar Alojamiento", lblTituloAlojamiento, getClass());
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

}
