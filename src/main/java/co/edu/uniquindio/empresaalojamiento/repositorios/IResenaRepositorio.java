package co.edu.uniquindio.empresaalojamiento.repositorios;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Resena;

import java.util.List;

public interface IResenaRepositorio {
    public void agregarResena(Resena resena);

    public void elimnarResena(Resena resena);

    public List<Resena> obtenerResenasAlojamiento(String idAlojamiento);
}
