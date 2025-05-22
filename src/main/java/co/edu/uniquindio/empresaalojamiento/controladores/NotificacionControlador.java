package co.edu.uniquindio.empresaalojamiento.controladores;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Alojamiento;
import co.edu.uniquindio.empresaalojamiento.modelo.enums.EstadoNotificacion;
import co.edu.uniquindio.empresaalojamiento.modelo.vo.Notificacion;
import co.edu.uniquindio.empresaalojamiento.servicios.EmpresaAlojamientoServicio;
import co.edu.uniquindio.empresaalojamiento.singleton.AlojamientoSingleton;
import co.edu.uniquindio.empresaalojamiento.singleton.Sesion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;

import java.util.List;

public class NotificacionControlador {

    @FXML
    private ListView<Notificacion> listViewNotificaciones;

    @FXML
    private ComboBox<String> cmbTipoNotificacion;

    private final EmpresaAlojamientoServicio controladorPrincipal = ControladorPrincipal.getInstancia().getEmpresaAlojamiento();
    private final Sesion sesion = Sesion.getInstancia();
    private final Alojamiento alojamiento = AlojamientoSingleton.getInstancia().getAlojamiento();

    @FXML
    public void initialize() {
        cmbTipoNotificacion.getItems().addAll(EstadoNotificacion.getListaDeNombres());
        cmbTipoNotificacion.setValue(EstadoNotificacion.PENDIENTE.getNombre());
        cargarNotificaciones(EstadoNotificacion.PENDIENTE);
        listViewNotificaciones.setOnMouseClicked(event -> {
            Notificacion seleccionada = listViewNotificaciones.getSelectionModel().getSelectedItem();
            if (seleccionada != null && !seleccionada.getEstadoNotificacion().equals(EstadoNotificacion.LEIDA)) {
                controladorPrincipal.marcarComoLeida(seleccionada.getId());
                cargarNotificaciones(EstadoNotificacion.PENDIENTE);
            }
        });
    }

    @FXML
    void regresar(ActionEvent event) {
        ControladorPrincipal.navegarVentana("/co/edu/uniquindio/empresaalojamiento/menuCliente.fxml", "Usuario", listViewNotificaciones, getClass());
    }

    @FXML
    void filtrarNotificaciones(ActionEvent event) {
        cargarNotificaciones(EstadoNotificacion.getEstadoNotificacionDesdeNombre(cmbTipoNotificacion.getValue()));
    }

    public void cargarNotificaciones(EstadoNotificacion estadoNotificacion) {
        List<Notificacion> notificaciones = controladorPrincipal
                .obtenerNotificaciones(sesion.getUsuario().getCedula());

        List<Notificacion> filtradas = notificaciones.stream()
                .filter(n -> n.getEstadoNotificacion() == estadoNotificacion)
                .toList();

        listViewNotificaciones.getItems().setAll(filtradas);

        listViewNotificaciones.setCellFactory(listView -> new javafx.scene.control.ListCell<>() {
            @Override
            protected void updateItem(Notificacion notificacion, boolean empty) {
                super.updateItem(notificacion, empty);
                if (empty || notificacion == null) {
                    setText(null);
                } else {
                    setText(" [" + notificacion.getEstadoNotificacion() + "] " + notificacion.getFecha() + ": " + notificacion.getMensaje());
                }
            }
        });
    }
}

