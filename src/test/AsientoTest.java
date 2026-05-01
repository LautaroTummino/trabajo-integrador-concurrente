package test;

import concurrencia.Asiento;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AsientoTest {

    @Test
    public void testNuevoAsientoEstaLibre() {
        Asiento asiento = new Asiento(5);
        assertFalse(asiento.estaOcupado(), "El asiento debería estar" +
                " libre al crearse");
        assertNull(asiento.getNombreCliente(), "No debería haber" +
                " cliente asignado");
        assertEquals(5, asiento.getNumero(), "El número del asiento" +
                " debería coincidir");
    }

    @Test
    public void testReservaExitosa() {
        Asiento asiento = new Asiento(3);
        boolean resultado = asiento.reservar("Ana");

        assertTrue(resultado, "La reserva debería ser exitosa");
        assertTrue(asiento.estaOcupado(), "El asiento debería estar ocupado");
        assertEquals("Ana", asiento.getNombreCliente(), "El nombre del " +
                "cliente debería coincidir");
    }

    @Test
    public void testReservaFallidaSiYaEstaOcupado() {
        Asiento asiento = new Asiento(7);
        asiento.reservar("Bruno"); // primera reserva
        boolean resultado = asiento.reservar("Carla"); // intento de reserva sobre ocupado

        assertFalse(resultado, "No se debería poder reservar un asiento ya ocupado");
        assertEquals("Bruno", asiento.getNombreCliente(), "El cliente" +
                " original no debería cambiar");
    }
}

