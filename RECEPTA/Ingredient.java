package RECEPTA;

public class Ingredient {
    private String nom;
    private int kcal;
    private double preu;

    public Ingredient(String nom, int kcal, double preu) {
        this.nom = nom;
        setKcal(kcal); // Usem el setter per a validar des del principi
        setPreu(preu);
    }
    

    // Getters i Setters amb validació (Fase 1.b)
    public String getNom() {
        return nom;
    }

    public boolean setKcal(int kcal) {
        // Validació: entre 1 i 1000 kcal
        if (kcal >= 1 && kcal <= 1000) {
            this.kcal = kcal;
            return true;
        } else {
            return false;
        }
    }

    public boolean setPreu(double preu) {
        // Validació: entre 0.01 i 100
        if (preu >= 0.01 && preu <= 100) {
            this.preu = preu;
            return true;
        } else {
            return false;
        }
    }

    public int getKcal() {
        return kcal;
    }

    public double getPreu() {
        return preu;
    }

    public void mostrarInfo() {
        System.out.println("Ingredient: " + nom + " | " + kcal + " kcal | " + preu + "€");
    }
}