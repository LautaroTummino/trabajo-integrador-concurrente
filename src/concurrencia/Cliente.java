package concurrencia;


/**
 * Clase que representa un cliente que intenta reservar un asiento
 *
 * Cada cliente se ejecuta como un hilo independiente, simulando
 * competencia por los asientos disponibles.
 */
public class Cliente implements Runnable {

    // Nombre del cliente
    private final String nombre;

    // Referencia al sistema de reservas compartido
    private final SistemaReserva sistema;

    /**
     * Constructor de la clase Cliente.
     *
     * @param nombre Nombre del cliente
     * @param sistema Referencia al sistema de reservas al que se quiere acceder
     */
    public Cliente(String nombre, SistemaReserva sistema) {
        this.nombre = nombre;
        this.sistema = sistema;
    }

    /**
     * Método ejecutado cuando el hilo del cliente comienza.
     * Simula un pequeño retardo aleatorio y luego intenta reservar un asiento.
     * Si no logra reservar, muestra un mensaje por consola.
     */
    @Override
    public void run() {

        try {
            // Simular un pequeño retardo aleatorio antes de reservar
            Thread.sleep((long)(Math.random() * 1000 + 300));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        boolean exito = sistema.reservarAsiento(nombre);

        if (!exito) {
            System.out.println("Cliente " + nombre + " se quedo sin lugar.");
        }
    }
}
