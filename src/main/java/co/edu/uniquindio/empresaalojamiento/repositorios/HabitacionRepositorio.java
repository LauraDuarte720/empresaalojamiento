package co.edu.uniquindio.empresaalojamiento.repositorios;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Habitacion;
import co.edu.uniquindio.empresaalojamiento.repositorios.interfaces.IHabitacionRepositorio;
import co.edu.uniquindio.empresaalojamiento.utilidades.Constantes;
import co.edu.uniquindio.empresaalojamiento.utilidades.Persistencia;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HabitacionRepositorio implements IHabitacionRepositorio {
    private final List<Habitacion> habitaciones;

    public HabitacionRepositorio() {

        habitaciones = leerDatos();
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

    public List<Habitacion> obtenerHabitaciones() {
        return habitaciones;
    }

    public List<Habitacion> leerDatos() {
        try {
            Object datos = Persistencia.deserializarObjeto(Constantes.RUTA_HABITACIONES);
            if (datos != null) {
                return (List<Habitacion>) datos;
            }
        } catch (Exception e) {
            System.err.println("Error cargando habitaciones: " + e.getMessage());
        }
        return new ArrayList<>();
    }


    public void guardarDatos(List<Habitacion> habitacion) {
        try {
            Persistencia.serializarObjeto(Constantes.RUTA_HABITACIONES, habitaciones);
        } catch (IOException e) {
            System.err.println("Error guardando habitaciones: " + e.getMessage());
        }
    }

}
