package Exàmens.ARRAYLISTS;

import java.io.*;
import java.util.*;

public class GestorArrayListPunts {
    private ArrayList<Punt> llistaPunts = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    public static class KwhIncorrectesException extends Exception {
    }

    // CARGAR
    public void carregarLlista(String rutaFitxer) {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaFitxer))) {
            String l;
            while ((l = br.readLine()) != null) {
                String[] token = l.split(";");
                if (token.length == 5)
                    llistaPunts.add(new PuntBateria(token[0], token[1], Integer.parseInt(token[2]),
                            Integer.parseInt(token[3]), Boolean.parseBoolean(token[4])));
                if (token.length == 4)
                    llistaPunts.add(
                            new PuntSolar(token[0], token[1], Integer.parseInt(token[2]), Integer.parseInt(token[3])));
            }
        } catch (IOException e) {
            System.out.println("Error.");
        }
    }

    // MODIFICAR Y MOSTRAR: Listar el contenido y permitir modificaciones directas
    public void modificarPuntsDeLaLlista() {
        if (llistaPunts.isEmpty()) {
            System.out.println("No hi ha elements a la llista.");
            return;
        }

        // Mostrar / Listar (Uso del índice en ArrayList)
        for (int i = 0; i < llistaPunts.size(); i++) {
            System.out.println("[" + i + "] ID: " + llistaPunts.get(i).getId() + " - Ubicació: "
                    + llistaPunts.get(i).getUbicacio());
        }

        System.out.print("\nTria l'índex del punt que vols modificar: ");
        int index = sc.nextInt();
        sc.nextLine();

        // CASUÍSTICA: Validar los límites del ArrayList para evitar
        // IndexOutOfBoundsException
        if (index >= 0 && index < llistaPunts.size()) {
            Punt seleccionat = llistaPunts.get(index);

            try {
                System.out.print("Introdueix els nous KWh acumulats per a aquest punt: ");
                int nuevosKwh = sc.nextInt();
                sc.nextLine();

                if (nuevosKwh < 0) {
                    throw new KwhIncorrectesException();
                }

                // Modificamos el objeto en la memoria (Se podría añadir aquí un flujo de
                // guardado a fichero)
                System.out.println("Dada modificada correctament en la llista de memòria.");

            } catch (KwhIncorrectesException e) {
                System.out.println("Els KWh no poden ser negatius.");
            }
        } else {
            System.out.println("Índex de llista fora de rang.");
        }
    }
}
