package Exàmens.HASHMAPS;

import java.io.*;
import java.sql.*;
import java.util.*;

public class GestorInventariMap {
    // Clave: ID del punto, Valor: Objeto genérico Punt (Polimorfismo)
    private HashMap<String, Punt> mapaPunts = new HashMap<>();
    private Scanner sc = new Scanner(System.in);

    public static class CapacitatExcedidaException extends Exception {
    }

    // CARGAR: Volcar datos del fichero al mapa asegurando claves únicas
    public void carregarMapa(String ruta) {
        File f = new File(ruta);
        if (!f.exists())
            return;

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linia;
            while ((linia = br.readLine()) != null) {
                try {
                    String[] words = linia.split(";");
                    Punt nouPunt = null;

                    if (words.length == 5) {
                        nouPunt = new PuntBateria(words[0], words[1], Integer.parseInt(words[2]),
                                Integer.parseInt(words[3]), Boolean.parseBoolean(words[4]));
                    } else if (words.length == 4) {
                        nouPunt = new PuntSolar(words[0], words[1], Integer.parseInt(words[2]),
                                Integer.parseInt(words[3]));
                    }

                    if (nouPunt != null) {
                        // Insertamos en el mapa usando el ID como clave única
                        mapaPunts.put(nouPunt.getId(), nouPunt);
                    }
                } catch (Exception e) {
                    System.out.println("Format erroni en línia de mapa.");
                }
            }
        } catch (IOException e) {
            System.out.println("Error IO.");
        }
    }

    // MODIFICAR/ACTUALIZAR: Recorrer el mapa y persistir cambios interactivos en DB
    public void actualitzarCapacitatsSolares() {
        String URL = "jdbc:mysql://localhost:3306/recarrega_db";
        String SQL = "UPDATE punts_solars SET capacitat_max = ? WHERE id_punt = ?";

        try (
                Connection c = DriverManager.getConnection(URL, "alumne", "Recarrega26!");
                PreparedStatement ps = c.prepareStatement(SQL)) {
            // Sintaxis de examen indispensable para recorrer un HashMap (entrySet)
            for (Map.Entry<String, Punt> entrada : mapaPunts.entrySet()) {
                Punt p = entrada.getValue();

                // Filtrado por tipo mediante herencia
                if (p instanceof PuntSolar) {
                    PuntSolar psolar = (PuntSolar) p;

                    try {
                        System.out.print("Introdueix la nova capacitat de potència per a " + psolar.getId() + ": ");
                        int novaCapacitat = sc.nextInt();
                        sc.nextLine(); // Evitar salto de línea en buffer

                        // Validación del modelo de negocio
                        if (novaCapacitat > 5000) { // Límite físico ficticio
                            throw new CapacitatExcedidaException();
                        }

                        // Mapeo al PreparedStatement de SQL
                        ps.setInt(1, novaCapacitat);
                        ps.setString(2, psolar.getId());
                        ps.executeUpdate();

                    } catch (CapacitatExcedidaException e) {
                        System.out.println("Error: La potència introduïda excedeix el límit permès.");
                    } catch (InputMismatchException e) {
                        System.out.println("Has d'introduir un número enter.");
                        sc.nextLine(); // Limpieza ante entradas de texto erróneas
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error en la connexió JDBC.");
        }
    }
}