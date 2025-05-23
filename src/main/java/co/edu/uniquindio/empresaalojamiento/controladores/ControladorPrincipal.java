package co.edu.uniquindio.empresaalojamiento.controladores;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Alojamiento;
import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Habitacion;
import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Usuario;
import co.edu.uniquindio.empresaalojamiento.modelo.enums.Ciudad;
import co.edu.uniquindio.empresaalojamiento.modelo.enums.Rol;
import co.edu.uniquindio.empresaalojamiento.modelo.enums.TipoAlojamiento;
import co.edu.uniquindio.empresaalojamiento.repositorios.UsuarioRepositorio;
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
import javafx.stage.Window;
import lombok.Getter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
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
                    nombre("Hotel Estelar Boutique").
                    descripcion("Hotel moderno con habitaciones lujosas, spa y una vista panorámica de la ciudad. Ideal para viajes de negocios o descanso.").
                    ruta("imagenes/imagenHotel1.png").
                    piscina(true).
                    desayuno(true).
                    wifi(true).
                    ciudad(Ciudad.ARAUCA).
                    id("1").
                    build());

            ControladorPrincipal.getInstancia().getEmpresaAlojamiento().getAlojamientoRepositorio().agregarAlojamiento(Alojamiento.builder().
                    tipoAlojamiento(TipoAlojamiento.CASA).
                    nombre("Villa Flores del Norte").
                    descripcion("Hermosa casa rural con jardín, terraza privada y zona BBQ. Perfecta para familias grandes.").
                    ruta("imagenes/casa1.png").
                    piscina(true).
                    desayuno(false).
                    wifi(true).
                    mascotasPermitidas(true).
                    precioPorNoche(155000).
                    costoAdicional(15000).
                    ciudad(Ciudad.ARAUCA).
                    id("2").
                    build());

            ControladorPrincipal.getInstancia().getEmpresaAlojamiento().getAlojamientoRepositorio().agregarAlojamiento(Alojamiento.builder().
                    tipoAlojamiento(TipoAlojamiento.HOTEL).
                    nombre("Eco Hotel La Paz").
                    descripcion("Alojamiento ecológico en medio de la naturaleza, con actividades al aire libre y senderos naturales.").
                    ruta("imagenes/imagenHotel2.png").
                    piscina(false).
                    desayuno(true).
                    wifi(true).
                    ciudad(Ciudad.ARAUCA).
                    id("3").
                    build());

            ControladorPrincipal.getInstancia().getEmpresaAlojamiento().getAlojamientoRepositorio().agregarAlojamiento(Alojamiento.builder().
                    tipoAlojamiento(TipoAlojamiento.APARTAMENTOS).
                    nombre("Suites La Milagrosa").
                    descripcion("Apartamentos amplios y modernos con cocina equipada, ideal para estancias largas o viajes en grupo.").
                    ruta("imagenes/apartamentos1.png").
                    precioPorNoche(135000).
                    capacidadMaximaHuespedes(5).
                    piscina(false).
                    desayuno(false).
                    wifi(true).
                    costoAdicional(0).
                    ciudad(Ciudad.ARMENIA).
                    id("4").
                    build());

            ControladorPrincipal.getInstancia().getEmpresaAlojamiento().getAlojamientoRepositorio().agregarAlojamiento(Alojamiento.builder().
                    tipoAlojamiento(TipoAlojamiento.HOTEL).
                    nombre("Hotel Caribe Sol").
                    descripcion("Hotel de playa con acceso privado al mar, restaurante gourmet y entretenimiento nocturno.").
                    ruta("imagenes/imagenHotel3.png").
                    precioPorNoche(180000).
                    capacidadMaximaHuespedes(4).
                    piscina(true).
                    desayuno(true).
                    wifi(true).
                    ciudad(Ciudad.ARAUCA).
                    id("5").
                    build());

            ControladorPrincipal.getInstancia().getEmpresaAlojamiento().getAlojamientoRepositorio().agregarAlojamiento(Alojamiento.builder().
                    tipoAlojamiento(TipoAlojamiento.CASA).
                    nombre("Casa Nieve Eterna").
                    descripcion("Acogedora casa en la nieve, equipada con chimenea y vistas al Nevado del Ruiz. Experiencia única y cálida.").
                    ruta("imagenes/casa2.png").
                    precioPorNoche(350000).
                    capacidadMaximaHuespedes(2).
                    piscina(false).
                    desayuno(true).
                    wifi(false).
                    costoAdicional(20000).
                    ciudad(Ciudad.ARAUCA).
                    id("6").
                    build());

            UsuarioRepositorio usuarioRepositorio = ControladorPrincipal.getInstancia().getEmpresaAlojamiento().getUsuarioRepositorio();

            Usuario usuario = ControladorPrincipal.getInstancia().getEmpresaAlojamiento().registrarUsuario("1092457610", "Joab", "Suarez", "3014750404", "suarezjoab2706@gmail.com", "Contrasena123*");
            usuario.setActivo(true);
            usuario.getBilletera().setSaldo(2000000000);

            usuarioRepositorio.agregarUsuario(Usuario.builder().rol(Rol.ADMINISTRADOR).activo(true).nombre("Admin").apellido("Book your stay").email("joablsuarez@gmail.com").cedula("12345").contrasena("Contrasena123*").build());

            ControladorPrincipal.getInstancia().getEmpresaAlojamiento().registrarOferta(LocalDate.now(), LocalDate.now().plusDays(1), "60", "2", "Descuento especial de Navidad");
            ControladorPrincipal.getInstancia().getEmpresaAlojamiento().registrarOferta(LocalDate.now(), LocalDate.now().plusDays(1), "40", "3", "Promoción Día de la Madre");
            ControladorPrincipal.getInstancia().getEmpresaAlojamiento().registrarOferta(LocalDate.now(), LocalDate.now().plusDays(1), "10", "5", "Oferta especial de Halloween");

            ControladorPrincipal.getInstancia().getEmpresaAlojamiento().getHabitacionRepositorio().agregarHabitacion(new Habitacion("1", 1, 30000, 4, "imagenes/interior-del-sitio-de-alojamiento-comodo.jpg", "Habitación con cama doble, muy iluminada y confortable", "1"));
            ControladorPrincipal.getInstancia().getEmpresaAlojamiento().getHabitacionRepositorio().agregarHabitacion(new Habitacion("2", 2, 40000, 4, "imagenes/habitacion.jpg", "Amplia habitación familiar con vista al jardín", "1"));
            ControladorPrincipal.getInstancia().getEmpresaAlojamiento().getHabitacionRepositorio().agregarHabitacion(new Habitacion("3", 1, 30000, 4, "imagenes/interior-del-sitio-de-alojamiento-comodo.jpg", "Habitación sencilla ideal para descansar después de un día de paseo", "3"));
            ControladorPrincipal.getInstancia().getEmpresaAlojamiento().getHabitacionRepositorio().agregarHabitacion(new Habitacion("4", 1, 30000, 4, "imagenes/habitacion.jpg", "Cómoda habitación con decoración minimalista y acogedora", "3"));

            ControladorPrincipal.getInstancia().getEmpresaAlojamiento().registrarReserva(LocalDate.of(2025, 6, 5), LocalDate.of(2025, 6, 10), "2", "1", "1092457610", "1");
            ControladorPrincipal.getInstancia().getEmpresaAlojamiento().registrarReserva(LocalDate.of(2025, 6, 20), LocalDate.of(2025, 6, 22), "3", "1", "1092457610", "2");
            ControladorPrincipal.getInstancia().getEmpresaAlojamiento().registrarReserva(LocalDate.of(2025, 6, 25), LocalDate.of(2025, 6, 30), "2", "6", "1092457610", "");
            ControladorPrincipal.getInstancia().getEmpresaAlojamiento().registrarReserva(LocalDate.of(2026, 5, 5), LocalDate.of(2026, 5, 10), "2", "3", "1092457610", "1");

            System.out.println(ControladorPrincipal.getInstancia().empresaAlojamiento.getAlojamientoRepositorio().obtenerAlojamientos().size());

            ControladorPrincipal.getInstancia().getEmpresaAlojamiento().crearResena("Me encantó la atención del personal y la limpieza del lugar. Volveré pronto.", 4, "1092457610", "1");
            ControladorPrincipal.getInstancia().getEmpresaAlojamiento().crearResena("El alojamiento no cumplió con lo que prometía. Hay cosas por mejorar.", 2, "1092457610", "1");
            ControladorPrincipal.getInstancia().getEmpresaAlojamiento().crearResena("Una estadía fantástica. Todo fue perfecto desde la llegada hasta el check-out.", 5, "1092457610", "1");
            ControladorPrincipal.getInstancia().getEmpresaAlojamiento().crearResena("El lugar no era lo que esperaba. Las fotos eran engañosas.", 1, "1092457610", "1");

        } catch (Exception e) {
            System.out.println(e.getMessage());
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