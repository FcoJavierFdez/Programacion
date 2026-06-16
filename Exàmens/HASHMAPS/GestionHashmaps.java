package Exàmens.HASHMAPS;

import java.io.*;
import java.util.*;

public class GestionHashmaps {

    // HASHMAP
    // Clave = especie
    // Valor = lista de animales

    static HashMap<String, ArrayList<String>> refugi = new HashMap<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int opcio;

        // Leer fichero al iniciar
        llegirFitxer();

        do {

            System.out.println("\n===== MENU =====");
            System.out.println("1. Mostrar animals");
            System.out.println("2. Afegir animal");
            System.out.println("3. Eliminar animal");
            System.out.println("4. Guardar fitxer");
            System.out.println("5. Exemple Queue");
            System.out.println("6. Exemple Stack");
            System.out.println("0. Eixir");

            opcio = sc.nextInt();
            sc.nextLine();

            switch (opcio) {

                case 1:
                    mostrarAnimals();
                    break;

                case 2:
                    afegirAnimal(sc);
                    break;

                case 3:
                    eliminarAnimal(sc);
                    break;

                case 4:
                    guardarFitxer();
                    break;

                case 5:
                    exempleQueue();
                    break;

                case 6:
                    exempleStack();
                    break;

                case 0:
                    System.out.println("Programa finalitzat");
                    break;

                default:
                    System.out.println("Opcio incorrecta");
            }

        } while (opcio != 0);

        sc.close();
    }

    // ==================================================
    // LECTURA DE FITXER
    // ==================================================

    public static void llegirFitxer() {

        try {
            BufferedReader br = new BufferedReader(new FileReader("animals.txt"));

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] dades = linea.split(";");

                String especie = dades[0];
                String nom = dades[1];

                // Si la especie no existe la creamos

                if (!refugi.containsKey(especie)) {

                    refugi.put(especie, new ArrayList<String>());
                }
                // Añadimos el animal
                refugi.get(especie).add(nom);
            }

            br.close();
            System.out.println("Fitxer carregat correctament");

        } catch (FileNotFoundException e) {
            System.out.println("No existeix el fitxer");

        } catch (IOException e) {
            System.out.println("Error llegint fitxer");
        }
    }

    // ==================================================
    // MOSTRAR HASHMAP + ARRAYLIST
    // ==================================================

    public static void mostrarAnimals() {

        for (Map.Entry<String, ArrayList<String>> entrada : refugi.entrySet()) {

            System.out.println("\nEspecie: " + entrada.getKey());

            for (String animal : entrada.getValue()) {

                System.out.println("- " + animal);
            }
        }
    }

    // ==================================================
    // AFEGIR ANIMAL
    // ==================================================

    public static void afegirAnimal(
            Scanner sc) {

        System.out.print("Especie: ");

        String especie = sc.nextLine();

        System.out.print("Nom animal: ");

        String nom = sc.nextLine();

        // Si no existe la especie

        if (!refugi.containsKey(especie)) {

            refugi.put(especie, new ArrayList<String>());
        }

        refugi.get(especie).add(nom);

        System.out.println("Animal afegit");
    }

    // ==================================================
    // ELIMINAR ANIMAL
    // ==================================================

    public static void eliminarAnimal(Scanner sc) {

        System.out.print("Especie: ");

        String especie = sc.nextLine();

        System.out.print("Nom: ");

        String nom = sc.nextLine();

        if (refugi.containsKey(especie)) {
            refugi.get(especie).remove(nom);

            System.out.println("Animal eliminat");
        } else {
            System.out.println("Especie no trobada");
        }
    }

    // ==================================================
    // ESCRIURE FITXER
    // ==================================================

    public static void guardarFitxer() {

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("backup_animals.txt"));

            for (Map.Entry<String, ArrayList<String>> entrada : refugi.entrySet()) {
                String especie = entrada.getKey();
                for (String animal : entrada.getValue()) {
                    bw.write(especie + ";" + animal);
                    bw.newLine();
                }
            }
            bw.close();
            System.out.println("Fitxer guardat");

        } catch (IOException e) {
            System.out.println("Error guardant");
        }
    }

    // ==================================================
    // EXEMPLE DE CUA (QUEUE)
    // FIFO
    // Primer en entrar, primer en eixir
    // ==================================================

    public static void exempleQueue() {

        Queue<String> cua = new LinkedList<>();

        cua.offer("Maria");
        cua.offer("Joan");
        cua.offer("Pau");

        System.out.println("\nCONTINGUT CUA");

        while (!cua.isEmpty()) {
            System.out.println("Ates: " + cua.poll());
        }
    }

    // ==================================================
    // EXEMPLE DE PILA (STACK)
    // LIFO
    // Ultim en entrar, primer en eixir
    // ==================================================

    public static void exempleStack() {

        Stack<String> pila = new Stack<>();

        pila.push("Google");
        pila.push("YouTube");
        pila.push("Amazon");

        System.out.println("\nHISTORIAL");

        while (!pila.isEmpty()) {

            System.out.println("Tornar a: " + pila.pop());
        }
    }
}
