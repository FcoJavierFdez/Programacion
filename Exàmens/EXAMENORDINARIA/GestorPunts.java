package EXAMENORDINARIA;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class GestorPunts {

    private Queue<Punt> cua = new LinkedList<>();
    private Scanner sc = new Scanner(System.in);

    /**
     * @param ruta
     */
    public void carregarPunts(String ruta) {
        File files[] = new File(ruta).listFiles();

        String filePath = "";
        for (File f : files)
            if (f.getName().endsWith(".txt"))
                filePath = f.getAbsolutePath();

        try (
                BufferedReader br = new BufferedReader(new FileReader(filePath));) {
            String linea;
            while ((linea = br.readLine()) != null) {
                try {
                    String[] words = linea.split(";");
                    if (words.length == 5)
                        cua.add(new PuntBateria(words[0], words[1], Integer.parseInt(words[2]),
                                Integer.parseInt(words[3]), Boolean.parseBoolean(words[4])));
                    if (words.length == 4)
                        cua.add(new PuntSolar(words[0], words[1], Integer.parseInt(words[2]),
                                Integer.parseInt(words[3])));
                } catch (Exception e) {
                    System.out.println("Hi havia una dada incorrecta en el fitxer");
                }
            }
        } catch (IOException e) {
            System.out.println("ERROR DE IO");
        }
    }

    /**
     * @param ubicacionsPermeses
     */
    public void guardarRutaBateries(ArrayList<String> ubicacionsPermeses) {
        String URL = "jdbc:mysql://localhost:3306/recarrega_db";
        String usuari = "alumne";
        String password = "Recarrega26!";
        try (
                Connection c = DriverManager.getConnection(URL, usuari, password);
                PreparedStatement ps = c.prepareStatement(
                        "INSERT INTO ruta_transportistes(id_punt,ubicacio_actual,ubicacio_nova) VALUES (?, ?, ?)");) {
            // El programa haurà de buidar la cua línia a línia
            while (!cua.isEmpty()) {
                Punt p = cua.poll();// Extrau i elimina el primer de la cua
                // 1. Seleccionem únicament aquells que siguen punts revisables (Bateria)
                if (p instanceof PuntBateria) {
                    // Casting forcat
                    PuntBateria pb = (PuntBateria) p;
                    // 2. Només s'hauran de processar els que necessiten revisió
                    if (pb.perRevisar())
                        // Try-catch intern per a evitar que una ubicació incorrecta pare el bucle
                        try {
                            System.out.println("Donam la nova ubicació per al punt ");
                            String novaUb = sc.nextLine();
                            // 3. Validació de la ubicació (passada a minúscules)
                            if (!ubicacionsPermeses.contains(novaUb.toLowerCase()))
                                throw new UbicacioIncorrectaException();
                            // 4. Mapeig de paràmetres en el PreparedStatement (Els índexs d'SQL comencen en
                            // 1)
                            ps.setString(1, pb.getId());
                            ps.setString(2, pb.getUbicacio());
                            ps.setString(3, novaUb);
                            // Executem la inserció a la BD
                            ps.executeUpdate();
                            System.out.println("Punt " + pb.getId() + " guardat correctament en la ruta.");

                        } catch (UbicacioIncorrectaException e) {
                            // Captura de l'excepció personalitzada exigida per l'enunciat
                            System.out.println("Ubicacio no permesa");
                        }
                }
            }
        } catch (SQLException e) {
            // Control d'excepcions de la BD per a evitar que el programa finalitze
            // bruscament
            System.out.println("ERROR DE BASE DE DADES");
        }
    }
}