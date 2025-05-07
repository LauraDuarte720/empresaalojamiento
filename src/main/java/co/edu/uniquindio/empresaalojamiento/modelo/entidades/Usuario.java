package co.edu.uniquindio.empresaalojamiento.modelo.entidades;

import co.edu.uniquindio.empresaalojamiento.modelo.enums.Rol;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class Usuario {
    private String cedula;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private String contrasena;
    private Billetera billetera;
    private Rol rol;
}
