package co.edu.uniquindio.empresaalojamiento.filtros;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Alojamiento;
import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Oferta;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FiltroPorOferta implements IFiltro {
    private List<Oferta> ofertas;

    public FiltroPorOferta(List<Oferta> ofertas) {
        this.ofertas = ofertas;
    }

    @Override
    public List<Alojamiento> filtrar(List<Alojamiento> alojamientos) {
        Set<String> idsConOferta = ofertas.stream()
                .map(Oferta::getIdAlojamiento)
                .collect(Collectors.toSet());

        return alojamientos.stream()
                .filter(a -> idsConOferta.contains(a.getId()))
                .collect(Collectors.toList());
    }


}
