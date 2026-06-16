
/**
 *  Escribir un programa que lea una frase por teclado y muestre el número de vocales, 
 * el número de espacios y el número de consonantes.
 */

import java.util.Scanner;

public class CuentVocalEspCon {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("\nIntroduce una frase: ");
        String cadena = scanner.nextLine();

        System.out.println("\nVocales: " + cuentaVocales(cadena));
        System.out.println("Espacios: " + cuentaEspacios(cadena));
        System.out.println("Consonantes: " + cuentaConsonantes(cadena));

        // Cerramos el scanner
        scanner.close();

    }

    public static int cuentaVocales(String s) {
        int contador = 0;
        // Pasamos todo a minúsculas
        s = s.toLowerCase();
        for (char c : s.toCharArray()) { // recorre cada letra del string s
            if ("aeiouáéíóú".indexOf(c) != -1) { // Comprueba si el caracter c es una vocal, si no lo encuentra devuelve -1,
                                            // si lo encuentra devuelve su posicion 0,1,2...
                // Si es distinto de -1 es vocal
                contador++;
            }
        }
        // Devolvemos el número total de vocales encontradas
        return contador;
    }

    public static int cuentaEspacios(String s) {
        int contador = 0;

        for (char c : s.toCharArray()) {
            if (c == ' ') {
                contador++;
            }
        }
        return contador;
    }

    public static int cuentaConsonantes(String s) {
        int contador = 0;
        s = s.toLowerCase();

        for (char c : s.toCharArray()) {
            // Lógica: Es una letra Y NO es una vocal
            if (Character.isLetter(c) && "aeiouáéíóú".indexOf(c) == -1) {
                contador++;
            }
        }
        return contador;
    }

}
