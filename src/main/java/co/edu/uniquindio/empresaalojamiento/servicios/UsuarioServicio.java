package co.edu.uniquindio.empresaalojamiento.servicios;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Billetera;
import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Usuario;
import co.edu.uniquindio.empresaalojamiento.modelo.enums.Rol;
import co.edu.uniquindio.empresaalojamiento.repositorios.interfaces.IUsuarioRepositorio;
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

    public Usuario actualizarUsuario(String cedulaAntigua, String cedulaNueva, String nombre, String apellido, String telefono, String email, String contrasena)throws Exception{

        if (cedulaNueva == null || cedulaNueva.isEmpty()) throw new Exception("La cedula es obligatoria");
        if (nombre == null || nombre.isEmpty()) throw new Exception("El nombre es obligatorio ");
        if (apellido == null || apellido.isEmpty()) throw new Exception("El apellido es obligatorio ");
        if(telefono == null || telefono.isEmpty()) throw new Exception("El télefono es obligatorio");
        if (!telefono.trim().matches("^3\\d{9}$")) throw new Exception("El telefono debe tener 10 digitos e iniciar en 3");
        if (email == null || email.isEmpty()) throw new Exception("El email es obligatorio");
        if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) throw new Exception("Email inválido");
        Usuario usuarioActualizar = usuarioRepositorio.buscarUsuario(cedulaAntigua);

        for (Usuario usuario : usuarioRepositorio.listarUsuarios ()) {
            if (usuario.getCedula().equals(telefonoNuevo) && contacto != contactoActulizar) {
                throw new Exception("El numero telefónico ya existe en la lista de contactos");
            }
        }

        contactoActulizar.setNombre(nombre);
        contactoActulizar.setApellido(apellido);
        contactoActulizar.setTelefono(telefonoNuevo);
        contactoActulizar.setCorreo(correo);
        contactoActulizar.setRutaFoto(ruta);
        contactoActulizar.setCumpleanos(cumpleanos);
    }
}
