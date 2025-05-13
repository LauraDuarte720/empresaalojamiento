package co.edu.uniquindio.empresaalojamiento.filtros;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Alojamiento;

import java.util.List;
import java.util.stream.Collectors;

public class FiltroPorNombre implements IFiltro {

    private String texto;

    public FiltroPorNombre(String texto) {
        this.texto = texto.toLowerCase();
    }

    @Override
    public List<Alojamiento> filtrar(List<Alojamiento> alojamientos) {
        return alojamientos.stream()
                .filter(a -> a.getNombre().toLowerCase().contains(texto))
                .collect(Collectors.toList());
    }
}
