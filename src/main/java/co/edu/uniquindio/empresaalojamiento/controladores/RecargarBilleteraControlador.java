package co.edu.uniquindio.empresaalojamiento.controladores;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Alojamiento;
import co.edu.uniquindio.empresaalojamiento.servicios.EmpresaAlojamientoServicio;
import co.edu.uniquindio.empresaalojamiento.singleton.AlojamientoSingleton;
import co.edu.uniquindio.empresaalojamiento.singleton.Sesion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class RecargarBilleteraControlador {

    @FXML
    private TextField txtMontoRecargar;

    @FXML
    private Label lblSaldoActual;

    private final EmpresaAlojamientoServicio controladorPrincipal = ControladorPrincipal.getInstancia().getEmpresaAlojamiento();
    private final Sesion sesion = Sesion.getInstancia();
    private final Alojamiento alojamiento = AlojamientoSingleton.getInstancia().getAlojamiento();

    @FXML
    public void initialize(){
        lblSaldoActual.setText("Su saldo actual es: " + sesion.getUsuario().getBilletera().getSaldo());
    }
    @FXML
    void recargarBilletera(ActionEvent event) {
        try{
             controladorPrincipal.recargarBilletera(Double.parseDouble(txtMontoRecargar.getText()),sesion.getUsuario().getCedula());
             ControladorPrincipal.crearAlerta("Ha recargado su billetera con exito", Alert.AlertType.INFORMATION);
             txtMontoRecargar.clear();
             lblSaldoActual.setText("Su saldo actual es: " + sesion.getUsuario().getBilletera().getSaldo());

        }catch (Exception e) {
            ControladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

}
