package ARCHIVOS;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class ExploradorRutesAvancat {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String rutaInput;
        boolean tornarADemanar = true;

        System.out.println("=== Explorador de Rutes Avançat ===");

        while (tornarADemanar) {
            System.out.println("Introdueix una ruta (o prem Intro per eixir): ");
            rutaInput = scanner.nextLine();

            // Si la ruta està buida, canviem la condició d'eixida
            if (rutaInput.trim().isEmpty()) {
                System.out.println("Programa finalitzat.");
                tornarADemanar = false;
            } else {
                // Tota la resta només s'executa si l'usuari HA introduït una ruta vàlida
                System.out.print("Vols veure la informació detallada (S/N)? ");
                String resposta = scanner.nextLine();
                boolean mostrarInfo = resposta.trim().equalsIgnoreCase("S");

                try {
                    mostraInfoRuta(rutaInput, mostrarInfo);
                } catch (FileNotFoundException e) {
                    System.out.println("Error: " + e.getMessage());
                } 
            }
        }
        scanner.close();
    }

    /**
     * Mostra informació sobre una ruta donada, separant directoris i arxius en ordre alfabètic.
     * @param ruta La ruta a analitzar en format de text.
     * @param info Si és true, mostra grandària i data de modificació.
     * @throws FileNotFoundException Si la ruta no existeix.
     */
    public static void mostraInfoRuta(String ruta, boolean info) throws FileNotFoundException {
        File f = new File(ruta);

        if (!f.exists()) {
            throw new FileNotFoundException("La ruta especificada no existeix ");
        }

        System.out.println("\n--> Analitzant: " + f.getAbsolutePath());

        if (f.isFile()) {
            imprimeixDetalls(f, "[A]", info);
        } 
        else if (f.isDirectory()) {
            File[] llista = f.listFiles();

            if (llista == null) {
                System.out.println("No es pot llegir el contingut (manca de permisos).");
                return;
            }

            // Ordenem l'array alfabèticament ignorant majúscules i minúscules
            Arrays.sort(llista, (arxiu1, arxiu2) -> arxiu1.getName().compareToIgnoreCase(arxiu2.getName()));

            // Primer mostrem els directoris
            for (int i = 0; i < llista.length; i++) {
                File element = llista[i];
                if (element.isDirectory()) {
                    imprimeixDetalls(element, "[D]", info);
                }
            }

            // Després mostrem els arxius
            for (int i = 0; i < llista.length; i++) {
                File element = llista[i];
                if (element.isFile()) {
                    imprimeixDetalls(element, "[A]", info);
                }
            }
        }
    }

    /**
     * Mètode auxiliar per formatar i imprimir per pantalla les dades d'un element.
     */
    private static void imprimeixDetalls(File element, String etiqueta, boolean info) {
        if (info) {
            long midaBytes = element.length();
            Date dataModificacio = new Date(element.lastModified()); 
            
            System.out.println(etiqueta + " " + element.getName() + " | " + midaBytes + " bytes | Modificat: " + dataModificacio);
        } else {
            System.out.println(etiqueta + " " + element.getName());
        }
    }
}
