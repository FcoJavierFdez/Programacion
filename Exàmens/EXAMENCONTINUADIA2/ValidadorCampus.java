package EXAMENCONTINUADIA2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ValidadorCampus {

    public void validarDispositiusBD() {
        String url = "jdbc:mysql://localhost:3306/campus_db";
        String usuari = "alumne";
        String contrasenya = "Campus26!";
        String sql = "SELECT codi, ubicacio, tipus FROM dispositius";

        // Expresió regular per validar el codi (7 posicions alfanumèriques)
        String regexCodi = "^[a-zA-Z0-9]{7}$";

        // Obrim la connexió a la BD i el fitxer d'escriptura de manera segura
        try (Connection conn = DriverManager.getConnection(url, usuari, contrasenya);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                BufferedWriter writer = new BufferedWriter(new FileWriter("incorrectes.txt"))) {

            boolean hiHaErrors = false;

            // Recorrem tots els registres de la taula
            while (rs.next()) {
                String codiBD = rs.getString("codi");
                String tipusBD = rs.getString("tipus");

                // Validem si el format NO és correcte
                if (codiBD == null || !codiBD.matches(regexCodi)) {
                    // Escrivim al fitxer amb el format demanat: codi -> tipus
                    writer.write(codiBD + " -> " + tipusBD);
                    writer.newLine();
                    hiHaErrors = true;
                }
            }

            if (hiHaErrors) {
                System.out.println(" S'han trobat codis incorrectes. S'ha generat el fitxer 'incorrectes.txt'.");
            } else {
                System.out.println(" Tota la base de dades és íntegra. No hi ha codis erronis.");
            }

        } catch (SQLException e) {
            System.out.println(" ERROR DE BASE DE DADES: No s'ha pogut connectar o llegir la taula 'dispositius'.");
            System.out.println("Detalls: " + e.getMessage());
        } catch (IOException e) {
            System.out.println(" ERROR DE FITXER: No s'ha pogut crear o escriure en el fitxer 'incorrectes.txt'.");
            System.out.println("Detalls: " + e.getMessage());
        } catch (Exception e) {
            System.out.println(" ERROR INESPERAT: " + e.getMessage());
        }
    }
}