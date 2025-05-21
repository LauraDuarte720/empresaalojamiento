package co.edu.uniquindio.empresaalojamiento.servicios;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Habitacion;
import co.edu.uniquindio.empresaalojamiento.repositorios.interfaces.IHabitacionRepositorio;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class HabitacionServicio {

    private IHabitacionRepositorio habitacionRepositorio;

    public HabitacionServicio(IHabitacionRepositorio habitacionRepositorio) {
        this.habitacionRepositorio = habitacionRepositorio;
    }

    public Habitacion crearHabitacion(String numeroHabitacion, String precioPorNoche, String capacidadHuespedes, String descripcion, String idHotel, String rutaImagen) throws Exception {
        int numeroHabitacionI;
        double precioPorNocheD;
        int capacidadHuespedesI;

        try {
            numeroHabitacionI = Integer.parseInt(numeroHabitacion);
            precioPorNocheD = Double.parseDouble(precioPorNoche);
            capacidadHuespedesI = Integer.parseInt(capacidadHuespedes);
        } catch (NumberFormatException e) {
            numeroHabitacionI = -1;
            precioPorNocheD = -1;
            capacidadHuespedesI = -1;
        }
        if (numeroHabitacionI < 0) {
            throw new Exception("El número de habitación ingresado es inválido");
        }
        if (precioPorNocheD < 0) {
            throw new Exception("El precio por noche ingresado es inválido");
        }
        if (capacidadHuespedesI < 0) {
            throw new Exception("La capacidad de huespedes debe ser mayor a 0");
        }
        if (descripcion.isEmpty()) {
            throw new Exception("La descripcion no puede estar vacia");
        }
        Habitacion habitacion = Habitacion.builder()
                .id(UUID.randomUUID().toString())
                .numero(numeroHabitacionI)
                .precioPorNoche(precioPorNocheD)
                .capacidadHuespedes(capacidadHuespedesI)
                .descripcion(descripcion)
                .idHotel(idHotel)
                .rutaImagen(rutaImagen)
                .build();

        habitacionRepositorio.agregarHabitacion(habitacion);
        return habitacion;

    }

    public void eliminarHabitacion(String idHabitacion) throws Exception {
        Habitacion habitacionEliminar = habitacionRepositorio.buscarHabitacion(idHabitacion);
        if (habitacionEliminar == null) {
            throw new Exception("La habitacion no existe");
        }
        habitacionRepositorio.eliminarHabitacion(habitacionEliminar);
    }

    public List<Habitacion> obtenerHabitacionesHotel(String idHotel) {
        return habitacionRepositorio.obtenerHabitacionesHotel(idHotel);
    }

    public Habitacion buscarHabitacion(String id) {
        return habitacionRepositorio.buscarHabitacion(id);
    }
}
