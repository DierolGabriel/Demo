package Controllers_y_Main;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.*;

public class ActividadesController {

    @FXML private TextField txtIdAct;
    @FXML private TextField txtNombreAct;
    @FXML private TextField txtDescripcionAct;
    @FXML private TextField txtIdLocalizacionAct;
    @FXML private TextField txtIdEntrenadorAct;
    @FXML private Label lblEstado;
    @FXML private Button Limpiar;
    @FXML private Button Salir;

    private final String archivoActividades ="Actividades.txt";
    private final String archivoLocalizaciones ="Localización.txt";
    private final String archivoEntrenadores = "Entrenadores.txt";

    @FXML
    public void initialize() {
        crearArchivoSiNoExiste(archivoActividades);
        crearArchivoSiNoExiste(archivoLocalizaciones);
        crearArchivoSiNoExiste(archivoEntrenadores);
        txtIdAct.setOnKeyReleased(event -> verificarActividad());
    }

    private void crearArchivoSiNoExiste(String rutaArchivo) {
        File archivo = new File(rutaArchivo);
        if (!archivo.exists()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo)))
            {
            } catch (IOException e) {
                mostrarAlerta("Error al crear " + rutaArchivo);
            }
        }
    }

    private void verificarActividad() {
        String id = txtIdAct.getText().trim();
        if (id.isEmpty()) return;

        File archivo = new File(archivoActividades);
        if (!archivo.exists()) return;

        boolean encontrado = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(":");
                if (datos.length >= 5 && datos[0].equals(id)) {
                    txtNombreAct.setText(datos[1]);
                    txtDescripcionAct.setText(datos[2]);
                    txtIdLocalizacionAct.setText(datos[3]);
                    txtIdEntrenadorAct.setText(datos[4]);
                    lblEstado.setText("Estado: Modificando");
                    Activar();
                    lblEstado.setStyle("-fx-text-fill: orange;");
                    encontrado = true;
                    break;
                }
            }
        } catch (IOException e) {
            mostrarAlerta("Error al leer Actividades.txt");
        }

        if (!encontrado) {
            limpiarCampos(false);
            lblEstado.setText("Estado: Creando");
            Activar();
            lblEstado.setStyle("-fx-text-fill: green;");
        }
    }

    private void limpiarCampos(boolean incluirId) {
        if (incluirId) txtIdAct.clear();
        txtNombreAct.clear();
        txtDescripcionAct.clear();
        txtIdLocalizacionAct.clear();
        txtIdEntrenadorAct.clear();
    }

    private boolean validarExistencia(String id, String archivoRuta) {
        File archivo = new File(archivoRuta);
        if (!archivo.exists()) return false;

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.split(":")[0].equals(id)) return true;
            }
        } catch (IOException e) {
            mostrarAlerta("Error al leer archivo: " + archivoRuta);
        }
        return false;
    }

    private void agregarNuevoRegistro(String archivoRuta, String id, String descripcion) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoRuta, true))) {
            writer.write(id + ":" + descripcion + "\n");
            System.out.println("Se agregó " + id + " en " + archivoRuta);
        } catch (IOException e) {
            mostrarAlerta("Error al escribir en " + archivoRuta);
        }
    }

    @FXML
    private void guardarActividad()
    {
        if (camposVacios())
        {
            mostrarAlerta("Todos los campos son obligatorios.");
            return;
        }

        String idLocalizacion = txtIdLocalizacionAct.getText().trim();
        String idEntrenador = txtIdEntrenadorAct.getText().trim();


        if (!validarExistencia(idLocalizacion, archivoLocalizaciones)) {
            agregarNuevoRegistro(archivoLocalizaciones, idLocalizacion, "Localización generada automáticamente");
        }

        if (!validarExistencia(idEntrenador, archivoEntrenadores)) {
            agregarNuevoRegistro(archivoEntrenadores, idEntrenador, "Entrenador sin nombre");
        }

        String id = txtIdAct.getText().trim();
        String lineaNueva = String.join(":",
                id,
                txtNombreAct.getText().trim(),
                txtDescripcionAct.getText().trim(),
                idLocalizacion,
                idEntrenador
        );

        File archivo = new File(archivoActividades);
        boolean actualizado = false;
        StringBuilder nuevoContenido = new StringBuilder();

        if (archivo.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    String[] datos = linea.split(":");
                    if (datos[0].equals(id)) {
                        nuevoContenido.append(lineaNueva).append("\n");
                        actualizado = true;
                    } else {
                        nuevoContenido.append(linea).append("\n");
                    }
                }
            } catch (IOException e) {
                mostrarAlerta("Error al actualizar archivo.");
                return;
            }
        }

        if (!actualizado) {
            nuevoContenido.append(lineaNueva).append("\n");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            writer.write(nuevoContenido.toString());
            mostrarAlerta("Actividad guardada correctamente.");
            lblEstado.setText("Estado: Guardado");
            lblEstado.setStyle("-fx-text-fill: blue;");
        } catch (IOException e) {
            mostrarAlerta("Error al guardar en Actividades.txt");
        }
    }

    private boolean camposVacios() {
        return txtIdAct.getText().isEmpty()
                || txtNombreAct.getText().isEmpty()
                || txtDescripcionAct.getText().isEmpty()
                || txtIdLocalizacionAct.getText().isEmpty()
                || txtIdEntrenadorAct.getText().isEmpty();
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Mensaje");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    void Salir(javafx.event.ActionEvent actionEvent)
    {
        Stage stageActual = (Stage) txtDescripcionAct.getScene().getWindow();
        stageActual.close();
    }

    @FXML
    void Limpiar(javafx.event.ActionEvent actionEvent)
    {
    txtIdAct.setText("");
    txtNombreAct.setText("");
    txtDescripcionAct.setText("");
    txtIdLocalizacionAct.setText("");
    txtIdEntrenadorAct.setText("");
    txtNombreAct.setDisable(true);
    txtDescripcionAct.setDisable(true);
    txtIdLocalizacionAct.setDisable(true);
    txtIdEntrenadorAct.setDisable(true);
    txtDescripcionAct.setDisable(true);
    lblEstado.setText("Estado:");
    }

    void Activar()
    {
        txtNombreAct.setDisable(false);
        txtDescripcionAct.setDisable(false);
        txtIdLocalizacionAct.setDisable(false);
        txtIdEntrenadorAct.setDisable(false);
        txtDescripcionAct.setDisable(false);
    }

}
