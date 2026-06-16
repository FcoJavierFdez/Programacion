package BUCLES;

import java.util.Scanner;

// Crea un programa que pensa un número a l'atzar entre 0 i 100. 
// L'usuari ha de endevinar-ho i té per a això 5 oportunitats. Després de cada intent fallit, 
// el programa dirà quantes oportunitats queden i si el nombre introduït és menor o major que el que ha pensat.
public class Ejemplo6 {

    public static void main(String[] args) {

        // Declaració de variables
        final int OPORTUNITATS = 5;
        int nUsuari, nMisterios, intent;
        boolean encertat = false;
        Scanner entrada = new Scanner(System.in);

        // Numero al azar
        nMisterios = (int) (Math.random() * 101);

        // Petició del número i cálcul
        System.out.println("Estic pensant un número entre el 0 i el 100. Tens 5 oportunitats per a endivinar-lo.");
        intent = OPORTUNITATS;

        do {
            System.out.print("Introduce un numero:");
            nUsuari = entrada.nextInt();
            intent--;

            if (nUsuari == nMisterios) {
                encertat = true;
                System.out.println("El numero ha sido acertado");
            } else {
                if (nUsuari < nMisterios) {
                    System.out.println("El numero que estas pensando es mayor al numero introducido");
                } else {
                    System.out.println("El numero que estas pensando es menor al numero introducido");
                }
                System.out.println("Te quedan: " + intent + " oportunidades");
            }

        } while (!encertat && (intent > 0));
        if (!encertat) {
            System.out.println("Lo siento mucho no lo has logrado adivinar");
        }
    }
}
