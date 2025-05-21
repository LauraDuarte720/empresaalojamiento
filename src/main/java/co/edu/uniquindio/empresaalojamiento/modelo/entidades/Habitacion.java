package co.edu.uniquindio.empresaalojamiento.modelo.entidades;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder
@AllArgsConstructor
public class Habitacion implements Serializable {
    private String id;
    private int numero;
    private double precioPorNoche;
    private int capacidadHuespedes;
    private String rutaImagen;
    private String descripcion;
    private String idHotel;
}
