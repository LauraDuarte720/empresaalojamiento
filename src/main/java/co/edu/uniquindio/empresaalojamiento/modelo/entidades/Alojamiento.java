package co.edu.uniquindio.empresaalojamiento.modelo.entidades;

import co.edu.uniquindio.empresaalojamiento.modelo.enums.Ciudad;
import co.edu.uniquindio.empresaalojamiento.modelo.enums.TipoAlojamiento;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
//
@Setter
@Getter
@Builder
@ToString
public class Alojamiento implements Serializable {
    private TipoAlojamiento tipoAlojamiento;
    private String id;
    private String nombre;
    private String descripcion;
    private String ruta;
    private Ciudad ciudad;
    private double precioPorNoche;
    private int capacidadMaximaHuespedes;
    private float calificacionPromedio;
    private boolean piscina;
    private boolean wifi;
    private boolean desayuno;
    private double costoAdicional;
}
