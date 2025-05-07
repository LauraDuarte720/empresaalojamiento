package co.edu.uniquindio.empresaalojamiento.repositorios;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Hotel;

import java.util.ArrayList;
import java.util.List;

public class HotelRepositorio implements IHotelRepositorio {
    private final List<Hotel> hoteles;

    public HotelRepositorio() {
        this.hoteles = new ArrayList<Hotel>();
    }

    @Override
    public void agregarHotel(Hotel hotel) {

    }

    @Override
    public void eliminarHotel(Hotel hotel) {

    }

    @Override
    public Hotel buscarHotel(String idHotel) {
        return null;
    }


    @Override
    public List<Hotel> obtenerHoteles() {
        return List.of();
    }
}
