package co.edu.uniquindio.empresaalojamiento.test;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.*;
import co.edu.uniquindio.empresaalojamiento.modelo.enums.Ciudad;
import co.edu.uniquindio.empresaalojamiento.modelo.enums.Rol;
import co.edu.uniquindio.empresaalojamiento.modelo.enums.TipoAlojamiento;
import co.edu.uniquindio.empresaalojamiento.servicios.EmpresaAlojamientoServicio;
import co.edu.uniquindio.empresaalojamiento.servicios.ReservaServicio;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class EmpresaAlojamientoServicioTest {

    private EmpresaAlojamientoServicio empresaServicio;
    private ReservaServicio reservaServicioPersonalizado;

    @Before
    public void setUp() {
        empresaServicio = new EmpresaAlojamientoServicio();

        empresaServicio.getUsuarioRepositorio().agregarUsuario(
                Usuario.builder()
                        .cedula("123")
                        .nombre("Pedro")
                        .apellido("Pérez")
                        .telefono("3001234567")
                        .email("pedro@gmail.com")
                        .contrasena("Password123*")
                        .rol(Rol.CLIENTE)
                        .activo(true)
                        .billetera(new Billetera(0, UUID.randomUUID().toString()))
                        .build());

        empresaServicio.getUsuarioRepositorio().agregarUsuario(
                Usuario.builder()
                        .cedula("456")
                        .nombre("Ana")
                        .apellido("Gómez")
                        .telefono("3009876543")
                        .email("ana@gmail.com")
                        .contrasena("Password456*")
                        .rol(Rol.CLIENTE)
                        .activo(false)
                        .billetera(new Billetera(0, UUID.randomUUID().toString()))
                        .build());

        Alojamiento alojamiento = Alojamiento.builder().
                tipoAlojamiento(TipoAlojamiento.CASA).
                nombre("Casa flores").
                descripcion("Casa muy bonita al norte de la ciudad").
                piscina(true).
                desayuno(false).
                wifi(true).
                costoAdicional(500).
                ciudad(Ciudad.ARAUCA).
                id("2").
                build();

        Factura factura1 = new Factura(500.0, 450.0, LocalDate.of(2023, 3, 2), "FAC001");
        Reserva reserva1 = Reserva.builder()
                .id("R001")
                .fechaInicio(LocalDate.of(2023, 3, 1))
                .fechaFinal(LocalDate.of(2023, 3, 5))
                .idAlojamiento(alojamiento.getId())
                .factura(factura1)
                .build();
        empresaServicio.getReservaRepositorio().agregarReserva(reserva1);

        // Reserva fuera del año (2022)
        Factura factura2 = new Factura(300.0, 270.0, LocalDate.of(2022, 12, 5), "FAC002");
        Reserva reserva2 = Reserva.builder()
                .id("R002")
                .fechaInicio(LocalDate.of(2022, 12, 1))
                .fechaFinal(LocalDate.of(2022, 12, 10))
                .idAlojamiento(alojamiento.getId())
                .factura(factura2)
                .build();
        empresaServicio.getReservaRepositorio().agregarReserva(reserva2);

        // Reserva cruzada (2022-2023)
        Factura factura3 = new Factura(200.0, 180.0, LocalDate.of(2023, 1, 1), "FAC003");
        Reserva reserva3 = Reserva.builder()
                .id("R003")
                .fechaInicio(LocalDate.of(2022, 12, 30))
                .fechaFinal(LocalDate.of(2023, 1, 2))
                .idAlojamiento(alojamiento.getId())
                .factura(factura3)
                .build();
        empresaServicio.getReservaRepositorio().agregarReserva(reserva3);

    }


    @Test
    public void testObtenerGananciasTotalesPorAnoYAlojamiento() {
        String idAlojamiento = "2";
        int anio = 2023;

        double resultado = empresaServicio.obtenerGananciasTotales(anio, idAlojamiento);

        // Factura1 (500) + Factura3 (200) = 700.0
        assertEquals(700.0, resultado, 0.01);
    }

    @Test
    public void testRegistrarUsuarioExitosamente() throws Exception {
        Usuario usuario = empresaServicio.registrarUsuario(
                "1234567890",
                "Juan",
                "Pérez",
                "3001234567",
                "juan.perez@example.com",
                "Contrasena123@"
        );

        assertNotNull(usuario);
        assertEquals("1234567890", usuario.getCedula());
        assertEquals("Juan", usuario.getNombre());
        assertEquals("Pérez", usuario.getApellido());
        assertEquals("3001234567", usuario.getTelefono());
        assertEquals("juan.perez@example.com", usuario.getEmail());
        assertEquals("Contrasena123@", usuario.getContrasena());
        assertEquals(Rol.CLIENTE, usuario.getRol());
        assertNotNull(usuario.getBilletera());
        assertEquals(0, usuario.getBilletera().getSaldo(), 0);
    }

    @Test
    public void testOrdenarAlojamientosPopularesCiudad() {

        empresaServicio.getAlojamientoRepositorio().agregarAlojamiento(Alojamiento.builder().id("1").nombre("Hotel Armenia 1").ciudad(Ciudad.ARMENIA).build());
        empresaServicio.getAlojamientoRepositorio().agregarAlojamiento(Alojamiento.builder().id("2").nombre("Hotel Pereira 1").ciudad(Ciudad.PEREIRA).build());
        empresaServicio.getAlojamientoRepositorio().agregarAlojamiento(Alojamiento.builder().id("3").nombre("Hotel Armenia 2").ciudad(Ciudad.ARMENIA).build());
        empresaServicio.getAlojamientoRepositorio().agregarAlojamiento(Alojamiento.builder().id("4").nombre("Hotel Armenia 3").ciudad(Ciudad.ARMENIA).build());

        Reserva reserva1 = Reserva.builder()
                .id("R001")
                .idAlojamiento("1")
                .build();
        Reserva reserva2 = Reserva.builder()
                .id("R002")
                .idAlojamiento("1")
                .build();
        Reserva reserva3 = Reserva.builder()
                .id("R003")
                .idAlojamiento("1")
                .build();
        Reserva reserva4 = Reserva.builder()
                .id("R001")
                .idAlojamiento("4")
                .build();
        Reserva reserva5 = Reserva.builder()
                .id("R002")
                .idAlojamiento("3")
                .build();
        Reserva reserva6 = Reserva.builder()
                .id("R003")
                .idAlojamiento("3")
                .build();
        Reserva reserva7 = Reserva.builder()
                .id("R003")
                .idAlojamiento("2")
                .build();

        empresaServicio.getReservaRepositorio().agregarReserva(reserva1);
        empresaServicio.getReservaRepositorio().agregarReserva(reserva2);
        empresaServicio.getReservaRepositorio().agregarReserva(reserva3);
        empresaServicio.getReservaRepositorio().agregarReserva(reserva4);
        empresaServicio.getReservaRepositorio().agregarReserva(reserva5);
        empresaServicio.getReservaRepositorio().agregarReserva(reserva6);
        empresaServicio.getReservaRepositorio().agregarReserva(reserva7);

        List<Alojamiento> resultado = empresaServicio.ordenarAlojamientosPopularesCiudad(Ciudad.ARMENIA);

        assertEquals(3, resultado.size()); //Se inlcuye el alojamiento que esta en el Set up
        assertEquals("Hotel Armenia 1", resultado.get(0).getNombre()); // 3 reservas
        assertEquals("Hotel Armenia 2", resultado.get(1).getNombre()); // 2 reservas
        assertEquals("Hotel Armenia 3", resultado.get(2).getNombre()); //1 reservas
    }

    //Test Inicia Sesion
    @Test
    public void testIniciarSesion_UsuarioValidoYActivo() throws Exception {

        Usuario usuario = empresaServicio.iniciarSesion("pedro@gmail.com", "Password123*");
        assertNotNull(usuario);
        assertEquals("Pedro", usuario.getNombre());
    }

    @Test
    public void testIniciarSesion_UsuarioInactivo() {
        IllegalAccessException thrown = assertThrows(IllegalAccessException.class, () -> {
            empresaServicio.iniciarSesion("ana@gmail.com", "Password456*");
        });
        assertEquals("Usuario inactivo", thrown.getMessage());
    }

    @Test
    public void testIniciarSesion_CredencialesIncorrectas() {
        Exception thrown = assertThrows(Exception.class, () -> {
            empresaServicio.iniciarSesion("noexiste@gmail.com", "pass123");
        });
        assertEquals("Usuario o contraseña incorrecta", thrown.getMessage());
    }




}
