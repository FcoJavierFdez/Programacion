package com.restaurant;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class App {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== OBRINT NOVA COMANDA ===");

        System.out.println("\n");
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                      HORT I CUINA                           ║");
        System.out.println("║                 TABLET DE COMANDES 🍽️                      ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        System.out.println("║  Benvingut/da                                               ║");
        System.out.println("║  Toca el número del plat que vols afegir a la comanda       ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");

        System.out.println("");
        System.out.println("┌──────────────────────────────────────────────────────────────┐");
        System.out.println("│                      🍴 MENÚ DISPONIBLE                     │");
        System.out.println("├──────┬────────────────────────────────────────────┬──────────┤");
        System.out.println("│  Nº  │ Plat                                       │   Preu   │");
        System.out.println("├──────┼────────────────────────────────────────────┼──────────┤");

        // todo: carrega els plats

        // Cargamos los platos desde la base de datos y los mostramos en el menú.
        try {
            // Conectamos a la base de datos y obtenemos la lista de platos.
            Connection con = connectarBBDD("jdbc:mysql://localhost:3306/hort_i_cuina", "root", "");
            ArrayList<PlatPrincipal> plats = getPlats(con);

            // Mostramos cada plato con su número, nombre (añadiendo "(V)" si es
            // vegetariano) y precio.
            for (PlatPrincipal p : plats) {
                String nom = p.getNom();
                // Si el plato es vegetariano, añadimos " (V)" al nombre para indicar que es una
                // opción vegetariana.
                if (p instanceof PlatVegetaria) {
                    nom += " (V)";
                }
                // Imprimimos el número del plato, su nombre (con indicación de vegetariano si
                // aplica) y su precio base.
                System.out.println(p.getId() + " - " + nom + " - " + p.getPreuBase());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("└──────┴────────────────────────────────────────────┴──────────┘");
        System.out.println("");
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  👉 Escriu el número del plat i prem ENTER                  ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");

        // Leemos la opción del plato seleccionada por el usuario, manejando posibles
        // errores de entrada.
        int opPlat;
        try {
            opPlat = sc.nextInt();
        } catch (Exception e) {
            System.out.println("Error");
            return;
        }

        System.out.println("");
        System.out.println("┌──────────────────────────────────────────────────────────────┐");
        System.out.println("│                    🥤 BEGUDES DISPONIBLES                   │");
        System.out.println("├──────┬────────────────────────────────────────────┬──────────┤");
        System.out.println("│  Nº  │ Beguda                                     │   Preu   │");
        System.out.println("├──────┼────────────────────────────────────────────┼──────────┤");

        // todo: carrega les begudes

        // Cargamos las bebidas desde la base de datos y las mostramos en el menú.
        ArrayList<Beguda> begudes = null;
        try {
            // Conectamos a la base de datos y obtenemos la lista de bebidas.
            Connection con = connectarBBDD("jdbc:mysql://localhost:3306/hort_i_cuina", "root", "");
            begudes = getBegudes(con);

            // Mostramos cada bebida con su número, nombre y precio.
            for (Beguda b : begudes) {
                System.out.println(b.getId() + " - " + b.getNom() + " - " + b.getPreuBase());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("└──────┴────────────────────────────────────────────┴──────────┘");
        System.out.println("");
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  👉 Escriu el número de la beguda i prem ENTER              ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");

        // Leemos la opción de bebida seleccionada por el usuario, manejando posibles
        // errores de entrada.
        int opBeguda;
        try {
            opBeguda = sc.nextInt();
        } catch (Exception e) {
            System.out.println("Error");
            return;
        }

        System.out.println("\n");
        System.out.println("╔═════════════════════════════════════════════╗");
        System.out.println("║           ✅ COMANDA REGISTRADA            ║");
        System.out.println("╠═════════════════════════════════════════════╣");
        System.out.println("║  La teua comanda s'ha enviat a cuina       ║");
        System.out.println("║  Gràcies per confiar en Hort i Cuina       ║");
        System.out.println("╚═════════════════════════════════════════════╝");

        // todo: guarda el ticket

        // Creamos la comanda con el plato y la bebida seleccionados, y luego imprimimos
        // el ticket de compra.
        try {
            Connection con = connectarBBDD("jdbc:mysql://localhost:3306/hort_i_cuina", "root", "");

            // Cargamos platos y bebidas para obtener los objetos correspondientes a las
            // opciones seleccionadas por el usuario.
            ArrayList<PlatPrincipal> plats = getPlats(con);
            ArrayList<Beguda> begudes2 = getBegudes(con);

            // Obtenemos el plato y la bebida seleccionados por el usuario utilizando las
            // opciones
            // ingresadas (restando 1 para ajustar al índice de la lista).
            PlatPrincipal platSeleccionat = plats.get(opPlat - 1);
            Beguda begudaSeleccionada = begudes2.get(opBeguda - 1);

            // Creamos la comanda con el plato y la bebida seleccionados.
            Comanda c = new Comanda(platSeleccionat, begudaSeleccionada);

            // Imprimimos el ticket de compra con los detalles de la comanda.
            imprimirTicket(c, "ticket.txt");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Método para obtener la lista de platos desde la base de datos. Se conecta a
     * la
     * base de datos, ejecuta una consulta para obtener los platos, y crea objetos
     * PlatPrincipal o PlatVegetaria según corresponda, añadiéndolos a la lista que
     * se
     * devuelve al final.
     * 
     * @param con
     * @return
     */
    public static ArrayList<PlatPrincipal> getPlats(Connection con) {

        // Creamos una lista para almacenar los platos obtenidos de la base de datos.
        ArrayList<PlatPrincipal> plats = new ArrayList<>();

        // Utilizamos un bloque try-catch para manejar posibles excepciones al
        // interactuar con la base de datos.
        try {
            // Creamos un Statement para ejecutar la consulta SQL.
            Statement st = con.createStatement();
            // Ejecutamos una consulta SQL para obtener todos los platos de la tabla "Plat".
            ResultSet rs = st.executeQuery("SELECT * FROM Plat");

            // Generamos un id manual para asignar a cada plato, ya que la clase
            // PlatPrincipal requiere un id en su constructor.
            int id = 1; // generamos id manual

            // Recorremos el ResultSet obtenido de la consulta, creando un objeto
            // PlatPrincipal o PlatVegetaria
            // según el valor del campo "vegetaria" para cada fila, y añadiéndolo a la lista
            // de platos.
            while (rs.next()) {
                String nom = rs.getString("nom");
                double preu = rs.getDouble("preu");
                boolean vegetaria = rs.getBoolean("vegetaria");

                if (vegetaria) {
                    plats.add(new PlatVegetaria(id++, nom, preu));
                } else {
                    plats.add(new PlatPrincipal(id++, nom, preu));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Devolvemos la lista de platos obtenida de la base de datos.
        return plats;
    }

    /**
     * Método para obtener la lista de bebidas desde la base de datos. Se conecta a
     * la
     * base de datos, ejecuta una consulta para obtener las bebidas, y crea objetos
     * Beguda para cada fila obtenida, añadiéndolos a la lista que se devuelve al
     * final.
     * 
     * @param con
     * @return
     */
    public static ArrayList<Beguda> getBegudes(Connection con) {

        // Creamos una lista para almacenar las bebidas obtenidas de la base de datos.
        ArrayList<Beguda> begudes = new ArrayList<>();

        // Utilizamos un bloque try-catch para manejar posibles excepciones al
        // interactuar con la base de datos.
        try {
            // Creamos un Statement para ejecutar la consulta SQL.
            Statement st = con.createStatement();
            // Ejecutamos una consulta SQL para obtener todas las bebidas de la tabla
            // "Beguda".
            ResultSet rs = st.executeQuery("SELECT * FROM Beguda");

            int id = 1; // geramos un id manual

            // Recorremos el ResultSet obtenido de la consulta, creando un objeto Beguda
            // para cada
            // fila, y añadiéndolo a la lista de bebidas.
            while (rs.next()) {
                begudes.add(new Beguda(
                        id++,
                        rs.getString("nom"),
                        rs.getDouble("preu")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Devolvemos la lista de bebidas obtenida de la base de datos.
        return begudes;
    }

    /**
     * Método para conectar a la base de datos utilizando JDBC.
     * Recibe la URL de conexión, el usuario y la contraseña, y devuelve un objeto
     * Connection
     * que se puede utilizar para interactuar con la base de datos.
     * Si ocurre un error al intentar conectar, se lanza una SQLException.
     * 
     * @param URL
     * @param user
     * @param password
     * @return
     * @throws SQLException
     */
    public static Connection connectarBBDD(String URL, String user, String password) throws SQLException {
        // Establecemos la conexión a la base de datos utilizando DriverManager, pasando
        // la URL, el usuario y la contraseña.
        return DriverManager.getConnection(URL, user, password);

    }

    /**
     * Método para imprimir el ticket de compra de una comanda. Recibe una comanda y
     * la
     * ruta del archivo donde se guardará el ticket. El método escribe en el archivo
     * los
     * detalles de la comanda, incluyendo el plato (con indicación de vegetariano si
     * aplica), la bebida, los precios con IVA incluido, y el total de la comanda.
     * Si ocurre un error
     * al intentar escribir el archivo, se imprime la traza del error.
     * 
     * @param c
     * @param ruta
     */
    public static void imprimirTicket(Comanda c, String ruta) {

        // Utilizamos un bloque try-catch para manejar posibles excepciones al escribir
        // el
        // archivo del ticket.
        try {
            // Creamos un FileWriter para escribir en el archivo especificado por la ruta.
            FileWriter fw = new FileWriter(ruta);

            // Escribimos el encabezado del ticket.
            fw.write("TICKET DE COMPRA\n\n");

            // Información del plato, incluyendo el nombre (con indicación de vegetariano si
            // aplica) y el precio con IVA incluido.
            if (c.getPlat() != null) {

                String nomPlat = c.getPlat().getNom();

                // Si el plato es vegetariano, añadimos " (V)" al nombre para indicar que es una
                // opción vegetariana.
                if (c.getPlat() instanceof PlatVegetaria) {
                    fw.write("Plat: " + nomPlat + " (V)\n");
                } else {
                    fw.write("Plat: " + nomPlat + "\n");
                }

                // Escribimos el precio del plato con IVA incluido, formateado a dos decimales.
                fw.write("Preu plat: " + String.format("%.2f", c.getPlat().calcularPreuAmbIVA()) + "\n");
            }

            // Información de la bebida, incluyendo el nombre y el precio con IVA incluido.
            // Si no hay bebida, se indica que el precio es 0.00.
            if (c.getBeguda() != null) {
                fw.write("Beguda: " + c.getBeguda().getNom() + "\n");
                fw.write("Preu beguda: " + String.format("%.2f", c.getBeguda().calcularPreuAmbIVA()) + "\n\n");
            } else {
                fw.write("Beguda: \n");
                fw.write("Preu beguda: 0.00\n");
            }

            // Escribimos el total de la comanda, formateado a dos decimales.
            fw.write("Total: " + String.format("%.2f", c.getTotal()) + "\n");

            // Cerramos el FileWriter para finalizar la escritura del archivo.
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
