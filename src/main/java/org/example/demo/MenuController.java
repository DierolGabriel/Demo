package org.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MenuController {

    @FXML
    private MenuBar Barra;

    @FXML
    private Menu Consultas;

    @FXML
    private Pane MainMenu;

    @FXML
    private MenuItem ManEntrenador;

    @FXML
    private MenuItem ManUsuarios;

    @FXML
    private Menu Mantenimiento;

    @FXML
    private Menu Movimientos;

    @FXML
    private Menu Procesos;

    @FXML
    void ManUsuarios(ActionEvent event) {

    }

    @FXML
    void ManEntrenador(ActionEvent event)
    {
        MenuAdmin menuAdmin = new MenuAdmin();
        menuAdmin.Entrenador(new Stage());

    }

}
