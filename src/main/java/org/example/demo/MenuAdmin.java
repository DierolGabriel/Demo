package org.example.demo;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

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
    Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Entrenador");
        stage.setScene(scene);
        stage.show();
    }
}