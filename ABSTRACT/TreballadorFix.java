package ABSTRACT;

public class TreballadorFix extends Treballador {
    private double souMensual;

    public TreballadorFix(String nom, double souMensual) {
        super(nom);
        this.souMensual = souMensual;
    }

    @Override
    public double calcularSou() {
        return souMensual;
    }

}
