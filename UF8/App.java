package UF8;
/*Gestor Musical */

import java.util.Scanner;

public class App {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("--- BENVINGUT AL GESTOR D'ÀLBUMS ---");
        System.out.print("Nom de l'àlbum: ");
        String nomAlbum = sc.nextLine();
        Album album = new Album(nomAlbum);
        afegirCanconsAlAlbumTeclat(album);
        // Resultats
        album.mostrarAlbum();
        album.filtrarLlargaDurada();
        album.mostrarExtrems();
        album.recomanacioAleatoria();

        sc.close();
    }

    public static void afegirCanconsAlAlbumTeclat(Album album) {

        System.out.print("Vols afegir una cançó? (s/n): ");
        String continuar = sc.nextLine();

        while (continuar.equalsIgnoreCase("s")) {

            System.out.print("\nArtista: ");
            String artista = sc.nextLine();

            System.out.print("Títol: ");
            String titol = sc.nextLine();

            Canco c = new Canco(artista);
            c.setTitol(titol);

            boolean duracioCorrecta = false;
            int duracio = 0;
            while (!duracioCorrecta) {
                System.out.print("Duració (segons): ");
                duracio = sc.nextInt();
                sc.nextLine(); // neteja buffer

                duracioCorrecta = c.setDuracio(duracio);
            }

            album.afegirCanco(c);

            System.out.print("Vols afegir una altra cançó? (s/n): ");
            continuar = sc.nextLine();
        }

    }
}