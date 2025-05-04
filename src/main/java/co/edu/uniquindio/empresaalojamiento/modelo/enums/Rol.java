package co.edu.uniquindio.empresaalojamiento.modelo.enums;

import java.util.ArrayList;
import java.util.List;

public enum Rol {
    CLIENTE("Cliente"), ADMINISTRADOR("Administrador");

    private final String nombre;
    Rol(String nombre){
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public static List<String> getListaDeNombres() {
        List<String> nombres = new ArrayList<>();
        for (Rol rol : Rol.values()) {
            nombres.add(rol.getNombre());
        }
        return nombres;
    }
}
