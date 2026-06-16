
/**
 * Exercici 4:
 * a) Escriu una funció que mostre un menú amb les opcions: sumar, restar,
 * multiplicar, dividir i acabar el programa.
 * b) Escriu una funcució que retorne la suma de dos nombres rebuts per
 * paràmetres.
 * c) Escriu una funció que retorne la resta de dos nombres rebuts per
 * paràmetres.
 * d) Escriu una funció que retorne la multiplicació de dos nombres rebuts per
 * paràmetres.
 * e) Escriu una funció que retorne la divisió de dos nombres rebuts per
 * paràmetres (tenint en compte la divisió per zero).
 * f) Escriu un programa que demane dos nombres, mostre el menú i permeta triar
 * una opció. Ho farà fins que l'usuari seleccione l'opció d'eixir del programa.
 */

import java.util.Scanner;

public class MenuFunciones {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;

        // Pedimos los números al principio como indica el enunciado
        System.out.print("Introduce el primer número: ");
        double num1 = sc.nextDouble();
        System.out.print("Introduce el segundo número: ");
        double num2 = sc.nextDouble();

        do {
            menu(); // Mostramos el menú (Apartado A)
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Resultado de la suma: " + sumar(num1, num2));
                    break;
                case 2:
                    System.out.println("Resultado de la resta: " + restar(num1, num2));
                    break;
                case 3:
                    System.out.println("Resultado de la multiplicación: " + multiplicar(num1, num2));
                    break;
                case 4:
                    // Guardamos el resultado para controlar el error de división por cero
                    double resultadoDiv = dividir(num1, num2);
                    if (num2 != 0) {
                        System.out.println("Resultado de la división: " + resultadoDiv);
                    }
                    break;
                case 5:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }

            // Si no ha elegido salir, hacemos una pausa estética
            if (opcion != 5) {
                System.out.println("\nPresiona Enter para continuar...");
                sc.nextLine(); // Limpiar buffer
                sc.nextLine(); // Pausa real
            }

        } while (opcion != 5); // Repetir hasta que la opción sea 5 (Apartado F)

        sc.close();
    }

    // a) Función menú
    public static void menu() {
        System.out.println("\n--- MENÚ DE OPERACIONES ---");
        System.out.println("1. Sumar");
        System.out.println("2. Restar");
        System.out.println("3. Multiplicar");
        System.out.println("4. Dividir");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opción: ");
    }

    // b) Suma
    public static double sumar(double a, double b) {
        return a + b;
    }

    // c) Resta
    public static double restar(double a, double b) {
        return a - b;
    }

    // d) Multiplicación
    public static double multiplicar(double a, double b) {
        return a * b;
    }

    // e) División con control de cero
    public static double dividir(double a, double b) {
        if (b == 0) {
            System.out.println("Error: No se puede dividir por cero.");
            return 0;
        }
        return a / b;
    }
}