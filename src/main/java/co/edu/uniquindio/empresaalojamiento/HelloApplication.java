package co.edu.uniquindio.empresaalojamiento;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        //Font vollkorn = Font.loadFont(getClass().getResourceAsStream("fuente/Vollkorn.ttf"), 14);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("menuCliente.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}