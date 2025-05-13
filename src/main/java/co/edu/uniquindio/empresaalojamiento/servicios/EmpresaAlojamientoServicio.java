package co.edu.uniquindio.empresaalojamiento.servicios;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Alojamiento;
import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Billetera;
import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Reserva;
import co.edu.uniquindio.empresaalojamiento.modelo.enums.Ciudad;
import co.edu.uniquindio.empresaalojamiento.modelo.enums.Rol;
import co.edu.uniquindio.empresaalojamiento.modelo.enums.TipoAlojamiento;
import co.edu.uniquindio.empresaalojamiento.repositorios.AlojamientoRepositorio;
import co.edu.uniquindio.empresaalojamiento.repositorios.ReservaRepositorio;
import co.edu.uniquindio.empresaalojamiento.repositorios.UsuarioRepositorio;
import co.edu.uniquindio.empresaalojamiento.repositorios.interfaces.IAlojamientoRepositorio;
import co.edu.uniquindio.empresaalojamiento.servicios.interfaces.IEmpresaAlojamiento;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class EmpresaAlojamientoServicio implements IEmpresaAlojamiento {

    private final UsuarioServicio usuarioServicio;
    private final AlojamientoRepositorio alojamientoRepositorio;
    private final AlojamientoServicio alojamientoServicio;
    private final UsuarioRepositorio usuarioRepositorio;
    private final ReservaServicio reservaServicio;
    private final ReservaRepositorio reservaRepositorio;


    public EmpresaAlojamientoServicio() {

        this.reservaRepositorio = new ReservaRepositorio();
        this.reservaServicio = new ReservaServicio(reservaRepositorio);
        this.usuarioRepositorio = new UsuarioRepositorio();
        this.usuarioServicio = new UsuarioServicio(usuarioRepositorio);
        this.alojamientoRepositorio = new AlojamientoRepositorio();
        this.alojamientoServicio = new AlojamientoServicio(alojamientoRepositorio);

    }

    @Override
    public void registrarAlojamiento(TipoAlojamiento tipoAlojamiento, String nombre, String descripcion, String ruta, double precioPorNoche, int capacidadMaximaHuespedes, boolean piscina, boolean wifi, boolean desayuno, double costoAdicional) throws Exception {

    }

    @Override
    public void eliminarAlojamiento(String idAlojamiento) throws Exception {

    }

    @Override
    public void actualizarAlojamiento(String idActualizar, TipoAlojamiento tipoAlojamiento, String nombre, String descripcion, String ruta, double precioPorNoche, int capacidadMaximaHuespedes, boolean piscina, boolean wifi, boolean desayuno, double costoAdicional) throws Exception {

    }

    @Override
    public void registrarUsuario(String cedula, String nombre, String apellido, String telefono, String email, String contrasena, Billetera billetera, Rol rol) throws Exception {

    }

    @Override
    public void eliminarUsuario(String cedula) throws Exception {

    }

    @Override
    public void actualizarUsuario(String cedulaAntigua, String cedulaNueva, String nombre, String apellido, String telefono, String email) throws Exception {
        usuarioServicio.actualizarUsuario(cedulaAntigua, cedulaNueva,nombre,apellido,telefono,email);
    }

    @Override
    public void registrarOferta(LocalDate fechaInicio, LocalDate fechaFinal, double valorPorcentaje, String idAlojamiento) throws Exception {

    }

    @Override
    public void eliminarOferta(String idOferta) throws Exception {

    }

    @Override
    public void actualizarOferta(String ofertaActualizar, LocalDate fechaInicio, LocalDate fechaFinal, double valorPorcentaje, String idAlojamiento) throws Exception {

    }

    @Override
    public void registrarHabitacion(int numero, double precioPorNoche, int capacidadHuespedes, String rutaImagen, String descripcion, String idHotel) throws Exception {

    }

    @Override
    public void eliminarHabitacion(int idHabitacion) throws Exception {

    }

    @Override
    public void actualizarHabitacion(String idHabitacionActualizar, int numero, double precioPorNoche, int capacidadHuespedes, String rutaImagen, String descripcion, String idHotel) throws Exception {

    }

    @Override
    public void crearResena(String valoracion, int calificacion, String idUsuario, String idAlojamiento) throws Exception {

    }

    @Override
    public void registrarReserva(LocalDate fechaInicio, LocalDate fechaFinal, int numeroHuespedes, String idAlojamiento) throws Exception {

    }

    @Override
    public void cancelarReserva(String idReserva) throws Exception {

    }

    @Override
    public void recargarBilletera(String cedula, double monto) throws Exception {

    }

    @Override
    public List<Alojamiento> obtenerAlojamientosAleatorios() throws Exception {
        List<Alojamiento> copiaLista = alojamientoServicio.obtenerAlojamientos();
        Collections.shuffle(copiaLista);

        if (copiaLista.size() >= 6) {
            return copiaLista.subList(0, 6);
        } else {
            return copiaLista;
        }
    }

    public double calcularOcupacionPorcentual(int ano, String idAlojamiento) {

        LocalDate inicioAno = LocalDate.of(ano, 1, 1);
        LocalDate finAno = LocalDate.of(ano, 12, 31);
        long diasTotales = ChronoUnit.DAYS.between(inicioAno, finAno.plusDays(1));
        int diasOcupados =  0;
        List <Reserva> reservasAlojamiento=reservaServicio.obtenerReservasAlojamiento(idAlojamiento);

            for (Reserva reserva : reservasAlojamiento) {
                LocalDate inicioReserva = reserva.getFechaInicio();
                LocalDate finReserva = reserva.getFechaFinal();

                LocalDate inicio = inicioReserva.isBefore(inicioAno) ? inicioAno : inicioReserva;
                LocalDate fin = finReserva.isAfter(finAno) ? finAno : finReserva;

            if (!inicio.isAfter(fin)) {
                    diasOcupados += (int) ChronoUnit.DAYS.between(inicio, fin.plusDays(1));
                }
            }

        return ((double) diasOcupados / diasTotales) * 100;
    }

    public double obtenerGananciasTotales(int ano, String idAlojamiento){
        LocalDate inicioAno = LocalDate.of(ano, 1, 1);
        LocalDate finAno = LocalDate.of(ano, 12, 31);
        double gananciasTotales = 0;

        List <Reserva> reservasAlojamiento=reservaServicio.obtenerReservasAlojamiento(idAlojamiento);

        for (Reserva reserva : reservasAlojamiento) {
            LocalDate inicioReserva = reserva.getFechaInicio();
            LocalDate finReserva = reserva.getFechaFinal();

            LocalDate inicio = inicioReserva.isBefore(inicioAno) ? inicioAno : inicioReserva;
            LocalDate fin = finReserva.isAfter(finAno) ? finAno : finReserva;

            if (!inicio.isAfter(fin)) {
                gananciasTotales+=reserva.getFactura().getTotal();
            }
        }
        return gananciasTotales;
    }

    public double obtenerCantidadReservasAlojamiento( String idAlojamiento){
        List <Reserva> reservasAlojamiento=reservaServicio.obtenerReservasAlojamiento(idAlojamiento);
        double cantidadReservas = 0;

        for (Reserva reserva : reservasAlojamiento) {
            cantidadReservas++;
        }
        return cantidadReservas;
    }

    public List<Alojamiento> ordenarAlojamientosPopulares(List<Alojamiento> alojamientos) {
        alojamientos.sort((a1, a2) -> {
            double reservas1 = obtenerCantidadReservasAlojamiento(a1.getId());
            double reservas2 = obtenerCantidadReservasAlojamiento(a2.getId());
            return Double.compare(reservas2, reservas1);
        });
        return alojamientos;
    }

    public List<Alojamiento> ordenarAlojamientosPopularesCiudad(Ciudad ciudad) {

        List<Alojamiento> alojamientosCiudad= alojamientoServicio.obtenerAlojamientos().stream()
                .filter(a -> a.getCiudad().equals(ciudad))
                .toList();
        return ordenarAlojamientosPopulares(alojamientosCiudad);
    }
}
