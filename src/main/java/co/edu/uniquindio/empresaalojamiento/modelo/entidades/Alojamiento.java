package co.edu.uniquindio.empresaalojamiento.modelo.entidades;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Builder
public class Alojamiento {
    private String nombre;
    private String descripcion;
    private String ruta;
    private double precioPorNoche;
    private int capacidadMaximaHuespedes;
    private boolean piscina;
    private boolean wifi;
    private boolean desayuno;
}
