package co.edu.uniquindio.empresaalojamiento.modelo.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class Reserva implements Serializable {
    private String id;
    private LocalDate fechaInicio;
    private LocalDate fechaFinal;
    private int numeroHuespedes;
    private String idAlojamiento;
    private Factura factura;
    private String idUsuario;
}
