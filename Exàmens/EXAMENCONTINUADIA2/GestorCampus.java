package EXAMENCONTINUADIA2;

import java.util.HashMap;
import java.util.Scanner;

// Excepció personalitzada requerida
class CodiRepetitException extends Exception {
    public CodiRepetitException(String missatge) {
        super(missatge);
    }
}

public class GestorCampus {
    private Scanner sc = new Scanner(System.in);

    // CREAR DICCIONARI dispositius
    private HashMap<String, Dispositiu> dispositius = new HashMap<>();

    // 1) MÈTODE insertarNouDispositiu
    public boolean insertarNouDispositiu(Dispositiu d) throws CodiRepetitException {
        if (dispositius.containsKey(d.getCodi())) {
            throw new CodiRepetitException("El dispositiu amb codi " + d.getCodi() + " ja existeix al sistema.");
        }
        dispositius.put(d.getCodi(), d);
        return true;
    }

    // 2) MÈTODE modificarUbicacio
    public boolean modificarUbicacio(String codi) {
        if (!dispositius.containsKey(codi)) {
            return false; // El dispositiu no existeix
        }

        System.out.print("Introdueix la nova ubicació per al dispositiu " + codi + ": ");
        String novaUbicacio = sc.nextLine();

        Dispositiu d = dispositius.get(codi);
        d.setUbicacio(novaUbicacio);

        return true;
    }

    // 3) MÈTODE llistarDispositius
    public void llistarDispositius() {
        System.out.println("\n--- LLISTAT DE DISPOSITIUS ---");

        for (Dispositiu d : dispositius.values()) {
            double consum = d.calcularCostMensual(); // Polimorfisme en acció

            // Format bàsic
            String info = String.format("Codi: %s – Consum: %.2f€", d.getCodi(), consum);

            // Si el dispositiu és calibrable, afegim la informació extra de la ubicació
            if (d instanceof Calibrable) {
                info += " - Ubicació: " + d.getUbicacio() + " (temporal)";
            }

            System.out.println(info);
        }
    }
}