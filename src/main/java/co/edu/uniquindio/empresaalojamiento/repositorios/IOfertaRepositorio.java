package co.edu.uniquindio.empresaalojamiento.repositorios;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Oferta;

import java.util.List;

public interface IOfertaRepositorio {
    public void agregarOferta(Oferta oferta);

    public void eliminarOferta(Oferta oferta);

    public List<Oferta> obtenerOfertasAlojamiento(String idAlojamiento);
}
