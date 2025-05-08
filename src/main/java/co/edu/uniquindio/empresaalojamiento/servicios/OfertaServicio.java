package co.edu.uniquindio.empresaalojamiento.servicios;

import co.edu.uniquindio.empresaalojamiento.repositorios.interfaces.IOfertaRepositorio;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfertaServicio {

    private IOfertaRepositorio ofertaRepositorio;

    public OfertaServicio(IOfertaRepositorio ofertaRepositorio) {
        this.ofertaRepositorio = ofertaRepositorio;
    }
}
