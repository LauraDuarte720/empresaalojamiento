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

    public static Ciudad getCiudadDesdeNombre(String nombreLegible) {
        return switch (nombreLegible) {
            case "Arauca" -> Ciudad.ARAUCA;
            case "Armenia" -> Ciudad.ARMENIA;
            case "Barranquilla" -> Ciudad.BARRANQUILLA;
            case "Bogotá" -> Ciudad.BOGOTA;
            case "Bucaramanga" -> Ciudad.BUCARAMANGA;
            case "Cali" -> Ciudad.CALI;
            case "Cartagena" -> Ciudad.CARTAGENA;
            case "Cúcuta" -> Ciudad.CUCUTA;
            case "Florencia" -> Ciudad.FLORENCIA;
            case "Ibagué" -> Ciudad.IBAGUE;
            case "Inírida" -> Ciudad.INIRIDA;
            case "Leticia" -> Ciudad.LETICIA;
            case "Manizales" -> Ciudad.MANIZALES;
            case "Medellín" -> Ciudad.MEDELLIN;
            case "Mitú" -> Ciudad.MITU;
            case "Mocoa" -> Ciudad.MOCOA;
            case "Montería" -> Ciudad.MONTERIA;
            case "Neiva" -> Ciudad.NEIVA;
            case "Pasto" -> Ciudad.PASTO;
            case "Pereira" -> Ciudad.PEREIRA;
            case "Popayán" -> Ciudad.POPAYAN;
            case "Puerto Carreño" -> Ciudad.PUERTO_CARRENO;
            case "Quibdó" -> Ciudad.QUIBDO;
            case "Riohacha" -> Ciudad.RIOHACHA;
            case "San Andrés" -> Ciudad.SAN_ANDRES;
            case "San José del Guaviare" -> Ciudad.SAN_JOSE_DEL_GUAVIARE;
            case "Santa Marta" -> Ciudad.SANTA_MARTA;
            case "Sincelejo" -> Ciudad.SINCELEJO;
            case "Tunja" -> Ciudad.TUNJA;
            case "Valledupar" -> Ciudad.VALLEDUPAR;
            case "Villavicencio" -> Ciudad.VILLAVICENCIO;
            case "Yopal" -> Ciudad.YOPAL;
            default -> null;
        };
    }
}