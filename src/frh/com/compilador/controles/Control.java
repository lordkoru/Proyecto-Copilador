package frh.com.compilador.controles;

import javax.swing.*;
import java.io.*;

public class Control {
    private JTextArea codigoArea;
    private JTextArea mensajesArea;

    public Control(JTextArea codigoArea, JTextArea mensajesArea) {
        this.codigoArea = codigoArea;
        this.mensajesArea = mensajesArea;
    }
    
    public void abrirArchivo(JFrame frame) {
        JFileChooser fileChooser = new JFileChooser();
        int opcion = fileChooser.showOpenDialog(frame);
        if (opcion == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            cargarArchivo(archivo);
        }
    }
    
    private void cargarArchivo(File archivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            codigoArea.setText("");
            String linea;
            while ((linea = br.readLine()) != null) {
                codigoArea.append(linea + "\n");
            }
        } catch (IOException ex) {
            mensajesArea.setText("Error al abrir el archivo: " + ex.getMessage());
        }
    }
}