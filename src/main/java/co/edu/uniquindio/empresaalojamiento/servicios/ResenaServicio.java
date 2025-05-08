package co.edu.uniquindio.empresaalojamiento.servicios;

import co.edu.uniquindio.empresaalojamiento.repositorios.interfaces.IResenaRepositorio;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResenaServicio {

    private IResenaRepositorio resenaRepositorio;

    public ResenaServicio(IResenaRepositorio resenaRepositorio){
        this.resenaRepositorio = resenaRepositorio;
    }
}
