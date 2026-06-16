package BUCLES;

import java.util.Scanner;

/**
 * Programa que muestra un menú de opciones para realizar operaciones. El menú
 * se repetirá hasta que se introduzca la opción 5.
 */
public class MenuOpciones {

    public static void main(String[] args) {

        int opcion, n1, n2, suma, resta, multi, division;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("\n¿Que quieres hacer? ");
            System.out.println("1. Sumar ");
            System.out.println("2. Restar ");
            System.out.println("3. Multiplicar ");
            System.out.println("4. Dividir ");
            System.out.println("5. Salir \n");
            System.out.print("Introduce una opcion: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1: // Sumar
                    System.out.println("Suma de dos numeros");
                    System.out.print("Introduce el primer numero: ");
                    n1 = sc.nextInt();
                    System.out.print("Introduce el segundo numero: ");
                    n2 = sc.nextInt();
                    suma = n1 + n2;
                    System.out.print("La suma de los dos numeros es: " + suma);
                    break;

                case 2: // Restar
                    System.out.println("Resta de dos numeros");
                    System.out.print("Introduce el primer numero: ");
                    n1 = sc.nextInt();
                    System.out.print("Introduce el segundo numero: ");
                    n2 = sc.nextInt();
                    resta = n1 - n2;
                    System.out.print("La resta de los dos numeros es: " + resta);
                    break;

                case 3: // Multiplicacion
                    System.out.println("Multiplicacion de dos numeros");
                    System.out.print("Introduce el primer numero: ");
                    n1 = sc.nextInt();
                    System.out.print("Introduce el segundo numero: ");
                    n2 = sc.nextInt();
                    multi = n1 * n2;
                    System.out.print("La resta de los dos numeros es: " + multi);
                    break;

                case 4: // Division
                    System.out.println("Division de dos numeros");
                    System.out.print("Introduce el primer numero: ");
                    n1 = sc.nextInt();
                    System.out.print("Introduce el segundo numero: ");
                    n2 = sc.nextInt();
                    if (n2 != 0) {
                        division = n1 / n2;
                        System.out.println("La división es: " + division);
                    } else {
                        System.out.println("Error: División entre 0.");
                    }
                    break;

                case 5: // Salir
                    System.out.println("Adios!");
                    break;

                default: // En otro caso
                    System.out.println("Error: opción incorrecta.");
            }

        } while (opcion != 5);

    }

}
