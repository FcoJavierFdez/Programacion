package BUCLES;
// Mostra els números del 320 al 160, contant de 20 en 20 cap a baix utilitzant el bucle while

public class Ejemplo4 {

    public static void main(String[] args) {
        int i = 320;

        while (i >= 160) {
            System.out.println(i);
            i -= 20;
        }
    }
}
