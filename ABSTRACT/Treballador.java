package ABSTRACT;

public abstract class Treballador {

    protected String nom;

    public Treballador(String nom) {
        this.nom = nom;
    }

    public void mostrarNom() {
        System.out.println("Treballador: " + nom);
    }
    // Mètode abstracte que serà implementat per les subclasses
    public abstract double calcularSou();
}
