package EXAMENCONTINUADIA1.SOLUCIONS;

public class Tecnic extends Empleado {
    private int analisisRealizado;
    private final double cuotAutonom = 400.0;

    public Tecnic(String id, String nom, String torn,
            int analisisRealizado) {
        super(id, nom, torn);
        this.analisisRealizado = analisisRealizado;
    }

    @Override
    public double calcularSou() {
        double salari;
        if (analisisRealizado < 10) {
            salari = 500.0;
        } else {
            salari = analisisRealizado * 50;
        }

        if (getTorn().equals("nit")) {
            salari += 400.0;
        }

        return salari - cuotAutonom;
    }

    @Override
    public String toString() {
        return super.toString() + " | Categoria: Tècnic Lab. (Autònom) | Estudis: " + analisisRealizado
                + " | Ingressos Nets: " + calcularSou() + "€";
    }
}
