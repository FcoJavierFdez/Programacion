package com.restaurant;

/**
 * Clase abastracta Producte que sirve como
 * base para los diferentes tipos de productos del restaurante
 * 
 * @return
 */
public abstract class Producte {
    protected String nom;
    protected double preu_base;

    // Constructor que inicializa el nombre y el precio base del producto.
    public Producte(String nom, double preu_base) {
        this.nom = nom;
        this.preu_base = preu_base;
    }

    // Getter para obtener el nombre del producto.
    public String getNom() {
        return nom;
    }

    // Getter para obtener el precio base del producto.
    public double getPreuBase() {
        return preu_base;
    }
}
