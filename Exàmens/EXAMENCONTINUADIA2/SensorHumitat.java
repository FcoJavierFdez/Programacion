package EXAMENCONTINUADIA2;

public class SensorHumitat extends Dispositiu {
    public SensorHumitat(String codi, String ubicacio) {
        super(codi, ubicacio);
    }

    @Override
    public double calcularCostMensual() {
        // 8 hores al dia * 21 dies al mes * 0.05€ l'hora
        return 8 * 21 * 0.05;
    }
}