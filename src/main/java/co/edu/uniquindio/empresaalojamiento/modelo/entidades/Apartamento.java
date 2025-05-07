package co.edu.uniquindio.empresaalojamiento.modelo.entidades;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Apartamento extends Alojamiento {
    private double costoAdicional;

    Apartamento(String nombre, String descripcion, String ruta, double precioPorNoche, int capacidadMaximaHuespedes, boolean piscina, boolean wifi, boolean desayuno, double costoAdicional) {
        super(nombre, descripcion, ruta, precioPorNoche, capacidadMaximaHuespedes, piscina, wifi, desayuno);
        this.costoAdicional = costoAdicional;
    }
}
