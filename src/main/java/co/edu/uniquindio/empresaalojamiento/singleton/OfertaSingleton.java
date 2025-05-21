package co.edu.uniquindio.empresaalojamiento.singleton;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Oferta;
import lombok.Getter;
import lombok.Setter;

public class OfertaSingleton {

    public static OfertaSingleton INSTANCIAOFERTA;

    @Getter
    @Setter
    private Oferta oferta;

    private OfertaSingleton() {
    }

    public static OfertaSingleton getInstancia() {
        if (INSTANCIAOFERTA == null) {
            INSTANCIAOFERTA = new OfertaSingleton();
        }
        return INSTANCIAOFERTA;
    }
}
