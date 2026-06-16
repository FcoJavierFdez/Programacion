package EXAMENCONTINUADIA2;

public class SensorPressio extends Dispositiu implements Calibrable {
    public SensorPressio(String codi, String ubicacio) {
        super(codi, ubicacio);
    }

    @Override
    public double calcularCostMensual() {
        // Cost fix d'energia + cost de calibratge/manteniment
        return 240.0 + PREU_CALIBRATGE;
    }

    @Override
    public void ferManteniment(String novaUbicacio) {
        // L'acció de manteniment implica reubicar el dispositiu
        setUbicacio(novaUbicacio);
    }
}