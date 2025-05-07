package co.edu.uniquindio.empresaalojamiento.repositorios;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Resena;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ResenaRepositorio implements IResenaRepositorio {
    private ArrayList<Resena> resenas;

    public ResenaRepositorio() {
        this.resenas = new ArrayList<>();
    }

    @Override
    public void agregarResena(Resena resena) {
        resenas.add(resena);

    }

    @Override
    public void elimnarResena(Resena resena) {
        resenas.remove(resena);
    }

    @Override
    public List<Resena> obtenerResenasAlojamiento(String idAlojamiento) {
        return resenas
                .stream()
                .filter(c -> idAlojamiento.equalsIgnoreCase(c.getIdAlojamiento()))
                .collect(Collectors.toList()
                );
    }
}
