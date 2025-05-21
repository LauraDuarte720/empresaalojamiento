package co.edu.uniquindio.empresaalojamiento.modelo.entidades;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor

public class Factura implements Serializable {
    private double total;
    private double subtotal;
    private LocalDate fecha;
    private String codigo;
}
