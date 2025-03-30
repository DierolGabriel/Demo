package org.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;

public class UsuarioControl {

    // Campos del formulario
    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private ComboBox<String> nivelAccesoCombo;
    @FXML
    private TextField nombreField;
    @FXML
    private TextField apellidoField;
    @FXML
    private TextField correoField;

    @FXML
    private Label statusLabel;

    // Método de inicialización
    @FXML
    public void initialize() {
        // Rellenar el ComboBox con las opciones de nivel de acceso
        nivelAccesoCombo.setItems(FXCollections.observableArrayList(
                "Administrador (1)",
                "Usuario (0)"
        ));
    }

    // Método para registrar al usuario
    @FXML
    public void onRegisterSubmit() {
        String login = loginField.getText();
        String password = passwordField.getText();
        String nivelAcceso = nivelAccesoCombo.getValue();
        String nombre = nombreField.getText();
        String apellido = apellidoField.getText();
        String correo = correoField.getText();

        // Validaciones simples
        if (login.isEmpty() || password.isEmpty() || nivelAcceso == null || nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty()) {
            statusLabel.setText("Todos los campos son obligatorios.");
        } else {
            // Aquí podrías agregar la lógica para guardar al usuario en la base de datos
            System.out.println("Login: " + login);
            System.out.println("Contraseña: " + password);
            System.out.println("Nivel de Acceso: " + nivelAcceso);
            System.out.println("Nombre: " + nombre);
            System.out.println("Apellido: " + apellido);
            System.out.println("Correo: " + correo);
            statusLabel.setText("Usuario registrado exitosamente.");
        }
    }
}
