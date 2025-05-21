package co.edu.uniquindio.empresaalojamiento.repositorios;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Reserva;
import co.edu.uniquindio.empresaalojamiento.repositorios.interfaces.IReservaRepositorio;
import co.edu.uniquindio.empresaalojamiento.utilidades.Constantes;
import co.edu.uniquindio.empresaalojamiento.utilidades.Persistencia;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReservaRepositorio implements IReservaRepositorio {

    private final List<Reserva> reservas;

    public ReservaRepositorio() {

        this.reservas = leerDatos();
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

    @Override
    public List<Reserva> obtenerReservasHabitacion(String idHabitacion) {

        return reservas
                .stream()
                .filter(c -> idHabitacion.equalsIgnoreCase(c.getIdHabitacion()))
                .collect(Collectors.toList()
                );
    }

    public List<Reserva> leerDatos() {
        try {
            Object datos = Persistencia.deserializarObjeto(Constantes.RUTA_RESERVAS);
            if (datos != null) {
                return (List<Reserva>) datos;
            }
        } catch (Exception e) {
            System.err.println("Error cargando reservas: " + e.getMessage());
        }
        return new ArrayList<>();
    }


    public void guardarDatos(List<Reserva> reservas) {
        try {
            Persistencia.serializarObjeto(Constantes.RUTA_RESERVAS, reservas);
        } catch (IOException e) {
            System.err.println("Error guardando reservas: " + e.getMessage());
        }
    }
}

