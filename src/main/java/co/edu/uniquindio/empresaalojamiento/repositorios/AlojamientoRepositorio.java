package co.edu.uniquindio.empresaalojamiento.repositorios;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Alojamiento;
import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Usuario;
import co.edu.uniquindio.empresaalojamiento.modelo.enums.TipoAlojamiento;
import co.edu.uniquindio.empresaalojamiento.repositorios.interfaces.IAlojamientoRepositorio;
import co.edu.uniquindio.empresaalojamiento.utilidades.Constantes;
import co.edu.uniquindio.empresaalojamiento.utilidades.Persistencia;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AlojamientoRepositorio implements IAlojamientoRepositorio {
    private final List<Alojamiento> alojamientos;

    public AlojamientoRepositorio() {

        this.alojamientos = leerDatos();
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

    public void actualizarAlojamiento(String idAlojamiento, String nombre, String descripcion, String ruta,
                                      double precioPorNoche, int capacidadMaximaHuespede, boolean piscina, boolean wifi, boolean desayuno, double costoAdicional){
        Alojamiento alojamientoActualizar = buscarAlojamiento(idAlojamiento);
        alojamientoActualizar.setNombre(nombre);
        alojamientoActualizar.setDescripcion(descripcion);
        alojamientoActualizar.setRuta(ruta);
        alojamientoActualizar.setPrecioPorNoche(precioPorNoche);
        alojamientoActualizar.setCapacidadMaximaHuespedes(capacidadMaximaHuespede);
        alojamientoActualizar.setPiscina(piscina);
        alojamientoActualizar.setWifi(wifi);
        alojamientoActualizar.setDesayuno(desayuno);
        alojamientoActualizar.setCostoAdicional(costoAdicional);
    }

    public  List<String> obtenerCamposOpcionales(Alojamiento alojamiento) {
        List<String> presentes = new ArrayList<>();

        if (alojamiento.isPiscina()) {
            presentes.add("Piscina");
        }

        if (alojamiento.isWifi()) {
            presentes.add("Wifi");
        }
        if (alojamiento.isDesayuno()) {
            presentes.add("Desayuno");
        }
        return presentes;
    }

    public List<Alojamiento> leerDatos() {
        try {
            Object datos = Persistencia.deserializarObjeto(Constantes.RUTA_ALOJAMIENTOS);
            if (datos != null) {
                return (List<Alojamiento>) datos;
            }
        } catch (Exception e) {
            System.err.println("Error cargando alojamientos: " + e.getMessage());
        }
        return new ArrayList<>();
    }


    public void guardarDatos(List<Alojamiento> alojamientos) {
        try {
            Persistencia.serializarObjeto(Constantes.RUTA_ALOJAMIENTOS, alojamientos);
        } catch (IOException e) {
            System.err.println("Error guardando alojamientos: " + e.getMessage());
        }
    }

}
