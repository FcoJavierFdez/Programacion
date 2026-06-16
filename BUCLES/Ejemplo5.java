package BUCLES;

// Realitza un programa que demane un número per teclat i després ens mostre el número al revés.
import java.util.*;

public class Ejemplo5 {

    public static void main(String[] args) {

        // Declaracion de variables
        int numero, auxiliar, reves;
        Scanner sc = new Scanner(System.in);

        // Peticion de datos
        System.out.print("Introduce un numero entero: ");
        numero = sc.nextInt();

        // Inversion del numero
        reves = 0;
        auxiliar = numero;

        while (auxiliar > 0) {
            reves = (reves * 10) + (auxiliar % 10);
            auxiliar = auxiliar / 10;
        }
        System.out.println("El numero " + numero + "girado del reves es: " + reves);
        sc.close();
    }
}
