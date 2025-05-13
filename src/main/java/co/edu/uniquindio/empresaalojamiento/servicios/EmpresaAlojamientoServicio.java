package co.edu.uniquindio.empresaalojamiento.servicios;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Alojamiento;
import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Billetera;
import co.edu.uniquindio.empresaalojamiento.modelo.enums.Rol;
import co.edu.uniquindio.empresaalojamiento.modelo.enums.TipoAlojamiento;
import co.edu.uniquindio.empresaalojamiento.repositorios.AlojamientoRepositorio;
import co.edu.uniquindio.empresaalojamiento.repositorios.UsuarioRepositorio;
import co.edu.uniquindio.empresaalojamiento.repositorios.interfaces.IAlojamientoRepositorio;
import co.edu.uniquindio.empresaalojamiento.servicios.interfaces.IEmpresaAlojamiento;

import java.time.LocalDate;
import java.util.List;

public class EmpresaAlojamientoServicio implements IEmpresaAlojamiento {

    private final UsuarioServicio usuarioServicio;
    private final AlojamientoRepositorio alojamientoRepositorio;
    private final AlojamientoServicio alojamientoServicio;
    private final UsuarioRepositorio usuarioRepositorio;


    public EmpresaAlojamientoServicio() {
        this.usuarioRepositorio = new UsuarioRepositorio();
        this.alojamientoRepositorio = new AlojamientoRepositorio();
        this.alojamientoServicio = new AlojamientoServicio(alojamientoRepositorio);
        this.usuarioServicio = new UsuarioServicio(usuarioRepositorio);
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
    public void registrarUsuario(String cedula, String nombre, String apellido, String telefono, String email, String contrasena) throws Exception {

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
        return List.of();
    }
}
