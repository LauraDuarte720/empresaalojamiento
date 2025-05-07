package co.edu.uniquindio.empresaalojamiento.repositorios;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Hotel;

import java.util.List;

public interface IHotelRepositorio {
    public void agregarHotel(Hotel hotel);

    public void eliminarHotel(Hotel hotel);

    public Hotel buscarHotel(String idHotel);

    public List<Hotel> obtenerHoteles();
}
