package co.edu.uniquindio.empresaalojamiento.servicios;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Billetera;
import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Usuario;
import co.edu.uniquindio.empresaalojamiento.modelo.enums.Rol;
import co.edu.uniquindio.empresaalojamiento.repositorios.interfaces.IUsuarioRepositorio;
import co.edu.uniquindio.empresaalojamiento.utilidades.Utilidades;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UsuarioServicio {

    private IUsuarioRepositorio usuarioRepositorio;

    public UsuarioServicio(IUsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    public Usuario registarUsuario(String cedula, String nombre, String apellido, String telefono, String email, String contrasena)throws Exception{

        if (cedula == null || cedula.isEmpty()) throw new Exception("La cedula es obligatoria");
        if (nombre == null || nombre.isEmpty()) throw new Exception("El nombre es obligatorio ");
        if (apellido == null || apellido.isEmpty()) throw new Exception("El apellido es obligatorio ");
        if(telefono == null || telefono.isEmpty()) throw new Exception("El télefono es obligatorio");
        if (!telefono.trim().matches("^3\\d{9}$")) throw new Exception("El telefono debe tener 10 digitos e iniciar en 3");
        if (email == null || email.isEmpty()) throw new Exception("El email es obligatorio");
        if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) throw new Exception("Email inválido");
        if(contrasena == null || contrasena.isEmpty()) throw new Exception("El télefono es obligatorio");
        if(!contrasena.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&._\\-])[A-Za-z\\d@$!%*?&._\\-]{8,}$"))throw new Exception("La contrasena debe tener:\n" +
                "Al menos una letra minúscula\n" +
                "Al menos una letra mayúscula\n" +
                "Al menos un número\n" +
                "Al menos un carácter especial\n" +
                "Mínimo 8 caracteres\n" +
                "Solo permite letras, números y los símbolos");

        Billetera billetera= new Billetera(0, UUID.randomUUID().toString());
        Usuario usuario=new Usuario(cedula,nombre,apellido,telefono,email,contrasena,billetera, Rol.CLIENTE,false);

        usuarioRepositorio.agregarUsuario(usuario);
        return usuario;
    }

    public void actualizarUsuario(String cedulaAntigua, String cedulaNueva, String nombre, String apellido, String telefono, String email)throws Exception {

        if (cedulaNueva == null || cedulaNueva.isEmpty()) throw new Exception("La cedula es obligatoria");
        if (nombre == null || nombre.isEmpty()) throw new Exception("El nombre es obligatorio ");
        if (apellido == null || apellido.isEmpty()) throw new Exception("El apellido es obligatorio ");
        if (telefono == null || telefono.isEmpty()) throw new Exception("El télefono es obligatorio");
        if (!telefono.trim().matches("^3\\d{9}$"))
            throw new Exception("El telefono debe tener 10 digitos e iniciar en 3");
        if (email == null || email.isEmpty()) throw new Exception("El email es obligatorio");
        if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) throw new Exception("Email inválido");
        Usuario usuarioActualizar = usuarioRepositorio.buscarUsuario(cedulaAntigua);

        for (Usuario usuario : usuarioRepositorio.listarUsuarios()) {
            if (usuario.getCedula().equals(cedulaNueva) && usuario != usuarioActualizar) {
                throw new Exception("La cedula ya existe en la lista clientes");
            }
        }
        if (!usuarioActualizar.getActivo()) {
            throw new Exception("El usuario esta inactivo");
        }

        usuarioActualizar.setCedula(cedulaNueva);
        usuarioActualizar.setNombre(nombre);
        usuarioActualizar.setApellido(apellido);
        usuarioActualizar.setTelefono(telefono);
        usuarioActualizar.setEmail(email);

    }

    public void eliminarUsuario(String cedula)throws Exception{
        Usuario usuarioEliminar = usuarioRepositorio.buscarUsuario(cedula);
        if (usuarioEliminar == null) {
            throw new Exception("El usuario no existe");
        }
        usuarioRepositorio.eliminarUsuario(usuarioEliminar);

    }

    public void recargarBilletera(double monto, String cedulaUsuario)throws Exception{
        Usuario usuarioRecargar = usuarioRepositorio.buscarUsuario(cedulaUsuario);
        if (usuarioRecargar == null) {
            throw new Exception("El usuario no existe");
        }
        if (monto <= 0) {
            throw new Exception("El monto debe ser mayor a 0");
        }

        double saldoActual = usuarioRecargar.getBilletera().getSaldo();
        usuarioRecargar.getBilletera().setSaldo(saldoActual+monto);
    }

    public void activarUsuario(String cedula, String codigoIngresado)throws Exception{
        String codigoGenerado= Utilidades.generarCodigoVerificacion();
        Usuario usuarioActivar = usuarioRepositorio.buscarUsuario(cedula);
        Utilidades.enviarNotificacion(usuarioActivar.getEmail(),"Activación correo", "Su correo de verificacion es" + codigoGenerado);
        if(!codigoIngresado.equals(codigoGenerado)){
            throw new Exception("El codigo es incorrecto");
        }
        usuarioActivar.setActivo(true);
    }

}
