package co.edu.uniquindio.empresaalojamiento.controladores;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Sesion;
import co.edu.uniquindio.empresaalojamiento.modelo.enums.Calificacion;
import co.edu.uniquindio.empresaalojamiento.servicios.EmpresaAlojamientoServicio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

public class CrearResenaControlador {

    @FXML
    private ComboBox<String> cmbCalificacion;

    @FXML
    private TextArea txtValoracion;

    private final EmpresaAlojamientoServicio controladorPrincipal = ControladorPrincipal.getInstancia().getEmpresaAlojamiento();
    private final Sesion sesion = Sesion.getInstancia();


    public void inicializar() {
        cmbCalificacion.getItems().addAll(Arrays.stream(Calificacion.values())
                .map(Calificacion::getNombreLegible)
                .toList());

    }

    @FXML
    void crearResena(ActionEvent event) {
        try {
            //controladorPrincipal.crearResena(txtValoracion.getText(),Calificacion.getCalificacion(cmbCalificacion.getValue()),sesion.getUsuario().getCedula(),alojamiento.getId());
        }catch (Exception e){

        }
    }

}
