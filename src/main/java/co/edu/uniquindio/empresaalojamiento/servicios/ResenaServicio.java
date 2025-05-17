package co.edu.uniquindio.empresaalojamiento.servicios;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Resena;
import co.edu.uniquindio.empresaalojamiento.repositorios.interfaces.IResenaRepositorio;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class ResenaServicio {

    private IResenaRepositorio resenaRepositorio;

    public ResenaServicio(IResenaRepositorio resenaRepositorio) {
        this.resenaRepositorio = resenaRepositorio;
    }

    public Resena crearResena(String valoracion, Integer calificacion, String idUsuario, String idAlojamiento) throws Exception {
        if (valoracion == null || valoracion.isEmpty()) throw new Exception("La valoración no puede ser vacia");
        if (calificacion == null) throw new Exception("Debe ingresar una calificacion al alojamiento");

        Resena resena = new Resena(UUID.randomUUID().toString(), valoracion, calificacion, idUsuario, idAlojamiento);
        resenaRepositorio.agregarResena(resena);
        return resena;
    }

    public List<Resena> obtenerResenasAlojamiento(String idAlojamiento) throws Exception {
        List<Resena> resenas = resenaRepositorio.obtenerResenasAlojamiento(idAlojamiento);
        if (resenas.isEmpty()) {
            throw new Exception();
        }
        return resenas;
    }
}
