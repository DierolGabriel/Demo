package Controllers_y_Main;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.awt.event.ActionEvent;
import java.io.*;
import java.time.LocalDate;

public class ReservaActController {

    @FXML private TextField txtIdReservaActividad, txtIdEstadoReservaAct, txtIdClienteResAct, txtIdActividad, txtIdResHorAct;
    @FXML private DatePicker fechaReservaPicker, fechaBajaPicker;

    private final String archivoReservas ="Reservas Actividades.txt";
    private final String archivoEstados ="EstadoReserva.txt";
    private final String archivoClientes ="Cliente.txt";
    private final String archivoActividades ="Actividades.txt";
    private final String archivoHorarios ="Horarios.txt";

    @FXML
    void initialize()
    {
        crearArchivoSiNoExiste(archivoReservas);
        txtIdReservaActividad.setOnKeyReleased(e -> verificarReserva());
    }

    private void crearArchivoSiNoExiste(String ruta) {
        File file = new File(ruta);
        if (!file.exists()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                System.out.println("Archivo creado: " + ruta);
            } catch (IOException e) {
                mostrarAlerta("Error creando archivo: " + ruta);
            }
        }
    }

    private void verificarReserva() {
        String id = txtIdReservaActividad.getText().trim();
        if (id.isEmpty()) return;

        boolean encontrado = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(archivoReservas))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(":");
                if (datos[0].equals(id)) {
                    fechaReservaPicker.setValue(LocalDate.parse(datos[1]));
                    fechaBajaPicker.setValue(datos[2].equals("null") ? null : LocalDate.parse(datos[2]));
                    txtIdEstadoReservaAct.setText(datos[3]);
                    txtIdClienteResAct.setText(datos[4]);
                    txtIdActividad.setText(datos[5]);
                    txtIdResHorAct.setText(datos[6]);
                    mostrarAlerta("Estado: Modificando");
                    encontrado = true;
                    break;
                }
            }
        } catch (IOException e) {
            mostrarAlerta("Error leyendo Reservas Actividades.txt");
        }

        if (!encontrado) {
            limpiarCampos(false);
            mostrarAlerta("Estado: Creando");
        }
    }

    @FXML
    private void guardarReserva() {
        if (camposVacios()) {
            mostrarAlerta("Todos los campos son obligatorios.");
            return;
        }

        if (!validarExistencia(txtIdEstadoReservaAct.getText(), archivoEstados)) {
            mostrarAlerta("El Id_Est_Reserva_Act no existe.");
            return;
        }

        if (!validarExistencia(txtIdClienteResAct.getText(), archivoClientes)) {
            mostrarAlerta("El Id_Cliente_Res_Act no existe.");
            return;
        }

        if (!validarExistencia(txtIdActividad.getText(), archivoActividades)) {
            mostrarAlerta("El Id_Actividad no existe.");
            return;
        }

        if (!validarExistencia(txtIdResHorAct.getText(), archivoHorarios)) {
            mostrarAlerta("El Id_Res_Hor_Act no existe.");
            return;
        }

        String id = txtIdReservaActividad.getText().trim();
        String linea = String.join(":",
                id,
                fechaReservaPicker.getValue().toString(),
                fechaBajaPicker.getValue() != null ? fechaBajaPicker.getValue().toString() : "null",
                txtIdEstadoReservaAct.getText().trim(),
                txtIdClienteResAct.getText().trim(),
                txtIdActividad.getText().trim(),
                txtIdResHorAct.getText().trim()
        );

        guardarLinea(linea, id);
    }

    private void guardarLinea(String nuevaLinea, String id) {
        File archivo = new File(archivoReservas);
        boolean actualizado = false;
        StringBuilder contenido = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(":");
                if (datos[0].equals(id)) {
                    contenido.append(nuevaLinea).append("\n");
                    actualizado = true;
                } else {
                    contenido.append(linea).append("\n");
                }
            }
        } catch (IOException e) {
            mostrarAlerta("Error leyendo archivo.");
            return;
        }

        if (!actualizado) {
            contenido.append(nuevaLinea).append("\n");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            writer.write(contenido.toString());
            mostrarAlerta(actualizado ? "Reserva actualizada correctamente." : "Reserva guardada exitosamente.");
        } catch (IOException e) {
            mostrarAlerta("Error al guardar reserva.");
        }
    }

    private boolean validarExistencia(String id, String archivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.split(",")[0].equals(id)) return true;
            }
        } catch (IOException e) {
            mostrarAlerta("Error leyendo archivo: " + archivo);
        }
        return false;
    }

    private boolean camposVacios() {
        return txtIdReservaActividad.getText().isEmpty()
                || fechaReservaPicker.getValue() == null
                || txtIdEstadoReservaAct.getText().isEmpty()
                || txtIdClienteResAct.getText().isEmpty()
                || txtIdActividad.getText().isEmpty()
                || txtIdResHorAct.getText().isEmpty();
    }

    private void limpiarCampos(boolean incluirId) {
        if (incluirId) txtIdReservaActividad.clear();
        fechaReservaPicker.setValue(null);
        fechaBajaPicker.setValue(null);
        txtIdEstadoReservaAct.clear();
        txtIdClienteResAct.clear();
        txtIdActividad.clear();
        txtIdResHorAct.clear();
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Reserva Actividades");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
