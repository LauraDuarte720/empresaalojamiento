package co.edu.uniquindio.empresaalojamiento.repositorios.interfaces;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Alojamiento;
import co.edu.uniquindio.empresaalojamiento.modelo.enums.TipoAlojamiento;


import java.util.List;

public interface IAlojamientoRepositorio {
    public void agregarAlojamiento(Alojamiento alojamiento);

    public void eliminarAlojamiento(Alojamiento alojamiento);

    public Alojamiento buscarAlojamiento(String idAlojamiento);

    public List<Alojamiento> obtenerAlojamientos();

    public List<Alojamiento> obtenerAlojamientoPorTipo(TipoAlojamiento tipo);

    public void actualizarAlojamiento(String idAlojamiento, String nombre, String descripcion, String ruta,
                                      double precioPorNoche, int capacidadMaximaHuespede, boolean piscina, boolean wifi, boolean desayuno, double costoAdicional);
}
