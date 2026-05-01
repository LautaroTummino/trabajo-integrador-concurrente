package concurrencia;
import java.util.ArrayList;
import java.util.List;


/**
 * Clase que representa un micro de larga distancia con una cantidad fija
 * de asientos.
 *
 * La clase se encarga la creación de los asientos y permite mostrar
 * visualmente su estado (ocupado o libre) por consola.
 */
public class Micro {

    // Cantidad fija de asientos en el micro
    private static final int CANTIDAD_ASIENTOS = 32;

    // Lista de objetos Asiento que representa los asientos del micro
    private final List<Asiento> asientos;

    /**
     * Constructor de la clase Micro.
     * Crea los asientos y los agrega a la lista, numerándolos del 1 al 32.
     */
    public Micro() {
        this.asientos = new ArrayList<>();

        for (int i = 1; i <= CANTIDAD_ASIENTOS; i++) {
            asientos.add(new Asiento(i));
        }
    }

    /**
     * Muestra el estado actual de los asientos en consola.
     * Los asientos ocupados se representan con "|x|" y los libres con "| |".
     * Se agrupan visualmente en filas de 4 asientos.
     */
    public void mostrarEstado() {
        int asientosPorFila = 4;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < asientos.size(); i++) {
            Asiento asiento = asientos.get(i);

            /**
             * Se comenta esta parte del metodo ya que solo sirve para mostrar por terminal
             *
             */

            /*

            if (asiento.estaOcupado()) {
                sb.append("|x|");
            } else {
                sb.append("| |");
            }

            if ((i + 1) % asientosPorFila == 0) {
                sb.append("\n");
            }

             */
        }

        System.out.println(sb.toString());
    }

    /**
     * Devuelve la lista de asientos del micro.
     *
     * @return Lista de objetos Asiento
     */
    public List<Asiento> getAsientos() {
        return asientos;
    }


}


