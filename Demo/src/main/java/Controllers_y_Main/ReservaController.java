package Controllers_y_Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.chrono.Chronology;

public class ReservaController {

    @FXML
    private Button Guardar;

    @FXML
    private TextField IdEstadoReserva;

    @FXML
    private Button Limpiar;

    @FXML
    private TextField Notificador;

    @FXML
    private Button Salir;

    @FXML
    private DatePicker fechaReserva;

    @FXML
    private TextField idClienteReserva;

    @FXML
    private TextField idHorarioReserva;

    @FXML
    private TextField idReserva;

    @FXML
    private TextField idSalaReserva;

    @FXML
    void Guardar(ActionEvent event)
    {

    }

    @FXML
    void Limpiar(ActionEvent event)
    {
    }

    @FXML
    void Reserva(ActionEvent event)
    {
            if (idReserva.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Por favor un id");
                return;
            }

            String reserva = idReserva.getText();

            File arq = new File("Reserva.txt");
            boolean encontrado = false;

            try {
                if (!arq.exists()) {
                    arq.createNewFile();
                }

                try (BufferedReader br = new BufferedReader(new FileReader(arq))) {
                    String linea;

                    while ((linea = br.readLine()) != null && !linea.isEmpty())
                    {
                        String[] partes = linea.split(":");
                        if (partes.length >= 6)
                        {
                            try {
                                String Reserva = partes[0];
                                String SalaReserva = partes[1];
                                String ClienteReserva = partes[2];
                                Chronology FechaReserva = Chronology.of(partes[3]);
                                String HorarioReserva = partes[4];
                                String EstadoReserva = partes[5];

                                if (Reserva.equals(reserva))
                                {
                                    idSalaReserva.setText(SalaReserva.trim());
                                    idClienteReserva.setText(ClienteReserva.trim());
                                    fechaReserva.setChronology(FechaReserva);
                                    idHorarioReserva.setText(HorarioReserva.trim());
                                    IdEstadoReserva.setText(EstadoReserva.trim());
                                    Notificador.setText("Modificado");
                                    encontrado = true;
                                    activar();
                                    break;
                                }

                            } catch (NumberFormatException e)
                            {
                                continue;
                            }
                        }
                    }
                }


                if (!encontrado)
                {
                    activar();
                    Notificador.setText("Creando");
                    idSalaReserva.setText("");
                    idClienteReserva.setText("");
                    idHorarioReserva.setText("");
                    IdEstadoReserva.setText("");
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error al acceder al archivo: " + e.getMessage());
            }
        }

    @FXML
    void Salir(ActionEvent event) {

    }

    public void activar()
    {
        idClienteReserva.setDisable(false);
        idReserva.setDisable(false);
        idHorarioReserva.setDisable(false);
        fechaReserva.setDisable(false);
        idSalaReserva.setDisable(false);
        IdEstadoReserva.setDisable(false);
    }

}
