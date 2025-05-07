package co.edu.uniquindio.empresaalojamiento.repositorios;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Casa;

import java.util.LinkedList;
import java.util.List;

public class CasaRepositorio implements ICasaRepositorio {
    private final List<Casa> casas;

    public CasaRepositorio() {
        this.casas = new LinkedList<Casa>();
    }


    @Override
    public void agregarCasa(Casa casa) {

    }

    @Override
    public void eliminarCasa(Casa casa) {

    }

    @Override
    public Casa buscarCasa(String idCasa) {
        return null;
    }


    @Override
    public List<Casa> obtenerCasas() {
        return List.of();
    }
}
