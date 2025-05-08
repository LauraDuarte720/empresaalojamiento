package co.edu.uniquindio.empresaalojamiento.repositorios;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Alojamiento;
import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Oferta;
import co.edu.uniquindio.empresaalojamiento.repositorios.interfaces.IOfertaRepositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OfertaRepositorio implements IOfertaRepositorio {

    private final ArrayList<Oferta> ofertas;

    public OfertaRepositorio() {
        this.ofertas = new ArrayList<>();
    }


    @Override
    public void agregarOferta(Oferta oferta) {
        ofertas.add(oferta);

    }

    @Override
    public void eliminarOferta(Oferta oferta) {
        ofertas.remove(oferta);

    }

    @Override
    public Oferta buscarOferta(String id) {
        return ofertas.stream().filter(c -> c.getId().equalsIgnoreCase(id)).findFirst().orElse(null);
    }

    @Override
    public List<Oferta> obtenerOfertasAlojamiento(String idAlojamiento) {

        return ofertas
                .stream()
                .filter(c -> idAlojamiento.equalsIgnoreCase(c.getIdAlojamiento()))
                .collect(Collectors.toList()
                );
    }
}
