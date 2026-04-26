package ABSTRACT;

public class TreballadorPerHores extends Treballador {

    private int hores;
    private double preuHora;

    public TreballadorPerHores(String nom, int hores, double preuHora) {
        super(nom);
        this.hores = hores;
        this.preuHora = preuHora;
    }

    @Override
    public double calcularSou() {
        return hores * preuHora;
    }
}