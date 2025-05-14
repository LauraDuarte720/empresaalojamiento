package co.edu.uniquindio.empresaalojamiento.singleton;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Alojamiento;
import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Usuario;
import lombok.Getter;
import lombok.Setter;

public class AlojamientoSingleton {
    public static AlojamientoSingleton INSTANCIAALOJAMIENTO;

    @Getter
    @Setter
    private Alojamiento alojamiento;


    private AlojamientoSingleton() {
    }


    public static AlojamientoSingleton getInstancia() {
        if (INSTANCIAALOJAMIENTO == null) {
            INSTANCIAALOJAMIENTO = new AlojamientoSingleton();
        }
        return INSTANCIAALOJAMIENTO;
    }


}
