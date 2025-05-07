package co.edu.uniquindio.empresaalojamiento.repositorios;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Reserva;

import java.util.List;

public interface IReservasRepositorio {
    public void agregarReserva(Reserva reserva);

    public void eliminarReserva(Reserva reserva);

    public List<Reserva> obtenerReservas();
}
