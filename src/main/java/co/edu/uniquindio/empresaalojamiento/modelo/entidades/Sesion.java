package co.edu.uniquindio.empresaalojamiento.modelo.entidades;

import lombok.Getter;
import lombok.Setter;

public class Sesion {



        public static Sesion INSTANCIASESION;

        @Getter
        @Setter
        private Usuario usuario;


        private Sesion() {
        }


        public static Sesion getInstancia() {
            if (INSTANCIASESION == null) {
                INSTANCIASESION = new Sesion();
            }
            return INSTANCIASESION;
        }


        public void cerrarSesion() {
            usuario = null;
        }


}
