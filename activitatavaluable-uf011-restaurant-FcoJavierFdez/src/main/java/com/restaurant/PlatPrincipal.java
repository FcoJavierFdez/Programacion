package com.restaurant;

public class PlatPrincipal extends Producte {

    // Constructor con id, para cargar desde BBDD.
    public PlatPrincipal(int id, String nom, double preuBase) {
        super(id, nom, preuBase);
    }

    // Constructor adicional sin id, asignando id = 0 por defecto.
    public PlatPrincipal(String nom, double preuBase) {
        super(nom, preuBase);
    }
}