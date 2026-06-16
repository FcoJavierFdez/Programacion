
/**
 * a) Declara i ompli un vector amb N paraules, on N és un número introduït per
 * teclat i les paraules
 * són cadenes també introduïdes per teclat.
 * b) Mostra aquelles paraules que comencen per vocal.
 * c) Mostra quina és la paraula més llarga, i indica el tamany.
 */

import java.util.Scanner;

public class ComienzaPorVocal {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce un numero de palabras: ");
        int numeroPalabras = scanner.nextInt();

        // a) Declarar y llenar el vector
        String[] palabras = new String[numeroPalabras];
        scanner.nextLine(); // Limpiar buffer
        
        for (int i = 0; i < numeroPalabras; i++) {
            System.out.print("Palabra " + (i + 1) + ": ");
            palabras[i] = scanner.nextLine();
        }

        // Variables para el apartado c)
        String palabraMasLarga = "";

        // b) Mostrar palabras que comienzan por vocal
        System.out.println("\n--- Palabras que comienzan por vocal ---");
        String vocales = "aeiouAEIOU";

        for (String p : palabras) {
            // Comprobamos si la palabra no está vacía y si su primer caracter es vocal
            if (!p.isEmpty() && vocales.indexOf(p.charAt(0)) != -1) {
                System.out.println(p);
            }

            // c) Lógica para encontrar la palabra más larga
            if (p.length() > palabraMasLarga.length()) {
                palabraMasLarga = p;
            }
        }

        // c) Mostrar la palabra más larga y su tamaño
        System.out.println("\n--- Palabra más larga ---");
        if (!palabraMasLarga.isEmpty()) {
            System.out.println("La palabra es: " + palabraMasLarga);
            System.out.println("Tamaño: " + palabraMasLarga.length() + " caracteres.");
        } else {
            System.out.println("No se introdujeron palabras.");
        }

        scanner.close();
    }
}
