package com.restaurant;

public class Producte {

    // id no es necesario para el funcionamiento, pero se incluye para futuras
    // mejoras o funcionalidades adicionales.
    protected int id;
    protected String nom;
    protected double preuBase;

    // Constructor con id, aunque no se utiliza en el código actual.
    public Producte(int id, String nom, double preuBase) {
        this.id = id;
        this.nom = nom;
        this.preuBase = preuBase;
    }

    // Constructor sin id, asignando id = 0 por defecto.
    public Producte(String nom, double preuBase) {
        this.nom = nom;
        this.preuBase = preuBase;
    }

    // getter requerido.
    public String getNom() {
        return nom;
    }

    // getter requerido.
    public double getPreuBase() {
        return preuBase;
    }

    // getter para id.
    public int getId() {
        return id;
    }

    // Método para calcular el precio con IVA incluido, aplicando un 10% de IVA al precio base.
    public double calcularPreuAmbIVA() {
        return preuBase * 1.10;
    }

}