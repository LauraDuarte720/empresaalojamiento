package co.edu.uniquindio.empresaalojamiento.controladores;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Alojamiento;
import co.edu.uniquindio.empresaalojamiento.servicios.EmpresaAlojamientoServicio;
import co.edu.uniquindio.empresaalojamiento.singleton.AlojamientoSingleton;
import co.edu.uniquindio.empresaalojamiento.singleton.Sesion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.time.Year;
import java.util.ResourceBundle;

public class EstadisticaAlojamientoControlador {

    @FXML
    private CategoryAxis ano;

    @FXML
    private BarChart<String, Number> barcharOcupacion;

    @FXML
    private ComboBox<Integer> cmbAno;

    @FXML
    private NumberAxis procentaje;

    @FXML
    private ComboBox<Integer> cmbAnoGanaciasTotales;


    @FXML
    private BarChart<String, Number> barcharGananciasTotales;


    private final EmpresaAlojamientoServicio controladorPrincipal = ControladorPrincipal.getInstancia().getEmpresaAlojamiento();
    private final Sesion sesion = Sesion.getInstancia();
    private final Alojamiento alojamiento = AlojamientoSingleton.getInstancia().getAlojamiento();

    @FXML
    private void initialize() {

        int currentYear = Year.now().getValue();
        for (int i = currentYear; i >= currentYear - 10; i--) {
            cmbAno.getItems().add(i);
            cmbAnoGanaciasTotales.getItems().add(i);
        }

        cmbAno.setOnAction(event -> {
            Integer selectedYear = cmbAno.getValue();
            if (selectedYear != null && alojamiento.getId() != null) {
                mostrarOcupacion(selectedYear, alojamiento.getId());
            }
        });

        cmbAnoGanaciasTotales.setOnAction(event -> {
            Integer selectedYear = cmbAnoGanaciasTotales.getValue();
            if (selectedYear != null && alojamiento.getId() != null) {
                mostrarGanancias(selectedYear, alojamiento.getId());
            }
        });
    }

    @FXML
    void regresar(ActionEvent event) {
        ControladorPrincipal.navegarVentana("/co/edu/uniquindio/empresaalojamiento/menuAdministrador.fxml", "Menu Administrador", cmbAno, getClass());
        AlojamientoSingleton.getInstancia().setAlojamiento(null);
    }


    private void mostrarOcupacion(int ano, String idAlojamiento) {
        barcharOcupacion.getData().clear();

        double porcentaje = controladorPrincipal.calcularOcupacionPorcentual(ano, idAlojamiento);

        XYChart.Series<String, Number> serie = new XYChart.Series<>();
        serie.setName("Año " + ano);
        serie.getData().add(new XYChart.Data<>(String.valueOf(ano), porcentaje));

        barcharOcupacion.getData().add(serie);
    }

    private void mostrarGanancias(int ano, String idAlojamiento) {
        barcharGananciasTotales.getData().clear();

        double ganancias = controladorPrincipal.obtenerGananciasTotales(ano, idAlojamiento);

        XYChart.Series<String, Number> serie = new XYChart.Series<>();
        serie.setName("Año " + ano);
        serie.getData().add(new XYChart.Data<>(String.valueOf(ano), ganancias));

        barcharGananciasTotales.getData().add(serie);
    }


}

