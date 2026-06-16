package HERENCIA_1;

import java.util.ArrayList;

public class App {

    public static void main(String[] args) {

        Persona p = new Persona("Pepe", "00000000T", "C/ Colón", 666666666);
        System.out.println("\nMostrem una persona");
        p.mostrarPersona();

        ArrayList<Integer> notes = new ArrayList<>();
        notes.add(7);
        notes.add(9);
        notes.add(6);

        Alumne a = new Alumne("Maria", "12345678Z", "P/ Llibertat", 111111111, 1, "DAM", 1, notes);

        System.out.println("\nMostrem un alumne");
        a.mostrarPersona();

        ArrayList<String> moduls = new ArrayList<>();
        moduls.add("Programació");
        moduls.add("Llenguatges de marques");
        moduls.add("Entorns de desenvolupament");

        Professor profe = new Professor("Joan", "00000001R", "C/ Major", 222222222, 3, "Informàtica", moduls, "Matins");

        System.out.println("\nMostrem un professor");
        profe.mostrarPersona();
    }
}