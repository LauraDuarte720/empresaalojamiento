package co.edu.uniquindio.empresaalojamiento.controladores;

import co.edu.uniquindio.empresaalojamiento.servicios.EmpresaAlojamientoServicio;
import co.edu.uniquindio.empresaalojamiento.singleton.Sesion;
import co.edu.uniquindio.empresaalojamiento.utilidades.Utilidades;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class RecargarBilleteraControlador {

    @FXML
    private TextField txtMontoRecargar;

    @FXML
    private TextField txtSaldo;


    private final EmpresaAlojamientoServicio empresaAlojamientoServicio = ControladorPrincipal.getInstancia().getEmpresaAlojamiento();
    private final Sesion sesion = Sesion.getInstancia();

    @FXML
    public void initialize() {
        try {

            txtSaldo.setText("Su saldo actual es: " + Utilidades.obtenerValorCadena(sesion.getUsuario().getBilletera().getSaldo()));
        } catch (Exception e) {
            ControladorPrincipal.crearAlerta("No existe un usuario con esa cedula", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void recargarBilletera(ActionEvent event) {
        try {
            empresaAlojamientoServicio.recargarBilletera(Double.parseDouble(txtMontoRecargar.getText()), sesion.getUsuario().getCedula());
            ControladorPrincipal.crearAlerta("Ha recargado su billetera con exito", Alert.AlertType.INFORMATION);
            txtMontoRecargar.clear();
            txtSaldo.setText("Su saldo actual es: " + Utilidades.obtenerValorCadena(sesion.getUsuario().getBilletera().getSaldo()));

        } catch (Exception e) {
            ControladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

}
