package Controllers_y_Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SalasController {

    @FXML
    private Button Limpiar;

    @FXML
    private Button Salir;

    @FXML
    private TextArea Descripcion;

    @FXML
    private Button Guardar;

    @FXML
    private TextField IdLocalizacion;


    @FXML
    private TextField Nombre;

    @FXML
    private TextField Notificador;

    @FXML
    private TextField idSala;

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
            idLocal = Integer.parseInt(idSala.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El ID debe ser un número entero", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int idLocalizacion;
        File archivoLocal = new File("Localización.txt");
        List<String> lineasL = new ArrayList<>();
        boolean existeLocalizacion = false;

        try {
            idLocalizacion = Integer.parseInt(IdLocalizacion.getText().trim());
                try (BufferedReader br = new BufferedReader(new FileReader(archivoLocal)))
                {
                    String linea;
                    while ((linea = br.readLine()) != null)
                    {
                        if (!linea.trim().isEmpty()) {
                            String[] partes = linea.split(":");
                            if (partes.length >= 1)
                            {
                                try {
                                    int idActual = Integer.parseInt(partes[0].trim());
                                    if (idActual == idLocalizacion)
                                    {
                                        existeLocalizacion = true;
                                    }
                                }catch (NumberFormatException ignored)
                                {
                                }
                            }
                        }
                    }
                    if (!existeLocalizacion)
                    {
                    JOptionPane.showMessageDialog(null, "La id de la localización no existe", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                    }
                } catch (FileNotFoundException e)
                {throw new RuntimeException(e);
                }catch (IOException e)
                {throw new RuntimeException(e);}
        }catch (NumberFormatException e)
        {throw new RuntimeException(e);}

        File archivo = new File("Salas.txt");
        List<String> lineas = new ArrayList<>();
        boolean existeLocal = false;

        try {
            if (archivo.exists()) {
                try (BufferedReader br = new BufferedReader(new FileReader(archivo)))
                {
                    String linea;
                    while ((linea = br.readLine()) != null) {
                        if (!linea.trim().isEmpty()) {
                            String[] partes = linea.split(":");
                            if (partes.length >= 2) {
                                try {
                                    int idActual = Integer.parseInt(partes[0].trim());

                                    if (idActual == idLocal)
                                    {
                                        lineas.add(crearLineaEntrenador());
                                        existeLocal = true;
                                        continue;
                                    }
                                } catch (NumberFormatException ignored) {
                                }
                            }
                            lineas.add(linea);
                        }
                    }
                }
            }

            if (!existeLocal)
            {
                lineas.add(crearLineaEntrenador());
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
                for (String linea : lineas) {
                    bw.write(linea);
                    bw.newLine();
                }
            }

            JOptionPane.showMessageDialog(null, "Datos guardados exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar los datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }


    }
    private boolean validarCamposCompletos()
    {
        return !IdLocalizacion.getText().trim().isEmpty() &&
                !idSala.getText().trim().isEmpty() &&
                !Nombre.getText().trim().isEmpty() &&
                !Descripcion.getText().trim().isEmpty();
    }

    private String crearLineaEntrenador()
    {
        return String.join(":",
                IdLocalizacion.getText().trim(),
                idSala.getText().trim(),
                Nombre.getText().trim(),
                Descripcion.getText().trim()
        );
    }

    @FXML
    void Limpiar(ActionEvent event)
    {
        Descripcion.setText("");
        IdLocalizacion.setText("");
        Nombre.setText("");
        Notificador.setText("");
        idSala.setText("");

        Nombre.setDisable(true);
        Descripcion.setDisable(true);
        IdLocalizacion.setDisable(true);
    }

    @FXML
    void Sala(ActionEvent event)
    {
        if (idSala.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Por favor ingrese un ID");
            return;
        }

        int idBuscado;
        try {
            idBuscado = Integer.parseInt(idSala.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El ID de la sala debe ser un número entero");
            return;
        }

        File arq = new File("Salas.txt");
        boolean encontrado = false;

        try {
            if (!arq.exists())
            {
                arq.createNewFile();
                return;
            }

            try (BufferedReader br = new BufferedReader(new FileReader(arq)))
            {
                String linea;
                while ((linea = br.readLine()) != null && !linea.isEmpty()) {
                    String[] partes = linea.split(":");

                    if (partes.length >= 4)
                    {
                        try {
                            int idSalaActual = Integer.parseInt(partes[1]);
                            String nombreActual = partes[2];
                            String descripcion = partes[3];
                            String idlocalizacionActual = partes[0];

                            if (idSalaActual == idBuscado)
                            {
                                Nombre.setText(nombreActual);
                                Descripcion.setText(descripcion);
                                IdLocalizacion.setText(idlocalizacionActual);
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
                Descripcion.setText("");
                IdLocalizacion.setText("");
                Nombre.setText("");
                Notificador.setText("Creando");
            }

            activar();

        } catch (IOException e)
        {
            JOptionPane.showMessageDialog(null, "Error al acceder al archivo: " + e.getMessage());
        }
    }
    @FXML
    void Salir(ActionEvent event)
    {
        Stage stageActual = (Stage) idSala.getScene().getWindow();
        stageActual.close();
    }
    public void activar()
    {
        Nombre.setDisable(false);
        Descripcion.setDisable(false);
        IdLocalizacion.setDisable(false);
    }

    private void limpiarCampos()
    {
        idSala.setText("");
        Notificador.setText("");
        Descripcion.setText("");
        IdLocalizacion.setText("");
        Nombre.setText("");
        Descripcion.setDisable(true);
        Nombre.setDisable(true);
        IdLocalizacion.setDisable(true);
    }
}
