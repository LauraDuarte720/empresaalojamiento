package co.edu.uniquindio.empresaalojamiento.servicios;

import co.edu.uniquindio.empresaalojamiento.factory.FactoryAlojamiento;
import co.edu.uniquindio.empresaalojamiento.filtros.FiltroDinamico;
import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Alojamiento;
import co.edu.uniquindio.empresaalojamiento.modelo.enums.Ciudad;
import co.edu.uniquindio.empresaalojamiento.modelo.enums.TipoAlojamiento;
import co.edu.uniquindio.empresaalojamiento.repositorios.interfaces.IAlojamientoRepositorio;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AlojamientoServicio {
    private IAlojamientoRepositorio alojamientoRepositorio;

    public AlojamientoServicio(IAlojamientoRepositorio alojamientoRepositorio) {
        this.alojamientoRepositorio = alojamientoRepositorio;
    }

    public Alojamiento crearAlojamiento(TipoAlojamiento tipoAlojamiento, String nombre, String descripcion, String ruta,
                                        double precioPorNoche, int capacidadMaximaHuespede, boolean piscina, boolean wifi, boolean desayuno, double costoAdicional, Ciudad ciudad) throws Exception {

        if (tipoAlojamiento == null) throw new Exception("Debe ingresar un tipo de alojamiento");
        if (nombre == null || nombre.isEmpty()) throw new Exception("El nombre del alojamiento no puede ser vacio");
        if (descripcion == null || descripcion.isEmpty())
            throw new Exception("La descripcion del alojamiento no puede ser vacia");
        if (ruta == null || ruta.isEmpty()) throw new Exception("La ruta del alojamiento no puede ser vacia");
        if (precioPorNoche <= 0) throw new Exception("El precio por noche debe ser mayor a 0");
        if (capacidadMaximaHuespede <= 0) throw new Exception("La capacidad maxima de huespedes debe ser mayor a 0");
        if (ciudad == null) throw new Exception("Debe ingresar una ciudad");

        Alojamiento alojamiento = FactoryAlojamiento.crearAlojamiento(tipoAlojamiento, nombre, descripcion, ruta,
                precioPorNoche, capacidadMaximaHuespede, piscina, wifi, desayuno, costoAdicional, ciudad);

        alojamientoRepositorio.agregarAlojamiento(alojamiento);
        return alojamiento;

    }

    public void eliminarAlojamiento(String id) throws Exception {
        Alojamiento alojamiento = alojamientoRepositorio.buscarAlojamiento(id);
        if (alojamiento == null) throw new Exception("El alojamiento no existe");
        alojamientoRepositorio.eliminarAlojamiento(alojamiento);
    }

    public void actualizarAlojamiento(String idAlojamiento, String nombre, String descripcion, String ruta,
                                      double precioPorNoche, int capacidadMaximaHuespede, boolean piscina, boolean wifi, boolean desayuno, double costoAdicional) throws Exception {

        Alojamiento alojamientoActualizar = alojamientoRepositorio.buscarAlojamiento(idAlojamiento);
        if (alojamientoActualizar == null) throw new Exception("El alojamiento no existe");
        if (nombre == null || nombre.isEmpty()) throw new Exception("El nombre del alojamiento no puede ser vacio");
        if (descripcion == null || descripcion.isEmpty())
            throw new Exception("La descripcion del alojamiento no puede ser vacia");
        if (ruta == null || ruta.isEmpty()) throw new Exception("La ruta del alojamiento no puede ser vacia");
        if (precioPorNoche <= 0) throw new Exception("El precio por noche debe ser mayor a 0");
        if (capacidadMaximaHuespede <= 0) throw new Exception("La capacidad maxima de huespedes debe ser mayor a 0");

        alojamientoRepositorio.actualizarAlojamiento(idAlojamiento, nombre, descripcion, ruta, precioPorNoche, capacidadMaximaHuespede, piscina,
                wifi, desayuno, costoAdicional);

    }

    public List<Alojamiento> obtenerAlojamientosFiltrados(String nombreBuscado, TipoAlojamiento tipoSeleccionado, Ciudad ciudadSeleccionada, double precioMin, double precioMax) {
        return FiltroDinamico.filtrar(nombreBuscado, tipoSeleccionado, ciudadSeleccionada, precioMin, precioMax, alojamientoRepositorio.obtenerAlojamientos());
    }

    public List<Alojamiento> obtenerAlojamientos() {
        return alojamientoRepositorio.obtenerAlojamientos();
    }

    public Alojamiento obtenerAlojamientoPorId(String id) {
        return alojamientoRepositorio.buscarAlojamiento(id);
    }

    public List<String> obtenerCamposOpcionales(String idAlojamiento) throws Exception {
        Alojamiento alojamiento = alojamientoRepositorio.buscarAlojamiento(idAlojamiento);
        List<String> presentes = alojamientoRepositorio.obtenerCamposOpcionales(alojamiento);
        if (presentes.isEmpty()) {
            throw new Exception();
        }
        return presentes;
    }
}







