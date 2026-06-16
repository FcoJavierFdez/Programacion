package EXAMENCONTINUADIA2;

public class SensorTemperatura extends Dispositiu {
    private int lecturesMes;

    public SensorTemperatura(String codi, String ubicacio, int lecturesMes) {
        super(codi, ubicacio);
        this.lecturesMes = lecturesMes;
    }

    @Override
    public double calcularCostMensual() {
        return lecturesMes * 0.15;
    }
}


