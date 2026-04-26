package com.restaurant;

import java.util.ArrayList;

/**
 * Representa una comanda que puede contener un plato principal y una bebida,
 * así como otros productos adicionales.
 * La clase permite calcular el total de la comanda incluyendo el IVA, y
 * gestionar los productos añadidos a la comanda.
 *
 */
public class Comanda {

    // Atributos.
    private ArrayList<Producte> productes;
    private PlatPrincipal plat;
    private Beguda beguda;

    // Constructor con id.
    public Comanda(int id) {
        productes = new ArrayList<>();
    }

    // Constructor adicional sin id, asignando id = 0 por defecto.
    public Comanda(PlatPrincipal plat, Beguda beguda) {
        this.plat = plat;
        this.beguda = beguda;

        // Inicializamos la lista de productos y añadimos el plato y la bebida.
        productes = new ArrayList<>();
        productes.add(plat);
        productes.add(beguda);
    }

    // Método para añadir un producto a la comanda. Si el producto es un plato o una
    // bebida, se actualizan los atributos correspondientes.
    public void afegirProducte(Producte p) {
        productes.add(p);

        if (p instanceof Beguda) {
            beguda = (Beguda) p; // siempre actualiza.
        }

        if (p instanceof PlatPrincipal) {
            plat = (PlatPrincipal) p; // siempre actualiza.
        }
    }

    // Getters para plato, bebida, total y lista de productos.

    public PlatPrincipal getPlat() {
        return plat;
    }

    public Beguda getBeguda() {
        return beguda;
    }

    public ArrayList<Producte> getProductes() {
        return productes;
    }

    // Calcula el total de la comanda sumando el precio con IVA de cada producto.
    public double getTotal() {
        double total = 0;

        for (Producte p : productes) {
            total += p.calcularPreuAmbIVA();
        }

        return total;
    }

}