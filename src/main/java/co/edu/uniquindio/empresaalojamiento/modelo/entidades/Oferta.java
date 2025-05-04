package co.edu.uniquindio.empresaalojamiento.modelo.entidades;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
public class Oferta {
    private LocalDate fechaInicio;
    private LocalDate fechaFinal;
    private double valorPorcentaje;
}
