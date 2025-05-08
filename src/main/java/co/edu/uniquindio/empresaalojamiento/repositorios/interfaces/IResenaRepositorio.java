package co.edu.uniquindio.empresaalojamiento.repositorios.interfaces;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Oferta;
import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Resena;

import java.util.List;

public interface IResenaRepositorio {
    public void agregarResena(Resena resena);

    public void elimnarResena(Resena resena);

    public Resena buscarResena(String id);

    public List<Resena> obtenerResenasAlojamiento(String idAlojamiento);
}
