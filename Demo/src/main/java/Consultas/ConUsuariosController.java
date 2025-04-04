package Consultas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ConUsuariosController
{

    @FXML
    private TableView<?> Table;
    @FXML
    private Button Consultar;

    @FXML
    private Button Limpiar;

    @FXML
    private RadioButton Nombre;

    @FXML
    private TextField TextField;

    @FXML
    private RadioButton Usuario;

    @FXML
    void Consultar(ActionEvent event)
    {
        File arq = new File("archivo.txt");
        boolean encontrado = false;

        try {
            if (!arq.exists()) {
                arq.createNewFile();
            }

            try (BufferedReader br = new BufferedReader(new FileReader(arq))) {
                String linea;

                while ((linea = br.readLine()) != null && !linea.isEmpty()) {
                    String[] partes = linea.split(":");


                    if (partes.length >= 6)
                    {
                        try {

                            String Login = partes[0];
                            String passwordusuario = partes[1];
                            String nombreusuario = partes[3];
                            int Nivel = Integer.parseInt(partes[4]);
                            String apellidousuario = partes[4];
                            String correousuario = partes[5];

                            }catch (Exception e)
                            {
                            JOptionPane.showMessageDialog(null, "No se pudo ingresar el usuario");
                            }
                        }
                    }
                } catch (IOException e)

                {
                throw new RuntimeException(e);
                }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }







    }

    @FXML
    void Limpiar(ActionEvent event)
    {
        Usuario.setSelected(false);
        Nombre.setSelected(false);
    }

}
