package co.edu.uniquindio.empresaalojamiento.filtros;
import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Alojamiento;
import co.edu.uniquindio.empresaalojamiento.modelo.enums.TipoAlojamiento;

import java.util.List;
import java.util.stream.Collectors;

public class FiltroPorTipo implements IFiltro {
    private TipoAlojamiento tipo;

    public FiltroPorTipo(TipoAlojamiento tipo) {
        this.tipo = tipo;
    }

    @Override
    public List<Alojamiento> filtrar(List<Alojamiento> alojamientos) {
        return alojamientos.stream()
                .filter(a -> a.getTipoAlojamiento().equals(tipo))
                .collect(Collectors.toList());
    }
}
