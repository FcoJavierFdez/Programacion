package com.restaurant;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase Comanda que representa una orden en el restaurante.
 * Contiene el número de mesa y una lista de productos asociados a la orden.
 * Permite agregar productos a la orden y preparar la orden verificando el
 * stock.
 * Si se encuentra un producto que contiene "entrecot" en su nombre,
 * se lanza una excepción SenseEstocException indicando que no hay entrecot
 * disponible para esa mesa.
 * Proporciona un método para obtener la lista de productos asociados a la
 * orden.
 * 
 * @param numTaula  El número de mesa asociado a la orden.
 * @param productes La lista de productos asociados a la orden.
 * 
 * 
 */
public class Comanda {

    // Atributos de la clase Comanda: número de mesa y lista de productos.
    private int numTaula;
    // Lista de productos asociados a la orden.
    private ArrayList<Producte> productes;

    // Getter para obtener el número de mesa asociado a la orden.
    public int getNumTaula() {
        return numTaula;
    }

    // Constructor que inicializa el número de mesa y la lista de productos.
    public Comanda(int i) {
        this.numTaula = i;
        this.productes = new ArrayList<>();
    }

    // Método para agregar un producto a la orden. Verifica que el producto no sea
    // nulo antes de agregarlo a la lista.
    public void afegirProducte(Producte p1) {
        if (p1 != null) {
            this.productes.add(p1);
        }
    }

    // Método para preparar la orden. Recorre la lista de productos y
    // verifica si alguno contiene "entrecot" en su nombre. Si es así,
    // lanza una excepción indicando que no hay entrecot disponible para esa mesa.
    public void prepararComanda() {
        for (Producte prod : productes) {
            if (prod.nom.toLowerCase().contains("entrecot")) {
                throw new SenseEstocException("No hi ha entrecot disponible per a la taula " + numTaula);
            }
        }
    }

    // Método para obtener la lista de productos asociados a la orden.
    public List<Producte> getProductes() {
        // Devuelve una nueva lista que contiene los productos de la orden para evitar
        // modificaciones externas.
        return new ArrayList<>(productes);
    }
}
