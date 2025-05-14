package co.edu.uniquindio.empresaalojamiento.modelo.enums;


import lombok.Getter;

@Getter
public enum Calificacion {

    UNA_ESTRELLA(1, "1 estrella"),
    DOS_ESTRELLAS(2, "2 estrellas"),
    TRES_ESTRELLAS(3, "3 estrellas"),
    CUATRO_ESTRELLAS(4, "4 estrellas"),
    CINCO_ESTRELLAS(5, "5 estrellas");

    private final int valor;
    private final String nombreLegible;

    Calificacion(int valor, String nombreLegible) {
        this.valor = valor;
        this.nombreLegible = nombreLegible;
    }

    public static int getCalificacion(String nombreLegible) {
        return switch (nombreLegible) {
            case "1 estrella" -> 1;
            case "2 estrellas" -> 2;
            case "3 estrellas" -> 3;
            case "4 estrellas" -> 4;
            case "5 estrellas" -> 5;
            default -> 0;
        };
    }
}
