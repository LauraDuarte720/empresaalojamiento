package co.edu.uniquindio.empresaalojamiento.modelo.enums;

import java.util.ArrayList;
import java.util.List;

public enum Ciudad {
    LETICIA("Leticia"),
    MEDELLIN("Medellín"),
    ARAUCA("Arauca"),
    BARRANQUILLA("Barranquilla"),
    CARTAGENA("Cartagena"),
    TUNJA("Tunja"),
    MANIZALES("Manizales"),
    FLORENCIA("Florencia"),
    YOPAL("Yopal"),
    POPAYAN("Popayán"),
    VALLEDUPAR("Valledupar"),
    QUIBDO("Quibdó"),
    MONTERIA("Montería"),
    BOGOTA("Bogotá"),
    INIRIDA("Inírida"),
    SAN_JOSE_DEL_GUAVIARE("San José del Guaviare"),
    NEIVA("Neiva"),
    RIOHACHA("Riohacha"),
    SANTA_MARTA("Santa Marta"),
    VILLAVICENCIO("Villavicencio"),
    PASTO("Pasto"),
    CUCUTA("Cúcuta"),
    MOCOA("Mocoa"),
    ARMENIA("Armenia"),
    PEREIRA("Pereira"),
    SAN_ANDRES("San Andrés"),
    BUCARAMANGA("Bucaramanga"),
    SINCELEJO("Sincelejo"),
    IBAGUE("Ibagué"),
    CALI("Cali"),
    MITU("Mitú"),
    PUERTO_CARRENO("Puerto Carreño");

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
}