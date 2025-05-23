package co.edu.uniquindio.empresaalojamiento.servicios.interfaces;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.*;
import co.edu.uniquindio.empresaalojamiento.modelo.enums.Ciudad;
import co.edu.uniquindio.empresaalojamiento.modelo.enums.Rol;
import co.edu.uniquindio.empresaalojamiento.modelo.enums.TipoAlojamiento;
import co.edu.uniquindio.empresaalojamiento.modelo.vo.Notificacion;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface IEmpresaAlojamiento {
    public Alojamiento registrarAlojamiento(TipoAlojamiento tipoAlojamiento, String nombre, String descripcion, String ruta,
                                            double precioPorNoche, int capacidadMaximaHuespede, boolean piscina, boolean wifi, boolean desayuno, double costoAdicional, Ciudad ciudad, boolean parqueadro, boolean mascotasPermitidas, boolean gym) throws Exception;

    public void eliminarAlojamiento(String idAlojamiento) throws Exception;


    public void actualizarAlojamiento(String idAlojamiento, String nombre, String descripcion, String ruta,
                                      double precioPorNoche, int capacidadMaximaHuespede, boolean piscina, boolean wifi, boolean desayuno, double costoAdicional,Ciudad ciudad, boolean parqueadro, boolean mascotasPermitidas, boolean gym) throws Exception;

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

    public double calcularOcupacionPorcentual(int ano, String idAlojamiento);

    public double obtenerGananciasTotales(int ano, String idAlojamiento);

    public double obtenerCantidadReservasAlojamiento(String idAlojamiento);

    public List<Alojamiento> ordenarAlojamientosPopulares(List<Alojamiento> alojamientos);

    public List<Alojamiento> ordenarAlojamientosPopularesCiudad(Ciudad ciudad);

    public void activarUsuario(String cedula, String codigo) throws Exception;

    public void enviarCodigo(String correo) throws Exception;

    public double obtenerGananciasTotales(String idAlojamiento);

    public List<Alojamiento> ordenarAlojamientosMasRentable(List<Alojamiento> alojamientos);

    public List<Alojamiento> ordenarAlojamientosMasRentableTipo(TipoAlojamiento tipo);

    public Usuario iniciarSesion(String correo, String contrasena) throws Exception;

    public Usuario buscarUsuarioCorreo(String correo);

    public Usuario buscarUsuario(String id);

    public void validarCambioContrasena(String codigoIngresado, String contrasena) throws Exception;

    public void cambiarCodigoEnviado(String cedula, String codigoEnviado);

    public void cambiarContrasena(Usuario usuario, String contrasena, String contrasenaConfirmar) throws Exception;

    public List<String> obtenerCamposOpcionales(String idAlojamiento) throws Exception;

    public List<Resena> obtenerResenasAlojamiento(String idAlojamiento) throws Exception;

    public Alojamiento obtenerAlojamientoPorId(String idAlojamiento);

    public List<Reserva> obtenerReservasUsuario(String idUsuario);

    public void promediarCalificaciones(String idAlojamiento) throws Exception;

    public List<Alojamiento> obtenerAlojamientosFiltrados(String nombreBuscado, TipoAlojamiento tipoSeleccionado, Ciudad ciudadSeleccionada, String precioMin, String precioMax, boolean ofertaAplicada) throws Exception;

    public String generarInfoFactura(Reserva reserva) throws Exception;

    public String obtenerServiciosIncluidosString(Alojamiento alojamiento) throws Exception;

    public List<Habitacion> obtenerHabitacionesHotel(String idHotel);

    public List<Oferta> obtenerOfertas();

    public List<Integer> obtenerNumerosHabitaciones(String idHotel);

    public void enviarNotificacion(String mensaje, String idCliente);

    public List<Notificacion> obtenerNotificaciones(String idCliente);

    public void marcarComoLeida(UUID id);

    public List<Usuario> obtenerUsuarios();

    public List<Oferta> obtenerOfertasAlojamiento(String idAlojamiento);



}
