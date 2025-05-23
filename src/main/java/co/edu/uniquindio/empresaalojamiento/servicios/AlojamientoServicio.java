package co.edu.uniquindio.empresaalojamiento.servicios;

import co.edu.uniquindio.empresaalojamiento.factory.FactoryAlojamiento;
import co.edu.uniquindio.empresaalojamiento.filtros.FiltroDinamico;
import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Alojamiento;
import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Oferta;
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
                                        String precioPorNoche, String capacidadMaximaHuespedes, boolean piscina, boolean wifi, boolean desayuno, String costoAdicional, Ciudad ciudad, boolean parqueadro, boolean mascotasPermitidas, boolean gym) throws Exception {

        if (tipoAlojamiento == null) throw new Exception("Debe ingresar un tipo de alojamiento");
        if (nombre == null || nombre.isEmpty()) throw new Exception("El nombre del alojamiento no puede ser vacio");
        if (descripcion == null || descripcion.isEmpty())
            throw new Exception("La descripcion del alojamiento no puede ser vacia");
        if (ruta == null || ruta.isEmpty()) throw new Exception("La ruta del alojamiento no puede ser vacia");


        double precioPorNocheD = 0;
        int capacidadMaximaHuespedesD = 0;
        double costoAdicionalD = 0;
        if (tipoAlojamiento == TipoAlojamiento.APARTAMENTOS || tipoAlojamiento == TipoAlojamiento.CASA) {
            try {
                precioPorNocheD = Double.parseDouble(precioPorNoche);
            } catch (Exception e) {
                throw new Exception("El precio por noche debe ser un número");
            }

            try {
                capacidadMaximaHuespedesD = Integer.parseInt(capacidadMaximaHuespedes);
            } catch (Exception e) {
                throw new Exception("La capacidad máxima de huespedes debe ser un número");
            }

            try {
                costoAdicionalD = Double.parseDouble(costoAdicional);
            } catch (Exception e) {
                throw new Exception("El costo adicional debe ser un número");
            }


            if (precioPorNocheD <= 0) throw new Exception("El precio por noche debe ser mayor a 0");
            if (capacidadMaximaHuespedesD <= 0)
                throw new Exception("La capacidad maxima de huespedes debe ser mayor a 0");
            if (costoAdicionalD < 0)
                throw new Exception("El costo adicional debe ser mayor o igual a 0");
        }

        if (ciudad == null) throw new Exception("Debe ingresar una ciudad");

        Alojamiento alojamiento = FactoryAlojamiento.crearAlojamiento(tipoAlojamiento, nombre, descripcion, ruta,
                precioPorNocheD, capacidadMaximaHuespedesD, piscina, wifi, desayuno, costoAdicionalD, ciudad, parqueadro, mascotasPermitidas, gym);

        alojamientoRepositorio.agregarAlojamiento(alojamiento);
        return alojamiento;

    }

    public void eliminarAlojamiento(String id) throws Exception {
        Alojamiento alojamiento = alojamientoRepositorio.buscarAlojamiento(id);
        if (alojamiento == null) throw new Exception("El alojamiento no existe");
        alojamientoRepositorio.eliminarAlojamiento(alojamiento);
    }

    public void actualizarAlojamiento(String idAlojamiento, String nombre, String descripcion, String ruta,
                                      String precioPorNoche, String capacidadMaximaHuespedes, boolean piscina, boolean wifi, boolean desayuno, String costoAdicional, Ciudad ciudad, boolean parqueadro, boolean mascotasPermitidas, boolean gym) throws Exception {

        Alojamiento alojamientoActualizar = alojamientoRepositorio.buscarAlojamiento(idAlojamiento);
        if (alojamientoActualizar == null) throw new Exception("El alojamiento no existe");
        if (nombre == null || nombre.isEmpty()) throw new Exception("El nombre del alojamiento no puede ser vacio");
        if (descripcion == null || descripcion.isEmpty())
            throw new Exception("La descripcion del alojamiento no puede ser vacia");
        if (ruta == null || ruta.isEmpty()) throw new Exception("La ruta del alojamiento no puede ser vacia");

        double precioPorNocheD = 0;
        int capacidadMaximaHuespedesD = 0;
        double costoAdicionalD = 0;
        if (alojamientoActualizar.getTipoAlojamiento() == TipoAlojamiento.APARTAMENTOS || alojamientoActualizar.getTipoAlojamiento() == TipoAlojamiento.CASA) {
            try {
                precioPorNocheD = Double.parseDouble(precioPorNoche);
            } catch (Exception e) {
                throw new Exception("El precio por noche debe ser un número");
            }

            try {
                capacidadMaximaHuespedesD = Integer.parseInt(capacidadMaximaHuespedes);
            } catch (Exception e) {
                throw new Exception("La capacidad máxima de huespedes debe ser un número");
            }

            try {
                costoAdicionalD = Double.parseDouble(costoAdicional);
            } catch (Exception e) {
                throw new Exception("El costo adicional debe ser un número");
            }


            if (precioPorNocheD < 0) throw new Exception("El precio por noche debe ser mayor a 0");
            if (capacidadMaximaHuespedesD < 0)
                throw new Exception("La capacidad maxima de huespedes debe ser mayor a 0");
            if (costoAdicionalD < 0)
                throw new Exception("El costo adicional debe ser mayor o igual a 0");
        }
        if (ciudad == null) throw new Exception("Debe ingresar una ciudad");

        alojamientoRepositorio.actualizarAlojamiento(idAlojamiento, nombre, descripcion, ruta, precioPorNocheD, capacidadMaximaHuespedesD, piscina,
                wifi, desayuno, costoAdicionalD, ciudad, parqueadro, mascotasPermitidas, gym);

    }

    public List<Alojamiento> obtenerAlojamientosFiltrados(String nombreBuscado, TipoAlojamiento tipoSeleccionado, Ciudad ciudadSeleccionada, String precioMin, String precioMax, boolean ofertaAplicada, List<Oferta> ofertas) throws Exception {
        double precioMinD;
        double precioMaxD;
        try {
            precioMinD = Double.parseDouble(precioMin);
            precioMaxD = Double.parseDouble(precioMax);

        } catch (Exception e) {
            precioMinD = 0;
            precioMaxD = 0;
        }

        if (precioMaxD < precioMinD) throw new Exception("El precio máximo debe ser mayor que el mínimo");

        if (precioMinD < 0) throw new Exception("El precio mínimo debe ser mayor que 0");

        return FiltroDinamico.filtrar(nombreBuscado, tipoSeleccionado, ciudadSeleccionada, precioMinD, precioMaxD, alojamientoRepositorio.obtenerAlojamientos(), ofertaAplicada, ofertas);
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







