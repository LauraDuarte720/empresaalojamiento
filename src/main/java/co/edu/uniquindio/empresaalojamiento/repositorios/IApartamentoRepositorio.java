package co.edu.uniquindio.empresaalojamiento.repositorios;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Apartamento;

import java.util.List;

public interface IApartamentoRepositorio {
    public void agregarApartamento(Apartamento apartamento);

    public void eliminarApartamento(Apartamento apartamento);

    public Apartamento buscarApartamento(String idApartamento);

    public List<Apartamento> obtenerApartamentos();
}
