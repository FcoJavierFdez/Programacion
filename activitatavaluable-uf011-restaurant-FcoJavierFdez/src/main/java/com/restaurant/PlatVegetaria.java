package com.restaurant;

public class PlatVegetaria extends PlatPrincipal {

    // Constructor con id, para cargar desde BBDD.
    public PlatVegetaria(int id, String nom, double preuBase) {
        super(id, nom, preuBase);
    }

    // Constructor adicional sin id, asignando id = 0 por defecto.
    public PlatVegetaria(String nom, double preuBase) {
        super(0, nom, preuBase);
    }
}