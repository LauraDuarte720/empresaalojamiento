package co.edu.uniquindio.empresaalojamiento.modelo.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
public class Billetera implements Serializable {
    private double saldo;
    private String id;
}