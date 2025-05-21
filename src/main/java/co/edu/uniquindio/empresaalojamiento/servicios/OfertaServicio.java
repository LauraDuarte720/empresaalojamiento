package co.edu.uniquindio.empresaalojamiento.servicios;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Oferta;
import co.edu.uniquindio.empresaalojamiento.repositorios.OfertaRepositorio;
import co.edu.uniquindio.empresaalojamiento.repositorios.interfaces.IOfertaRepositorio;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class OfertaServicio {

    private IOfertaRepositorio ofertaRepositorio;

    public OfertaServicio(IOfertaRepositorio ofertaRepositorio) {
        this.ofertaRepositorio = ofertaRepositorio;
    }

    public Oferta crearOferta(LocalDate fechaInicio, LocalDate fechaFin, double ofertaValor, String idAlojamiento, String descripcion) throws Exception{

        if (fechaInicio==null||fechaFin==null){
            throw new Exception("Las fechas son obligatorias");
        }
        if(fechaFin.isBefore(fechaInicio)){
            throw new Exception("La fecha final debe ser posterior a la de inicio");
        }

        if(fechaInicio.isBefore(LocalDate.now())){
            throw new Exception("La fecha ingresada debe ser posterior a la de hoy");
        }
        if (ofertaValor<=0 || ofertaValor>100){
            throw new Exception("La oferta debe ser mayor a 0 y menor a 100");
        }
        if (idAlojamiento==null||idAlojamiento.isEmpty()){
            throw new Exception("El id del alojamiento es obligatorio");
        }
        if (descripcion==null||descripcion.isEmpty()){
            throw new Exception("La descripcion es obligatoria");
        }

        double ofertaConversion=ofertaValor/100;
        Oferta oferta = Oferta.builder()
                .id(UUID.randomUUID().toString())
                .fechaInicio(fechaInicio).fechaFinal(fechaFin)
                .valorPorcentaje(ofertaConversion)
                .idAlojamiento(idAlojamiento)
                .descripcion(descripcion)
                .build();

        getOfertaRepositorio().agregarOferta(oferta);
        System.out.println("id de la oferta: "+oferta.getId());
        System.out.println("id de alojamiento: "+oferta.getIdAlojamiento());
        return oferta;
    }

    public List<Oferta> obtenerOfertas() {
        return ofertaRepositorio.obtenerOfertas();
    }

    public void modificarOferta(String idOferta, LocalDate fechaInicio, LocalDate fechaFin, double ofertaValor, String idAlojamiento, String descripcion) throws Exception{
        Oferta ofertaModificar = ofertaRepositorio.buscarOferta(idOferta);
        if (ofertaModificar==null){
            throw new Exception("La oferta no existe");
        }
        if (fechaInicio==null||fechaFin==null){
            throw new Exception("Las fechas son obligatorias");
        }
        if (ofertaValor<=0 || ofertaValor>100){
            throw new Exception("La oferta debe ser mayor a 0 y menor a 100");
        }
        if (descripcion==null||descripcion.isEmpty()){
            throw new Exception("La descripcion es obligatoria");
        }

        ofertaModificar.setFechaInicio(fechaInicio);
        ofertaModificar.setFechaInicio(fechaFin);
        ofertaModificar.setValorPorcentaje(ofertaValor/100);
        ofertaModificar.setDescripcion(descripcion);

    }

    public void eliminarOferta(String idOferta) throws Exception{
        Oferta ofertaEliminar = ofertaRepositorio.buscarOferta(idOferta);
        if (ofertaEliminar==null){
            throw new Exception("La oferta no existe");
        }
        ofertaRepositorio.eliminarOferta(ofertaEliminar);
    }

    public List<Oferta> obtenerOfertasAlojamiento(String idAlojamiento) throws Exception{
        return ofertaRepositorio.obtenerOfertasAlojamiento(idAlojamiento);
    }
}
