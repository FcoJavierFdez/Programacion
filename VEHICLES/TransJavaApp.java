package VEHICLES;

import java.util.ArrayList;

/* ===== PROGRAMA PRINCIPAL ===== */
public class TransJavaApp {
    public static void main(String[] args) {

        ArrayList<Vehicle> flota = new ArrayList<>();

        try {
            flota.add(new Camio("1234-ABC", "Volvo", "FH"));
        } catch (MatriculaInvalidaException e) {
            System.out.println(e.getMessage());
        }

        try {
            flota.add(new CamioPerillos("7777-HZD", "MAN", "TGX"));
        } catch (MatriculaInvalidaException e) {
            System.out.println(e.getMessage());
        }

        try {
            flota.add(new Moto("5555-MOT", "Yamaha", "MT-07"));
        } catch (MatriculaInvalidaException e) {
            System.out.println(e.getMessage());
        }

        try {
            flota.add(new Cotxe("9999-CAR", "SEAT", "Leon"));
        } catch (MatriculaInvalidaException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n=== FLOTA ===");

        for (Vehicle v : flota) {
            v.mostrarInfo(); // polimorfisme

            if (v instanceof MercaderiaPerillosa) {
                System.out.println("  [ALERTA] Mercaderia perillosa");
            }

            System.out.println("----");
        }
    }
}