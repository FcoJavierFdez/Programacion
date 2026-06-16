package EXAMENCONTINUADIA1.SOLUCIONS;

public class Infermer extends Empleado {

    private int numPacients;

    public Infermer(String id, String nom, String torn,
            int numPacients) {
        super(id, nom, torn);
        this.numPacients = numPacients;
    }

    @Override
    public double calcularSou() {
        double salari = 1900.0;
        if (numPacients > 20) {
            return salari * 0.2;
        } else {
            return salari;
        }

    }
}
