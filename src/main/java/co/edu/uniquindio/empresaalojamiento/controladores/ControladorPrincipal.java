package co.edu.uniquindio.empresaalojamiento.controladores;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Usuario;
import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Alojamiento;
import co.edu.uniquindio.empresaalojamiento.modelo.enums.Rol;
import co.edu.uniquindio.empresaalojamiento.modelo.enums.TipoAlojamiento;
import co.edu.uniquindio.empresaalojamiento.modelo.enums.Ciudad;
import co.edu.uniquindio.empresaalojamiento.repositorios.UsuarioRepositorio;
import co.edu.uniquindio.empresaalojamiento.servicios.EmpresaAlojamientoServicio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import lombok.Getter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class ControladorPrincipal {

    private static ControladorPrincipal instancia;
    private final EmpresaAlojamientoServicio empresaAlojamiento;


    private ControladorPrincipal() {
        empresaAlojamiento = new EmpresaAlojamientoServicio();


        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            empresaAlojamiento.getUsuarioRepositorio().guardarDatos(empresaAlojamiento.getUsuarioRepositorio().listarUsuarios());
            empresaAlojamiento.getAlojamientoRepositorio().guardarDatos(empresaAlojamiento.getAlojamientoRepositorio().obtenerAlojamientos());
            empresaAlojamiento.getOfertaRepositorio().guardarDatos(empresaAlojamiento.getOfertaRepositorio().obtenerOfertas());
            empresaAlojamiento.getHabitacionRepositorio().guardarDatos(empresaAlojamiento.getHabitacionRepositorio().obtenerHabitaciones());
            empresaAlojamiento.getReservaRepositorio().guardarDatos(empresaAlojamiento.getReservaRepositorio().obtenerReservas());
            empresaAlojamiento.getResenaRepositorio().guardarDatos(empresaAlojamiento.getResenaRepositorio().obtenerResenas());
            System.out.println("Datos guardados automáticamente al cerrar el programa.");
        }));
    }


    public static ControladorPrincipal getInstancia() {
        if (instancia == null) {
            instancia = new ControladorPrincipal();
        }
        return instancia;
    }


    public void cerrarVentana(Node node) {
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }


    public static void crearAlerta(String mensaje, Alert.AlertType tipo) {
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
        if (mostrar) {
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

    public static String guardarImagenEnDirectorio(File archivoOrigen) throws IOException {
        File directorioImagenes = new File("imagenes");
        if (!directorioImagenes.exists()) {
            directorioImagenes.mkdirs();
        }

        String nombreArchivo = archivoOrigen.getName();
        File archivoDestino = new File(directorioImagenes, nombreArchivo);

        Files.copy(archivoOrigen.toPath(), archivoDestino.toPath(), StandardCopyOption.REPLACE_EXISTING);

        return "imagenes/" + nombreArchivo;
    }


    public static void cargarData() {
        try {
            ControladorPrincipal.getInstancia().getEmpresaAlojamiento().getAlojamientoRepositorio().agregarAlojamiento(Alojamiento.builder().
                    tipoAlojamiento(TipoAlojamiento.HOTEL).
                    nombre("Hotel Estelar").
                    descripcion("Un hotel moderno con vista a la ciudad.").
                    ruta("imagenes/imagenHotel1.png").
                    piscina(true).
                    desayuno(false).
                    wifi(true).
                    costoAdicional(300).
                    ciudad(Ciudad.ARAUCA).
                    id("1").
                    build());

            ControladorPrincipal.getInstancia().getEmpresaAlojamiento().getAlojamientoRepositorio().agregarAlojamiento(Alojamiento.builder().
                    tipoAlojamiento(TipoAlojamiento.CASA).
                    nombre("Casa flores").
                    descripcion("Casa muy bonita al norte de la ciudad").
                    ruta("imagenes/casa1.png").
                    piscina(true).
                    desayuno(false).
                    wifi(true).
                    costoAdicional(500).
                    ciudad(Ciudad.ARAUCA).
                    id("2").
                    build());

            ControladorPrincipal.getInstancia().getEmpresaAlojamiento().getAlojamientoRepositorio().agregarAlojamiento(Alojamiento.builder().
                    tipoAlojamiento(TipoAlojamiento.HOTEL).
                    nombre("Hotel la paz").
                    descripcion("Un hotel moderno con vista al monte.").
                    ruta("imagenes/imagenHotel2.png").
                    piscina(true).
                    desayuno(false).
                    wifi(true).
                    costoAdicional(300).
                    ciudad(Ciudad.ARAUCA).
                    id("3").
                    build());

            ControladorPrincipal.getInstancia().getEmpresaAlojamiento().getAlojamientoRepositorio().agregarAlojamiento(Alojamiento.builder().
                    tipoAlojamiento(TipoAlojamiento.APARTAMENTOS).
                    nombre("Apartamentos la milagrosa").
                    descripcion("Apartamentos muy bonitos al norte de la ciudad").
                    ruta("imagenes/apartamentos1.png").
                    precioPorNoche(130000).
                    capacidadMaximaHuespedes(6).
                    piscina(true).
                    desayuno(false).
                    wifi(true).
                    costoAdicional(100).
                    ciudad(Ciudad.ARMENIA).
                    id("4").
                    build());

            ControladorPrincipal.getInstancia().getEmpresaAlojamiento().registrarAlojamiento(TipoAlojamiento.HOTEL,
                    "Hotel hola",
                    "Hotel al borde del mar", "imagenes/imagenHotel3.png", 250000,
                    4, true, true, true, 300, Ciudad.ARAUCA);


            ControladorPrincipal.getInstancia().getEmpresaAlojamiento().getAlojamientoRepositorio().agregarAlojamiento(Alojamiento.builder().
                    tipoAlojamiento(TipoAlojamiento.HOTEL).
                    nombre("Hotel caribe").
                    descripcion("Hotel al borde del mar").
                    ruta("imagenes/imagenHotel3.png").
                    precioPorNoche(110000).
                    capacidadMaximaHuespedes(6).
                    piscina(true).
                    desayuno(false).
                    wifi(true).
                    costoAdicional(10000).
                    ciudad(Ciudad.ARAUCA).
                    id("5").
                    build());

            ControladorPrincipal.getInstancia().getEmpresaAlojamiento().getAlojamientoRepositorio().agregarAlojamiento(Alojamiento.builder().
                    tipoAlojamiento(TipoAlojamiento.CASA).
                    nombre("Casa de las nieves").
                    descripcion("Casa iglue en el nevado del ruiz").
                    ruta("imagenes/casa2.png").
                    precioPorNoche(320000).
                    capacidadMaximaHuespedes(3).
                    piscina(false).
                    desayuno(true).
                    wifi(false).
                    costoAdicional(30000).
                    ciudad(Ciudad.ARAUCA).
                    id("5").
                    build());
            ;

            ControladorPrincipal.getInstancia().getEmpresaAlojamiento().registrarOferta(LocalDate.now(), LocalDate.now().plusDays(1), "60", "2", "Oferta por navidad");
            ControladorPrincipal.getInstancia().getEmpresaAlojamiento().registrarOferta(LocalDate.now(), LocalDate.now().plusDays(1), "40", "3", "Oferta por dia de la madre");
            ControladorPrincipal.getInstancia().getEmpresaAlojamiento().registrarOferta(LocalDate.now(), LocalDate.now().plusDays(1), "10", "5", "Oferta por hallowen");

            ControladorPrincipal.getInstancia().getEmpresaAlojamiento().registrarHabitacion("1", "30000", "4", "Hola", "1", "imagenes/interior-del-sitio-de-alojamiento-comodo.jpg");
            ControladorPrincipal.getInstancia().getEmpresaAlojamiento().registrarHabitacion("2", "40000", "4", "Adios", "1", "imagenes/casa-de-lujo-moderna-con-un-hermoso-cesped-y-un-cielo-soleado.jpg");

            Alojamiento alojamiento = ControladorPrincipal.getInstancia().empresaAlojamiento.getAlojamientoRepositorio().obtenerAlojamientos().getFirst();
            alojamiento.setId("1");

            System.out.println(ControladorPrincipal.getInstancia().empresaAlojamiento.getAlojamientoRepositorio().obtenerAlojamientos().size());

            ControladorPrincipal.getInstancia().getEmpresaAlojamiento().crearResena("El alojamiento es muy bonito", 4, "1092457610", "1");
            ControladorPrincipal.getInstancia().getEmpresaAlojamiento().crearResena("El alojamiento es muy feo bonito", 1, "1092457610", "1");
            ControladorPrincipal.getInstancia().getEmpresaAlojamiento().crearResena("El alojamiento es hermoso", 5, "1092457610", "1");
            ControladorPrincipal.getInstancia().getEmpresaAlojamiento().crearResena("El alojamiento es asqueroso", 1, "1092457610", "1");
            UsuarioRepositorio usuarioRepositorio = ControladorPrincipal.getInstancia().getEmpresaAlojamiento().getUsuarioRepositorio();
            usuarioRepositorio.agregarUsuario(Usuario.builder().rol(Rol.ADMINISTRADOR).activo(true).nombre("Laura mi amor").apellido("De Suarez").email("joablsuarez@gmail.com").cedula("12345").contrasena("Joab2007*").build());
        } catch (Exception ignored) {
        }
    }

    public static void cerrarVentanaPorTitulo(String titulo) {
        for (Window window : Window.getWindows()) {
            if (window instanceof Stage && ((Stage) window).getTitle().equals(titulo)) {
                Stage stage = (Stage) window;
                if (stage.isShowing()) {
                    stage.close();
                    break;
                }
            }
        }

    }
}