package co.edu.uniquindio.empresaalojamiento.filtros;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Alojamiento;
import co.edu.uniquindio.empresaalojamiento.modelo.enums.Ciudad;

import java.util.List;
import java.util.stream.Collectors;

public class FiltroPorCiudad implements IFiltro {
    private Ciudad ciudad;

    public FiltroPorCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public List<Alojamiento> filtrar(List<Alojamiento> alojamientos) {
        return alojamientos.stream()
                .filter(a -> a.getCiudad().equals(ciudad))
                .collect(Collectors.toList());
    }
}
