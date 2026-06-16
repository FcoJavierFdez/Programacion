
/**
 * Escriu un programa que demana per teclat una paraula i mostre si és palíndrom usant les funcions anteriors.
 */

import java.util.Scanner;

public class Palindromo {

    // Metodo principal
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce una palabra: ");
        String palabra = scanner.nextLine();
        /**
         * Llama al método inversa pasando como parámetro la palabra que introdujo el
         * usuario.
         * El método inversa devuelve la palabra al revés y se guarda en reverse
         */
        String reverse = inversa(palabra);

        // Llama al método iguals para comparar si la palabra original y su reverso son
        // iguales.
        if (iguals(palabra, reverse)) {
            // Si devuelve true, entra en el bloque if e imprime por pantalla es palindromo.
            System.out.println("Es palindromo");
        } else {
            System.out.println("No es palindromo");
        }
        scanner.close();
    }
    // Metodos auxiliares.

    public static String inversa(String s) {
        // Crea una variable reverse vacía donde iremos construyendo la palabra
        // invertida.
        String reverse = "";
        // s.length() - 1 es el índice del último carácter, i >= 0 asegura que llegamos
        // al primer carácter.
        for (int i = s.length() - 1; i >= 0; i--) {
            // s.charAt(i) devuelve el carácter en la posición i.
            reverse += s.charAt(i); // concatena ese carácter al final de la variable reverse, construyendo la
                                    // palabra al revés.
        }
        // Devuelve la palabra invertida al lugar donde se llamó al método (String
        // reverse = inversa(palabra);)
        return reverse;
    }

    public static boolean iguals(String a, String b) {
        return a.equals(b);
    }
}
