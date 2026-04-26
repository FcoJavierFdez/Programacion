package RECEPTA;

/*Gestor */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class App {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ArrayList<Recepta> receptes = new ArrayList<>();

        int opcio;

        do {
            mostrarMenu();
            opcio = sc.nextInt();
            sc.nextLine();

            if (opcio == 1) {
                // Afegir ingredient
                Ingredient ing = crearIngredient();
                ingredients.add(ing);
                System.out.println("Ingredient afegit.");

            } else if (opcio == 2) {
                // Llistar ingredients
                mostrarIngredients(ingredients);

            } else if (opcio == 3) {
                // Cercar ingredient
                System.out.print("Nom de l'ingredient a cercar: ");
                String nom = sc.nextLine();
                buscarIngredientPerNom(nom, ingredients);

            } else if (opcio == 4) {
                // Afegir recepta (1 recepta)
                if (ingredients.size() == 0) {
                    System.out.println("Primer has de crear almenys un ingredient.");
                } else {
                    Recepta r = crearUnaRecepta(ingredients);
                    receptes.add(r);
                    System.out.println("Recepta afegida.");
                }

            } else if (opcio == 5) {
                // Llistar receptes (ordre alfabètic)
                mostrarReceptesOrdenades(receptes);

            } else if (opcio == 6) {
                // Filtre econòmic < 3€
                mostrarReceptesEconomiques(receptes, opcio);

            } else if (opcio == 7) {
                // Recomanació aleatòria
                recomanarReceptaAleatoria(receptes);

            } else if (opcio == 8) {
                // Mostrar estadístiques
                mostrarEstadistiques(receptes);

            } else if (opcio == 0) {
                System.out.println("Eixint del programa...");

            } else {
                System.out.println("Opció no vàlida.");
            }

        } while (opcio != 0);
    }

    public static Ingredient crearIngredient() {
        System.out.print("Nom del Ingredient: ");
        String nom = sc.nextLine();

        System.out.print("Kcal: ");
        int kcal = sc.nextInt();

        System.out.print("Preu (€): ");
        double preu = sc.nextDouble();
        sc.nextLine();

        Ingredient ing = new Ingredient(nom, kcal, preu);
        return ing;
    }

    public static Recepta crearUnaRecepta(ArrayList<Ingredient> ingredients) {

        System.out.print("\nNom de la recepta: ");
        String nomRec = sc.nextLine();

        Recepta r = new Recepta(nomRec);

        char opIng;
        do {
            System.out.print("Vols afegir un ingredient a la recepta? (s/n): ");
            opIng = sc.nextLine().toLowerCase().charAt(0);

            if (opIng == 's') {

                // Mostrar ingredients disponibles
                for (int i = 0; i < ingredients.size(); i++) {
                    System.out.println(i + " - " + ingredients.get(i).getNom());
                }

                System.out.print("Tria ingredient (posició): ");
                int pos = sc.nextInt();

                System.out.print("Quantitat: ");
                int quantitat = sc.nextInt();
                sc.nextLine();

                r.afegirIngredient(ingredients.get(pos), quantitat);
            }

        } while (opIng == 's');

        return r;
    }

    public static void mostrarIngredients(ArrayList<Ingredient> ingredients) {
        if (ingredients.size() == 0) {
            System.out.println("No hi ha ingredients.");
        } else {
            System.out.println("\n--- INGREDIENTS ---");
            for (Ingredient ing : ingredients) {
                ing.mostrarInfo();
            }
        }
    }

    // ---------- FASE 3.e ----------
    public static void buscarIngredientPerNom(String nomBuscat, ArrayList<Ingredient> ingredients) {
        boolean trobat = false;

        for (Ingredient ing : ingredients) {
            if (ing.getNom().equalsIgnoreCase(nomBuscat)) {
                ing.mostrarInfo();
                trobat = true;
            }
        }

        if (!trobat) {
            System.out.println("No s'ha trobat l'ingredient: " + nomBuscat);
        }
    }

    public static void mostrarReceptesOrdenades(ArrayList<Recepta> receptes) {

        List<Recepta> copia = new ArrayList<>(receptes);

        Collections.sort(copia, new Comparator<Recepta>() {
            public int compare(Recepta r1, Recepta r2) {
                return r1.getNom().compareToIgnoreCase(r2.getNom());
            }
        });

        System.out.println("\n--- RECEPTES ORDENADES ALFABÈTICAMENT ---");
        for (Recepta r : copia) {
            System.out.println("- " + r.getNom());
        }
    }

    public static void mostrarReceptesEconomiques(ArrayList<Recepta> receptes, int limit) {

        boolean hiHa = false;

        System.out.println("\n--- RECEPTES AMB COST INFERIOR A " + limit + " € ---");

        for (Recepta r : receptes) {
            if (r.calcularCostTotal() < limit) {
                r.mostrarDetalls();
                hiHa = true;
            }
        }

        if (!hiHa) {
            System.out.println("No hi ha cap recepta amb un cost inferior a " + limit + " €");
        }
    }

    // Fase 5: Estadístiques globals
    public static void mostrarEstadistiques(ArrayList<Recepta> receptes) {

        if (receptes.size() == 0) {
            System.out.println("No hi ha receptes per a calcular estadístiques.");
        } else {

            double sumaCost = 0;
            int sumaKcal = 0;
            Recepta mesLleugera = receptes.get(0);

            for (Recepta r : receptes) {
                sumaCost += r.calcularCostTotal();
                sumaKcal += r.calcularTotalKcal();

                if (r.calcularTotalKcal() < mesLleugera.calcularTotalKcal()) {
                    mesLleugera = r;
                }
            }

            System.out.println("\n--- ESTADÍSTIQUES GLOBALS ---");
            System.out.println("Mitjana Preu: " + (sumaCost / receptes.size()) + "€");
            System.out.println("Recepta més lleugera: " + mesLleugera.getNom());
        }
    }

    public static void recomanarReceptaAleatoria(ArrayList<Recepta> receptes) {

        if (receptes.size() == 0) {
            System.out.println("No hi ha receptes disponibles per a recomanar.");
        } else {
            Random rnd = new Random();
            int pos = rnd.nextInt(receptes.size()); // 0 .. size-1
            Recepta triada = receptes.get(pos);

            System.out.println("\n--- SUGGERIMENT DE MENÚ (ALEATORI) ---");
            triada.mostrarDetalls(); // ja mostra ingredients, quantitats, totals kcal i preu
        }
    }

    // ---------------- MENÚ ----------------

    public static void mostrarMenu() {
        System.out.println("\n===== MENÚ =====");
        System.out.println("1) Afegir ingredient");
        System.out.println("2) Llistar ingredients");
        System.out.println("3) Cercar ingredient per nom");
        System.out.println("4) Crear recepta");
        System.out.println("5) Llistar receptes (alfabètic)");
        System.out.println("6) Filtre econòmic (< 3€)");
        System.out.println("7) Recomanació aleatòria");
        System.out.println("8) Estadístiques globals");
        System.out.println("0) Eixir");
        System.out.print("Tria una opció: ");
    }

}
