package frh.com.compilador.vistas;

import javax.swing.*;
import java.awt.*;
import frh.com.compilador.controles.Control;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Ventana extends JFrame {
    private JTextArea codigoArea;
    private JTextArea mensajesArea;
    private JMenuBar menuBar;
    private JMenu menuArchivo, menuCompilar;
    private JMenuItem abrirItem, salirItem, limpiarItem, lexicoItem;
    private Control control;

    public Ventana() {
        setTitle("Compilador");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Crear áreas de texto
        codigoArea = new JTextArea();
        mensajesArea = new JTextArea();
        mensajesArea.setEditable(false);
        
        // Agregar scroll a las áreas de texto
        JScrollPane scrollCodigo = new JScrollPane(codigoArea);
        JScrollPane scrollMensajes = new JScrollPane(mensajesArea);
        
        // Panel principal con GridLayout
        setLayout(new GridLayout(2, 1));
        add(scrollCodigo);
        add(scrollMensajes);
        
        // Crear barra de menú
        menuBar = new JMenuBar();
        
        // Menú Archivo
        menuArchivo = new JMenu("Archivo");
        abrirItem = new JMenuItem("Abrir");
        salirItem = new JMenuItem("Salir");
        menuArchivo.add(abrirItem);
        menuArchivo.add(salirItem);
        
        // Menú Compilar
        menuCompilar = new JMenu("Compilar");
        limpiarItem = new JMenuItem("Limpiar");
        lexicoItem = new JMenuItem("Léxico");
        menuCompilar.add(limpiarItem);
        menuCompilar.add(lexicoItem);
        
        // Agregar menús a la barra de menú
        menuBar.add(menuArchivo);
        menuBar.add(menuCompilar);
        
        // Configurar la barra de menú en la ventana
        setJMenuBar(menuBar);
        
        // Inicializar controlador
        control = new Control(codigoArea, mensajesArea);
        
        // Asignar acciones a los botones
        abrirItem.addActionListener(e -> control.abrirArchivo(this));
        salirItem.addActionListener(e -> System.exit(0));
    }
}
