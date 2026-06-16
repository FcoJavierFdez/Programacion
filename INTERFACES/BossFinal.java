package INTERFACES;


public final class BossFinal extends Personatge implements Atacant {
    
    private String habilitatEspecial;

    public BossFinal(String nom, String habilitat) {
        super(nom, 500, 50, 50);
        this.habilitatEspecial = habilitat;
    }

    @Override
    public void atacar() {
        System.out.println(nom + " utilitza " + habilitatEspecial);
    }

    @Override
    public void pujarNivell() {
        System.out.println(">>> EL BOSS ES FA MÉS FORT! (+100 a tot)");
        this.vida += 100;
        this.atacFisic += 100;
        this.poderMagic += 100;
    }
}
