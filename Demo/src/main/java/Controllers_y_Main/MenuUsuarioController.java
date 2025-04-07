package Controllers_y_Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuUsuarioController {

    @FXML
    private TitledPane Consultas;

    @FXML
    private Button ManActividades;

    @FXML
    private Button ManClientes;

    @FXML
    private Button ManEntrenador;

    @FXML
    private Button ManEstados;

    @FXML
    private Button ManHorarios;

    @FXML
    private Button ManLocalizacion;

    @FXML
    private Button ManReserva;

    @FXML
    private Button ManReservaAc;

    @FXML
    private Button ManSalas;

    @FXML
    private TitledPane Mantenimiento;

    @FXML
    private TitledPane Movimientos;

    @FXML
    private TitledPane Procesos;

    @FXML
    void ConActividades(ActionEvent event) throws IOException {
        MenuAdmin menuAdmin = new MenuAdmin();
        menuAdmin.ConActividades(new Stage());
    }

    @FXML
    void ConClientes(ActionEvent event)
    {
        MenuAdmin menuAdmin = new MenuAdmin();
    }

    @FXML
    void ConClientesPendiente(ActionEvent event) {

    }

    @FXML
    void ConCobroCliente(ActionEvent event) {

    }

    @FXML
    void ConCobroFecha(ActionEvent event) {

    }

    @FXML
    void ConCuotaClientes(ActionEvent event) {

    }

    @FXML
    void ConCuotaFecha(ActionEvent event)
    {
    }

    @FXML
    void ConEntrenador(ActionEvent event) throws IOException {
        MenuAdmin menuAdmin = new MenuAdmin();
        menuAdmin.ConEntrenador(new Stage());
    }

    @FXML
    void ConHorarios(ActionEvent event) throws IOException {
        MenuAdmin menuAdmin = new MenuAdmin();
        menuAdmin.ConHorarios(new Stage());
    }

    @FXML
    void ConLocalización(ActionEvent event) throws IOException {
        MenuAdmin menuAdmin = new MenuAdmin();
        menuAdmin.ConLocalizacion(new Stage());
    }

    @FXML
    void ConSalas(ActionEvent event) throws IOException {
        MenuAdmin menuAdmin = new MenuAdmin();
        menuAdmin.ConSalas(new Stage());
    }

    @FXML
    void ConUsuarios(ActionEvent event) throws IOException {
        MenuAdmin menuAdmin = new MenuAdmin();
        menuAdmin.ConUsuario(new Stage());
    }

    @FXML
    void ManActividades(ActionEvent event) throws IOException {
        MenuAdmin menuAdmin = new MenuAdmin();
        menuAdmin.Actividades(new Stage());
    }

    @FXML
    void ManClientes(ActionEvent event) throws IOException {
        MenuAdmin menuAdmin = new MenuAdmin();
        menuAdmin.Clientes(new Stage());
    }

    @FXML
    void ManEntrenador(ActionEvent event) throws IOException {
        MenuAdmin menuAdmin = new MenuAdmin();
        menuAdmin.Entrenador(new Stage());
    }

    @FXML
    void ManEstados(ActionEvent event) throws IOException {
        MenuAdmin menuAdmin = new MenuAdmin();
        menuAdmin.EstadoR(new Stage());
    }

    @FXML
    void ManHorarios(ActionEvent event) throws IOException {
        MenuAdmin menuAdmin = new MenuAdmin();
        menuAdmin.Horarios(new Stage());
    }

    @FXML
    void ManLocalizacion(ActionEvent event) throws IOException {
        MenuAdmin menuAdmin = new MenuAdmin();
        menuAdmin.Localizacion(new Stage());
    }

    @FXML
    void ManReserva(ActionEvent event) throws IOException {
        MenuAdmin menuAdmin = new MenuAdmin();
        menuAdmin.ReservaAct(new Stage());
    }

    @FXML
    void ManReservaAc(ActionEvent event) throws IOException {
        MenuAdmin menuAdmin = new MenuAdmin();
        menuAdmin.Usuarios(new Stage());
    }

    @FXML
    void ManSalas(ActionEvent event) throws IOException {
        MenuAdmin menuAdmin = new MenuAdmin();
        menuAdmin.Salas(new Stage());
    }

}
