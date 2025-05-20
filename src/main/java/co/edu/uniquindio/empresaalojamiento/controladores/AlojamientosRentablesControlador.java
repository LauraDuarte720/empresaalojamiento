package co.edu.uniquindio.empresaalojamiento.controladores;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Alojamiento;
import co.edu.uniquindio.empresaalojamiento.modelo.enums.TipoAlojamiento;
import co.edu.uniquindio.empresaalojamiento.servicios.EmpresaAlojamientoServicio;
import co.edu.uniquindio.empresaalojamiento.singleton.AlojamientoSingleton;
import co.edu.uniquindio.empresaalojamiento.singleton.Sesion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;

import java.util.List;
import java.util.stream.Collectors;

public class AlojamientosRentablesControlador {

    private final EmpresaAlojamientoServicio controladorPrincipal = ControladorPrincipal.getInstancia().getEmpresaAlojamiento();
    private final Sesion sesion = Sesion.getInstancia();
    private final Alojamiento alojamiento = AlojamientoSingleton.getInstancia().getAlojamiento();

    @FXML
    private PieChart graficaRentabilidadPorTipo;


    @FXML
    void regresar(ActionEvent event) {
        ControladorPrincipal.navegarVentana("/co/edu/uniquindio/empresaalojamiento/menuAdministrador.fxml", "Inicio Sesion", graficaRentabilidadPorTipo, getClass());
    }

    @FXML
    public void initialize() {
        cargarGraficaRentabilidadPorTipo();
    }

    public void cargarGraficaRentabilidadPorTipo() {
        ObservableList<PieChart.Data> datosGrafica = FXCollections.observableArrayList();

        for (TipoAlojamiento tipo : TipoAlojamiento.values()) {
            List<Alojamiento> alojamientosTipo = controladorPrincipal.obtenerAlojamientos().stream()
                    .filter(a -> a.getTipoAlojamiento().equals(tipo))
                    .toList();

            double gananciasTipo = 0;

            for (Alojamiento alojamiento : alojamientosTipo) {
                gananciasTipo += controladorPrincipal.obtenerGananciasTotales(alojamiento.getId());
            }

            if (gananciasTipo > 0) {
                datosGrafica.add(new PieChart.Data(tipo.name(), gananciasTipo));
            }
        }

        graficaRentabilidadPorTipo.setData(datosGrafica);
    }

}
