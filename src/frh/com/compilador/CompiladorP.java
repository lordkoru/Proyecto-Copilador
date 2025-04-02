
package frh.com.compilador;

import javax.swing.*;
import frh.com.compilador.vistas.Ventana;

public class CompiladorP {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Ventana().setVisible(true));
    }
}