package co.edu.uniquindio.empresaalojamiento.controladores;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Alojamiento;
import co.edu.uniquindio.empresaalojamiento.servicios.EmpresaAlojamientoServicio;
import co.edu.uniquindio.empresaalojamiento.singleton.AlojamientoSingleton;
import co.edu.uniquindio.empresaalojamiento.singleton.Sesion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    private Alojamiento alojamiento = AlojamientoSingleton.getInstancia().getAlojamiento();

    @FXML
    private void initialize(){
        dateFechaFinal.setValue(null);
        dateFechaInicio.setValue(null);
        txtDescripcion.setText(null);
        txtDescuento.setText(null);
    }
    @FXML
    void actualizarOferta(ActionEvent event) {

    }

    @FXML
    void regresar(ActionEvent event) {

    }

}
