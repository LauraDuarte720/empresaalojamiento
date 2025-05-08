package co.edu.uniquindio.empresaalojamiento.modelo.enums;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public enum Rol {
    CLIENTE("Cliente"), ADMINISTRADOR("Administrador");

    private final String nombre;
    Rol(String nombre){
        this.nombre = nombre;
    }

    public static List<String> getListaDeNombres() {
        List<String> nombres = new ArrayList<>();
        for (Rol rol : Rol.values()) {
            nombres.add(rol.getNombre());
        }
        return nombres;
    }
}
