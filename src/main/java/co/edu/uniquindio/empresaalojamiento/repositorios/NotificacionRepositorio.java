package co.edu.uniquindio.empresaalojamiento.repositorios;

import co.edu.uniquindio.empresaalojamiento.modelo.enums.EstadoNotificacion;
import co.edu.uniquindio.empresaalojamiento.modelo.vo.Notificacion;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class NotificacionRepositorio {
    private final List<Notificacion> notificaciones = new ArrayList<>();

    public void guardar(Notificacion n) {
        notificaciones.add(n);
    }

    public List<Notificacion> obtenerPorCliente(String idCliente) {
        return notificaciones.stream()
                .filter(n -> n.getIdReceptor().equals(idCliente))
                .collect(Collectors.toList());
    }

    public void marcarComoLeida(UUID id) {
        notificaciones.stream()
                .filter(n -> n.getId().equals(id))
                .findFirst()
                .ifPresent(n -> n.setEstadoNotificacion(EstadoNotificacion.LEIDA));
    }
}
