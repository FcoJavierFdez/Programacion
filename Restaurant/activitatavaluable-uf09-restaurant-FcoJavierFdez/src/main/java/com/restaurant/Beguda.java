package com.restaurant;

/**
 * Clase Beguda que representa una bebida en el restaurante.
 * Hereda de Producte y implementa las interfaces Facturable y SenseGluten.
 * 
 * @param nom       El nombre de la bebida.
 * @param preu_base El precio base de la bebida.
 */
public class Beguda extends Producte implements Facturable, SenseGluten {

    // Constructor que inicializa el nombre y el precio base de la bebida.
    public Beguda(String nom, double preu_base) {
        // Llamada al constructor de la clase base Producte para inicializar el nombre y
        // el precio base.
        super(nom, preu_base);
    }

    // Implementación del método calcularPreuAmbIVA de la interfaz Facturable.
    @Override
    public double calcularPreuAmbIVA() {
        // IVA del 21% para bebidas
        return this.preu_base * 1.21;
    }
}
