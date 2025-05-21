package co.edu.uniquindio.empresaalojamiento.controladores;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Alojamiento;
import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Oferta;
import co.edu.uniquindio.empresaalojamiento.servicios.EmpresaAlojamientoServicio;
import co.edu.uniquindio.empresaalojamiento.singleton.AlojamientoSingleton;
import co.edu.uniquindio.empresaalojamiento.singleton.OfertaSingleton;
import co.edu.uniquindio.empresaalojamiento.singleton.Sesion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class ActualizarOfertaControlador {

    @FXML
    private DatePicker dateFechaFinal;

    @FXML
    private DatePicker dateFechaInicio;

    @FXML
    private TextField txtDescripcion;

    @FXML
    private TextField txtDescuento;

    private final EmpresaAlojamientoServicio controladorPrincipal = ControladorPrincipal.getInstancia().getEmpresaAlojamiento();
    private final Sesion sesion = Sesion.getInstancia();
    private final Alojamiento alojamiento = AlojamientoSingleton.getInstancia().getAlojamiento();
    private final Oferta oferta = OfertaSingleton.getInstancia().getOferta();

    @FXML
    private void initialize(){
        dateFechaFinal.setValue(oferta.getFechaFinal());
        dateFechaInicio.setValue(oferta.getFechaInicio());
        txtDescripcion.setText(oferta.getDescripcion());
        txtDescuento.setText(String.valueOf(oferta.getValorPorcentaje()*100));
    }
    @FXML
    void actualizarOferta(ActionEvent event) {
        try {
            controladorPrincipal.actualizarOferta(oferta.getId(),dateFechaInicio.getValue(),dateFechaFinal.getValue(),Double.parseDouble(txtDescuento.getText()),alojamiento.getId(),txtDescripcion.getText());
            ControladorPrincipal.crearAlerta("Se ha actualizado con exito la oferta", Alert.AlertType.INFORMATION);

        }catch (Exception e){
            ControladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    void regresar(ActionEvent event) {
        ControladorPrincipal.navegarVentana("menuAdministrador", "Administrador", txtDescripcion, getClass());
    }

}
