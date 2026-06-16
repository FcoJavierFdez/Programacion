package com.restaurant;

/**
 * Clase PlatPrincipal que representa un plato principal en el restaurante.
 * Esta clase hereda de Producte e implementa la interfaz Facturable.
 * 
 * @param nom
 * @param preu_base
 *
 */
public class PlatPrincipal extends Producte implements Facturable {
    // Constructor que inicializa el nombre y el precio base del plato principal.
    public PlatPrincipal(String nom, double preu_base) {
        super(nom, preu_base);
    }

    // Implementación del método calcularPreuAmbIVA de la interfaz Facturable.
    @Override
    public double calcularPreuAmbIVA() {
        // IVA del 10% para platos principales
        return this.preu_base * 1.10;
    }

}
