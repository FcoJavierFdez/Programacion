import java.util.Scanner;

public class Vector3 {

    public static void main(String[] args) {
        /*
         * Introduce un vector de 10 palabras dadas por el usuario y muestralas.
         * Indica si hay palabras respetidas. Muestralas.
         */
        System.out.println("Introduce 10 palabras:");
        Scanner scanner = new Scanner(System.in);
        String[] palabras = new String[10]; 

        for (int i = 0; i < 10; i++) {
            System.out.print("Palabra " + (i + 1) + ": ");
            palabras[i] = scanner.nextLine();
        }   
        System.out.println("Las palabras introducidas son:");
        for (String palabra : palabras) {
            System.out.println(palabra);
        }
        scanner.close();

        // Indica si hay palabras repetidas. Muestralas.
        System.out.println("Palabras repetidas:");
        boolean siRepetidas = false;
        for (int i = 0; i < palabras.length; i++) {
            for (int j = i + 1; j < palabras.length; j++) {
                if (palabras[i].equals(palabras[j])) {
                    System.out.println(palabras[i]);
                    siRepetidas = true;
                    break;
                }
            }
        }

    }
    
}
