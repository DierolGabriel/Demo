package Controllers_y_Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuAdmin extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MenuAdmin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Menu");
        stage.setScene(scene);
        stage.show();
    }

    public void Entrenador(Stage stage) throws IOException
    {
    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Entrenador.fxml"));
    Scene scene = new Scene(fxmlLoader.load(), 477, 641);
        stage.setTitle("Entrenador");
        stage.setScene(scene);
        stage.show();
    }

    public void Usuarios(Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Usuarios.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 800);
        stage.setTitle("Usuarios");
        stage.setScene(scene);
        stage.show();
    }
    public void Localizacion(Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Localizacion.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 601, 212);
        stage.setTitle("Localizacion");
        stage.setScene(scene);
        stage.show();
    }
    public void Salas(Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Salas.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 245);
        stage.setTitle("Salas");
        stage.setScene(scene);
        stage.show();
    }
}