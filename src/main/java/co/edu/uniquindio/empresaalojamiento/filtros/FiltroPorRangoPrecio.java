package co.edu.uniquindio.empresaalojamiento.filtros;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Alojamiento;

import java.util.List;
import java.util.stream.Collectors;

public class FiltroPorRangoPrecio implements IFiltro {
    private double precioMin;
    private double precioMax;

    public FiltroPorRangoPrecio(double precioMin, double precioMax) {
        this.precioMin = precioMin;
        this.precioMax = precioMax;
    }

    @Override
    public List<Alojamiento> filtrar(List<Alojamiento> alojamientos) {
        return alojamientos.stream()
                .filter(a -> a.getPrecioPorNoche() >= precioMin && a.getPrecioPorNoche() <= precioMax)
                .collect(Collectors.toList());
    }
    
}
