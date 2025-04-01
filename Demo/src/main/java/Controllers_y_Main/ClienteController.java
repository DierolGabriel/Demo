package Controllers_y_Main;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javax.swing.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ClienteController { //Fecha nececita Try Cath
    @FXML private RadioButton Activo;
    @FXML private RadioButton Invitado;
    @FXML private TextField Notificado;
    @FXML private RadioButton Pasivo;
    @FXML private Button Salir;
    @FXML private RadioButton SocioActivo;
    @FXML private DatePicker fechaIngresoPicker;
    @FXML private DatePicker fechaNacPicker;
    @FXML private TextField txtApellidoMat;
    @FXML private TextField txtApellidoPat;
    @FXML private TextField txtBalance;
    @FXML private TextField txtCelular;
    @FXML private TextField txtCorreo;
    @FXML private TextField txtDireccion;
    @FXML private TextField txtIdCliente;
    @FXML private TextField txtNombreCliente;
    @FXML private TextField txtTelefono;
    @FXML private TextField txtValorCuota;

    private static final String ARCHIVO_CLIENTES = "Clientes.txt";
    private boolean modificando = false;

    @FXML
    public void initialize() {
        txtIdCliente.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                validarIdCliente(newValue);
            }
        });
    }

    private void validarIdCliente(String idCliente) {
        File archivo = new File(ARCHIVO_CLIENTES);

        if (!archivo.exists()) {
            Notificado.setText("Creando");
            activarCampos();
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            boolean encontrado = false;

            while ((linea = br.readLine()) != null && !linea.isEmpty()) {
                String[] partes = linea.split(":");
                if (partes.length > 0 && partes[0].equals(idCliente)) {
                    cargarDatosCliente(partes);
                    encontrado = true;
                    modificando = true;
                    Notificado.setText("Modificando");
                    break;
                }
            }

            if (!encontrado) {
                Notificado.setText("Creando");
                limpiarCampos();
                activarCampos();
                modificando = false;
                fechaIngresoPicker.setValue(LocalDate.now());
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al leer el archivo de clientes: " + e.getMessage());
        }
    }

    private void cargarDatosCliente(String[] datos) {
        if (datos.length >= 15) {
            txtNombreCliente.setText(datos[1]);
            txtApellidoPat.setText(datos[2]);
            txtApellidoMat.setText(datos[3]);
            txtDireccion.setText(datos[4]);
            fechaNacPicker.setValue(LocalDate.parse(datos[5]));
            txtTelefono.setText(datos[6]);
            txtCelular.setText(datos[7]);
            fechaIngresoPicker.setValue(LocalDate.parse(datos[8]));
            txtCorreo.setText(datos[9]);
            txtBalance.setText(datos[10]);
            txtValorCuota.setText(datos[11]);

            if (datos[12].equals("Activo")) {
                Activo.setSelected(true);
            } else {
                Pasivo.setSelected(true);
            }

            if (datos[13].equals("Socio Activo")) {
                SocioActivo.setSelected(true);
            } else {
                Invitado.setSelected(true);
            }
        }
    }

    @FXML
    private void guardarCliente() {
        if (!validarCampos()) {
            return;
        }

        if (Invitado.isSelected() && Activo.isSelected()) {
            JOptionPane.showMessageDialog(null, "Un invitado no puede tener status Activo", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String idCliente = txtIdCliente.getText().trim();
        String nombre = txtNombreCliente.getText().trim();
        String apellidoPat = txtApellidoPat.getText().trim();
        String apellidoMat = txtApellidoMat.getText().trim();
        String direccion = txtDireccion.getText().trim();
        String fechaNac = fechaNacPicker.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE);
        String telefono = txtTelefono.getText().trim();
        String celular = txtCelular.getText().trim();
        String fechaIngreso = fechaIngresoPicker.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE);
        String correo = txtCorreo.getText().trim();
        String balance = txtBalance.getText().trim();
        String valorCuota = txtValorCuota.getText().trim();
        String status = Activo.isSelected() ? "Activo" : "Pasivo";
        String tipoCliente = SocioActivo.isSelected() ? "Socio Activo" : "Invitado";

        String linea = String.join(":",
                idCliente, nombre, apellidoPat, apellidoMat, direccion, fechaNac,
                telefono, celular, fechaIngreso, correo, balance, valorCuota,
                status, tipoCliente
        );

        File archivo = new File(ARCHIVO_CLIENTES);
        List<String> lineas = new ArrayList<>();
        boolean existe = false;

        try {
            if (archivo.exists()) {
                try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                    String lineaActual;
                    while ((lineaActual = br.readLine()) != null) {
                        if (!lineaActual.trim().isEmpty()) {
                            String[] partes = lineaActual.split(":");
                            if (partes.length > 0 && partes[0].equals(idCliente)) {
                                lineas.add(linea);
                                existe = true;
                            } else {
                                lineas.add(lineaActual);
                            }
                        }
                    }
                }
            }

            if (!existe) {
                lineas.add(linea);
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
                for (String l : lineas) {
                    bw.write(l);
                    bw.newLine();
                }
            }

            JOptionPane.showMessageDialog(null, "Cliente " + (existe ? "modificado" : "creado") + " exitosamente");
            Notificado.setText(existe ? "Modificado" : "Creado");

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar el cliente: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    private void limpiarCampos() {
        txtNombreCliente.setText("");
        txtApellidoPat.setText("");
        txtApellidoMat.setText("");
        txtDireccion.setText("");
        fechaNacPicker.setValue(null);
        txtTelefono.setText("");
        txtCelular.setText("");
        txtCorreo.setText("");
        txtBalance.setText("");
        txtValorCuota.setText("");
        Activo.setSelected(false);
        Pasivo.setSelected(false);
        SocioActivo.setSelected(false);
        Invitado.setSelected(false);
        Notificado.setText("");
    }

    @FXML
    private void salir() {
        System.exit(0);
    }

    private boolean validarCampos() {
        if (txtIdCliente.getText().trim().isEmpty() ||
                txtNombreCliente.getText().trim().isEmpty() ||
                txtApellidoPat.getText().trim().isEmpty() ||
                txtDireccion.getText().trim().isEmpty() ||
                fechaNacPicker.getValue() == null ||
                txtTelefono.getText().trim().isEmpty() ||
                txtCelular.getText().trim().isEmpty() ||
                txtCorreo.getText().trim().isEmpty() ||
                txtBalance.getText().trim().isEmpty() ||
                txtValorCuota.getText().trim().isEmpty() ||
                (!Activo.isSelected() && !Pasivo.isSelected()) ||
                (!SocioActivo.isSelected() && !Invitado.isSelected())) {

            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }


        if (!txtCorreo.getText().trim().contains("@")) {
            JOptionPane.showMessageDialog(null, "Ingrese un correo electrónico válido", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }


        try {
            Double.parseDouble(txtBalance.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El balance debe ser un valor numérico", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try {
            Double.parseDouble(txtValorCuota.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El valor de cuota debe ser un valor numérico", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private void activarCampos() {
        txtNombreCliente.setDisable(false);
        txtApellidoPat.setDisable(false);
        txtApellidoMat.setDisable(false);
        txtDireccion.setDisable(false);
        fechaNacPicker.setDisable(false);
        txtTelefono.setDisable(false);
        txtCelular.setDisable(false);
        txtCorreo.setDisable(false);
        txtBalance.setDisable(false);
        txtValorCuota.setDisable(false);
        Activo.setDisable(false);
        Pasivo.setDisable(false);
        SocioActivo.setDisable(false);
        Invitado.setDisable(false);
    }

    @FXML
    private void borrarCliente() {
        String idCliente = txtIdCliente.getText().trim();

        if (idCliente.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese un ID de cliente para borrar", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double balance = Double.parseDouble(txtBalance.getText().trim());
            if (balance > 0) {
                JOptionPane.showMessageDialog(null, "No se puede borrar un cliente con balance mayor a cero", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error al validar el balance del cliente", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        File archivo = new File(ARCHIVO_CLIENTES);
        List<String> lineas = new ArrayList<>();
        boolean encontrado = false;

        try {
            if (archivo.exists()) {
                try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                    String linea;
                    while ((linea = br.readLine()) != null) {
                        if (!linea.trim().isEmpty()) {
                            String[] partes = linea.split(":");
                            if (partes.length > 0 && partes[0].equals(idCliente)) {
                                encontrado = true;
                            } else {
                                lineas.add(linea);
                            }
                        }
                    }
                }

                if (encontrado) {
                    try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
                        for (String l : lineas) {
                            bw.write(l);
                            bw.newLine();
                        }
                    }

                    JOptionPane.showMessageDialog(null, "Cliente borrado exitosamente");
                    limpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontró el cliente con ID: " + idCliente, "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "No existe el archivo de clientes", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al borrar el cliente: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}