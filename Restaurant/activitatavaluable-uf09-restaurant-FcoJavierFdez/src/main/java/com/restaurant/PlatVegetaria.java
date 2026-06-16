package com.restaurant;

/**
 * Clase PlatVegetaria que representa un plato vegetariano en el restaurante.
 * Esta clase hereda de PlatPrincipal e implementa la interfaz Vegetaria.
 * 
 * @param nom
 * @param preu_base
 *
 */
public class PlatVegetaria extends PlatPrincipal implements Vegetaria {

    // Constructor que inicializa el nombre y el precio base del plato vegetariano.
    public PlatVegetaria(String nom, double preu_base) {
        super(nom, preu_base);
    }

}
