package concurrencia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Clase que se encarga de gestionar el sistema de reservas de asientos para un micro.
 *
 * Este sistema permite que múltiples clientes intenten reservar asientos
 * de forma concurrente. Utiliza sincronización para evitar condiciones de carrera.
 */
public class SistemaReserva {

    // Referencia al micro sobre el cual se realizan las reservas
    private Micro micro;


    /**
     * Constructor del sistema de reservas.
     *
     * @param micro Instancia del micro que contiene los asientos a reservar
     */
    public SistemaReserva(Micro micro) {
        this.micro = micro;
    }


    /**
     * Método sincronizado que permite a un cliente intentar reservar un asiento.
     * Los asientos se recorren en orden aleatorio para simular competencia real.
     *
     * Si se encuentra un asiento disponible, se reserva para el cliente.
     * En caso contrario, se notifica que no hay asientos disponibles.
     *
     * @param nombreCliente Nombre del cliente que intenta reservar
     * @return true si se logró reservar un asiento; false si no había lugares disponibles
     */
    public synchronized boolean reservarAsiento(String nombreCliente) {
        boolean reservado = false;

        List<Asiento> asientosAleatorios = new ArrayList<>(micro.getAsientos());
        Collections.shuffle(asientosAleatorios);

        int i = 0;
        int total = asientosAleatorios.size();

        while (!reservado && i < total) {
            Asiento asiento = asientosAleatorios.get(i);
            if (!asiento.estaOcupado()) {
                reservado = asiento.reservar(nombreCliente);
                if (reservado) {
                    System.out.println("Cliente " + nombreCliente +
                            " reservo el asiento " + asiento.getNumero());
                    micro.mostrarEstado();
                }
            }
            i++;
        }

        if (!reservado) {
            System.out.println("Cliente " + nombreCliente +
                    " no pudo reservar ningun asiento.");
        }

        return reservado;
    }
}
