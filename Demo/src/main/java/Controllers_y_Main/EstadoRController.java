package Controllers_y_Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EstadoRController {

    @FXML
    private CheckBox Estado;

    @FXML
    private Button Guardar;

    @FXML
    private Button Limpiar;

    @FXML
    private TextField Notificador;

    @FXML
    private Button Salir;

    @FXML
    private TextField idReserva;

    @FXML
    void Guardar(ActionEvent event)
    {
        if (!validarCamposCompletos())
        {
            JOptionPane.showMessageDialog(null, "Todos los campos deben estar llenos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int idLocal;
        try {
            idLocal = Integer.parseInt(idReserva.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El ID debe ser un número entero", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        File archivo = new File("ReservasEstado.txt");
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
                                    boolean Disponible = Integer.parseInt(partes[1].trim()) == 1;

                                    if (idActual == idLocal)
                                    {
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
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }

            if (!existeLocal)
            {
                lineas.add(crearLineaEntrenador());
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo)))
            {
                for (String linea : lineas)
                {
                    bw.write(linea);
                    bw.newLine();
                }
            }

            JOptionPane.showMessageDialog(null, "Datos guardados exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar los datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//fin del guardar

    private boolean validarCamposCompletos()
    {
        return !idReserva.getText().trim().isEmpty();
    }

    private String crearLineaEntrenador()
    {
        String Disponible = "";
        if (Estado.isSelected())
        {
            Disponible = "1";
        }
        else
        {
            Disponible = "0";
        }
        return String.join(":",
                idReserva.getText().trim()
                , Disponible.trim()
        );
    }

    @FXML
    void Limpiar(ActionEvent event)
    {
        idReserva.setText("");
        Notificador.setText("");
        Estado.setDisable(true);
        Estado.setSelected(false);
    }//fin del limpiar

    @FXML
    void Reserva(ActionEvent event)
    {
        if (idReserva.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Por favor ingrese un ID");
            return;
        }

        int idBuscado;
        try {
            idBuscado = Integer.parseInt(idReserva.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El ID de la localización debe ser un número entero");
            return;
        }

        File arq = new File("ReservasEstado.txt");
        boolean encontrado = false;

        try {
            if (!arq.exists())
            {
                arq.createNewFile();
            }

            try (BufferedReader br = new BufferedReader(new FileReader(arq))) {
                String linea;
                boolean disponible = false;

                while ((linea = br.readLine()) != null && !linea.isEmpty())
                {
                    String[] partes = linea.split(":");

                    if (partes.length >= 2)
                    {
                        try {
                            int idActual = Integer.parseInt(partes[0].trim());
                            Boolean Disponible = Integer.parseInt(partes[1].trim()) == 1;

                            if (idActual == idBuscado)
                            {
                                Estado.setSelected(Disponible);
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
            }

            activar();

        } catch (IOException e)
        {
            JOptionPane.showMessageDialog(null, "Error al acceder al archivo: " + e.getMessage());
        }
    }

    private void limpiarCampos()
    {
        Notificador.setText("");
        Estado.setDisable(true);
        Estado.setSelected(false);
        idReserva.setText("");
    }


    @FXML
    void Salir(ActionEvent event)
    {
        Stage stageActual = (Stage) idReserva.getScene().getWindow();
        stageActual.close();
    }

    public void activar()
    {
        Estado.setDisable(false);
    }

}

