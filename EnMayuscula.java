
/**
 * /*
 * Crea una función que reciba un String de cualquier tipo y se encargue de
 * poner en mayúscula la primera letra de cada palabra.
 * - No se pueden utilizar operaciones del lenguaje que
 *   lo resuelvan directamente.
 */

import java.util.Scanner;

public class EnMayuscula {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce un texto: ");
        String texto = scanner.nextLine();

        String resultado = ponerMayusculaInicio(texto);

        System.out.print("\nTexto capitalizado: " + resultado);

        scanner.close();
    }

    public static String ponerMayusculaInicio(String texto) {
        String resultado = "";
        boolean nuevaPalabra = true; // Flag para saber si viene una letra que debe ser mayúscula

        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);

            if (c == ' ') {
                // Si es un espacio, lo añadimos y activamos el flag de nueva palabra
                resultado += c;
                nuevaPalabra = true;
            } else {
                if (nuevaPalabra) {
                    // Convertimos a mayúscula de forma segura
                    resultado += Character.toUpperCase(c);
                    nuevaPalabra = false; // Ya procesamos la primera letra
                } else {
                    // El resto de la palabra la forzamos a minúscula
                    resultado += Character.toLowerCase(c);
                }
            }
        }
        return resultado;
    }
}
