package co.edu.uniquindio.empresaalojamiento.controladores;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Alojamiento;
import co.edu.uniquindio.empresaalojamiento.servicios.EmpresaAlojamientoServicio;
import co.edu.uniquindio.empresaalojamiento.singleton.AlojamientoSingleton;
import co.edu.uniquindio.empresaalojamiento.singleton.Sesion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class CrearReservaControlador {

    @FXML
    private DatePicker datePickFechaIngreso;

    @FXML
    private DatePicker datePickFechaSalida;

    @FXML
    private ImageView imgFechaIngreso;

    @FXML
    private ImageView imgFechaSalida;

    @FXML
    private ImageView imgNumHuesped;

    @FXML
    private TextField txtNumHuesped;

    private final EmpresaAlojamientoServicio controladorPrincipal = ControladorPrincipal.getInstancia().getEmpresaAlojamiento();
    private final Sesion sesion = Sesion.getInstancia();
    private  Alojamiento alojamiento;

    @FXML
    void initialize(){
        alojamiento = AlojamientoSingleton.getInstancia().getAlojamiento();
    }

    @FXML
    void crearReserva(ActionEvent event) {
        try {
            controladorPrincipal.registrarReserva(datePickFechaIngreso.getValue(),datePickFechaSalida.getValue(),Integer.parseInt(txtNumHuesped.getText()),alojamiento.getId(),sesion.getUsuario().getCedula());
            ControladorPrincipal.crearAlerta("Se ha creado con exito la reserva", Alert.AlertType.INFORMATION);
            AlojamientoSingleton.getInstancia().setAlojamiento(null);
            ControladorPrincipal.navegarVentana("/co/edu/uniquindio/empresaalojamiento/menuCliente.fxml", "Usuario", txtNumHuesped, getClass());
        }catch (Exception e){
            ControladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    void regresar(ActionEvent event) {
        ControladorPrincipal.navegarVentana("/co/edu/uniquindio/empresaalojamiento/menuCliente.fxml", "Usuario", txtNumHuesped, getClass());
        AlojamientoSingleton.getInstancia().setAlojamiento(null);
    }

}

