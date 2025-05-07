package co.edu.uniquindio.empresaalojamiento.repositorios;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Reserva;

import java.util.ArrayList;
import java.util.List;

public class ReservaRepositorio implements IReservasRepositorio {

    private final ArrayList<Reserva> reservas;

    public ReservaRepositorio() {
        this.reservas = new ArrayList<>();
    }

    @Override
    public void agregarReserva(Reserva reserva) {
        reservas.add(reserva);

    }

    @Override
    public void eliminarReserva(Reserva reserva) {
        reservas.remove(reserva);
    }

    @Override
    public List<Reserva> obtenerReservas() {
        return reservas;
    }
}
