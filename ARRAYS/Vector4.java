import java.util.Scanner;

public class Vector4 {

    public static void main(String[] args) {

        /*
         * Declara un vector on s'emmagatzemen les respostes correctes
         * del test. Usa números enters aleatoris entre 1 i 4.
         */
        int[] respostesCorrectes = new int[10];
        for (int i = 0; i < respostesCorrectes.length; i++) {
            respostesCorrectes[i] = (int) (Math.random() * 4) + 1;
        }

        // Almacena las respuestas de un estudiante en otro vector. Respuesta 0 se
        // considera como no contestada.
        int[] respostesEstudiant = new int[10];
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introdueix les respostes de l'estudiant (números entre 0 i 4):");
        for (int i = 0; i < respostesEstudiant.length; i++) {
            System.out.print("Resposta " + (i + 1) + ": ");
            respostesEstudiant[i] = scanner.nextInt();
        }
        scanner.close();

        // Muestra cuantas preguntas han contestado correctamente, cuantas incorrectas y
        // cuantas no han contestado
        int correctes = 0;
        int incorrectes = 0;
        int noContestades = 0;
        for (int i = 0; i < respostesCorrectes.length; i++) {
            if (respostesEstudiant[i] == 0) {
                noContestades++;
            } else if (respostesEstudiant[i] == respostesCorrectes[i]) {
                correctes++;
            } else {
                incorrectes++;
            }
        }
        System.out.println("Preguntes correctes: " + correctes);
        System.out.println("Preguntes incorrectes: " + incorrectes);
        System.out.println("Preguntes no contestades: " + noContestades);

        /*
         * Calcula y muestra la nota obtenida. Si la nota sale negativa, será 0.
         * Usa la siguiente ecuación: nota = (n_correctes - 0.33 * n_incorrectes) /
         * n_total
         */

        double nota = (correctes - 0.33 * incorrectes) / respostesCorrectes.length;

        if (nota < 0) {
            nota = 0;
        }
        System.out.printf("Nota obtinguda: %.2f%n", nota);

    }

}
