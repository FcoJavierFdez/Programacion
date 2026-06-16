package HOSPITAL;

public class Pacient {
    private String codi;
    private String nom;
    private int diesIngres;

    // Constructor senzill
    public Pacient(String nom) {
        this.nom = nom;
        this.codi = "P-" + (int) (Math.random() * 1000); // Codi aleatori senzill
    }

    // Aquest mètode és el que el Gestor usarà per a validar
    public boolean setDiesIngres(int dies) {
        if (dies >= 1 && dies <= 365) {
            this.diesIngres = dies;
            return true; // Tot correcte
        } else {
            System.out.println("Error: Els dies han d'estar entre 1 i 365.");
            return false; // Torna a preguntar!
        }
    }

    public int getDiesIngres() {
        return diesIngres;
    }

    public String getCodi() {
        return codi;
    }

    public String getNom() {
        return nom;
    }
}