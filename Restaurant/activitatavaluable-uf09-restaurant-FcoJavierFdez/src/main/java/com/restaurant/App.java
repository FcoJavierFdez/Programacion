package com.restaurant;




public class App {

    public static void main(String[] args) {

        System.out.println("=== OBRINT NOVA COMANDA ===");

        Comanda taula4 = new Comanda(4);

        Producte p1 = new PlatPrincipal("Hamburguesa de Llentilles", 12.50);
        Producte p2 = new Beguda("Cervesa Artesana", 3.50);
        Producte p3 = new PlatPrincipal("Entrecot de Vedella", 22.00);
        Producte p4 = new PlatVegetaria("Tomaca", 1.0);

        taula4.afegirProducte(p1);
        taula4.afegirProducte(p2);
        taula4.afegirProducte(p3);
        taula4.afegirProducte(p4);

        System.out.println("\n--- ENVIANT COMANDA A CUINA ---");
        try {
            // Aquest mètode simula l'enviament.
            // Per a la pràctica, feu que llance la SenseEstocException
            // si el producte conté la paraula "Entrecot".
            taula4.prepararComanda();
            System.out.println("Comanda rebuda a cuina correctament!");
        } catch (SenseEstocException e) {
            System.out.println("Avís a sala (EXCEPCIÓ CAPTURADA): " + e.getMessage());
        }

        System.out.println("\n--- REVISIÓ D'AL·LÈRGENS I DIETES ---");
        for (Producte prod : taula4.getProductes()) {
            System.out.print("Revisant " + prod.getNom() + "... ");

            if (prod instanceof Vegetaria) {
                System.out.print("[ATENCIÓ: Plat Vegetarià] ");
            }
            if (prod instanceof SenseGluten) {
                System.out.print("[ATENCIÓ: Apte per a Celíacs] ");
            }
            System.out.println();
        }

        System.out.println("\n--- TICKET DE LA TAULA 4 ---");
        double totalAPagar = 0;

        for (Producte prod : taula4.getProductes()) {

            if (prod instanceof Facturable) {

                double preuAmbIva = ((Facturable) prod).calcularPreuAmbIVA();
                System.out.println(prod.getNom() + " -> " + preuAmbIva + "€");
                totalAPagar += preuAmbIva;
            } else {
                System.out.println(prod.getNom() + " -> (Producte no facturable, convida la casa)");
            }
        }

        System.out.println("-------------------------");
        System.out.println("TOTAL A PAGAR: " + totalAPagar + "€");
    }
}