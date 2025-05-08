package co.edu.uniquindio.empresaalojamiento.servicios;

import co.edu.uniquindio.empresaalojamiento.repositorios.interfaces.IAlojamientoRepositorio;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlojamientoServicio {
    private IAlojamientoRepositorio alojamientoRepositorio;

    public AlojamientoServicio(IAlojamientoRepositorio alojamientoRepositorio) {
        this.alojamientoRepositorio = alojamientoRepositorio;
    }
}
