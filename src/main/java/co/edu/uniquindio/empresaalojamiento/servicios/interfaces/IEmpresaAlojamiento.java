package co.edu.uniquindio.empresaalojamiento.servicios.interfaces;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.*;
import co.edu.uniquindio.empresaalojamiento.modelo.enums.Ciudad;
import co.edu.uniquindio.empresaalojamiento.modelo.enums.Rol;
import co.edu.uniquindio.empresaalojamiento.modelo.enums.TipoAlojamiento;

import java.time.LocalDate;
import java.util.List;

public interface IEmpresaAlojamiento {
    public Alojamiento registrarAlojamiento(TipoAlojamiento tipoAlojamiento, String nombre, String descripcion, String ruta,
                                     double precioPorNoche, int capacidadMaximaHuespede, boolean piscina, boolean wifi, boolean desayuno, double costoAdicional, Ciudad ciudad) throws Exception;

    public void eliminarAlojamiento(String idAlojamiento) throws Exception;


    public void actualizarAlojamiento(String idAlojamiento, String nombre, String descripcion, String ruta,
                                      double precioPorNoche, int capacidadMaximaHuespede, boolean piscina, boolean wifi, boolean desayuno, double costoAdicional) throws Exception;

    public Usuario registrarUsuario(String cedula, String nombre, String apellido, String telefono,
                                    String email, String contrasena) throws Exception;

    public void eliminarUsuario(String cedula) throws Exception;

    public void actualizarUsuario(String cedulaAntigua, String cedulaNueva, String nombre, String apellido, String telefono, String email) throws Exception;

    public Oferta registrarOferta(LocalDate fechaInicio, LocalDate fechaFin, String ofertaValor, String idAlojamiento, String descripcion) throws Exception;

    public void eliminarOferta(String idOferta) throws Exception;

    public void actualizarOferta(String idOferta, LocalDate fechaInicio, LocalDate fechaFin, double ofertaValor, String idAlojamiento, String descripcion) throws Exception;

    public Habitacion registrarHabitacion(String numeroHabitacion, String precioPorNoche, String capacidadHuespedes, String descripcion, String idHotel, String rutaImagen) throws Exception;

    public void eliminarHabitacion(String idHabitacion) throws Exception;

    public Resena crearResena(String valoracion, Integer calificacion, String idUsuario, String idAlojamiento) throws Exception;

    public Reserva registrarReserva(LocalDate fechaInicio, LocalDate fechaFinal, int numeroHuespedes, String idAlojamiento, String idUsuario,String idHabitacion) throws Exception;

    public void cancelarReserva(String idReserva) throws Exception;

    public void recargarBilletera(double monto, String cedulaUsuario) throws Exception;

    public List<Alojamiento> obtenerAlojamientosAleatorios() throws Exception;
}
