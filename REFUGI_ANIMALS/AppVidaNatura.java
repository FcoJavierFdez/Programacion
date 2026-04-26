package REFUGI_ANIMALS;

import java.util.ArrayList;
import java.util.Scanner;

public class AppVidaNatura {

    static Scanner scanner = new Scanner(System.in);
    static Refugi refugi = new Refugi();

    public static void main(String[] args) throws AdopcioDenegadaException {
        boolean eixir = false;

        // Dades de prova (per no començar buit)
        refugi.afegirAnimal("Gos", new Gos("Rex", 5, true, "Pastor Alemany"));
        refugi.afegirAnimal("Gat", new Gat("Mixa", 2, true, false));
        refugi.afegirAnimal("Ocell", new Ocell("Piu", 1, true, "Canari", false, true));

        while (!eixir) {
            mostrarMenu();
            int opcio = llegirEnter("Selecciona una opció: ");

            switch (opcio) {
                case 1:
                    mostrarTots();
                    break;
                case 2:
                    mostrarDetallAnimal();
                    break;
                case 3:
                    afegirNouAnimal();
                    break;
                case 4:
                    processarAdopcio();
                    break;
                case 5:
                    actualitzarEdat();
                    break;
                case 6:
                    actualitzarEspecifics();
                    break;
                case 7:
                    sesioTerapia();
                    break;
                case 8:
                    realitzarExhibicio();
                    break;
                case 9:
                    System.out.println("Tancant l'aplicació...");
                    eixir = true;
                    break;
                default:
                    System.out.println("Opció no vàlida.");
                    break;
            }
        }
    }

    // --- MÈTODES DEL MENÚ ---

    private static void mostrarMenu() {
        System.out.println("\n=== REFUGI VIDA I NATURA ===");
        System.out.println("1. Mostrar lArrayLista bàsica");
        System.out.println("2. Mostrar detall d'un animal (per ID)");
        System.out.println("3. Afegir animal");
        System.out.println("4. Adoptar animal");
        System.out.println("5. Sumar edat (+1)");
        System.out.println("6. Actualitzar dades específiques (Gat/Ocell)");
        System.out.println("7. Sessió de Teràpia (Gats)");
        System.out.println("8. Exhibició de Vol (Ocells)");
        System.out.println("9. Eixir");
    }

    private static void mostrarTots() {
        ArrayList<Animal> tots = refugi.getTotsElsAnimals();
        if (tots.isEmpty())
            System.out.println("No hi ha animals al refugi.");
        for (Animal a : tots) {
            System.out.println(a.informacioBasica());
        }
    }

    private static void mostrarDetallAnimal() {
        int id = llegirEnter("Introdueix l'ID de l'animal: ");
        Animal a = refugi.buscarAnimalPerId(id);
        if (a != null) {
            System.out.println(a.informacioDetallada());
        } else {
            System.out.println(" Animal no trobat.");
        }
    }

    private static void afegirNouAnimal() throws AdopcioDenegadaException {
        System.out.println("Tipus: 1. Gos | 2. Gat | 3. Ocell");
        int tipus = llegirEnter("Selecció: ");

        System.out.print("Nom: ");
        String nom = scanner.nextLine();
        int edat = llegirEnter("Edat: ");
        boolean vacunat = llegirBoolea("Està vacunat? (s/n): ");

        switch (tipus) {
            case 1 -> { // Gos
                System.out.print("Raça: ");
                String raca = scanner.nextLine();
                refugi.afegirAnimal("Gos", new Gos(nom, edat, vacunat, raca));
            }
            case 2 -> { // Gat
                boolean conducta = llegirBoolea("Té problemes de conducta? (s/n): ");
                refugi.afegirAnimal("Gat", new Gat(nom, edat, vacunat, conducta));
            }
            case 3 -> { // Ocell
                System.out.print("Raça/Espècie: ");
                String racaO = scanner.nextLine();
                boolean salvatge = llegirBoolea("És salvatge? (s/n): ");
                boolean vola = llegirBoolea("Pot volar? (s/n): ");
                refugi.afegirAnimal("Ocell", new Ocell(nom, edat, vacunat, racaO, salvatge, vola));
            }
            default -> System.out.println("Tipus incorrecte.");
        }
    }

    private static void processarAdopcio() throws AdopcioDenegadaException {
        System.out.println("De quina espècie vols adoptar? (Gos/Gat/Ocell)");
        String especie = scanner.nextLine(); // Respectem majúscules/minúscules segons com s'ha guardat al Map
        // Per robustesa, podriem normalitzar el text, però assumim input correcte
        // segons enunciat

        ArrayList<Animal> lArrayLista = refugi.getAnimalsPerEspecie(especie);

        System.out.println("--- Candidats per a adopció (" + especie + ") ---");
        boolean hiHaCandidats = false;

        for (Animal a : lArrayLista) {
            // Comprovem si compleix els requisits abans de mostrar-lo
            if (a.esAptaPerAdopcio()) {
                System.out.println(a.informacioBasica());
                hiHaCandidats = true;
            }
        }

        if (!hiHaCandidats) {
            System.out.println("No hi ha animals d'aquesta espècie aptes per adoptar.");
            return;
        }

        int id = llegirEnter("Introdueix l'ID de l'animal a adoptar: ");
        Animal a = refugi.buscarAnimalPerId(id);

        if (a != null && a.getEspecie().equalsIgnoreCase(especie) && a.esAptaPerAdopcio()) {
            a.adoptar(); // Missatge de comiat
            refugi.eliminarAnimal(a); // Esborrar del registre
        } else {
            System.out.println(" No s'ha pogut adoptar (ID incorrecte o no apte).");
        }
    }

    private static void actualitzarEdat() {
        int id = llegirEnter("ID de l'animal: ");
        Animal a = refugi.buscarAnimalPerId(id);
        if (a != null)
            a.envellir();
        else
            System.out.println("Animal no trobat.");
    }

    private static void actualitzarEspecifics() {
        int id = llegirEnter("ID de l'animal: ");
        Animal a = refugi.buscarAnimalPerId(id);

        if (a instanceof Gat) {
            ((Gat) a).canviarConducta();
        } else if (a instanceof Ocell) {
            ((Ocell) a).canviarCapacitatVol();
        } else if (a instanceof Gos) {
            System.out.println("Els gossos no tenen atributs actualitzables en aquest menú.");
        } else {
            System.out.println("Animal no trobat.");
        }
    }

    private static void sesioTerapia() {
        System.out.println("--- GATS APTES PER A TERÀPIA ---");
        ArrayList<Animal> gats = refugi.getAnimalsPerEspecie("Gat");
        for (Animal anim : gats) {
            Gat g = (Gat) anim;
            if (!g.teProblemesConducta()) { // Filtre: sense problemes
                System.out.println(g.informacioBasica());
            }
        }

        int id = llegirEnter("Selecciona ID per iniciar sessió: ");
        Animal a = refugi.buscarAnimalPerId(id);
        if (a instanceof Gat) {
            ((Gat) a).ferTerapia();
        }
    }

    private static void realitzarExhibicio() {
        System.out.println("--- OCELLS PER A EXHIBICIÓ ---");
        ArrayList<Animal> ocells = refugi.getAnimalsPerEspecie("Ocell");
        for (Animal anim : ocells) {
            Ocell o = (Ocell) anim;
            if (o.isPotVolar() && !o.isEsSalvatge()) { // Filtre
                System.out.println(o.informacioBasica());
            }
        }

        int id = llegirEnter("Selecciona ID per volar: ");
        Animal a = refugi.buscarAnimalPerId(id);
        if (a instanceof Ocell) {
            ((Ocell) a).ferExhibicio();
        }
    }

    // --- Helpers per a lectura de dades ---

    private static int llegirEnter(String missatge) {
        int resultat = 0;
        boolean dadaCorrecta = false;

        while (dadaCorrecta == false) {
            System.out.print(missatge);

            if (scanner.hasNextInt()) {
                // Si hi ha un enter esperant
                resultat = scanner.nextInt();
                dadaCorrecta = true;
            } else {
                // Si no és un enter
                System.out.println("Error: Això no és un número vàlid.");
                scanner.next(); // 4. Tirem el text incorrecte per a poder tornar a demanar
            }
            scanner.nextLine(); // Netejem buffer (el salt de linea)
        }

        return resultat;
    }

    private static boolean llegirBoolea(String missatge) {
        System.out.print(missatge);
        String s = scanner.nextLine().toLowerCase();
        return s.equals("s") || s.equals("si");
    }
}