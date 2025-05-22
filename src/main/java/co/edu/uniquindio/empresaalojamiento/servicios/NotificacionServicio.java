package co.edu.uniquindio.empresaalojamiento.servicios;

import co.edu.uniquindio.empresaalojamiento.modelo.enums.EstadoNotificacion;
import co.edu.uniquindio.empresaalojamiento.modelo.vo.Notificacion;
import co.edu.uniquindio.empresaalojamiento.repositorios.NotificacionRepositorio;

import java.util.List;
import java.util.UUID;

public class NotificacionServicio {
    private final NotificacionRepositorio repo;

    public NotificacionServicio(NotificacionRepositorio repo) {
        this.repo = repo;
    }

    public void enviarNotificacion(String mensaje, String idCliente) {
        Notificacion notificacion = Notificacion.builder().id(UUID.randomUUID()).mensaje(mensaje).idReceptor(idCliente).estadoNotificacion(EstadoNotificacion.PENDIENTE).build();
        repo.guardar(notificacion);
    }

    public List<Notificacion> obtenerNotificaciones(String idCliente) {
        return repo.obtenerPorCliente(idCliente);
    }

    public void marcarComoLeida(UUID id) {
        repo.marcarComoLeida(id);
    }
}
