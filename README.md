# Introducción a los sistemas distribuidos y paralelos

## Ingeniería en Computación

**Materia:** Introducción a los sistemas distribuidos y paralelos
**Docente:** Walter Fabian Aguero
**Trabajo Final:** Desarrollo de código concurrente en Java
**Fecha:** 30/05/2025

---

# TRABAJO FINAL

## Simulación Concurrente de un Sistema de Reservas

El trabajo consiste en la implementación de un sistema de reservas de asientos para un micro de larga distancia, con un enfoque en la concurrencia y una interfaz gráfica que permite visualizar el estado de los asientos en tiempo real. Para ello se utilizaron los conceptos aprendidos en la materia aplicando programación concurrente en Java.

---

## Objetivos del sistema

El sistema simula un entorno en el que 64 clientes (representados por hilos) intentan reservar 32 asientos disponibles en un micro. El objetivo principal fue lograr que múltiples hilos intenten acceder al recurso compartido (los asientos) al mismo tiempo, garantizando que no haya errores ni comportamientos inesperados producto del acceso concurrente.

---

## Problemas de concurrencia

Al tratarse de un sistema con múltiples hilos actuando sobre una misma estructura (la lista de asientos del micro), se presentan algunas de las siguientes problemáticas de la concurrencia:

* **Condición de carrera:** ocurre cuando dos o más hilos acceden simultáneamente a un mismo recurso, y al menos uno de ellos lo modifica. Esto puede provocar que el resultado final dependa del orden de ejecución de los hilos, generando errores o comportamientos no deseados.

* **Indeterminación:** se refiere a que no se puede predecir qué hilo va a ejecutarse primero ni en qué orden se producirán las operaciones, lo que puede generar inconsistencias si no se controla adecuadamente el acceso a los recursos compartidos.

* **Regiones críticas:** son aquellas partes del código donde se accede o modifica un recurso compartido y, por lo tanto, deben ser protegidas para evitar los problemas anteriores.

---

## Solución implementada

Para resolver estos problemas, se utilizó el mecanismo de monitores y sincronización que ofrece el lenguaje Java a través de la palabra clave `synchronized`. Este mecanismo es conocido como Monitor, el mismo actúa como un candado o bloqueo (lock), permitiendo que solo un hilo a la vez pueda entrar a una región crítica. De esta manera, se garantiza que dos hilos no puedan modificar el estado de un mismo asiento al mismo tiempo.

Las operaciones de reserva de asientos fueron definidas dentro de métodos `synchronized`, lo que asegura que cuando un hilo está intentando reservar, ningún otro pueda interferir hasta que el primero haya terminado su operación. Esto permite trabajar de forma concurrente pero segura.

---

## Clases que Hacen Posible la Simulación

### Micro

Esta clase representa el micro de larga distancia. Su función principal es almacenar la lista de asientos disponibles (en este caso, 32). También ofrece un método para acceder a estos asientos, que será utilizado por el sistema de reservas. Se encarga de crear todos los asientos al inicio y permite consultarlos en cualquier momento.

---

### Asiento

Cada objeto de esta clase representa un asiento individual dentro del micro. Tiene información sobre su número de identificación y si está ocupado o no, además de almacenar el nombre del cliente que lo reservó. Esta clase es fundamental porque contiene el método `reservar`, que está protegido con `synchronized` para evitar que dos clientes reserven el mismo asiento al mismo tiempo.

---

### Sistema Reserva

Esta clase es la que coordina las reservas. Tiene acceso al micro y a todos sus asientos. Cuando un cliente quiere hacer una reserva, llama a un método de esta clase. El sistema intenta encontrar un asiento libre y se lo asigna al cliente.

---

### Cliente

Representa un hilo de ejecución, es decir, una persona que intenta reservar un asiento. Cada cliente tiene un nombre y una referencia al sistema de reservas. Cuando se ejecuta, el cliente simplemente intenta hacer una reserva.

---


## Interfaz gráfica

Además del funcionamiento interno del sistema, se diseñó una interfaz gráfica utilizando Java Swing. Esta interfaz permite visualizar el estado actual de los asientos: si están libres (en verde) o ocupados (en rojo), mostrando además el nombre del cliente que logró realizar la reserva.

También se integró un área de logs que muestra en tiempo real los mensajes que normalmente aparecen en la consola, para tener un seguimiento completo del proceso de reserva. Por último, se agregó un botón para reiniciar la simulación, lo que permite volver a lanzar los hilos y observar nuevos resultados sin reiniciar el programa completo.

---

## Conclusión

Este trabajo permitió poner en práctica conceptos fundamentales de la programación concurrente, enfrentando y resolviendo problemas reales como la condición de carrera y la indeterminación. Gracias a los mecanismos que brinda Java, como los monitores implementados con `synchronized`, y la interfaz `Runnable` para definir tareas que se ejecutan en paralelo, fue posible coordinar correctamente el acceso a los recursos compartidos. De esta forma, se garantiza un comportamiento coherente del sistema, evitando errores comunes al trabajar con múltiples hilos.

---
