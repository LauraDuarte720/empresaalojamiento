package co.edu.uniquindio.empresaalojamiento.modelo.entidades;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Apartamento extends Alojamiento {
    private double costoAdicional;
}
