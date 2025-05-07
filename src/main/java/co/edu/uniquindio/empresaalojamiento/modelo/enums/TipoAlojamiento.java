package co.edu.uniquindio.empresaalojamiento.modelo.enums;

import java.util.ArrayList;
import java.util.List;

public enum TipoAlojamiento {
    APARTAMENTOS("Apartamentos"), CASA("Casa"), HOTEL("Hotel");

    private final String nombre;
    TipoAlojamiento(String nombre){
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public static List<String> getListaDeNombres() {
        List<String> nombres = new ArrayList<>();
        for (TipoAlojamiento tipoAlojamiento : TipoAlojamiento.values()) {
            nombres.add(tipoAlojamiento.getNombre());
        }
        return nombres;
    }
}
