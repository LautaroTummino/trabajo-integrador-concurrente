package test;

import concurrencia.Asiento;
import concurrencia.Micro;
import concurrencia.SistemaReserva;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class TestSistemaReserva {

    private Micro micro;
    private SistemaReserva sistema;

    @BeforeEach
    public void setup() {
        micro = new Micro();
        sistema = new SistemaReserva(micro);
    }

    @Test
    public void testReservaExitosa() {
        boolean resultado = sistema.reservarAsiento("Cliente1");

        assertTrue(resultado, "La reserva debe ser exitosa cuando hay asientos disponibles");

        long ocupados = micro.getAsientos().stream().filter(Asiento::estaOcupado).count();
        assertEquals(1, ocupados, "Debe haber exactamente un asiento ocupado");
    }

    @Test
    public void testReservarVariosClientes() {
        for (int i = 1; i <= 10; i++) {
            boolean resultado = sistema.reservarAsiento("Cliente" + i);
            assertTrue(resultado, "La reserva del cliente " + i + " debe ser exitosa");
        }

        long ocupados = micro.getAsientos().stream().filter(Asiento::estaOcupado).count();
        assertEquals(10, ocupados, "Deben haberse reservado 10 asientos");
    }

    @Test
    public void testReservaCuandoNoHayAsientosDisponibles() {
        // Reservar todos los asientos
        for (int i = 1; i <= 32; i++) {
            assertTrue(sistema.reservarAsiento("Cliente" + i));
        }

        // Intentar reservar uno más
        boolean resultado = sistema.reservarAsiento("ClienteExtra");

        assertFalse(resultado, "La reserva debe fallar cuando no hay asientos disponibles");

        long ocupados = micro.getAsientos().stream().filter(Asiento::estaOcupado).count();
        assertEquals(32, ocupados, "Deben estar ocupados los 32 asientos");
    }
}
