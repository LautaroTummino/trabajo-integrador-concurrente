package concurrencia;

import java.util.Random;


/**
 * Clase principal que simula la ejecución del sistema de reservas concurrente.
 *
 * Crea un micro con asientos, un sistema de reservas, y lanza múltiples hilos
 * clientes que intentan reservar asientos simultáneamente.
 *
 * Cada cliente recibe un nombre único basado en una lista base y un número.
 * Se introduce una pausa breve entre el inicio de cada hilo para simular un
 * delay entre los mismos.
 *
 * Al finalizar la ejecución de todos los hilos, se imprime un mensaje indicando
 * que todas las reservas han terminado.
 */

public class Main {

    /**
     * Cantidad total de clientes que intentarán reservar un asiento.
     */
    private static final int CANTIDAD_CLIENTES = 64;

    public static void main(String[] args) {
        String[] nombresBase = {
                "Ana", "Bruno", "Carla", "David", "Elena", "Fernando",
                "Gabriela", "Hugo", "Irene", "Javier", "Azul", "Luis",
                "Marta", "Nico", "Olga", "Pablo", "Roberto", "Raul",
                "Sofia", "Tomas", "Facundo", "Federico", "Walter"
        };

        Random random = new Random();
        Micro micro = new Micro();
        SistemaReserva sistema = new SistemaReserva(micro);
        Thread[] hilosClientes = new Thread[CANTIDAD_CLIENTES];

        // Crear y lanzar cada hilo cliente con nombre único
        for (int i = 0; i < CANTIDAD_CLIENTES; i++) {
            String nombreCliente = nombresBase[random.nextInt(nombresBase.length)] + "-" + (i + 1);
            Cliente cliente = new Cliente(nombreCliente, sistema);
            hilosClientes[i] = new Thread(cliente);
            hilosClientes[i].start(); // Inicia el hilo
            try {
                Thread.sleep(300); // Pausa entre inicios
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Esperar a que todos los hilos terminen
        for (Thread hilo : hilosClientes) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Todas las reservas han terminado.");
    }
}
