package co.edu.uniquindio.empresaalojamiento.servicios;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Habitacion;
import co.edu.uniquindio.empresaalojamiento.repositorios.interfaces.IHabitacionRepositorio;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HabitacionServicio {

    private IHabitacionRepositorio habitacionRepositorio;

    public HabitacionServicio(IHabitacionRepositorio habitacionRepositorio) {
        this.habitacionRepositorio = habitacionRepositorio;
    }

    public Habitacion crearHabitacion(){
        return null;
    }
}
