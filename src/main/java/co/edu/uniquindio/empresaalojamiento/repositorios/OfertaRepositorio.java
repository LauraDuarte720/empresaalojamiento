package co.edu.uniquindio.empresaalojamiento.repositorios;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Oferta;
import co.edu.uniquindio.empresaalojamiento.repositorios.interfaces.IOfertaRepositorio;
import co.edu.uniquindio.empresaalojamiento.utilidades.Constantes;
import co.edu.uniquindio.empresaalojamiento.utilidades.Persistencia;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OfertaRepositorio implements IOfertaRepositorio {

    private final List<Oferta> ofertas;

    public OfertaRepositorio() {

        this.ofertas = leerDatos();
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

    public List<Oferta> obtenerOfertas() {
        return ofertas;
    }

    public List<Oferta> leerDatos() {
        try {
            Object datos = Persistencia.deserializarObjeto(Constantes.RUTA_OFERTAS);
            if (datos != null) {
                return (List<Oferta>) datos;
            }
        } catch (Exception e) {
            System.err.println("Error cargando ofertas: " + e.getMessage());
        }
        return new ArrayList<>();
    }


    public void guardarDatos(List<Oferta> ofertas) {
        try {
            Persistencia.serializarObjeto(Constantes.RUTA_OFERTAS, ofertas);
        } catch (IOException e) {
            System.err.println("Error guardando ofertas: " + e.getMessage());
        }
    }
}
