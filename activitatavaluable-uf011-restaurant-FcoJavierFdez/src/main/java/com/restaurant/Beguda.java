package com.restaurant;
/**
 * Representa una bebida que es un tipo de producto en el restaurante.
 * La clase permite crear bebidas con un nombre y un precio base, y calcular su precio base.
 */
public class Beguda extends Producte {

    public Beguda(int id, String nom, double preuBase) {
        super(id, nom, preuBase);
    }

    public Beguda(String nom, double preuBase) {
        super(nom, preuBase);
    }
}