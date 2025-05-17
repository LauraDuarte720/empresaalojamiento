package co.edu.uniquindio.empresaalojamiento.controladores;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Alojamiento;
import co.edu.uniquindio.empresaalojamiento.modelo.enums.Ciudad;
import co.edu.uniquindio.empresaalojamiento.modelo.enums.TipoAlojamiento;
import co.edu.uniquindio.empresaalojamiento.servicios.EmpresaAlojamientoServicio;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
            empresaAlojamiento.getAlojamientoRepositorio().guardarDatos(empresaAlojamiento.getAlojamientoRepositorio().obtenerAlojamientos());
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
        alert.setHeight(200);
        alert.setWidth(400);
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


    public static Parent cargarPanel(String fxmlFile, Class<?> clase) {
        try {
            FXMLLoader loader = new FXMLLoader(clase.getResource(fxmlFile));
            Parent node = loader.load();
            return node;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean setMostrarContrasena(boolean mostrar, Button boton, PasswordField passwordField, TextField textField, Class<?> clase) {
        if(mostrar) {
            ((ImageView) boton.getGraphic()).setImage(new Image(Objects.requireNonNull(clase.getResourceAsStream("/co/edu/uniquindio/empresaalojamiento/imagenesIconos/ojoAbiertoClaro.png"))));
            ControladorPrincipal.cambiarEfectoHooverBoton(boton, "/co/edu/uniquindio/empresaalojamiento/imagenesIconos/ojoAbiertoOscuro.png");
            textField.setVisible(true);
            textField.setManaged(true);
            passwordField.setVisible(false);
            passwordField.setManaged(false);
            return true;
        } else {
            ((ImageView) boton.getGraphic()).setImage(new Image(Objects.requireNonNull(clase.getResourceAsStream("/co/edu/uniquindio/empresaalojamiento/imagenesIconos/ojoCerradoClaro.png"))));
            ControladorPrincipal.cambiarEfectoHooverBoton(boton, "/co/edu/uniquindio/empresaalojamiento/imagenesIconos/ojoCerradoOscuro.png");
            textField.setVisible(false);
            textField.setManaged(false);
            passwordField.setVisible(true);
            passwordField.setManaged(true);
            return false;
        }
    }

    public static void cargarData(){
        try {
            ControladorPrincipal.getInstancia().getEmpresaAlojamiento().registrarAlojamiento(TipoAlojamiento.HOTEL,
                    "Hotel Estelar",
                    "Un hotel moderno con vista a la ciudad.", "/co/edu/uniquindio/empresaalojamiento/imagenesAlojamientos/imagenHotel1.png", 250000,
                    4, true, false, true, 300, Ciudad.ARAUCA);

            ControladorPrincipal.getInstancia().getEmpresaAlojamiento().registrarAlojamiento(TipoAlojamiento.CASA,
                    "Casa flores",
                    "Casa muy bonita al norte de la ciudad", "/co/edu/uniquindio/empresaalojamiento/imagenesAlojamientos/casa1.png", 260000,
                    4, true, true, false, 300, Ciudad.ARAUCA);


            ControladorPrincipal.getInstancia().getEmpresaAlojamiento().registrarAlojamiento(TipoAlojamiento.HOTEL,
                    "Hotel Estelar",
                    "Un hotel moderno con vista a la ciudad.", "/co/edu/uniquindio/empresaalojamiento/imagenesAlojamientos/imagenHotel2.png", 250000,
                    4, false, true, true, 300, Ciudad.ARAUCA);

            ControladorPrincipal.getInstancia().getEmpresaAlojamiento().registrarAlojamiento(TipoAlojamiento.APARTAMENTOS,
                    "Apartamentos la milagrosa",
                    "Casa muy bonita al norte de la ciudad", "/co/edu/uniquindio/empresaalojamiento/imagenesAlojamientos/apartamentos1.png", 260000,
                    4, true, true, true, 300, Ciudad.ARMENIA);


            ControladorPrincipal.getInstancia().getEmpresaAlojamiento().registrarAlojamiento(TipoAlojamiento.HOTEL,
                    "Hotel caribe",
                    "Hotel al borde del mar", "/co/edu/uniquindio/empresaalojamiento/imagenesAlojamientos/imagenHotel3.png", 250000,
                    4, true, true, true, 300, Ciudad.ARAUCA);

            ControladorPrincipal.getInstancia().getEmpresaAlojamiento().registrarAlojamiento(TipoAlojamiento.CASA,
                    "Casa de las nieves",
                    "Casa iglue en el nevado del ruiz", "/co/edu/uniquindio/empresaalojamiento/imagenesAlojamientos/casa2.png", 260000,
                    4, true, true, true, 300, Ciudad.ARAUCA);

            Alojamiento alojamiento = ControladorPrincipal.getInstancia().empresaAlojamiento.getAlojamientoRepositorio().obtenerAlojamientos().getFirst();
            alojamiento.setId("1");

            ControladorPrincipal.getInstancia().getEmpresaAlojamiento().crearResena("El alojamiento es muy bonito", 4, "1092457610", "1");
            ControladorPrincipal.getInstancia().getEmpresaAlojamiento().crearResena("El alojamiento es muy feo bonito", 1, "1054863011", "1");
            ControladorPrincipal.getInstancia().getEmpresaAlojamiento().crearResena("El alojamiento es hermoso", 5, "1092457610", "1");
            ControladorPrincipal.getInstancia().getEmpresaAlojamiento().crearResena("El alojamiento es asqueroso", 1, "1054863011", "1");
        } catch (Exception ignored) {
        }
    }

}
