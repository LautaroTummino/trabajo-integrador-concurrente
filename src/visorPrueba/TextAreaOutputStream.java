package visorPrueba;

import javax.swing.*;
import java.io.OutputStream;


/**
 * Clase que permite redirigir la salida estándar (System.out)
 * a un componente JTextArea en una interfaz gráfica.
 *
 * Esto es útil para mostrar mensajes que normalmente irían a la consola
 * directamente dentro de una GUI Swing, como un área de logs.
 */
class TextAreaOutputStream extends OutputStream {

    // Referencia al JTextArea donde se mostrará el contenido redirigido
    private final JTextArea textArea;

    /**
     * Constructor que recibe el JTextArea destino para redirigir la salida.
     *
     * @param textArea El JTextArea donde se mostrará el texto
     */
    public TextAreaOutputStream(JTextArea textArea) {
        this.textArea = textArea;
    }

    /**
     * Método sobrescrito de OutputStream que se llama por cada byte escrito.
     * Convierte el byte a un carácter y lo agrega al JTextArea.
     *
     * Se asegura de ejecutar el cambio en el hilo de Swing (EDT)
     * mediante SwingUtilities.invokeLater, para evitar problemas de concurrencia.
     *
     * @param b El byte a escribir (representa un carácter)
     */
    @Override
    public void write(int b) {
        // Ejecuta de manera segura en el hilo de eventos de Swing
        SwingUtilities.invokeLater(() -> textArea.append(String.valueOf((char) b)));
        // Agrega el carácter correspondiente al final del JTextArea
    }
}