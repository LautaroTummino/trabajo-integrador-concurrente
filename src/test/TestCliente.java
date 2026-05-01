package test;

import concurrencia.Cliente;
import concurrencia.Micro;
import concurrencia.SistemaReserva;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestCliente {

    @Test
    public void testClienteReservaAsiento() throws InterruptedException {
        Micro micro = new Micro();
        SistemaReserva sistema = new SistemaReserva(micro);
        Cliente cliente = new Cliente("Juan", sistema);

        Thread hilo = new Thread(cliente);
        hilo.start();
        hilo.join(); // Esperar que el hilo termine

        boolean reservado = micro.getAsientos().stream()
                .anyMatch(asiento -> "Juan".equals(asiento.getNombreCliente()));

        assertTrue(reservado, "El cliente Juan debería haber reservado un asiento.");
    }
}
