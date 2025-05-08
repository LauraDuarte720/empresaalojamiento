package co.edu.uniquindio.empresaalojamiento.repositorios;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Habitacion;
import co.edu.uniquindio.empresaalojamiento.repositorios.interfaces.IHabitacionRepositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HabitacionRepositorio implements IHabitacionRepositorio {
    private final ArrayList<Habitacion> habitaciones;

    public HabitacionRepositorio() {
        this.habitaciones = new ArrayList<>();
    }

    @Override
    public void agregarHabitacion(Habitacion habitacion) {
        habitaciones.add(habitacion);

    }

    @Override
    public void eliminarHabitacion(Habitacion habitacion) {
        habitaciones.remove(habitacion);
    }

    @Override
    public Habitacion buscarHabitacion(String id) {
        return habitaciones.stream().filter(c -> c.getId().equalsIgnoreCase(id)).findFirst().orElse(null);
    }

    @Override
    public List<Habitacion> obtenerHabitacionesHotel(String idHotel) {
        return habitaciones
                .stream()
                .filter(c -> idHotel.equalsIgnoreCase(c.getIdHotel()))
                .collect(Collectors.toList()
                );
    }

}
