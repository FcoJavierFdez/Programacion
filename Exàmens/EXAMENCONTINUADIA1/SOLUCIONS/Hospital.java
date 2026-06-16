package EXAMENCONTINUADIA1.SOLUCIONS;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Hospital {
    private static Scanner sc = new Scanner(System.in);
    private static Empleado[] empleats = new Empleado[100];

    // Nou recurs: Llevem el rastreig del -1 i usem un comptador de posició
    private static int totalEmpleats = 0;

    // ==========================================
    // 1) MÈTODE afegirEmpleat (Amb Comptador)
    // ==========================================
    public static boolean afegirEmpleat() {
        // En lloc de buscar amb un for, comprovem directament si està ple
        if (totalEmpleats >= empleats.length) {
            System.out.println("Error: L'hospital ja està complet (límit de 100 empleats).");
            return false;
        }

        try {
            // Recollida de dades generals
            System.out.println("\n--- INTRODUCCIÓ DE DADES GENERALS ---");
            System.out.print("ID (9 caràcters alfanumèrics): ");
            String id = sc.nextLine();

            System.out.print("Nom de l'empleat: ");
            String nom = sc.nextLine();

            System.out.print("Torn (dia/nit): ");
            String torn = sc.nextLine();

            System.out.println("\nSelecciona la categoria professional:");
            System.out.println("1. Metge/essa");
            System.out.println("2. Infermer/a");
            System.out.println("3. Tècnic/a de Laboratori");
            System.out.print("Opció: ");
            int opcio = sc.nextInt();
            sc.nextLine(); // Netejar buffer

            // Guardem temporalment l'empleat que anem a crear
            Empleado nouEmpleat = null;

            switch (opcio) {
                case 1:
                    System.out.print("Especialitat mèdica: ");
                    String especialitat = sc.nextLine();
                    System.out.print("Fa guàrdies? (true/false): ");
                    boolean faGuardies = sc.nextBoolean();
                    sc.nextLine();

                    nouEmpleat = new Metge(id, nom, torn, especialitat, faGuardies);
                    break;

                case 2:
                    System.out.print("Nombre de pacients assignats: ");
                    int pacients = sc.nextInt();
                    sc.nextLine();

                    nouEmpleat = new Infermer(id, nom, torn, pacients);
                    break;

                case 3:
                    System.out.print("Analisis realizados: ");
                    int analisis = sc.nextInt();
                    sc.nextLine();

                    nouEmpleat = new Tecnic(id, nom, torn, analisis);
                    break;

                default:
                    System.out.println("Opció de categoria professional no vàlida.");
                    return false;
            }

            // Inserim directament en la posició que indica el comptador
            empleats[totalEmpleats] = nouEmpleat;

            // Incrementem el comptador per a la pròxima vegada
            totalEmpleats++;

            System.out.println("Empleat inserit correctament en el sistema.");
            return true;

        } catch (InputMismatchException e) {
            System.out.println("Error: Tipus de dada introduït incorrecte.");
            sc.nextLine(); // Netejar buffer
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println("Error de validació: " + e.getMessage());
            return false;
        }
    }

    // ==========================================
    // 2) MÈTODE mostrarEmpleats (Més eficient)
    // ==========================================
    public static void mostrarEmpleats() {
        System.out.println("\n==================================================");
        System.out.println("     LLISTA DE PERSONAL I INGRESSOS MENSUALS      ");
        System.out.println("==================================================");

        // Si el comptador està a 0, és que no hi ha ningú
        if (totalEmpleats == 0) {
            System.out.println("No hi ha cap empleat registrat actualment en el sistema.");
        } else {
            // En lloc de fer un bucle fins a 100, només anem fins on hi ha empleats reals
            for (int i = 0; i < totalEmpleats; i++) {
                String nom = empleats[i].getNom();
                double salari = empleats[i].calcularSou();
                System.out.printf("- Nom: %-25s | Ingressos: %.2f €\n", nom, salari);
            }
        }
        System.out.println("==================================================");
    }

    // ==========================================
    // 3) MÈTODE importarEmpleats
    // ==========================================
    public void importarEmpleats(String nomFitxer) {
        // Dades de connexió a la BD
        String url = "jdbc:mysql://localhost:3306/hospital";
        String usuari = "root";
        String contrasenya = "root";

        // Sentència SQL per a fer la inserció de l'infermer
        String sql = "INSERT INTO infermers (id, nom, numPacients) VALUES (?, ?, ?)";

        // Intentem obrir el fitxer i connectar amb la Base de Dades
        // (Try-with-resources)
        try (BufferedReader br = new BufferedReader(new FileReader(nomFitxer));
                Connection conn = DriverManager.getConnection(url, usuari, contrasenya);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            System.out.println("\n--- Iniciant el procés d'importació ---");
            String linia;
            int liniaActual = 0;

            // Llegim el fitxer línia per línia
            while ((linia = br.readLine()) != null) {
                liniaActual++;

                // Un bloc try-catch intern per línia evita que un registre erroni pare tot el
                // procés
                try {
                    // Trossegem la línia fent servir el punt i coma com a separador
                    String[] dades = linia.split(";");

                    // Validem que la línia continga, com a mínim, els camps generals i el tipus
                    if (dades.length < 4) {
                        throw new IllegalArgumentException("Format de línia incomplet.");
                    }

                    String tipus = dades[0];
                    String id = dades[1];
                    String nom = dades[2];
                    String torn = dades[3];

                    // 1. Validacions obligatòries abans de tractar la línia
                    if (id == null || id.length() != 9) {
                        throw new IllegalArgumentException("L'ID '" + id + "' no té exactament 9 caràcters.");
                    }
                    if (torn == null || (!torn.equalsIgnoreCase("dia") && !torn.equalsIgnoreCase("nit"))) {
                        throw new IllegalArgumentException(
                                "El torn '" + torn + "' no és vàlid (ha de ser 'dia' o 'nit').");
                    }

                    // 2. Processem ÚNICAMENT els infermers
                    if (tipus.equalsIgnoreCase("INFERMER")) {
                        // Verifiquem si tenim la dada específica dels pacients
                        if (dades.length < 5) {
                            throw new IllegalArgumentException("Falta el nombre de pacients de l'infermer.");
                        }

                        int numPacients = Integer.parseInt(dades[4]);

                        // Enllaçem els paràmetres de la consulta SQL (?)
                        pstmt.setString(1, id);
                        pstmt.setString(2, nom);
                        pstmt.setInt(3, numPacients);

                        // Executem la inserció a MySQL
                        pstmt.executeUpdate();
                        System.out.println(
                                " Línia " + liniaActual + ": Infermer/a " + nom + " importat amb èxit a la BD.");
                    } else {
                        // Si és un metge o tècnic, l'ignorem tal com demana l'enunciat
                        System.out.println(
                                "ℹ Línia " + liniaActual + ": Descartat (Categoria " + tipus + " no requerida).");
                    }

                } catch (IllegalArgumentException e) {
                    // Captura errors de format en l'ID, el torn o fallades en parsejar números
                    System.out.println(" Error a la línia " + liniaActual + ": " + e.getMessage());
                } catch (SQLException e) {
                    // Captura errors de BD d'aquesta línia en concret (p. ex. ID duplicat a la
                    // taula)
                    System.out.println(" Error de Base de Dades a la línia " + liniaActual + ": " + e.getMessage());
                }
            }

            System.out.println("--- Procés d'importació finalitzat ---");

        } catch (IOException e) {
            // Error crític: No s'ha pogut obrir o llegir el fitxer de text
            System.out.println(" Error crític en obrir el fitxer '" + nomFitxer + "': " + e.getMessage());
        } catch (SQLException e) {
            // Error crític: No s'ha pogut establir la connexió general amb MySQL
            System.out.println(" Error crític de connexió global a la BD: " + e.getMessage());
        }
    }
}
