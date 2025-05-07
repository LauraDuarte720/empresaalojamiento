package co.edu.uniquindio.empresaalojamiento.repositorios;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Habitacion;

import java.util.List;

public interface IHabitacionRepositorio {
    public void agregarHabitacion(Habitacion habitacion);

    public void eliminarHabitacion(Habitacion habitacion);

    public Habitacion buscarHabitacion(String idHabitacion);

    public List<Habitacion> obtenerHabitacionesHotel(String idHotel);
}
