package co.edu.uniquindio.empresaalojamiento.servicios.interfaces;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Billetera;
import co.edu.uniquindio.empresaalojamiento.modelo.enums.Rol;
import co.edu.uniquindio.empresaalojamiento.modelo.enums.TipoAlojamiento;

import java.time.LocalDate;

public interface IEmpresaAlojamiento {
    public void registrarAlojamiento(TipoAlojamiento tipoAlojamiento, String nombre, String descripcion,
                                     String ruta, double precioPorNoche, int capacidadMaximaHuespedes, boolean piscina, boolean wifi, boolean desayuno, double costoAdicional) throws Exception;

    public void eliminarAlojamiento(String idAlojamiento) throws Exception;


    public void actualizarAlojamiento(String idActualizar, TipoAlojamiento tipoAlojamiento, String nombre, String descripcion,
                                      String ruta, double precioPorNoche, int capacidadMaximaHuespedes, boolean piscina, boolean wifi, boolean desayuno, double costoAdicional) throws Exception;

    public void registrarUsuario(String cedula, String nombre, String apellido, String telefono,
                                 String email, String contrasena, Billetera billetera, Rol rol) throws Exception;

    public void eliminarUsuario(String cedula) throws Exception;

    public void actualizarUsuario(String cedulaActualizar, String nombre, String apellido, String telefono,
                                  String email, String contrasena, Billetera billetera, Rol rol) throws Exception;

    public void registrarOferta(LocalDate fechaInicio, LocalDate fechaFinal, double valorPorcentaje, String idAlojamiento) throws Exception;

    public void eliminarOferta(String idOferta) throws Exception;

    public void actualizarOferta(String ofertaActualizar, LocalDate fechaInicio, LocalDate fechaFinal, double valorPorcentaje, String idAlojamiento) throws Exception;

    public void registrarHabitacion(int numero, double precioPorNoche, int capacidadHuespedes,
                                    String rutaImagen, String descripcion, String idHotel) throws Exception;

    public void eliminarHabitacion(int idHabitacion) throws Exception;

    public void actualizarHabitacion(String idHabitacionActualizar, int numero, double precioPorNoche, int capacidadHuespedes,
                                    String rutaImagen, String descripcion, String idHotel) throws Exception;

    public void crearResena(String valoracion, int calificacion, String idUsuario, String idAlojamiento) throws Exception;

    public void registrarReserva(LocalDate fechaInicio, LocalDate fechaFinal, int numeroHuespedes, String idAlojamiento) throws Exception;

    public void cancelarReserva(String idReserva) throws Exception;
}
