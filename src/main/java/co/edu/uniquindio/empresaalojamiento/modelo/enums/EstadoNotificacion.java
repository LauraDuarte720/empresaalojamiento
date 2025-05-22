package co.edu.uniquindio.empresaalojamiento.modelo.enums;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter

public enum EstadoNotificacion {
    PENDIENTE ("Pendiente"),
    LEIDA("Leida");

    private final String nombre;
    EstadoNotificacion(String nombre){
        this.nombre = nombre;
    }

    public static List<String> getListaDeNombres() {
        List<String> nombres = new ArrayList<>();
        for (EstadoNotificacion estadoNotificacion : EstadoNotificacion.values()) {
            nombres.add(estadoNotificacion.getNombre());
        }
        return nombres;
    }

    @Override
    public String toString() {
        return nombre;
    }

    public static EstadoNotificacion getEstadoNotificacionDesdeNombre(String nombreLegible) {
        return switch (nombreLegible) {
            case "Pendiente" -> EstadoNotificacion.PENDIENTE;
            case "Leida" -> EstadoNotificacion.LEIDA;
            default -> null;
        };
    }
}
