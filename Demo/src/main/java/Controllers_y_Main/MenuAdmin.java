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
        Scene scene = new Scene(fxmlLoader.load(), 981, 792);
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
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
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
        Scene scene = new Scene(fxmlLoader.load(), 600, 296);
        stage.setTitle("Salas");
        stage.setScene(scene);
        stage.show();
    }

    public void Actividades(Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Actividades.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 477, 641);
        stage.setTitle("Actividades");
        stage.setScene(scene);
        stage.show();
    }
    public void EstadoR(Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("EstadoR.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 464, 373);
        stage.setTitle("Estado");
        stage.setScene(scene);
        stage.show();
    }
    public void Clientes(Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Clientes.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 464, 373);
        stage.setTitle("Estado");
        stage.setScene(scene);
        stage.show();
    }

    public void ReservaAct(Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Reserva Actividades.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 464, 373);
        stage.setTitle("Horarios");
        stage.setScene(scene);
        stage.show();
    }

    public void Reserva(Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Reserva.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 464, 373);
        stage.setTitle("Reserva");
        stage.setScene(scene);
        stage.show();
    }

    public void Horarios(Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Horarios_Actividades.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 464, 373);
        stage.setTitle("Horarios");
        stage.setScene(scene);
        stage.show();
    }


}