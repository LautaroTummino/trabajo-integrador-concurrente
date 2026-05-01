package visorPrueba;

import concurrencia.Micro;
import concurrencia.SistemaReserva;
import concurrencia.Cliente;

import javax.swing.*;

public class MainConInterfaz {

    public static void main(String[] args) {
        Micro micro = new Micro();
        SistemaReserva sistema = new SistemaReserva(micro);

        // Inicializa la interfaz gráfica en el hilo de eventos de Swing
        SwingUtilities.invokeLater(() -> {
            // Crea e inicializa el visor de asientos, que muestra la GUI
            VisorAsientos visor = new VisorAsientos(micro, sistema);
            visor.setVisible(true); // Muestra la ventana
            iniciarSimulacion(visor); // Inicia la simulación de reservas

        });
    }


    /**
     * Método que lanza 64 hilos de clientes, cada uno con un nombre ficticio,
     * que intentarán reservar un asiento. Se ejecuta en un hilo separado
     * para no bloquear la interfaz gráfica.
     *
     * @param visor la instancia de VisorAsientos que contiene el sistema de reservas
     */
    private static void iniciarSimulacion(VisorAsientos visor) {
        new Thread(() -> {
            Thread[] clientes = new Thread[64];

            for (int i = 0; i < 64; i++) {
                String nombre = generarNombreFicticio(i);
                Thread hiloCliente = new Thread(new Cliente(nombre, visor.getSistema()));
                clientes[i] = hiloCliente;
                hiloCliente.start();
                try {
                    Thread.sleep(900);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // Esperar a que todos los clientes terminen
            for (Thread hilo : clientes) {
                try {
                    hilo.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("Todas las reservas han terminado.");
        }).start();
    }


    /**
     * Genera un nombre ficticio compuesto por un nombre y un apellido.
     * El índice i asegura combinaciones únicas para cada cliente.
     *
     * @param i índice del cliente
     * @return nombre completo ficticio
     */
    public static String generarNombreFicticio(int i) {
        String[] nombres = {
                "Ana", "Bruno", "Carlos", "Diana",
                "Emilia", "Facundo", "Gabriel", "Helena",
                "Ivan", "Julieta", "Kevin", "Laura",
                "Martin", "Nadia", "Oscar", "Paula"
        };
        String[] apellidos = {
                "Perez", "Gomez", "Rodriguez", "Fernandez",
                "Lopez", "Martinez", "Sanchez", "Diaz",
                "Ramirez", "Torres", "Romero", "Silva",
                "Castro", "Molina", "Morales", "Herrera"
        };
        return nombres[i % nombres.length] + " " + apellidos[i % apellidos.length];
    }
}

