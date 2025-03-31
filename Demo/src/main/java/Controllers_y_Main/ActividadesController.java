package Controllers_y_Main;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.*;

public class ActividadesController {

    @FXML private TextField txtIdHorarioAct;
    @FXML private TextField txtIdActividad;
    @FXML private TextField txtDescripcion;
    @FXML private TextField txtIdLocalizacion;
    @FXML private TextField txtIdEntrenador;
    @FXML private Label lblEstado;
    @FXML private Button btnGuardar;

    private final String archivoHorarios = "horarios.txt";
    private final String archivoActividades = "actividades.txt";

    @FXML
    public void initialize() {
        txtIdHorarioAct.setOnKeyReleased(event -> verificarHorario());
    }

    private void verificarHorario() {
        String id = txtIdHorarioAct.getText().trim();
        if (id.isEmpty()) return;

        File archivo = new File(archivoHorarios);
        if (!archivo.exists()) return;

        boolean encontrado = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 5 && datos[0].equals(id)) {
                    txtIdActividad.setText(datos[1]);
                    txtDescripcion.setText(datos[2]);
                    txtIdLocalizacion.setText(datos[3]);
                    txtIdEntrenador.setText(datos[4]);
                    lblEstado.setText("Estado: Modificando");
                    encontrado = true;
                    break;
                }
            }
        } catch (IOException e) {
            mostrarAlerta("Error al leer horarios.txt");
        }

        if (!encontrado) {
            limpiarCampos(false);
            lblEstado.setText("Estado: Creando");
        }
    }

    private void limpiarCampos(boolean incluirId) {
        if (incluirId) txtIdHorarioAct.clear();
        txtIdActividad.clear();
        txtDescripcion.clear();
        txtIdLocalizacion.clear();
        txtIdEntrenador.clear();
    }

    private boolean actividadExiste(String idActividad) {
        File archivo = new File(archivoActividades);
        if (!archivo.exists()) return false;

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos[0].equals(idActividad)) {
                    return true;
                }
            }
        } catch (IOException e) {
            mostrarAlerta("Error al leer actividades.txt");
        }
        return false;
    }

    @FXML
    public void guardarHorario() {
        if (camposVacios()) {
            mostrarAlerta("Todos los campos son obligatorios.");
            return;
        }

        if (!actividadExiste(txtIdActividad.getText().trim())) {
            mostrarAlerta("El Id_Actividad no existe en el archivo de Actividades.");
            return;
        }

        String id = txtIdHorarioAct.getText().trim();
        String lineaNueva = String.join(",",
                id,
                txtIdActividad.getText().trim(),
                txtDescripcion.getText().trim(),
                txtIdLocalizacion.getText().trim(),
                txtIdEntrenador.getText().trim());

        File archivo = new File(archivoHorarios);
        boolean actualizado = false;
        StringBuilder nuevoContenido = new StringBuilder();

        if (archivo.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    String[] datos = linea.split(",");
                    if (datos[0].equals(id)) {
                        nuevoContenido.append(lineaNueva).append("\n");
                        actualizado = true;
                    } else {
                        nuevoContenido.append(linea).append("\n");
                    }
                }
            } catch (IOException e) {
                mostrarAlerta("Error al actualizar el archivo.");
                return;
            }
        }

        if (!actualizado) {
            nuevoContenido.append(lineaNueva).append("\n");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            writer.write(nuevoContenido.toString());
            mostrarAlerta("Horario guardado correctamente.");
            lblEstado.setText("Estado: Guardado");
        } catch (IOException e) {
            mostrarAlerta("Error al guardar el archivo.");
        }
    }

    private boolean camposVacios() {
        return txtIdHorarioAct.getText().isEmpty()
                || txtIdActividad.getText().isEmpty()
                || txtDescripcion.getText().isEmpty()
                || txtIdLocalizacion.getText().isEmpty()
                || txtIdEntrenador.getText().isEmpty();
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
