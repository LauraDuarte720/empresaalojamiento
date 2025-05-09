package co.edu.uniquindio.empresaalojamiento.repositorios;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Alojamiento;
import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Resena;
import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Reserva;
import co.edu.uniquindio.empresaalojamiento.repositorios.interfaces.IReservaRepositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReservaRepositorio implements IReservaRepositorio {

    private final ArrayList<Reserva> reservas;

    public ReservaRepositorio() {
        this.reservas = new ArrayList<>();
    }

    @Override
    public void agregarReserva(Reserva reserva) {
        reservas.add(reserva);

    }

    @Override
    public Reserva buscarReserva(String id) {
        return reservas.stream().filter(c -> c.getId().equalsIgnoreCase(id)).findFirst().orElse(null);
    }

    @Override
    public void eliminarReserva(Reserva reserva) {
        reservas.remove(reserva);
    }

    @Override
    public List<Reserva> obtenerReservas() {
        return reservas;
    }

    @Override
    public List<Reserva> obtenerReservasUsuario(String idUsuario) {

        return reservas
                .stream()
                .filter(c -> idUsuario.equalsIgnoreCase(c.getIdUsuario()))
                .collect(Collectors.toList()
                );
    }

    @Override
    public List<Reserva> obtenerReservasAlojamiento(String idAlojamiento) {

        return reservas
                .stream()
                .filter(c -> idAlojamiento.equalsIgnoreCase(c.getIdAlojamiento()))
                .collect(Collectors.toList()
                );
    }
}

