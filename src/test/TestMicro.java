package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import concurrencia.Micro;
import concurrencia.Asiento;

import java.util.List;

public class TestMicro {

    @Test
    public void testCantidadDeAsientos() {
        Micro micro = new Micro();
        List<Asiento> asientos = micro.getAsientos();

        assertEquals(32, asientos.size(), "El micro debe tener 32 asientos");
    }

    @Test
    public void testNumeracionCorrecta() {
        Micro micro = new Micro();
        List<Asiento> asientos = micro.getAsientos();

        for (int i = 0; i < asientos.size(); i++) {
            int numeroEsperado = i + 1;
            assertEquals(numeroEsperado, asientos.get(i).getNumero(),
                    "El asiento debe tener el número correcto");
        }
    }

    @Test
    public void testTodosAsientosDesocupadosAlInicio() {
        Micro micro = new Micro();

        for (Asiento asiento : micro.getAsientos()) {
            assertFalse(asiento.estaOcupado(), "Todos los asientos deben estar desocupados al inicio");
        }
    }

    @Test
    public void testReservarPrimerAsiento() {
        Micro micro = new Micro();
        Asiento primerAsiento = micro.getAsientos().get(0);
        boolean resultado = primerAsiento.reservar("Roman");

        assertTrue(resultado, "La reserva del primer asiento debe haberse realizado");
        assertTrue(primerAsiento.estaOcupado(), "El asiento debe estar marcado como ocupado");
        assertEquals("Roman", primerAsiento.getNombreCliente(),
                "El nombre del cliente debe coincidir con el que reservó");
    }
}
