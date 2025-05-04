package co.edu.uniquindio.empresaalojamiento.modelo.entidades;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor

public class Factura {
    private double total;
    private double subtotal;
    private LocalDate fecha;
    private String codigo;
}
