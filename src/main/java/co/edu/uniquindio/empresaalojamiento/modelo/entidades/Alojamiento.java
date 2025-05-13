package co.edu.uniquindio.empresaalojamiento.modelo.entidades;

import co.edu.uniquindio.empresaalojamiento.modelo.enums.Ciudad;
import co.edu.uniquindio.empresaalojamiento.modelo.enums.TipoAlojamiento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
//
@Setter
@Getter
@Builder
public class Alojamiento {
    private TipoAlojamiento tipoAlojamiento;
    private String id;
    private String nombre;
    private String descripcion;
    private String ruta;
    private Ciudad ciudad;
    private double precioPorNoche;
    private int capacidadMaximaHuespedes;
    private boolean piscina;
    private boolean wifi;
    private boolean desayuno;
    private double costoAdicional;
}
