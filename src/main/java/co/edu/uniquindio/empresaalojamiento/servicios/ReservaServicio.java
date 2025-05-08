package co.edu.uniquindio.empresaalojamiento.servicios;

import co.edu.uniquindio.empresaalojamiento.repositorios.interfaces.IReservaRepositorio;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservaServicio {

    private IReservaRepositorio reservaRepositorio;

    public ReservaServicio(IReservaRepositorio reservaRepositorio) {
        this.reservaRepositorio = reservaRepositorio;
    }
}
