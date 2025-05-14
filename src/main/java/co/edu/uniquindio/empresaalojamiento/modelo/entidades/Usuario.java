package co.edu.uniquindio.empresaalojamiento.modelo.entidades;

import co.edu.uniquindio.empresaalojamiento.modelo.enums.Rol;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder

public class Usuario implements Serializable {
    private String cedula;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private String contrasena;
    private Billetera billetera;
    private Rol rol;
    private Boolean activo;
    private String codigoEnviado;
}
