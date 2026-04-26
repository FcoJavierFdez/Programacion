package ARCHIVOS;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ExploradorRutes {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String rutaInput;

        System.out.println("=== Explorador de Rutes ===");
        boolean tornarADemanar = true;

        // El procés es repetirà fins que l'usuari introduïsca una ruta buida
        while (tornarADemanar) {
            System.out.print("\nIntrodueix una ruta (o prem Intro per eixir): ");
            rutaInput = scanner.nextLine();

            if (rutaInput.trim().isEmpty()) {
                System.out.println("Programa finalitzat.");
                tornarADemanar = false;
            } else {

                // Cal manejar les possibles excepcions
                try {
                    mostraInfoRuta(rutaInput);
                } catch (FileNotFoundException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
        scanner.close();
    }

    /**
     * Mostra informació sobre una ruta donada.
     * * @param ruta La ruta a analitzar en format de text.
     * @throws FileNotFoundException Si la ruta no existeix en el sistema.
     */
    public static void mostraInfoRuta(String ruta) throws FileNotFoundException {
        File f = new File(ruta); // La classe File representa una ruta dins del sistema d'arxius.

        // Si el path no existeix llançarà una excepció
        if (!f.exists()) {
            throw new FileNotFoundException("La ruta especificada no existeix ");
        }

        System.out.println("--> Analitzant: " + f.getAbsolutePath());

        // Si és un arxiu, mostrarà per pantalla el nom de l'arxiu
        if (f.isFile()) {
            System.out.println("[A] " + f.getName());
        } 
        // Si és un directori, mostrarà la llista de directoris i arxius
        else if (f.isDirectory()) {
            File[] llista = f.listFiles(); // Retorna un vector amb tots els elements

            if (llista == null) {
                System.out.println("No es pot llegir el contingut (manca de permisos o error I/O).");
                return;
            }

            // Haurà de mostrar primer els directoris
            for (File element : llista) {
                if (element.isDirectory()) { // Comprova si és una carpeta
                    System.out.println("[D] " + element.getName());
                }
            }

            // I després els arxius
            for (File element : llista) {
                if (element.isFile()) { // Comprova si és un fitxer
                    System.out.println("[A] " + element.getName());
                }
            }
        }
    }
}