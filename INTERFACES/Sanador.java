package INTERFACES;

public class Sanador extends Personatge {

    public Sanador(String nom) {
        super(nom, 90, 5, 40); // Molta màgia
    }

    public void curar() {
        System.out.println(nom + " cura un aliat usant " + poderMagic + " punts de màgia.");
    }

    @Override
    public void pujarNivell() {
        System.out.println(">>> " + nom + " puja de nivell! (+15 Vida, +15 Màgia)");
        this.vida += 15;
        this.atacFisic += 2; // L'atac físic gairebé no puja
        this.poderMagic += 15;
    }
}