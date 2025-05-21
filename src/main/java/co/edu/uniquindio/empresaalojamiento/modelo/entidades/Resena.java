package co.edu.uniquindio.empresaalojamiento.modelo.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class Resena implements Serializable {
    private String id;
    private String valoracion;
    private int calificacion;
    private String idUsuario;
    private String idAlojamiento;
}