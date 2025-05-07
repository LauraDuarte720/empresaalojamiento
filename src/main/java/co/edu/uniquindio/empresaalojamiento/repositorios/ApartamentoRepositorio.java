package co.edu.uniquindio.empresaalojamiento.repositorios;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Apartamento;

import java.util.LinkedList;
import java.util.List;

public class ApartamentoRepositorio implements IApartamentoRepositorio {
    private final List<Apartamento> apartamentos;

    public ApartamentoRepositorio() {
        this.apartamentos = new LinkedList<>();
    }


    @Override
    public void agregarApartamento(Apartamento apartamento) {

    }

    @Override
    public void eliminarApartamento(Apartamento apartamento) {

    }

    @Override
    public Apartamento buscarApartamento(String idApartamento) {
        return null;
    }

    @Override
    public List<Apartamento> obtenerApartamentos() {
        return List.of();
    }
}
