
/**
 * Dissenya un sistema que permeta afegir un estudiant, 
 * marcar la seva assistència i mostrar l'estat d'assistència de tots els estudiants. 
 * Quan l'usuari vulga afegir un estudiant, el programa demanarà el nom i l'incorporarà a 
 * la llista de persones matriculades només si no hi és ja, assignant-li automàticament l'estat de no assistit; 
 * si el nom ja existeix, no fa cap canvi. 
 * Quan l'usuari tria marcar assistència, el sistema demana el nom de l'estudiant i, 
 * si el troba a la llista, canvia el seu estat al de assistit, 
 * i si no existeix, informa que no es pot marcar l'assistència d'un estudiant desconegut. 
 * Quan l'usuari demana veure els assistents, el programa mostrarà tots els noms i si han assistit o no. 
 * Finalment, l'usuari podrà finalitzar quan vulga l'execució del programa.
 */

import java.util.Scanner;
import java.util.HashMap;

public class MenuHashmap {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        HashMap<String, Boolean> estudiantes = new HashMap<>();

        int opcion;

        do {
            menu();
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1:
                    System.out.println("\nVamos a añadir a un nuevo estudiante...");
                    anadeEstudiante(estudiantes);
                    break;
                case 2:
                    System.out.println("\nVamos a marcar la asistencia..");
                    marcarAsistencia(estudiantes);
                    break;
                case 3:
                    System.out.println("\nVamos a mostrar los estados de asistencias...");
                    mostrarEstados(estudiantes);
                    break;
                case 4:
                    System.out.println("Salimos de la aplicación");
                    break;
                default:
                    System.out.println("Opción incorrecta");

            }
            System.out.println("");

        } while (opcion != 4);

    }

    public static void menu() {
        System.out.println("\n------MENU------");
        System.out.println("1. Añadir un estudiante ");
        System.out.println("2. Marcar la asistencia ");
        System.out.println("3. Mostrar el estado de asistencia de todos los estudiantes ");
        System.out.println("4. Salir ");
        System.out.print("Elige una opción: ");
    }

    public static void anadeEstudiante(HashMap<String, Boolean> mapa) {

        System.out.print("Nombre del estudiante: ");
        String nombre = sc.nextLine();

        if (mapa.containsKey(nombre.toLowerCase())) {
            System.out.println("ERROR: el estudiante ya existe");
        } else {
            mapa.put(nombre.toLowerCase(), false);
            System.out.println("Estudiante añadido como NO ASISTIDO.");
        }
    }

    public static void marcarAsistencia(HashMap<String, Boolean> mapa) {
        System.out.print("Nombre del estudiante por marcar asistencia: ");
        String nombre = sc.nextLine().toLowerCase();

        if (mapa.containsKey(nombre)) {
            mapa.put(nombre, true);
            System.out.println("Asistencia marcada!");
        }else{
            System.out.println("ERROR: Estudiante no encontrado.");
        }
    }

    public static void mostrarEstados(HashMap<String, Boolean> mapa) {
        if (mapa.isEmpty()) {
            System.out.println("No hay estudiantes registrados");
            return;
        }

        System.out.println("------LISTA DE ESTUDIANTES REGISTRADOS------");
        for (String nombre : mapa.keySet()) {
            boolean estado = mapa.get(nombre);
            System.out.println(nombre + " ----> " + (estado ? "ASISTIDO" : "NO ASISTIDO"));
        }
    }

}
