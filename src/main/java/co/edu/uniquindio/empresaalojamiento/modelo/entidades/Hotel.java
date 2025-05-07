package co.edu.uniquindio.empresaalojamiento.modelo.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Hotel extends Alojamiento{
    Hotel(String nombre, String descripcion, String ruta, double precioPorNoche, int capacidadMaximaHuespedes, boolean piscina, boolean wifi, boolean desayuno) {
        super(nombre, descripcion, ruta, precioPorNoche, capacidadMaximaHuespedes, piscina, wifi, desayuno);
    }
}
