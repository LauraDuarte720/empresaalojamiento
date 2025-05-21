package co.edu.uniquindio.empresaalojamiento.repositorios;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Resena;
import co.edu.uniquindio.empresaalojamiento.repositorios.interfaces.IResenaRepositorio;
import co.edu.uniquindio.empresaalojamiento.utilidades.Constantes;
import co.edu.uniquindio.empresaalojamiento.utilidades.Persistencia;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ResenaRepositorio implements IResenaRepositorio {
    private List<Resena> resenas;

    public ResenaRepositorio() {

        this.resenas = leerDatos();
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
    public Resena buscarResena(String id) {
        return resenas.stream().filter(c -> c.getId().equalsIgnoreCase(id)).findFirst().orElse(null);
    }

    @Override
    public List<Resena> obtenerResenasAlojamiento(String idAlojamiento) {
        return resenas
                .stream()
                .filter(c -> idAlojamiento.equalsIgnoreCase(c.getIdAlojamiento()))
                .collect(Collectors.toList()
                );
    }

    public List<Resena> obtenerResenas() {
        return resenas;
    }

    public List<Resena> leerDatos() {
        try {
            Object datos = Persistencia.deserializarObjeto(Constantes.RUTA_RESENAS);
            if (datos != null) {
                return (List<Resena>) datos;
            }
        } catch (Exception e) {
            System.err.println("Error cargando reseñas: " + e.getMessage());
        }
        return new ArrayList<>();
    }


    public void guardarDatos(List<Resena> resenas) {
        try {
            Persistencia.serializarObjeto(Constantes.RUTA_RESENAS, resenas);
        } catch (IOException e) {
            System.err.println("Error guardando reseñas: " + e.getMessage());
        }
    }
}
