package co.edu.uniquindio.empresaalojamiento.servicios;

import co.edu.uniquindio.empresaalojamiento.modelo.entidades.Usuario;
import co.edu.uniquindio.empresaalojamiento.repositorios.interfaces.IUsuarioRepositorio;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioServicio {

    private IUsuarioRepositorio usuarioRepositorio;

    public UsuarioServicio(IUsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }
}
