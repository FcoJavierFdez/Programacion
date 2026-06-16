
/**
 * /*
 * Crea un programa que cuente cuantas veces se repite cada palabra
 * y que muestre el recuento final de todas ellas.
 * - Los signos de puntuación no forman parte de la palabra.
 * - Una palabra es la misma aunque aparezca en mayúsculas y minúsculas.
 * - No se pueden utilizar funciones propias del lenguaje que
 *   lo resuelvan automáticamente.
 */

import java.util.Scanner;

public class CuentaPalabras {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Introduce una frase: ");
        String frase = scanner.nextLine();

        contarPalabras(frase);

        scanner.close();
    }

    public static void contarPalabras(String texto) {
        // 1. Dividimos la frase original por espacios
        String[] todasLasPalabras = texto.split(" ");

        // 2. Preparamos arrays para almacenar únicas y sus contadores
        String[] palabrasUnicas = new String[todasLasPalabras.length];
        int[] contador = new int[todasLasPalabras.length];
        int indiceUnicas = 0;

        // 3. Procesamos cada palabra del texto
        for (int i = 0; i < todasLasPalabras.length; i++) {

            // LIMPIEZA: Pasamos a minúsculas y quitamos signos de puntuación
            String palabraLimpia = "";
            String palabraActual = todasLasPalabras[i].toLowerCase();

            for (int j = 0; j < palabraActual.length(); j++) {
                char c = palabraActual.charAt(j);
                // Solo guardamos si es una letra o número (así ignoramos , . ! ? etc.)
                if (Character.isLetterOrDigit(c)) {
                    palabraLimpia += c;
                }
            }

            // Si después de limpiar la palabra se queda vacía (era solo un "."), la
            // ignoramos
            if (palabraLimpia.isEmpty()) {
                continue;
            }

            // 4. Comprobamos si esta palabra ya la habíamos guardado antes
            int pos = buscarPosicion(palabraLimpia, palabrasUnicas, indiceUnicas);

            if (pos == -1) {
                // Es nueva: la añadimos al array de únicas
                palabrasUnicas[indiceUnicas] = palabraLimpia;
                contador[indiceUnicas] = 1;
                indiceUnicas++;
            } else {
                // Ya existe: incrementamos su contador en la misma posición
                contador[pos]++;
            }
        }

        // 5. Mostramos el recuento final
        System.out.println("\nRecuento final de palabras:");
        for (int i = 0; i < indiceUnicas; i++) {
            System.out.println(palabrasUnicas[i] + ": " + contador[i]);
        }
    }

    // Método auxiliar para buscar si un String ya existe en un array
    public static int buscarPosicion(String palabra, String[] array, int limite) {
        for (int i = 0; i < limite; i++) {
            if (array[i].equals(palabra)) {
                return i;
            }
        }
        return -1; // No encontrada
    }
}