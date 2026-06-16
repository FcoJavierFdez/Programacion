package EXAMENCONTINUADIA1.SOLUCIONS;

public class Metge extends Empleado {

    private boolean ferGuardies;
    private String especialitat;

    public Metge(String id, String nom, String torn,
            String especialitat, boolean ferGuardies) {
        super(id, nom, torn);
        this.ferGuardies = ferGuardies;
        this.especialitat = especialitat;

    }

    @Override
    public double calcularSou() {
        double salari = 2800.0;

        if (ferGuardies) {
            salari += 500.0;
        }
        return salari;

    }

    public boolean getFerGuardies() {
        return ferGuardies;

    }

    public void setFerGuardies(boolean ferGuardies) {
        this.ferGuardies = ferGuardies;
    }

    public String getEspecialitat() {
        return especialitat;
    }

    public void setEspecialitat(String especialitat) {
        this.especialitat = especialitat;
    }

    @Override
    public String toString() {
        return super.toString() + " | Categoria: Metge | Especialitat: " + especialitat +
                " | Guàrdies: " + (ferGuardies ? "Sí" : "No") + " | Ingressos: " + calcularSou() + "€";
    }

}