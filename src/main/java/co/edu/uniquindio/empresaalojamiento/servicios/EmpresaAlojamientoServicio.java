package co.edu.uniquindio.empresaalojamiento.servicios;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.*;
import co.edu.uniquindio.empresaalojamiento.modelo.enums.Ciudad;
import co.edu.uniquindio.empresaalojamiento.modelo.enums.TipoAlojamiento;
import co.edu.uniquindio.empresaalojamiento.modelo.vo.Notificacion;
import co.edu.uniquindio.empresaalojamiento.repositorios.*;
import co.edu.uniquindio.empresaalojamiento.servicios.interfaces.IEmpresaAlojamiento;
import co.edu.uniquindio.empresaalojamiento.utilidades.Utilidades;
import javafx.fxml.FXML;
import lombok.Getter;

import java.io.File;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
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
    private final NotificacionServicio notificacionServicio;
    private final NotificacionRepositorio notificacionRepositorio;


    public EmpresaAlojamientoServicio() {

        this.notificacionRepositorio = new NotificacionRepositorio();
        this.notificacionServicio = new NotificacionServicio(notificacionRepositorio);
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
                                            String precioPorNoche, String capacidadMaximaHuespede, boolean piscina, boolean wifi, boolean desayuno, String costoAdicional, Ciudad ciudad, boolean parqueadro, boolean mascotasPermitidas, boolean gym) throws Exception {
        return alojamientoServicio.crearAlojamiento(tipoAlojamiento, nombre, descripcion, ruta, precioPorNoche, capacidadMaximaHuespede, piscina, wifi, desayuno, costoAdicional, ciudad, parqueadro, mascotasPermitidas, gym);
    }

    @Override
    public void eliminarAlojamiento(String idAlojamiento) throws Exception {
        alojamientoServicio.eliminarAlojamiento(idAlojamiento);
    }

    @Override
    public void actualizarAlojamiento(String idAlojamiento, String nombre, String descripcion, String ruta,
                                      String precioPorNoche, String capacidadMaximaHuespede, boolean piscina, boolean wifi, boolean desayuno, String costoAdicional,Ciudad ciudad, boolean parqueadro, boolean mascotasPermitidas, boolean gym) throws Exception {
        alojamientoServicio.actualizarAlojamiento(idAlojamiento, nombre, descripcion, ruta, precioPorNoche, capacidadMaximaHuespede, piscina, wifi, desayuno, costoAdicional,ciudad,parqueadro,mascotasPermitidas,gym);
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
        usuarioServicio.actualizarUsuario(cedulaAntigua, cedulaNueva, nombre, apellido, telefono, email);
    }

    @Override
    public Oferta registrarOferta(LocalDate fechaInicio, LocalDate fechaFin, String ofertaValor, String idAlojamiento, String descripcion) throws Exception {
        Alojamiento alojamientoReserva = alojamientoServicio.obtenerAlojamientoPorId(idAlojamiento);
        System.out.println("Nombre del alojamiento al que se le hisho la oferta" + alojamientoReserva.getNombre());
        return ofertaServicio.crearOferta(fechaInicio, fechaFin, ofertaValor, idAlojamiento, descripcion);
    }

    @Override
    public void eliminarOferta(String idOferta) throws Exception {
        ofertaServicio.eliminarOferta(idOferta);
    }

    @Override
    public void actualizarOferta(String idOferta, LocalDate fechaInicio, LocalDate fechaFin, String ofertaValor, String descripcion) throws Exception {
        ofertaServicio.modificarOferta(idOferta, fechaInicio, fechaFin, ofertaValor, descripcion);
    }

    @Override
    public Habitacion registrarHabitacion(String numeroHabitacion, String precioPorNoche, String capacidadHuespedes, String descripcion, String idHotel, String rutaImagen) throws Exception {
        return habitacionServicio.crearHabitacion(numeroHabitacion, precioPorNoche, capacidadHuespedes, descripcion, idHotel, rutaImagen);
    }

    @Override
    public void eliminarHabitacion(String idHabitacion) throws Exception {
        habitacionServicio.eliminarHabitacion(idHabitacion);
    }

    @Override
    public Resena crearResena(String valoracion, Integer calificacion, String idUsuario, String idAlojamiento) throws Exception {
        Resena resena = resenaServicio.crearResena(valoracion, calificacion, idUsuario, idAlojamiento);
        promediarCalificaciones(idAlojamiento);
        return resena;
    }

    @Override
    public Reserva registrarReserva(LocalDate fechaInicio, LocalDate fechaFinal, String numeroHuespedes, String idAlojamiento, String idUsuario, String idHabitacion) throws Exception {
        Alojamiento alojamientoReserva = alojamientoServicio.obtenerAlojamientoPorId(idAlojamiento);
        Habitacion habitacionReserva = habitacionServicio.buscarHabitacion(idHabitacion);

        int capacidadMaximaHuespedesReserva;
        double precioNocheReserva;
        List<Reserva> reservas;

        if (habitacionReserva == null) {
            capacidadMaximaHuespedesReserva = alojamientoReserva.getCapacidadMaximaHuespedes();
            precioNocheReserva = alojamientoReserva.getPrecioPorNoche();
            reservas = reservaServicio.obtenerReservasAlojamiento(idAlojamiento);
        } else {
            capacidadMaximaHuespedesReserva = habitacionReserva.getCapacidadHuespedes();
            precioNocheReserva = habitacionReserva.getPrecioPorNoche();
            reservas = reservaServicio.obtenerReservarHabitacion(idHabitacion);
        }

        int numeroHuespedesI = 0;

        try{
            numeroHuespedesI = Integer.parseInt(numeroHuespedes);
        }
        catch(Exception e){
            throw new Exception("Numero de huespedes no valido");
        }


        if (capacidadMaximaHuespedesReserva < numeroHuespedesI) {
            throw new Exception("El numero de huespedes supera la capacidad maxima del alojamiento");
        }
        for (Reserva reservaAlojamiento : reservas) {
            LocalDate existenteInicio = reservaAlojamiento.getFechaInicio();
            LocalDate existenteFin = reservaAlojamiento.getFechaFinal();

            boolean seCruzanFechas = !(fechaFinal.isBefore(existenteInicio) || fechaInicio.isAfter(existenteFin));

            if (seCruzanFechas) {
                throw new Exception("Las fechas de la reserva no se pueden cruzar");
            }
        }

        int diasReserva = (int) ChronoUnit.DAYS.between(fechaInicio, fechaFinal);
        double subtotal = (precioNocheReserva * diasReserva) + alojamientoReserva.getCostoAdicional();
        double total = subtotal;
        for (Oferta oferta : ofertaServicio.obtenerOfertasAlojamiento(idAlojamiento)) {
            if (oferta.getFechaInicio().isBefore(fechaInicio) && oferta.getFechaFinal().isAfter(fechaFinal)) {
                total = subtotal - (subtotal * oferta.getValorPorcentaje());
            }
        }

        Usuario usuario = usuarioServicio.buscarUsuario(idUsuario);
        if (usuario.getBilletera().getSaldo() < total) {
            throw new Exception("No tiene saldo suficiente para realizar la reserva");
        }

        usuario.getBilletera().setSaldo(usuario.getBilletera().getSaldo() - total);

        Reserva reserva = reservaServicio.crearReserva(fechaInicio, fechaFinal, numeroHuespedesI, idAlojamiento, idUsuario, idHabitacion);
        reserva.getFactura().setSubtotal(subtotal);
        reserva.getFactura().setTotal(total);


        File factura = Utilidades.generarPdf("Factura" + reserva.getFactura().getCodigo(), generarInfoFactura(reserva));
        Utilidades.enviarCorreoQrPdf(usuario.getEmail(), "Reservación", "Hola " + usuario.getNombre() + "\nHas hecho una reservación en:\n\t " + alojamientoReserva.getNombre() + "\n\nPara ver su factura electrónica escaneé el siguiente código: \n\n", "imagenQr" + usuario.getCedula() + System.currentTimeMillis() + ".png", factura);
        return reserva;
    }


    @Override
    public void cancelarReserva(String idReserva) throws Exception {
        reservaServicio.cancelarReserva(idReserva);
    }

    @Override
    public void recargarBilletera(String monto, String cedulaUsuario) throws Exception {
        usuarioServicio.recargarBilletera(monto, cedulaUsuario);
    }

    @Override
    public List<Alojamiento> obtenerAlojamientosAleatorios() {
        List<Alojamiento> copiaLista = alojamientoServicio.obtenerAlojamientos();
        Collections.shuffle(copiaLista);

        if (copiaLista.size() >= 6) {
            return copiaLista.subList(0, 6);
        } else {
            return copiaLista;
        }
    }

    @Override
    public double calcularOcupacionPorcentual(int ano, String idAlojamiento) {

        LocalDate inicioAno = LocalDate.of(ano, 1, 1);
        LocalDate finAno = LocalDate.of(ano, 12, 31);
        long diasTotales = ChronoUnit.DAYS.between(inicioAno, finAno.plusDays(1));
        int diasOcupados = 0;

        Alojamiento alojamiento = alojamientoServicio.obtenerAlojamientoPorId(idAlojamiento);
        if (alojamiento.getPrecioPorNoche()==0){
            List<Habitacion> habitacionesHotel=obtenerHabitacionesHotel(idAlojamiento);
            for (Habitacion habitacion : habitacionesHotel) {
                List<Reserva> reservasAHabitacion = reservaServicio.obtenerReservasAlojamiento(habitacion.getId());
                for (Reserva reserva : reservasAHabitacion) {
                    LocalDate inicioReserva = reserva.getFechaInicio();
                    LocalDate finReserva = reserva.getFechaFinal();
                    LocalDate inicio = inicioReserva.isBefore(inicioAno) ? inicioAno : inicioReserva;
                    LocalDate fin = finReserva.isAfter(finAno) ? finAno : finReserva;
                    if (!inicio.isAfter(fin)) {
                        diasOcupados += (int) ChronoUnit.DAYS.between(inicio, fin.plusDays(1));
                    }
                }
            }
        }else{
            List<Reserva> reservasAlojamiento = reservaServicio.obtenerReservasAlojamiento(idAlojamiento);
            for (Reserva reserva : reservasAlojamiento) {
                LocalDate inicioReserva = reserva.getFechaInicio();
                LocalDate finReserva = reserva.getFechaFinal();

                LocalDate inicio = inicioReserva.isBefore(inicioAno) ? inicioAno : inicioReserva;
                LocalDate fin = finReserva.isAfter(finAno) ? finAno : finReserva;

                if (!inicio.isAfter(fin)) {
                    diasOcupados += (int) ChronoUnit.DAYS.between(inicio, fin.plusDays(1));
                }
            }
        }

        return ((double) diasOcupados / diasTotales) * 100;
    }

    @Override
    public double obtenerGananciasTotales(int ano, String idAlojamiento) {
        LocalDate inicioAno = LocalDate.of(ano, 1, 1);
        LocalDate finAno = LocalDate.of(ano, 12, 31);
        double gananciasTotales = 0;

        List<Reserva> reservasAlojamiento = reservaServicio.obtenerReservasAlojamiento(idAlojamiento);

        for (Reserva reserva : reservasAlojamiento) {
            LocalDate inicioReserva = reserva.getFechaInicio();
            LocalDate finReserva = reserva.getFechaFinal();

            LocalDate inicio = inicioReserva.isBefore(inicioAno) ? inicioAno : inicioReserva;
            LocalDate fin = finReserva.isAfter(finAno) ? finAno : finReserva;

            if (!inicio.isAfter(fin)) {
                gananciasTotales += reserva.getFactura().getTotal();
            }
        }
        return gananciasTotales;
    }

    @Override
    public double obtenerCantidadReservasAlojamiento(String idAlojamiento) {
        return reservaServicio.obtenerReservasAlojamiento(idAlojamiento).size();
    }

    @Override
    public List<Alojamiento> ordenarAlojamientosPopulares(List<Alojamiento> alojamientos) {
        alojamientos.sort((a1, a2) -> {
            double reservas1 = obtenerCantidadReservasAlojamiento(a1.getId());
            double reservas2 = obtenerCantidadReservasAlojamiento(a2.getId());
            return Double.compare(reservas2, reservas1);
        });
        return alojamientos;
    }

    @Override
    public List<Alojamiento> ordenarAlojamientosPopularesCiudad(Ciudad ciudad) {

        List<Alojamiento> alojamientosCiudad = alojamientoServicio.obtenerAlojamientos().stream()
                .filter(a -> a.getCiudad().equals(ciudad))
                .collect(Collectors.toList());
        return ordenarAlojamientosPopulares(alojamientosCiudad);
    }

    @Override
    public void activarUsuario(String cedula, String codigo) throws Exception {
        usuarioServicio.activarUsuario(cedula, codigo);
    }

    @Override
    public void enviarCodigo(String correo) throws Exception {
        usuarioServicio.enviarCodigo(correo);
    }

    @Override
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

    @Override
    public List<Alojamiento> ordenarAlojamientosMasRentable(List<Alojamiento> alojamientos) {
        alojamientos.sort((a1, a2) -> {
            double ganancias1 = obtenerGananciasTotales(a1.getId());
            double ganancias2 = obtenerGananciasTotales(a2.getId());
            return Double.compare(ganancias2, ganancias1); // orden descendente
        });
        return alojamientos;
    }

    @Override
    public List<Alojamiento> ordenarAlojamientosMasRentableTipo(TipoAlojamiento tipo) {
        List<Alojamiento> alojamientosTipo = alojamientoServicio.obtenerAlojamientos().stream()
                .filter(a -> a.getTipoAlojamiento().equals(tipo))
                .collect(Collectors.toList());

        return ordenarAlojamientosMasRentable(alojamientosTipo);
    }

    @Override
    public Usuario iniciarSesion(String correo, String contrasena) throws Exception {
        Usuario usuarioEncontrado = null;
        for (Usuario usuario : usuarioServicio.obtenerUsuarios()) {

            if (usuario.getEmail().equals(correo) && usuario.getContrasena().equals(contrasena)) {
                usuarioEncontrado = usuario;
                if (!usuarioEncontrado.getActivo()) {
                    throw new IllegalAccessException("Usuario inactivo");
                }
            }
        }
        if (usuarioEncontrado == null) {
            throw new Exception("Usuario o contraseña incorrecta");
        }
        return usuarioEncontrado;
    }


    @Override
    public Usuario buscarUsuarioCorreo(String correo) {
        return usuarioServicio.buscarUsuarioCorreo(correo);
    }

    @Override
    public Usuario buscarUsuario(String id) {
        return usuarioServicio.buscarUsuario(id);
    }

    @Override
    public void validarCambioContrasena(String codigoIngresado, String contrasena) throws Exception {
        usuarioServicio.validarCambioContrasena(codigoIngresado, contrasena);
    }

    @Override
    public void cambiarCodigoEnviado(String cedula, String codigoEnviado) {
        usuarioServicio.cambiarCodigoEnviado(cedula, codigoEnviado);
    }

    @Override
    public void cambiarContrasena(Usuario usuario, String contrasena, String contrasenaConfirmar) throws Exception {
        usuarioServicio.cambiarContrasena(usuario, contrasena, contrasenaConfirmar);
    }

    @Override
    public List<String> obtenerCamposOpcionales(String idAlojamiento) throws Exception {
        return alojamientoServicio.obtenerCamposOpcionales(idAlojamiento);
    }

    @Override
    public List<Resena> obtenerResenasAlojamiento(String idAlojamiento) throws Exception {
        return resenaServicio.obtenerResenasAlojamiento(idAlojamiento);
    }

    @Override
    public Alojamiento obtenerAlojamientoPorId(String idAlojamiento) {
        return alojamientoServicio.obtenerAlojamientoPorId(idAlojamiento);
    }

    @Override
    public List<Reserva> obtenerReservasUsuario(String idUsuario) {
        return reservaServicio.obtenerReservasUsuario(idUsuario);
    }

    @Override
    public void promediarCalificaciones(String idAlojamiento) throws Exception {
        Alojamiento alojamiento = alojamientoServicio.obtenerAlojamientoPorId(idAlojamiento);
        float suma = 0;
        int contador = 0;
        List<Resena> resenas = resenaServicio.obtenerResenasAlojamiento(idAlojamiento);
        for (Resena resena : resenas) {
            suma += resena.getCalificacion();
            contador++;
        }
        alojamiento.setCalificacionPromedio(Math.round((suma / contador) * 10.0) / 10.0f);
    }

    @Override
    public List<Alojamiento> obtenerAlojamientosFiltrados(String nombreBuscado, TipoAlojamiento tipoSeleccionado, Ciudad ciudadSeleccionada, String precioMin, String precioMax, boolean ofertaAplicada) throws Exception {
        return alojamientoServicio.obtenerAlojamientosFiltrados(nombreBuscado, tipoSeleccionado, ciudadSeleccionada, precioMin, precioMax, ofertaAplicada, obtenerOfertas());
    }

    public List<Alojamiento> obtenerAlojamientos() {
        return alojamientoServicio.obtenerAlojamientos();
    }

    @Override
    public String generarInfoFactura(Reserva reserva) throws Exception {
        Usuario usuario = buscarUsuario(reserva.getIdUsuario());
        Factura factura = reserva.getFactura();
        Alojamiento alojamiento = alojamientoRepositorio.buscarAlojamiento(reserva.getIdAlojamiento());
        Habitacion habitacion = habitacionRepositorio.buscarHabitacion(reserva.getIdHabitacion());
        String numeroHabitacion = habitacion != null ? habitacion.getNumero() + "" : "No aplica";
        Double precioNoche = habitacion != null ? habitacion.getPrecioPorNoche() : alojamiento.getPrecioPorNoche();

        return """
                ============================================================
                                         FACTURA ELECTRÓNICA
                ============================================================
                
                Factura #: %s
                Fecha      : %s
                
                --------------------- DATOS DEL CLIENTE ---------------------
                
                Cliente    : %s %s
                Cédula     : %s
                Teléfono   : %s
                Email      : %s
                
                --------------------- DETALLES DE LA RESERVA ----------------
                
                Reserva ID        : %s
                Fecha Entrada     : %s
                Fecha Salida      : %s
                Total de días     : %d
                Número Huéspedes  : %d
                
                -------------------- DETALLES DEL ALOJAMIENTO ----------------
                
                Alojamiento       : %s
                Habitación:       :%s
                Ciudad            : %s
                Tipo              : %s
                Precio por noche  : $%,.2f
                Servicios         : %s
                
                ----------------------- RESUMEN DE PAGO ----------------------
                
                Subtotal base     : $%,.2f
                Costo Adicional   : $%,.2f
                Subtotal          : $%,.2f
                Descuento         : $%,.2f
                -------------------------------
                Total a Pagar     : $%,.2f
                
                Gracias por preferirnos. ¡Esperamos su pronta visita!
                
                ============================================================
                """.formatted(
                factura.getCodigo(),
                factura.getFecha(),
                usuario.getNombre(), usuario.getApellido(),
                usuario.getCedula(),
                usuario.getTelefono(),
                usuario.getEmail(),
                reserva.getId(),
                reserva.getFechaInicio(),
                reserva.getFechaFinal(),
                (int) ChronoUnit.DAYS.between(reserva.getFechaInicio(), reserva.getFechaFinal()),
                reserva.getNumeroHuespedes(),
                alojamiento.getNombre(),
                numeroHabitacion,
                alojamiento.getCiudad().toString(),
                alojamiento.getTipoAlojamiento().toString(),
                precioNoche,
                obtenerServiciosIncluidosString(alojamiento),
                factura.getSubtotal() - alojamiento.getCostoAdicional(),
                alojamiento.getCostoAdicional(),
                factura.getSubtotal(),
                factura.getSubtotal() - factura.getTotal(),
                factura.getTotal()
        );
    }

    @Override
    public String obtenerServiciosIncluidosString(Alojamiento alojamiento) throws Exception {
        List<String> servicios = alojamientoRepositorio.obtenerCamposOpcionales(alojamiento);
        return servicios.isEmpty() ? "Ninguno" : String.join(", ", servicios);
    }

    @Override
    public List<Habitacion> obtenerHabitacionesHotel(String idHotel) {
        return habitacionServicio.obtenerHabitacionesHotel(idHotel);
    }

    @Override
    public List<Oferta> obtenerOfertas() {
        return ofertaServicio.obtenerOfertas();
    }

    @Override
    public List<Integer> obtenerNumerosHabitaciones(String idHotel) {
        List<Integer> numerosHabitaciones = new ArrayList<>();
        for (Habitacion habitacion : obtenerHabitacionesHotel(idHotel)) {
            numerosHabitaciones.add(habitacion.getNumero());
        }
        return numerosHabitaciones;
    }

    @Override
    public void enviarNotificacion(String mensaje, String idCliente) {
        notificacionServicio.enviarNotificacion(mensaje, idCliente);
    }

    @Override
    public List<Notificacion> obtenerNotificaciones(String idCliente) {
        return notificacionServicio.obtenerNotificaciones(idCliente);
    }

    @Override
    public void marcarComoLeida(UUID id) {
        notificacionServicio.marcarComoLeida(id);
    }

    @Override
    public List<Usuario> obtenerUsuarios(){
        return usuarioServicio.obtenerUsuarios();
    }

    @Override
    public List<Oferta> obtenerOfertasAlojamiento(String idAlojamiento) {
        return ofertaServicio.obtenerOfertasAlojamiento(idAlojamiento);
    }
}
