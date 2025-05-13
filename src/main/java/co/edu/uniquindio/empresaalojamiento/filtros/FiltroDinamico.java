package co.edu.uniquindio.empresaalojamiento.filtros;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Alojamiento;
import co.edu.uniquindio.empresaalojamiento.modelo.enums.Ciudad;
import co.edu.uniquindio.empresaalojamiento.modelo.enums.TipoAlojamiento;

import java.util.ArrayList;
import java.util.List;

public class FiltroDinamico {

    public static List<Alojamiento> filtrar(String nombreBuscado, TipoAlojamiento tipoSeleccionado, Ciudad ciudadSeleccionada, double precioMin, double precioMax, List<Alojamiento> alojamientos) {
        List<IFiltro> filtros = new ArrayList<>();

        if (!nombreBuscado.isEmpty()) {
            filtros.add(new FiltroPorNombre(nombreBuscado));
        }
        if (tipoSeleccionado!=null) {
            filtros.add(new FiltroPorTipo(tipoSeleccionado));
        }
        if (ciudadSeleccionada!=null) {
            filtros.add(new FiltroPorCiudad(ciudadSeleccionada));
        }
        if (precioMin >= 0 && precioMax >= 0) {
            filtros.add(new FiltroPorRangoPrecio(precioMin, precioMax));
        }

        List<Alojamiento> resultado = alojamientos;
        for (IFiltro f : filtros) {
            resultado = f.filtrar(resultado);
        }
        return resultado;
    }
}
