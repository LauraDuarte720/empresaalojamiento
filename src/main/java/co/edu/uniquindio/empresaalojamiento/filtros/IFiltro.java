package co.edu.uniquindio.empresaalojamiento.filtros;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Alojamiento;
import co.edu.uniquindio.empresaalojamiento.repositorios.AlojamientoRepositorio;

import java.util.List;

public interface IFiltro {
    List<Alojamiento> filtrar(List<Alojamiento> alojamientoRepositorio);
}
