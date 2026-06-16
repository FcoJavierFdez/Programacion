package BUCLES;

// Programa que suma independentment els parells i els imparells dels números compresos entre 100 i 200.
public class Ejemplo2 {
    public static void main(String[] args) {
        int pars, imparells, cont;

        pars = 0;
        imparells = 0;

        for (cont = 100; cont <= 200; cont++) {
            if (cont % 2 == 0)
                pars = pars + cont;
            else
                imparells = imparells + cont;
        }

        System.out.println("La suma total dels parells es: " + pars);
        System.out.println("La suma total dels impareslls es: " + imparells);
    }
}
