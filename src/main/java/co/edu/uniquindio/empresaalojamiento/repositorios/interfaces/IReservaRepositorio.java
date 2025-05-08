package co.edu.uniquindio.empresaalojamiento.repositorios.interfaces;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Oferta;
import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Reserva;

import java.util.List;

public interface IReservaRepositorio {
    public void agregarReserva(Reserva reserva);
    public Reserva buscarReserva(String id);

    public void eliminarReserva(Reserva reserva);

    public List<Reserva> obtenerReservas();
}
