package co.edu.uniquindio.empresaalojamiento.modelo.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@AllArgsConstructor
@Getter
public class Hotel {
    private List<Habitacion> habitaciones;

    public Hotel(){
        this.habitaciones = new ArrayList<Habitacion>();
    }
}
