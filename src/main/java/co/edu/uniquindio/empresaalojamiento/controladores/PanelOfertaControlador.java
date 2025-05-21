package co.edu.uniquindio.empresaalojamiento.controladores;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Alojamiento;
import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Oferta;
import co.edu.uniquindio.empresaalojamiento.servicios.EmpresaAlojamientoServicio;
import co.edu.uniquindio.empresaalojamiento.singleton.AlojamientoSingleton;
import co.edu.uniquindio.empresaalojamiento.singleton.Sesion;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import static javafx.collections.FXCollections.observableList;

public class PanelOfertaControlador {

    @FXML
    private TableColumn<Oferta, String> tbcAlojamiento;

    @FXML
    private TableColumn<Oferta, String> tbcDescripcion;

    @FXML
    private TableColumn<Oferta, String> tbcFechaFinal;

    @FXML
    private TableColumn<Oferta, String> tbcFechaInicio;

    @FXML
    private TableColumn<Oferta, String> tbcPorcentajes;

    @FXML
    private TableView<Oferta> tblOferta;

    private final EmpresaAlojamientoServicio controladorPrincipal = ControladorPrincipal.getInstancia().getEmpresaAlojamiento();
    private final Sesion sesion = Sesion.getInstancia();
    private final Alojamiento alojamiento = AlojamientoSingleton.getInstancia().getAlojamiento();

    @FXML
    public void initialize() {
        tbcAlojamiento.setCellValueFactory(cellData ->new SimpleStringProperty(controladorPrincipal.obtenerAlojamientoPorId(cellData.getValue().getIdAlojamiento()).getNombre()));
        tbcDescripcion.setCellValueFactory(cellData ->new SimpleStringProperty(cellData.getValue().getDescripcion()));
        tbcFechaInicio.setCellValueFactory(cellData ->new SimpleStringProperty(cellData.getValue().getFechaInicio().toString()));
        tbcFechaFinal.setCellValueFactory(cellData ->new SimpleStringProperty(cellData.getValue().getFechaFinal().toString()));
        tbcPorcentajes.setCellValueFactory(cellData ->new SimpleStringProperty(String.valueOf(cellData.getValue().getValorPorcentaje())));
        setOfertas();
    }

    @FXML
    void actualizar(ActionEvent event) {

    }

    @FXML
    void eliminar(ActionEvent event) {

    }

    public void setOfertas(){
        tblOferta.setItems(observableList(controladorPrincipal.obtenerOfertas()));
        tblOferta.refresh();
    }
}

