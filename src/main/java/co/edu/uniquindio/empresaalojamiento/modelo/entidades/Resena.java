package co.edu.uniquindio.empresaalojamiento.modelo.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Resena {
    private String id;
    private String valoracion;
    private int calificacion;
    private String idUsuario;
    private String idAlojamiento;
}