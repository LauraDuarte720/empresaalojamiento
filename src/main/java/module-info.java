module co.edu.uniquindio.empresaalojamiento {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires org.simplejavamail.core;
    requires org.simplejavamail;


    opens co.edu.uniquindio.empresaalojamiento to javafx.fxml;
    exports co.edu.uniquindio.empresaalojamiento;


    opens co.edu.uniquindio.empresaalojamiento.controladores to javafx.fxml;
    exports co.edu.uniquindio.empresaalojamiento.controladores;
}