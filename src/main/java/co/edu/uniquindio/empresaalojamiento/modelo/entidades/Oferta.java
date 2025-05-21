package co.edu.uniquindio.empresaalojamiento.modelo.entidades;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Builder
@Setter
@AllArgsConstructor
public class Oferta implements Serializable {
    private String id;
    private LocalDate fechaInicio;
    private LocalDate fechaFinal;
    private double valorPorcentaje;
    private String idAlojamiento;
    private String descripcion;
}
