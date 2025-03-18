package edu.itz.ejercicios.controles;

import edu.itz.ejercicios.vistas.Ventana;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Rapid
 */
public class Control {

    Ventana v;

    public Control(Ventana v) {
        this.v = v;
    }
    public void contar() {
    // Obtener la contraseña ingresada en la ventana
    var contraseña = v.getTxtContenido().getText().trim();

    // Expresión regular para validar una contraseña segura
        var texto= "(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%+.&*])(?=\\S+$).{8,}$";
            
    Pattern pattern = Pattern.compile(texto);
    Matcher matcher = pattern.matcher(contraseña);

    // Validar si la contraseña cumple con los requisitos
    boolean esValida = matcher.matches();

    // Mostrar el resultado en la interfaz
    if (esValida) {
        v.getTxtsalida().setText("La contraseña es segura.");
    } else {
        v.getTxtsalida().setText("""
                                 La contraseña NO es segura. Debe contener:
                                 - Minimo 8 caracteres
                                 - Al menos una mayascula
                                 - Al menos una minuscula
                                 - Al menos un numero
                                 - Al menos un caracter especial (!@#$%+.&*)
                                 - No debe contener espacios""");
    }
}

     public void validarHexadecimal() {
        // Obtener el texto ingresado en la ventana
        String texto = v.getTxtContenido().getText().trim();

        // Expresión regular para números hexadecimales
        Pattern hexPattern = Pattern.compile("(?i)^(0x)?[0-9A-F]+$");
        Matcher matcher = hexPattern.matcher(texto);

        // Validar si el texto es un número hexadecimal
        boolean esHexadecimal = matcher.matches();

        // Mostrar resultado en el área de salida
        if (esHexadecimal) {
            v.getTxtsalida().setText("El número ingresado es hexadecimal válido.");
        } else {
            v.getTxtsalida().setText("El número ingresado NO es hexadecimal válido.");
        }
    }
    public void contar3() {
        // Obtener el texto ingresado en la ventana
        String texto = v.getTxtContenido().getText();

        // Expresiones regulares
        Pattern numerosPattern = Pattern.compile("\\d+");
        Pattern identificadoresPattern = Pattern.compile("\\b[a-zA-Z_][a-zA-Z0-9_]*\\b");

        // Contar coincidencias
        int countNumeros = contarCoincidencias(texto, numerosPattern);
        int countIdentificadores = contarCoincidencias(texto, identificadoresPattern);

        // Mostrar resultados en el área de salida
        v.getTxtsalida().setText("Cantidad de números: " + countNumeros + 
                                       "\nCantidad de identificadores: " + countIdentificadores);
    }

    private int contarCoincidencias(String texto, Pattern pattern) {
        Matcher matcher = pattern.matcher(texto);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }
    


    public void abrirArchivo() {
        limpiar();
       // v.getTxtContenido().setText("Hola Mundo
       String path = null;
       JFileChooser filechooser = new JFileChooser();
       int returnValue = filechooser.showOpenDialog(v);
       if(returnValue == JFileChooser.APPROVE_OPTION){
           path = filechooser.getSelectedFile().getAbsolutePath();
           v.getLblarchivo().setText(path);
       }
       if(path == null){
           JOptionPane.showMessageDialog(v,"Le diste cancelar" );
           return;
       }
       leerArchivo(path);
        
    }
    
    public void limpiar(){
        v.getTxtContenido().setText("");
        v.getLblarchivo().setText("");
        v.getTxtsalida().setText("");
        
    }
    
    public void leerArchivo(String archivo){
        String texto="";
        try {
            BufferedReader br= new BufferedReader(new FileReader(archivo));
            
            String linea;
            while((linea = br.readLine()) != null){
                texto+=linea+"\n";
                
                
                
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        v.getTxtContenido().append(texto+"\n");
    }
    public void contar2(){
       String c =  v.getTxtContenido().getText();
       int letras=0, numeros=0, otros=0;
       v.getTxtsalida().setText(c.length()+"\n");
       char a = c.charAt(0);
       v.getTxtsalida().append(a+"\n");
        for (int i = 0; i < c.length(); i++) {     
            a = c.charAt(i);
            if(Character.isLetter(a)){
                letras++;
            }else if (Character.isDigit(a)){
                numeros++;
            }else{
                otros++;
            }
        }
        v.getTxtsalida().append("El numero de Letras es:"+letras+"\n");
        v.getTxtsalida().append("El total de numeros es:"+numeros+"\n");
        v.getTxtsalida().append("El total de caracteres especiales es:"+otros+"\n");
    }
public void IdConAFD() { 
    // Obtiene el texto del área de texto de entrada
    String texto = v.getTxtContenido().getText();

    // Limpia el área de texto de salida
    v.getTxtsalida().setText("");

    // Utiliza StringBuilder para optimizar la concatenación de caracteres
    StringBuilder id = new StringBuilder();

    // Recorre cada carácter del texto
    for (int i = 0; i < texto.length(); i++) {
        char c = texto.charAt(i); // Obtiene el carácter actual
        id.setLength(0);  // Reinicia el identificador para almacenar nuevos datos

        // Verifica si el carácter es una letra (inicio de un identificador)
        if (Character.isLetter(c)) {
            // Mientras sea letra, dígito o guion bajo, sigue construyendo el identificador
            while (i < texto.length() && (Character.isLetterOrDigit(c) || c == '_')) {
                id.append(c); // Agrega el carácter al identificador
                if (++i < texto.length()) {
                    c = texto.charAt(i); // Avanza al siguiente carácter
                } else break; // Sale del ciclo si llega al final del texto
            }
            // Muestra el identificador en el área de salida
            v.getTxtsalida().append(id.toString() + "\n");
        }

        id.setLength(0);  // Reinicia el identificador para manejar números
        // Verifica si el carácter es un dígito
        if (Character.isDigit(c)) {
            // Maneja el caso especial del número cero con decimales
            if (c == '0') {
                id.append(c); // Agrega el '0'
                // Verifica si el siguiente carácter es un punto decimal
                if (++i < texto.length() && texto.charAt(i) == '.') {
                    id.append('.'); // Agrega el punto decimal
                    i++; // Avanza al siguiente carácter
                    // Agrega los dígitos que siguen al punto decimal
                    while (i < texto.length() && Character.isDigit(texto.charAt(i))) {
                        id.append(texto.charAt(i++));
                    }
                }
            } else {
                // Si el número no empieza en cero, agrega todos los dígitos consecutivos
                while (i < texto.length() && Character.isDigit(texto.charAt(i))) {
                    id.append(texto.charAt(i++));
                }
            }
            // Muestra el número en el área de salida
            v.getTxtsalida().append(id.toString() + "\n");
        }
    }

    }
}