package Exàmens.COLAS;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class GestionColas {

    public static void main(String[] args) {

        // =====================================================
        // RA6 -> CREACIÓN DE LA COLA
        // =====================================================

        Queue<String> colaClientes = new LinkedList<>();

        // =====================================================
        // RA5 -> LECTURA DEL FICHERO
        // =====================================================

        try {

            BufferedReader br = new BufferedReader(new FileReader("clientes.txt"));

            String linea;

            // Leer línea a línea
            while ((linea = br.readLine()) != null) {

                // =====================================================
                // RA6 -> INSERTAR EN LA COLA
                // =====================================================

                colaClientes.add(linea);

                System.out.println("Cliente añadido a la cola: " + linea);
            }

            br.close();

        } catch (IOException e) {
            System.out.println("Error al leer el fichero");
        }

        // =====================================================
        // RA6 -> LECTURA DE LA COLA
        // =====================================================

        System.out.println("\nCLIENTES EN ESPERA:");

        for (String cliente : colaClientes) {
            System.out.println(cliente);
        }

        // =====================================================
        // RA6 -> CONSULTAR PRIMER CLIENTE
        // =====================================================

        if (!colaClientes.isEmpty()) {
            System.out.println("\nPrimer cliente: " + colaClientes.peek());
        }

        // =====================================================
        // RA6 -> ATENDER CLIENTES (FIFO)
        // =====================================================

        System.out.println("\nATENDIENDO CLIENTES:");
        // Mientras existan elementos en la cola
        while (!colaClientes.isEmpty()) {

            String clienteAtendido = colaClientes.poll();

            System.out.println("Atendido: " + clienteAtendido);
        }

        // =====================================================
        // RA6 -> COMPROBAR SI LA COLA ESTÁ VACÍA
        // =====================================================

        System.out.println("\n¿La cola está vacía? " + colaClientes.isEmpty());
    }
}