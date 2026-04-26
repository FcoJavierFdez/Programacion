package INTERFACES;

public class Arquer extends Personatge implements Atacant {

    public Arquer(String nom) {
        super(nom, 80, 20, 10); // Equilibrat entre físic i màgia
    }

    @Override
    public void atacar() {
        System.out.println(nom + " dispara una fletxa fent " + atacFisic + " de dany!");
    }

    @Override
    public void pujarNivell() {
        System.out.println(">>> " + nom + " puja de nivell! (+10 Vida, +5 Atac)");
        this.vida += 10;
        this.atacFisic += 5;
        this.poderMagic += 5; // La màgia puja més que el físic
    }
    
}
