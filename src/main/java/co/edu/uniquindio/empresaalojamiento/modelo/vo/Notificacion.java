package co.edu.uniquindio.empresaalojamiento.modelo.vo;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Usuario;
import co.edu.uniquindio.empresaalojamiento.modelo.enums.EstadoNotificacion;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
public class Notificacion {
    private UUID id;
    private String mensaje;
    private LocalDateTime fecha;
    private EstadoNotificacion estadoNotificacion;
    private String idReceptor;

}
