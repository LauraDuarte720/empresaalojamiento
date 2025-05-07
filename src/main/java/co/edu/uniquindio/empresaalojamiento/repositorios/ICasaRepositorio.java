package co.edu.uniquindio.empresaalojamiento.repositorios;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Casa;

import java.util.List;

public interface ICasaRepositorio {
    public void agregarCasa(Casa casa);

    public void eliminarCasa(Casa casa);

    public Casa buscarCasa(String idCasa);

    public List<Casa> obtenerCasas();
}
