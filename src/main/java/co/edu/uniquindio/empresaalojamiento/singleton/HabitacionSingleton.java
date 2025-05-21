package co.edu.uniquindio.empresaalojamiento.singleton;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Alojamiento;
import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Habitacion;
import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Usuario;
import lombok.Getter;
import lombok.Setter;

public class HabitacionSingleton {
    public static HabitacionSingleton INSTANCIAHABITACION;

    @Getter
    @Setter
    private Habitacion habitacion;


    private HabitacionSingleton() {
    }


    public static HabitacionSingleton getInstancia() {
        if (INSTANCIAHABITACION == null) {
            INSTANCIAHABITACION = new HabitacionSingleton();
        }
        return INSTANCIAHABITACION;
    }


}
