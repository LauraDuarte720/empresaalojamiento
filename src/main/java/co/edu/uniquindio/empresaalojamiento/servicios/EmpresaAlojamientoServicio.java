package co.edu.uniquindio.empresaalojamiento.servicios;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.*;
import co.edu.uniquindio.empresaalojamiento.modelo.enums.Ciudad;
import co.edu.uniquindio.empresaalojamiento.modelo.enums.Rol;
import co.edu.uniquindio.empresaalojamiento.modelo.enums.TipoAlojamiento;
import co.edu.uniquindio.empresaalojamiento.repositorios.*;
import co.edu.uniquindio.empresaalojamiento.repositorios.interfaces.IAlojamientoRepositorio;
import co.edu.uniquindio.empresaalojamiento.servicios.interfaces.IEmpresaAlojamiento;
import lombok.Getter;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Getter
public class EmpresaAlojamientoServicio implements IEmpresaAlojamiento {

    private final UsuarioServicio usuarioServicio;
    private final AlojamientoRepositorio alojamientoRepositorio;
    private final AlojamientoServicio alojamientoServicio;
    private final UsuarioRepositorio usuarioRepositorio;
    private final ReservaServicio reservaServicio;
    private final ReservaRepositorio reservaRepositorio;
    private final OfertaServicio ofertaServicio;
    private final OfertaRepositorio ofertaRepositorio;
    private final HabitacionServicio habitacionServicio;
    private final HabitacionRepositorio habitacionRepositorio;
    private final ResenaServicio resenaServicio;
    private final ResenaRepositorio resenaRepositorio;



    public EmpresaAlojamientoServicio() {

        this.resenaRepositorio = new ResenaRepositorio();
        this.resenaServicio = new ResenaServicio(resenaRepositorio);
        this.habitacionRepositorio = new HabitacionRepositorio();
        this.habitacionServicio = new HabitacionServicio(habitacionRepositorio);
        this.ofertaRepositorio = new OfertaRepositorio();
        this.ofertaServicio = new OfertaServicio(ofertaRepositorio);
        this.reservaRepositorio = new ReservaRepositorio();
        this.reservaServicio = new ReservaServicio(reservaRepositorio);
        this.usuarioRepositorio = new UsuarioRepositorio();
        this.usuarioServicio = new UsuarioServicio(usuarioRepositorio);
        this.alojamientoRepositorio = new AlojamientoRepositorio();
        this.alojamientoServicio = new AlojamientoServicio(alojamientoRepositorio);

    }

    @Override
    public Alojamiento registrarAlojamiento(TipoAlojamiento tipoAlojamiento, String nombre, String descripcion, String ruta,
                                     double precioPorNoche, int capacidadMaximaHuespede, boolean piscina, boolean wifi, boolean desayuno, double costoAdicional, Ciudad ciudad) throws Exception {
        return alojamientoServicio.crearAlojamiento(tipoAlojamiento, nombre, descripcion, ruta, precioPorNoche, capacidadMaximaHuespede, piscina, wifi, desayuno, costoAdicional, ciudad);
    }

    @Override
    public void eliminarAlojamiento(String idAlojamiento) throws Exception {
        alojamientoServicio.eliminarAlojamiento(idAlojamiento);
    }

    @Override
    public void actualizarAlojamiento(String idAlojamiento, String nombre, String descripcion, String ruta,
                                      double precioPorNoche, int capacidadMaximaHuespede, boolean piscina, boolean wifi, boolean desayuno, double costoAdicional) throws Exception {
        alojamientoServicio.actualizarAlojamiento(idAlojamiento, nombre, descripcion, ruta, precioPorNoche, capacidadMaximaHuespede, piscina, wifi, desayuno, costoAdicional);
    }

    @Override
    public Usuario registrarUsuario(String cedula, String nombre, String apellido, String telefono, String email, String contrasena) throws Exception {
        return usuarioServicio.registarUsuario(cedula, nombre, apellido, telefono, email, contrasena);
    }

    @Override
    public void eliminarUsuario(String cedula) throws Exception {
        usuarioServicio.eliminarUsuario(cedula);
    }

    @Override
    public void actualizarUsuario(String cedulaAntigua, String cedulaNueva, String nombre, String apellido, String telefono, String email) throws Exception {
        usuarioServicio.actualizarUsuario(cedulaAntigua, cedulaNueva,nombre,apellido,telefono,email);
    }

    @Override
    public Oferta registrarOferta(LocalDate fechaInicio, LocalDate fechaFin, double ofertaValor, String idAlojamiento, String descripcion) throws Exception {
        return ofertaServicio.crearOferta(fechaInicio, fechaFin, ofertaValor, idAlojamiento, descripcion);
    }

    @Override
    public void eliminarOferta(String idOferta) throws Exception {
        ofertaServicio.eliminarOferta(idOferta);
    }

    @Override
    public void actualizarOferta(String idOferta, LocalDate fechaInicio, LocalDate fechaFin, double ofertaValor, String idAlojamiento, String descripcion) throws Exception {
        ofertaServicio.modificarOferta(idOferta, fechaInicio, fechaFin, ofertaValor, idAlojamiento, descripcion);
    }

    @Override
    public Habitacion registrarHabitacion(int numeroHabitacion,double precioPorNoche,int capacidadHuespedes,String descripcion, String idHotel, String rutaImagen) throws Exception {
        return habitacionServicio.crearHabitacion(numeroHabitacion,precioPorNoche,capacidadHuespedes,descripcion,idHotel,rutaImagen);
    }

    @Override
    public void eliminarHabitacion(String idHabitacion) throws Exception {
        habitacionServicio.eliminarHabitacion(idHabitacion);
    }

    @Override
    public Resena crearResena(String valoracion, Integer calificacion, String idUsuario, String idAlojamiento) throws Exception {
        return resenaServicio.crearResena(valoracion, calificacion, idUsuario, idAlojamiento);
    }

    @Override
    public Reserva registrarReserva(LocalDate fechaInicio, LocalDate fechaFinal, int numeroHuespedes, String idAlojamiento, String idUsuario) throws Exception {
        Reserva reserva = reservaServicio.crearReserva(fechaInicio, fechaFinal, numeroHuespedes, idAlojamiento, idUsuario);
        Alojamiento alojamientoReserva = alojamientoServicio.obtenerAlojamientoPorId(idAlojamiento);
        if (alojamientoReserva.getCapacidadMaximaHuespedes()<numeroHuespedes){
            throw new Exception("El numero de huespedes supera la capacidad maxima del alojamiento");
        }
        for (Reserva reservaAlojamiento : reservaServicio.obtenerReservasAlojamiento(idAlojamiento)) {
            LocalDate existenteInicio = reservaAlojamiento.getFechaInicio();
            LocalDate existenteFin = reservaAlojamiento.getFechaFinal();

            boolean seCruzanFechas = !(fechaFinal.isBefore(existenteInicio) || fechaInicio.isAfter(existenteFin));

            if (seCruzanFechas) {
                throw new Exception("Las fechas de la reserva no se pueden cruzar");
            }
        }

        int diasReserva = (int) ChronoUnit.DAYS.between(fechaInicio, fechaFinal.plusDays(1));
        double subtotal = (alojamientoReserva.getPrecioPorNoche() * diasReserva) + alojamientoReserva.getCostoAdicional();
        double total = subtotal;
        for (Oferta oferta : ofertaServicio.obtenerOfertasAlojamiento(idAlojamiento)) {
            if (oferta.getFechaInicio().isBefore(fechaInicio) && oferta.getFechaFinal().isAfter(fechaFinal)) {
                total = subtotal-(subtotal * oferta.getValorPorcentaje());
            }
        }

        reserva.getFactura().setSubtotal(subtotal);
        reserva.getFactura().setTotal(total);

        return reserva;
    }


    @Override
    public void cancelarReserva(String idReserva) throws Exception {
        reservaServicio.cancelarReserva(idReserva);
    }

    @Override
    public void recargarBilletera(double monto, String cedulaUsuario) throws Exception {
        usuarioServicio.recargarBilletera(monto, cedulaUsuario);
    }

    @Override
    public List<Alojamiento> obtenerAlojamientosAleatorios(){
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

    public void activarUsuario(String cedula, String codigo) throws Exception{
        usuarioServicio.activarUsuario(cedula, codigo);
    }

    public void enviarCodigo(String correo){
        usuarioServicio.enviarCodigo(correo);
    }

    public double obtenerGananciasTotales(String idAlojamiento) {
        double gananciasTotales = 0;

        List<Reserva> reservasAlojamiento = reservaServicio.obtenerReservasAlojamiento(idAlojamiento);

        for (Reserva reserva : reservasAlojamiento) {
            if (reserva.getFactura() != null) {
                gananciasTotales += reserva.getFactura().getTotal();
            }
        }

        return gananciasTotales;
    }

    public List<Alojamiento> ordenarAlojamientosMasRentable(List<Alojamiento> alojamientos) {
        alojamientos.sort((a1, a2) -> {
            double ganancias1 = obtenerGananciasTotales(a1.getId());
            double ganancias2 = obtenerGananciasTotales(a2.getId());
            return Double.compare(ganancias2, ganancias1); // orden descendente
        });
        return alojamientos;
    }

    public List<Alojamiento> ordenarAlojamientosMasRentableTipo(TipoAlojamiento tipo){
        List<Alojamiento> alojamientosTipo = alojamientoServicio.obtenerAlojamientos().stream()
                .filter(a -> a.getTipoAlojamiento().equals(tipo))
                .collect(Collectors.toList());

        return ordenarAlojamientosMasRentable(alojamientosTipo);
    }

    public Usuario iniciarSesion(String correo, String contrasena) throws Exception{
        Usuario usuarioEncontrado = null;
        for(Usuario usuario : usuarioServicio.obtenerUsuarios()){

            if(usuario.getEmail().equals(correo) && usuario.getContrasena().equals(contrasena)){
                usuarioEncontrado = usuario;
                if(!usuarioEncontrado.getActivo()){
                    throw new IllegalAccessException("Usuario inactivo");
                }
            }
        }
        if(usuarioEncontrado == null){
            throw new Exception("Usuario o contraseña incorrecta");
        }
        return usuarioEncontrado;
    }

    public Usuario buscarUsuarioCorreo(String correo){
        return usuarioServicio.buscarUsuarioCorreo(correo);
    }

    public Usuario buscarUsuario(String id){
        return usuarioServicio.buscarUsuario(id);
    }

    public void validarCambioContrasena(String codigoIngresado, String contrasena) throws Exception{
        usuarioServicio.validarCambioContrasena(codigoIngresado, contrasena);
    }

    public void cambiarCodigoEnviado(String cedula, String codigoEnviado) {
        usuarioServicio.cambiarCodigoEnviado(cedula, codigoEnviado);
    }

    public void cambiarContrasena(Usuario usuario, String contrasena, String contrasenaConfirmar) throws Exception{
        usuarioServicio.cambiarContrasena(usuario, contrasena, contrasenaConfirmar);
    }

    public List<String> obtenerCamposOpcionales(String idAlojamiento) throws Exception{
        return alojamientoServicio.obtenerCamposOpcionales(idAlojamiento);
    }

    public List<Resena> obtenerResenasAlojamiento(String idAlojamiento) throws Exception{
        return resenaServicio.obtenerResenasAlojamiento(idAlojamiento);
    }
}
