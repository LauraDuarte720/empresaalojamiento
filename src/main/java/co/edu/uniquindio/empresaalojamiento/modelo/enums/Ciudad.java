package co.edu.uniquindio.empresaalojamiento.modelo.enums;

import java.util.ArrayList;
import java.util.List;

public enum Ciudad {
        ARAUCA("Arauca"),
        ARMENIA("Armenia"),
        BARRANQUILLA("Barranquilla"),
        BOGOTA("Bogotá"),
        BUCARAMANGA("Bucaramanga"),
        CALI("Cali"),
        CARTAGENA("Cartagena"),
        CUCUTA("Cúcuta"),
        FLORENCIA("Florencia"),
        IBAGUE("Ibagué"),
        INIRIDA("Inírida"),
        LETICIA("Leticia"),
        MANIZALES("Manizales"),
        MEDELLIN("Medellín"),
        MITU("Mitú"),
        MOCOA("Mocoa"),
        MONTERIA("Montería"),
        NEIVA("Neiva"),
        PASTO("Pasto"),
        PEREIRA("Pereira"),
        POPAYAN("Popayán"),
        PUERTO_CARRENO("Puerto Carreño"),
        QUIBDO("Quibdó"),
        RIOHACHA("Riohacha"),
        SAN_ANDRES("San Andrés"),
        SAN_JOSE_DEL_GUAVIARE("San José del Guaviare"),
        SANTA_MARTA("Santa Marta"),
        SINCELEJO("Sincelejo"),
        TUNJA("Tunja"),
        VALLEDUPAR("Valledupar"),
        VILLAVICENCIO("Villavicencio"),
        YOPAL("Yopal");

    private final String nombre;

    Ciudad(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public static List<String> getListaDeNombres() {
        List<String> nombres = new ArrayList<>();
        for (Ciudad ciudad : Ciudad.values()) {
            nombres.add(ciudad.getNombre());
        }
        return nombres;
    }

    @Override
    public String toString() {
        return nombre;
    }
}