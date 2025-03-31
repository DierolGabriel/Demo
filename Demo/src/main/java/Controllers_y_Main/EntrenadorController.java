package Controllers_y_Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EntrenadorController {

    @FXML
    private TextField ApellidoEnt;

    @FXML
    private TextField CorreoEnt;

    @FXML
    private Button Guargar;

    @FXML
    private TextField IDEnt;

    @FXML
    private Button Limpiar;

    @FXML
    private TextField NombreEnt;

    @FXML
    private TextField Notificador;

    @FXML
    private Button Salir;

    @FXML
    private TextField TelefonoEnt;

    @FXML
    void Guardar(ActionEvent event)
    {

        if (!validarCamposCompletos())
        {
            JOptionPane.showMessageDialog(null, "Todos los campos deben estar llenos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int idEntrenador;
        try {
            idEntrenador = Integer.parseInt(IDEnt.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El ID debe ser un número entero", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        File archivo = new File("Entrenadores.txt");
        List<String> lineas = new ArrayList<>();
        boolean existeEntrenador = false;

        try {
            if (archivo.exists()) {
                try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                    String linea;
                    while ((linea = br.readLine()) != null) {
                        if (!linea.trim().isEmpty()) {
                            String[] partes = linea.split(":");
                            if (partes.length >= 1) {
                                try {
                                    int idActual = Integer.parseInt(partes[0].trim());
                                    if (idActual == idEntrenador) {
                                        lineas.add(crearLineaEntrenador());
                                        existeEntrenador = true;
                                        continue;
                                    }
                                } catch (NumberFormatException e) {
                                }
                            }
                            lineas.add(linea);
                        }
                    }
                }
            }

            if (!existeEntrenador) {
                lineas.add(crearLineaEntrenador());
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
                for (String linea : lineas) {
                    bw.write(linea);
                    bw.newLine();
                }
            }

            JOptionPane.showMessageDialog(null, "Datos guardados exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            limpiarFormulario();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar los datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//fin del guardar

    private boolean validarCamposCompletos() {
        return !IDEnt.getText().trim().isEmpty() &&
                !NombreEnt.getText().trim().isEmpty() &&
                !ApellidoEnt.getText().trim().isEmpty() &&
                !TelefonoEnt.getText().trim().isEmpty() &&
                !CorreoEnt.getText().trim().isEmpty();
    }

    private String crearLineaEntrenador() {
        return String.join(":",
                IDEnt.getText().trim(),
                NombreEnt.getText().trim(),
                ApellidoEnt.getText().trim(),
                TelefonoEnt.getText().trim(),
                CorreoEnt.getText().trim()
        );
    }

    private void limpiarFormulario() {
        IDEnt.setText("");
        NombreEnt.setText("");
        ApellidoEnt.setText("");
        TelefonoEnt.setText("");
        CorreoEnt.setText("");
        Notificador.setText("");
        desactivar();
    }
    @FXML
    void Id(ActionEvent event)
    {
        Stage stageActual = (Stage) IDEnt.getScene().getWindow();
        if (IDEnt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor ingrese un ID");
            stageActual.toFront();
            return;
        }

        int idBuscado;
        try {
            idBuscado = Integer.parseInt(IDEnt.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El ID del entrenador debe ser un número entero");
            stageActual.toFront();
            return;
        }

        File arq = new File("Entrenadores.txt");
        boolean encontrado = false;

        try {
            if (!arq.exists()) {
                arq.createNewFile();
                activar();
                return;
            }

            try (BufferedReader br = new BufferedReader(new FileReader(arq))) {
                String linea;
                while ((linea = br.readLine()) != null && !linea.isEmpty()) {
                    String[] partes = linea.split(":");

                    if (partes.length >= 5) {
                        try {
                            int idActual = Integer.parseInt(partes[0].trim());

                            if (idActual == idBuscado) {
                                NombreEnt.setText(partes[1].trim());
                                ApellidoEnt.setText(partes[2].trim());
                                TelefonoEnt.setText(partes[3].trim());
                                CorreoEnt.setText(partes[4].trim());
                                Notificador.setText("Modificando");
                                encontrado = true;
                                break;
                            }
                        } catch (NumberFormatException e)
                        {
                            continue;
                        }
                    }
                }
            }
            if (!encontrado )
            {
                limpiarCampos();
                Notificador.setText("Creando");
            }

            activar();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al acceder al archivo: " + e.getMessage());
            stageActual.toFront();
        }
    }

    private void limpiarCampos() {
        NombreEnt.setText("");
        ApellidoEnt.setText("");
        TelefonoEnt.setText("");
        CorreoEnt.setText("");
        Notificador.setText("");
    }

    @FXML
    void Limpiar(ActionEvent event)
    {
        IDEnt.setText("");
        ApellidoEnt.setText("");
        CorreoEnt.setText("");
        NombreEnt.setText("");
        TelefonoEnt.setText("");
        Notificador.setText("");

        ApellidoEnt.setEditable(false);
        CorreoEnt.setEditable(false);
        NombreEnt.setEditable(false);
        TelefonoEnt.setEditable(false);

        ApellidoEnt.setDisable(true);
        CorreoEnt.setDisable(true);
        NombreEnt.setDisable(true);
        TelefonoEnt.setDisable(true);
    }

    @FXML
    void Salir(ActionEvent event)
    {
        Stage stageActual = (Stage) IDEnt.getScene().getWindow();
        stageActual.close();
    }

    public void activar()
    {
        ApellidoEnt.setEditable(true);
        CorreoEnt.setEditable(true);
        NombreEnt.setEditable(true);
        TelefonoEnt.setEditable(true);

        ApellidoEnt.setDisable(false);
        CorreoEnt.setDisable(false);
        NombreEnt.setDisable(false);
        TelefonoEnt.setDisable(false);
    }

    public void desactivar()
    {
        ApellidoEnt.setEditable(false);
        CorreoEnt.setEditable(false);
        NombreEnt.setEditable(false);
        TelefonoEnt.setEditable(false);

        ApellidoEnt.setDisable(true);
        CorreoEnt.setDisable(true);
        NombreEnt.setDisable(true);
        TelefonoEnt.setDisable(true);
    }

}
