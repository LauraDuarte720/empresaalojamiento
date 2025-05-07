package co.edu.uniquindio.empresaalojamiento.modelo.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class Reserva {
    private LocalDate fechaInicio;
    private LocalDate fechaFinal;
    private int numeroHuespedes;
    private Alojamiento alojamiento;
    private Factura factura;
}
