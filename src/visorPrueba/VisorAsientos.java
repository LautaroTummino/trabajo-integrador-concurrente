package visorPrueba;

import concurrencia.Asiento;
import concurrencia.Micro;
import concurrencia.SistemaReserva;

import javax.swing.*;
import java.awt.*;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;



/**
 * Ventana principal que representa visualmente el estado de los asientos del micro.
 * También contiene un área de logs donde se muestra la salida del programa.
 */
public class VisorAsientos extends JFrame {

    private Micro micro;
    private SistemaReserva sistema;

    // Panel que contiene los botones de cada asiento
    private JPanel panelAsientos;

    // Mapa para acceder a los botones por número de asiento
    private Map<Integer, JButton> botones;

    // Área de texto para mostrar logs (salida del programa)
    private JTextArea areaLogs;

    /**
     * Constructor principal que inicializa la interfaz gráfica del sistema de reservas.
     *
     * @param micro   Instancia del micro que contiene los asientos.
     * @param sistema Sistema de reservas que gestiona las operaciones concurrentes.
     */
    public VisorAsientos(Micro micro, SistemaReserva sistema) {
        this.micro = micro;
        this.sistema = sistema;
        this.botones = new HashMap<>();

        setTitle("SIMULACION DE RESERVAS EN VIAJES LARGA DISTANCIA");
        setSize(800, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10,10));

        // Panel superior que contendrá logo y área de logs
        JPanel panelSuperior = new JPanel(new BorderLayout(10, 10));
        add(panelSuperior, BorderLayout.NORTH);

        // Cargar y poner logo
        ImageIcon logoIcon = new ImageIcon("logo_unrn.png");
        JLabel labelLogo = new JLabel(logoIcon);
        panelSuperior.add(labelLogo, BorderLayout.WEST);

        // Área de logs (texto para mostrar lo que se imprime en consola)
        areaLogs = new JTextArea(5, 40);
        areaLogs.setEditable(false);
        JScrollPane scrollLogs = new JScrollPane(areaLogs);
        scrollLogs.setBorder(BorderFactory.createTitledBorder("Logs del Programa"));
        panelSuperior.add(scrollLogs, BorderLayout.CENTER);

        // Redirigir System.out y System.err al área de logs
        PrintStream printStream = new PrintStream(new TextAreaOutputStream(areaLogs));
        System.setOut(printStream);
        System.setErr(printStream);

        // Panel para botones (asientos)
        panelAsientos = new JPanel();
        panelAsientos.setLayout(new GridLayout(8, 4, 5, 5));
        add(panelAsientos, BorderLayout.CENTER);

        // Botón reiniciar
        JButton botonReiniciar = new JButton("Reiniciar Simulacion");
        botonReiniciar.addActionListener(e -> reiniciarSimulacion());
        add(botonReiniciar, BorderLayout.SOUTH);

        inicializarBotones();
        iniciarActualizacionVisual();
    }

    /**
     * Inicializa los botones que representan los asientos.
     */
    private void inicializarBotones() {
        panelAsientos.removeAll();
        botones.clear();
        for (Asiento asiento : micro.getAsientos()) {
            JButton boton = new JButton("Libre");
            boton.setBackground(Color.GREEN);
            boton.setEnabled(false);
            botones.put(asiento.getNumero(), boton);
            panelAsientos.add(boton);
        }
    }

    /**
     * Inicia un temporizador que actualiza la visualización del estado
     * de los asientos cada 300 ms.
     */
    private void iniciarActualizacionVisual() {
        Timer timer = new Timer(300, e -> actualizarEstadoAsientos());
        timer.start();
    }


    /**
     * Actualiza visualmente el estado de los botones de los asientos según su ocupación.
     */
    private void actualizarEstadoAsientos() {
        for (Asiento asiento : micro.getAsientos()) {
            JButton boton = botones.get(asiento.getNumero());
            if (asiento.estaOcupado()) {
                boton.setText("<html>Ocupado<br>" + asiento.getNumero() + " - " + asiento.getNombreCliente() + "</html>");
                boton.setBackground(Color.RED);
            } else {
                boton.setText("Libre");
                boton.setBackground(Color.GREEN);
            }
        }
    }

    /**
     * Reinicia la simulación:
     * - Se reinicia el micro y el sistema.
     * - Se reinician los botones visuales.
     * - Se relanza la ejecución de los clientes.
     */
    private void reiniciarSimulacion() {
        micro = new Micro();
        sistema = new SistemaReserva(micro);
        inicializarBotones();
        actualizarEstadoAsientos();

        // Lanzar nuevamente los clientes
        new Thread(() -> {
            for (int i = 0; i < 64; i++) {
                String nombre = MainConInterfaz.generarNombreFicticio(i);
                new Thread(new concurrencia.Cliente(nombre, sistema)).start();
                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * Devuelve el sistema de reserva actual.
     *
     * @return SistemaReserva asociado a esta interfaz.
     */
    public SistemaReserva getSistema() {
        return sistema;
    }
}

