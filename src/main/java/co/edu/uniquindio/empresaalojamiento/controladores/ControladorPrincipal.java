package co.edu.uniquindio.empresaalojamiento.controladores;

import co.edu.uniquindio.empresaalojamiento.servicios.EmpresaAlojamientoServicio;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import lombok.Getter;

import java.util.Objects;

@Getter
public class ControladorPrincipal {

    private static ControladorPrincipal instancia;
    private final EmpresaAlojamientoServicio empresaAlojamiento;


    private ControladorPrincipal(){
        empresaAlojamiento = new EmpresaAlojamientoServicio();


        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            empresaAlojamiento.getUsuarioRepositorio().guardarDatos(empresaAlojamiento.getUsuarioRepositorio().listarUsuarios());
            System.out.println("Usuarios guardados automáticamente al cerrar el programa.");
        }));
    }


    public static ControladorPrincipal getInstancia(){
        if(instancia == null){
            instancia = new ControladorPrincipal();
        }
        return instancia;
    }

    public void cerrarVentana(Node node){
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }


    public static void crearAlerta(String mensaje, Alert.AlertType tipo){
        Alert alert = new Alert(tipo);
        alert.setHeight(300);
        alert.setWidth(500);
        alert.setTitle("Alerta");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public static void navegarVentana(String fxml, String titulo, Node nodo, Class<?> clase) {
        try {
            FXMLLoader loader = new FXMLLoader(clase.getResource(fxml));
            Parent root = loader.load();
            Stage stage = (Stage) nodo.getScene().getWindow();
            stage.setTitle(titulo);
            stage.setScene(new Scene(root));
            stage.centerOnScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void cambiarEfectoHooverBoton(Button boton, String rutaImagenHover) {
        ImageView imageView = (ImageView) boton.getGraphic();
        Image imagenOriginal = imageView.getImage();
        System.out.println(imagenOriginal + "Antes de cargar la imagen");
        Image imagenHover = new Image(Objects.requireNonNull(ControladorPrincipal.class.getResourceAsStream(rutaImagenHover)));
        System.out.println("Despues de cargar la imagen");
        boton.setOnMouseEntered(e -> imageView.setImage(imagenHover));
        boton.setOnMouseExited(e -> imageView.setImage(imagenOriginal));
    }

}
