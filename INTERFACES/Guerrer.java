package INTERFACES;

public class Guerrer extends Personatge implements Atacant {

    public Guerrer(String nom) {
        super(nom, 100, 30, 5); // Molt físic, poca màgia
    }

    @Override
    public void atacar() {
        System.out.println(nom + " clava l'espasa fent " + atacFisic + " de dany!");
    }

    @Override
    public void pujarNivell() {
        System.out.println(">>> " + nom + " puja de nivell! (+20 Vida, +10 Atac)");
        this.vida += 20;
        this.atacFisic += 10;
        this.poderMagic += 1; // La màgia puja molt poc
    }
}