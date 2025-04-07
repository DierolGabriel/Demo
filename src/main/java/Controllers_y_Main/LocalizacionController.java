package Controllers_y_Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LocalizacionController {

    @FXML
    private Button Guardar;

    @FXML
    private Button Limpiar;

    @FXML
    private TextField Localizacion;

    @FXML
    private TextField Notificador;

    @FXML
    private Button Salir;

    @FXML
    private TextField Tipo;

    @FXML
    void Guardar(ActionEvent event) {
        if (!validarCamposCompletos()) {
            mostrarAlerta("Todos los campos deben estar llenos");
            return;
        }

        int idLocal;
        try {
            idLocal = Integer.parseInt(Localizacion.getText().trim());
        } catch (NumberFormatException e) {
            mostrarAlerta("El id debe ser un numero entero");
            return;
        }

        File archivo = new File("Localización.txt");
        List<String> lineas = new ArrayList<>();
        boolean existeLocal = false;

        try {
            if (archivo.exists()) {
                try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                    String linea;
                    while ((linea = br.readLine()) != null) {
                        if (!linea.trim().isEmpty()) {
                            String[] partes = linea.split(":");
                            if (partes.length >= 2) {
                                try {
                                    int idActual = Integer.parseInt(partes[0].trim());

                                    if (idActual == idLocal) {
                                        lineas.add(crearLineaEntrenador());
                                        existeLocal = true;
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

            if (!existeLocal) {
                lineas.add(crearLineaEntrenador());
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
                for (String linea : lineas) {
                    bw.write(linea);
                    bw.newLine();
                }
            }

            mostrarAlerta("Datos guardados exitosamente");
            limpiarCampos();

        } catch (IOException e) {
            mostrarAlerta("Error al guardar los datos: ");
        }


    }//fin del guardar

    private boolean validarCamposCompletos()
    {
        return !Localizacion.getText().trim().isEmpty() &&
                !Tipo.getText().trim().isEmpty();
    }

    private String crearLineaEntrenador()
    {
        return String.join(":",
                Localizacion.getText().trim(),
                Tipo.getText().trim()
        );
    }

    @FXML
    void Limpiar(ActionEvent event)
    {
        Localizacion.setText("");
        Notificador.setText("");
        Tipo.setText("");
        Tipo.setDisable(true);
    }//fin del limpiar

    @FXML
    void Localizacion(ActionEvent event)
    {
        if (Localizacion.getText().isEmpty())
        {
            mostrarAlerta("Debe ingresar el la localizacion");
            return;
        }

        int idBuscado;
        try {
            idBuscado = Integer.parseInt(Localizacion.getText().trim());
        } catch (NumberFormatException e) {
            mostrarAlerta("El id debe ser un entero");
            return;
        }

        File arq = new File("Localización.txt");
        boolean encontrado = false;

        try {
            if (!arq.exists())
            {
                arq.createNewFile();
                activar();
                return;
            }

            try (BufferedReader br = new BufferedReader(new FileReader(arq))) {
                String linea;
                while ((linea = br.readLine()) != null && !linea.isEmpty()) {
                    String[] partes = linea.split(":");

                    if (partes.length >= 2) {
                        try {
                            int idActual = Integer.parseInt(partes[0].trim());
                            String Tipoarc = partes[1].trim();

                            if (idActual == idBuscado)
                            {
                               Tipo.setText(Tipoarc);
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
                Notificador.setText("Creando");
                Tipo.setText("");

            }

            activar();

        } catch (IOException e)
        {
            mostrarAlerta("Error al guardar los datos: " + e.getMessage());
        }
    }// Localización

    private void limpiarCampos()
    {
        Tipo.setText("");
        Notificador.setText("");
        Tipo.setDisable(true);
        Localizacion.setText("");
    }


    @FXML
    void Salir(ActionEvent event)
    {
        Stage stageActual = (Stage) Localizacion.getScene().getWindow();
        stageActual.close();
    }

    public void activar()
    {
        Tipo.setDisable(false);
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}
