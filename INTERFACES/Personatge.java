package INTERFACES;

public abstract class Personatge {

    protected String nom;
    protected int vida;
    protected int atacFisic;
    protected int poderMagic;

    // Constructor
    public Personatge(String nom, int vida, int atacFisic, int poderMagic) {
        this.nom = nom;
        this.vida = vida;
        this.atacFisic = atacFisic;
        this.poderMagic = poderMagic;
    }

    public void mostrarEstat() {
        System.out.println(nom + " [Vida: " + vida + " | Atac: " + atacFisic + " | Màgia: " + poderMagic + "]");
    }

    public void rebreDany(int dany) {
        vida -= dany;
    }

    // MÈTODE ABSTRACTE: Cada classe defineix com millora
    public abstract void pujarNivell();
}
