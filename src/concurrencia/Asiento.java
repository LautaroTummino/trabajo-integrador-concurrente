package concurrencia;



/**
 * Clase que representa un asiento en un sistema de reservas.
 * Cada asiento tiene un número único, un estado, y puede estar asociado a un cliente.
 *
 * Esta clase está diseñada para ser utilizada en contextos concurrentes,
 * por lo que el método de reserva está sincronizado para evitar condiciones de carrera.
 */
public class Asiento {

    // Número identificador del asiento
    private int numero;

    // Indica si el asiento está ocupado o no
    private boolean ocupado;

    // Nombre del cliente que reservó el asiento
    private String nombreCliente;


    /**
     * Constructor de la clase Asiento.
     * Inicializa un asiento con un número dado y marca como no ocupado.
     *
     * @param numero Número identificador del asiento
     */
    public Asiento(int numero) {
        this.numero = numero;
        this.ocupado = false;
        this.nombreCliente = null;

    }

    /**
     * Intenta reservar el asiento para un cliente con un nombre dado.
     * El método es sincronizado para asegurar exclusión mutua.
     *
     * @param nombreCliente Nombre del cliente que desea reservar el asiento
     * @return true si la reserva fue exitosa, false si el asiento ya estaba ocupado
     */
    public synchronized boolean reservar(String nombreCliente) {
        boolean reservado = false;
        if (!ocupado) {
            ocupado = true;
            this.nombreCliente = nombreCliente;
            reservado = true;
        }
        return reservado;
    }

    /**
     * Consulta si el asiento está ocupado.
     *
     * @return true si el asiento está ocupado, false en caso contrario
     */
    public boolean estaOcupado() {
        return ocupado;
    }

    /**
     * Devuelve el número del asiento.
     *
     * @return número identificador del asiento
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Devuelve el nombre del cliente que reservó el asiento.
     *
     * @return nombre del cliente o null si el asiento no ha sido reservado
     */
    public String getNombreCliente() {
        return nombreCliente;
    }
}
