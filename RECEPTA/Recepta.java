package RECEPTA;

import java.util.HashMap;

public class Recepta {
    private String nom;
    private HashMap<Ingredient, Integer> ingredients; // Ingredient i quantitat

    public Recepta(String nom) {
        this.nom = nom;
        this.ingredients = new HashMap<>();
    }

    public String getNom() {
        return nom;
    }

    public void afegirIngredient(Ingredient ing, int quantitat) {
        ingredients.put(ing, quantitat);
    }

    // Fase 4.g: Càlculs
    public int calcularTotalKcal() {
        int total = 0;
        for (HashMap.Entry<Ingredient, Integer> entrada : ingredients.entrySet()) {
            total += entrada.getKey().getKcal() * entrada.getValue();
        }
        return total;
    }

    public double calcularCostTotal() {
        double total = 0;
        for (HashMap.Entry<Ingredient, Integer> entrada : ingredients.entrySet()) {
            total += entrada.getKey().getPreu() * entrada.getValue();
        }
        return total;
    }

    public void mostrarDetalls() {
        System.out.println("\n--- RECEPTA: " + nom + " ---");
        for (HashMap.Entry<Ingredient, Integer> entrada : ingredients.entrySet()) {
            System.out.println("- " + entrada.getKey().getNom() + ": " + entrada.getValue() + " unitats.");
        }
        System.out.println("TOTAL: " + calcularTotalKcal() + " kcal | Preu: " + calcularCostTotal() + "€");
    }
}