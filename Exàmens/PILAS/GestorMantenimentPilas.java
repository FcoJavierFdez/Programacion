package Exàmens.PILAS;

import java.io.*;
import java.util.*;

public class GestorMantenimentPila {
    // Usamos la estructura Stack polimórfica
    private Stack<Punt> pilaRevisions = new Stack<>();
    private Scanner sc = new Scanner(System.in);

    // CARGAR: Aplicando tu patrón de captura interna por línea
    public static class CodiTecnicInexistentException extends Exception {
    }

    public void carregarHistorial(String rutaCarpeta) {
        File dir = new File(rutaCarpeta);
        if (!dir.exists() || dir.listFiles() == null)
            return;

        for (File f : dir.listFiles()) {
            if (f.getName().endsWith(".log")) { // Buscamos archivos de traza .log
                try (BufferedReader br = new BufferedReader(new FileReader(f))) {
                    String linia;
                    while ((linia = br.readLine()) != null) {
                        try {
                            String[] dades = linia.split(";");
                            // Tu lógica de negocio basada en la longitud del split
                            if (dades.length == 5) {
                                pilaRevisions.push(new PuntBateria(dades[0], dades[1], Integer.parseInt(dades[2]),
                                        Integer.parseInt(dades[3]), Boolean.parseBoolean(dades[4])));
                            } else if (dades.length == 4) {
                                pilaRevisions.push(new PuntSolar(dades[0], dades[1], Integer.parseInt(dades[2]),
                                        Integer.parseInt(dades[3])));
                            }
                        } catch (Exception e) {
                            System.out.println("Linia corrupta ignorada en l'historial.");
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Error d'accés al fitxer de logs.");
                }
            }
        }
    }

    // PROCESAR: Consumir la pila (Pop) guardando en un fichero de salida
    public void processarICloureRevisions(ArrayList<String> tecnicsAutoritzats, String fitxerSortida) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fitxerSortida, true))) {

            // Consumimos la pila hasta que se vacíe (Igual que hiciste con tu cua.poll())
            while (!pilaRevisions.isEmpty()) {
                Punt p = pilaRevisions.pop(); // Sacamos la cima (LIFO)

                try {
                    System.out.println("Introduce el código del técnico para revisar el punto: " + p.getId());
                    String codi = sc.nextLine();

                    // Validación interactiva usando tu patrón .contains()
                    if (!tecnicsAutoritzats.contains(codi.toUpperCase())) {
                        throw new CodiTecnicInexistentException();
                    }

                    // Escribimos en el fichero de salida (Persistencia)
                    bw.write("PUNT: " + p.getId() + " revisat per TÈCNIC: " + codi);
                    bw.newLine();

                } catch (CodiTecnicInexistentException e) {
                    System.out.println("Tècnic no autoritzat. Operació cancel·lada per a aquest punt.");
                    // Al capturarlo aquí dentro, el bucle while NO se rompe y pasa al siguiente
                    // elemento
                }
            }
            bw.flush();
        } catch (IOException e) {
            System.out.println("Error en desar el fitxer de tancament.");
        }
    }
}