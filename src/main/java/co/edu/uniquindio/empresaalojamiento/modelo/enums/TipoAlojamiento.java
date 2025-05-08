package co.edu.uniquindio.empresaalojamiento.modelo.enums;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public enum TipoAlojamiento {
    APARTAMENTOS("Apartamentos"), CASA("Casa"), HOTEL("Hotel");

    private final String nombre;
    TipoAlojamiento(String nombre){
        this.nombre = nombre;
    }

    public static List<String> getListaDeNombres() {
        List<String> nombres = new ArrayList<>();
        for (TipoAlojamiento tipoAlojamiento : TipoAlojamiento.values()) {
            nombres.add(tipoAlojamiento.getNombre());
        }
        return nombres;
    }
}
