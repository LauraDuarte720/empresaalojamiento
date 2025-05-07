package co.edu.uniquindio.empresaalojamiento.repositorios;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Habitacion;

import java.util.LinkedList;
import java.util.List;

public class HabitacionRepositorio implements IHabitacionRepositorio {
    private final LinkedList<Habitacion> habitaciones;

    public HabitacionRepositorio() {
        this.habitaciones = new LinkedList<>();
    }

    @Override
    public void agregarHabitacion(Habitacion habitacion) {

    }

    @Override
    public void eliminarHabitacion(Habitacion habitacion) {

    }

    @Override
    public Habitacion buscarHabitacion(String idHabitacion) {
        return null;
    }

    @Override
    public List<Habitacion> obtenerHabitacionesHotel(String idHotel) {
        return List.of();
    }

}
