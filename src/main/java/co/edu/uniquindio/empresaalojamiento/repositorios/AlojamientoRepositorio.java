package co.edu.uniquindio.empresaalojamiento.repositorios;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Alojamiento;
import co.edu.uniquindio.empresaalojamiento.modelo.enums.TipoAlojamiento;
import javafx.collections.FXCollections;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AlojamientoRepositorio implements IAlojamientoRepositorio {
    private final ArrayList<Alojamiento> alojamientos;

    public AlojamientoRepositorio() {
        this.alojamientos = new ArrayList<>();
    }


    @Override
    public void agregarAlojamiento(Alojamiento alojamiento) {
        alojamientos.add(alojamiento);
    }

    @Override
    public void eliminarAlojamiento(Alojamiento alojamiento) {
        alojamientos.remove(alojamiento);
    }

    @Override
    public Alojamiento buscarAlojamiento(String id) {
        return alojamientos.stream().filter(c -> c.getId().equalsIgnoreCase(id)).findFirst().orElse(null);
    }

    @Override
    public List<Alojamiento> obtenerAlojamientos() {
        return alojamientos;
    }

    @Override
    public List<Alojamiento> obtenerAlojamientoPorTipo(TipoAlojamiento tipo) {
        return alojamientos
                .stream()
                .filter(c -> tipo.equals(c.getTipoAlojamiento()))
                .collect(Collectors.toList()
                );
    }

}
