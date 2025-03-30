package Controllers_y_Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    @FXML
    private MenuBar Barra;

    @FXML
    private Menu Consultas;

    @FXML
    private Pane MainMenu;

    @FXML
    private MenuItem ManActividades;

    @FXML
    private MenuItem ManClientes;

    @FXML
    private MenuItem ManEntrenador;

    @FXML
    private MenuItem ManEstados;

    @FXML
    private MenuItem ManHorarios;

    @FXML
    private MenuItem ManLocalizacion;

    @FXML
    private MenuItem ManReserva;

    @FXML
    private MenuItem ManReservaAct;

    @FXML
    private MenuItem ManSalas;

    @FXML
    private MenuItem ManUsuarios;

    @FXML
    private Menu Mantenimiento;

    @FXML
    private Menu Movimientos;

    @FXML
    private Menu Procesos;

    @FXML
    void ManUsuarios(ActionEvent event) throws IOException
    {
        MenuAdmin menuAdmin= new MenuAdmin();
        menuAdmin.Usuarios(new Stage());
    }

    @FXML
    void ManEntrenador(ActionEvent event) throws IOException
    {
        MenuAdmin menuAdmin = new MenuAdmin();
        menuAdmin.Entrenador(new Stage());
    }

    @FXML
    void ManActividades(ActionEvent event)
    {
        MenuAdmin menuAdmin = new MenuAdmin();

    }

    @FXML
    void ManClientes(ActionEvent event)
    {
        MenuAdmin menuAdmin = new MenuAdmin();

    }
    @FXML
    void ManEstados(ActionEvent event)
    {
        MenuAdmin menuAdmin = new MenuAdmin();

    }

    @FXML
    void ManHorarios(ActionEvent event)
    {
        MenuAdmin menuAdmin = new MenuAdmin();


    }

    @FXML
    void ManLocalizacion(ActionEvent event) throws IOException {
        MenuAdmin menuAdmin = new MenuAdmin();
        menuAdmin.Localizacion(new Stage());
    }

    @FXML
    void ManReserva(ActionEvent event)
    {
    }

    @FXML
    void ManSalas(ActionEvent event) throws IOException {
        MenuAdmin menuAdmin = new MenuAdmin();
        menuAdmin.Salas(new Stage());

    }
    @FXML
    void ManReservaAct(ActionEvent event) {

    }

}
