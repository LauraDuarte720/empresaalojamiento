package co.edu.uniquindio.empresaalojamiento.servicios;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Factura;
import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Reserva;
import co.edu.uniquindio.empresaalojamiento.repositorios.interfaces.IReservaRepositorio;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class ReservaServicio {

    private IReservaRepositorio reservaRepositorio;

    public ReservaServicio(IReservaRepositorio reservaRepositorio) {
        this.reservaRepositorio = reservaRepositorio;
    }

    public Reserva crearReserva(LocalDate fechaInicio, LocalDate fechaFinal, int numeroHuespedes, String idAlojamiento, String idUsuario) throws Exception {
        if (fechaInicio==null || fechaFinal==null){
            throw new Exception("Las fechas son obligatorias");
        }
        if(fechaFinal.isBefore(fechaInicio)){
            throw new Exception("La fecha final debe ser posterior a la de inicio");
        }

        if(fechaInicio.isBefore(LocalDate.now())){
            throw new Exception("La fecha ingresada debe ser posterior a la de hoy");
        }

        if(numeroHuespedes <= 0){
            throw new Exception("El numero de huespedes debe ser mayor que 0");
        }

        Factura factura = new Factura(0, 0, LocalDate.now(), UUID.randomUUID().toString());

        Reserva reserva =  new Reserva(UUID.randomUUID().toString(), fechaInicio, fechaFinal, numeroHuespedes, idAlojamiento, factura, idUsuario);
        reservaRepositorio.agregarReserva(reserva);
        return reserva;
    }

    public void cancelarReserva(String idReserva) throws Exception {
        Reserva reserva = reservaRepositorio.buscarReserva(idReserva);
        if(reserva == null){
            throw new Exception("No se encontró la reserva");
        }
        reservaRepositorio.eliminarReserva(reserva);
    }

    public List<Reserva> obtenerReservas()  {
        return reservaRepositorio.obtenerReservas();
    }

    public List<Reserva> obtenerReservasUsuario(String idUsuario)  {
        return reservaRepositorio.obtenerReservasUsuario(idUsuario);
    }

    public List<Reserva> obtenerReservasAlojamiento(String idAlojamiento)  {
        return reservaRepositorio.obtenerReservasAlojamiento(idAlojamiento);
    }
}
