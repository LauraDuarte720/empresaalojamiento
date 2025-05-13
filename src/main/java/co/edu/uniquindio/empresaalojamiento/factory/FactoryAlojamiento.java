package co.edu.uniquindio.empresaalojamiento.factory;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Alojamiento;
import co.edu.uniquindio.empresaalojamiento.modelo.enums.Ciudad;
import co.edu.uniquindio.empresaalojamiento.modelo.enums.TipoAlojamiento;

import java.util.UUID;

public class FactoryAlojamiento {

    public static Alojamiento crearAlojamiento(TipoAlojamiento tipoAlojamiento, String nombre, String descripcion, String ruta,
                                               double precioPorNoche, int capacidadMaximaHuespede, boolean piscina, boolean wifi, boolean desayuno, double costoAdicional, Ciudad ciudad) {

        Alojamiento alojamiento= null;
        switch (tipoAlojamiento) {
            case TipoAlojamiento.CASA, TipoAlojamiento.APARTAMENTOS -> alojamiento=Alojamiento.builder()
                    .id(UUID.randomUUID().toString())
                    .tipoAlojamiento(tipoAlojamiento)
                    .nombre(nombre)
                    .descripcion(descripcion)
                    .ruta(ruta)
                    .precioPorNoche(precioPorNoche)
                    .capacidadMaximaHuespedes(capacidadMaximaHuespede).piscina(piscina)
                    .wifi(wifi)
                    .desayuno(desayuno)
                    .costoAdicional(costoAdicional)
                    .ciudad(ciudad)
                    .build();
            case TipoAlojamiento.HOTEL -> alojamiento=Alojamiento.builder()
                    .id(UUID.randomUUID().toString())
                    .tipoAlojamiento(tipoAlojamiento)
                    .nombre(nombre)
                    .descripcion(descripcion)
                    .ruta(ruta)
                    .precioPorNoche(precioPorNoche)
                    .capacidadMaximaHuespedes(capacidadMaximaHuespede).piscina(piscina)
                    .wifi(wifi)
                    .desayuno(desayuno)
                    .ciudad(ciudad)
                    .build();
        }
        return alojamiento;
    }
}
