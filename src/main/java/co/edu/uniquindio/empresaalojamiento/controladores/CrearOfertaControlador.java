package co.edu.uniquindio.empresaalojamiento.controladores;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Alojamiento;
import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Usuario;
import co.edu.uniquindio.empresaalojamiento.servicios.EmpresaAlojamientoServicio;
import co.edu.uniquindio.empresaalojamiento.singleton.AlojamientoSingleton;
import co.edu.uniquindio.empresaalojamiento.singleton.Sesion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class CrearOfertaControlador {

    @FXML
    private DatePicker dateFechaFinal;

    @FXML
    private DatePicker dateFechaInicio;

    @FXML
    private Button regresar;

    @FXML
    private TextField txtDescripcion;

    @FXML
    private TextField txtDescuento;

    private final EmpresaAlojamientoServicio controladorPrincipal = ControladorPrincipal.getInstancia().getEmpresaAlojamiento();
    private final Alojamiento alojamiento = AlojamientoSingleton.getInstancia().getAlojamiento();

    @FXML
    void crearOferta(ActionEvent event) {
        try{
            controladorPrincipal.registrarOferta(dateFechaInicio.getValue(),dateFechaFinal.getValue(),txtDescuento.getText(),alojamiento.getId(),txtDescripcion.getText());
            ControladorPrincipal.crearAlerta("Se ha creado con exito la oferta", Alert.AlertType.INFORMATION);

            for(Usuario usuarioNotificacion:controladorPrincipal.obtenerUsuarios()){
                controladorPrincipal.enviarNotificacion("Hay una nueva oferta para " + alojamiento.getNombre()+ " para las fechas " + dateFechaInicio.getValue() +" - "+ dateFechaFinal.getValue() ,usuarioNotificacion.getCedula());
            }
            AlojamientoSingleton.getInstancia().setAlojamiento(null);
            ControladorPrincipal.navegarVentana("/co/edu/uniquindio/empresaalojamiento/menuAdministrador.fxml", "Usuario", txtDescripcion, getClass());
        }catch (Exception e){
            ControladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }


    @FXML
    void regresar(ActionEvent event) {
        ControladorPrincipal.navegarVentana("/co/edu/uniquindio/empresaalojamiento/menuAdministrador.fxml", "Administrador", txtDescripcion, getClass());
    }


}

