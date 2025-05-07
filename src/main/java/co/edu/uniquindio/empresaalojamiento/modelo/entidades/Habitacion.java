package co.edu.uniquindio.empresaalojamiento.modelo.entidades;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Habitacion {
    private int numero;
    private double precioPorNoche;
    private int capacidadHuespedes;
    private String rutaImagen;
    private String descripcion;
}
