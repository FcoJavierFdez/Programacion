package UF8;

import java.util.ArrayList;
import java.util.Random;

public class Album {
    private String nom;
    private ArrayList<Canco> llistaCancons;

    public Album(String nom) {
        this.nom = nom;
        this.llistaCancons = new ArrayList<>();
    }

    public void afegirCanco(Canco c) {
        llistaCancons.add(c);
    }

    public void mostrarAlbum() {
        System.out.println("\n--- ÀLBUM: " + nom + " ---");
        for (Canco c : llistaCancons) {
            c.mostrarInfo();
        }
    }

    // Fase 3: Filtre > 300s
    public void filtrarLlargaDurada() {
        System.out.println("\nCançons de més de 5 minuts:");
        for (Canco c : llistaCancons) {
            if (c.getDuracio() > 300)
                c.mostrarInfo();
        }
    }

    // Fase 4: Estadístiques
    public int getDuradaTotal() {
        int total = 0;
        for (Canco c : llistaCancons)
            total += c.getDuracio();
        return total;
    }

    public void mostrarExtrems() {

        if (llistaCancons.size() > 0) {

            Canco curta = llistaCancons.get(0);
            Canco llarga = llistaCancons.get(0);

            // Comencem des de l'índex 1 perquè el 0 ja l'hem assignat
            for (int i = 1; i < llistaCancons.size(); i++) {
                Canco c = llistaCancons.get(i);

                if (c.getDuracio() < curta.getDuracio()) {
                    curta = c;
                }
                if (c.getDuracio() > llarga.getDuracio()) {
                    llarga = c;
                }
            }

            System.out.print("Més curta: ");
            curta.mostrarInfo();
            System.out.print("Més llarga: ");
            llarga.mostrarInfo();
        } else {
            System.out.println("La llista està buida.");
        }
    }

    // Fase 5: Aleatòria
    public void recomanacioAleatoria() {
        if (llistaCancons.size() > 0) {
            Random r = new Random();
            int index = r.nextInt(llistaCancons.size());
            System.out.print("\nRecomanació d'avui: ");
            llistaCancons.get(index).mostrarInfo();
        } else {
            System.out.println("La llista està buida.");
        }
    }

}