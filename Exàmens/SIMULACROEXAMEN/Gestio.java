package SIMULACROEXAMEN;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Queue;

public class Gestio {
    public double processarEnviaments(Queue<Enviament> cua) {
        // Variable para ir guardando la suma de todos los costes
        double costTotalAcumulat = 0.0;

        // BUCLE FOR-EACH: Recorre la cola extrayendo cada paquete y llamándolo 'env'
        for (Enviament env : cua) {

            // Gracias a que la clase padre obligaba a tener este método,
            // Java sabe calcularlo sin importar si el paquete es Estándar o Premium.
            double costEnviament = env.calcularCost();

            // Sumamos el coste al total general
            costTotalAcumulat += costEnviament;

            // Imprimimos la información común a todos los paquetes
            System.out.println("Identificador: " + env.getCodi() + " | Cost: " + costEnviament + "€");

            // EL TRUCO DEL POLIMORFISMO (instanceof)
            // Como en la cola hay paquetes mezclados, le preguntamos a este paquete
            // específico:
            // "¿Tú eres de los que implementa la interfaz Assegurable?"
            if (env instanceof Asegurable) {

                // Si la respuesta es sí, "disfrazamos" (casting) la variable 'env' a tipo
                // Assegurable.
                // Si no hacemos esto, Java no nos dejaría llamar a obtenirPrima().
                Asegurable assegurable = (Asegurable) env;

                System.out.println("   -> [Info Assegurança] Prima a pagar: " + assegurable.obtenirPrima() + "€");
            }
        }

        // Al terminar de vaciar la cola, devolvemos la suma total
        return costTotalAcumulat;
    }

    public void registrarEnviament(Enviament enviament) {

        // ==========================================
        // 1. GUARDAR EN ARCHIVO DE TEXTO
        // ==========================================

        // Usamos un bloque "try con recursos" (los paréntesis después del try).
        // Esto asegura que el archivo se cierre automáticamente al acabar, aunque haya
        // un error.
        // OJO AL 'true': Sirve para añadir texto al final del archivo sin borrar lo que
        // ya había.
        try (FileWriter fw = new FileWriter("enviaments.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)) {

            // Escribimos una nueva línea en el .txt solo con el código del envío
            out.println(enviament.getCodi());

        } catch (IOException e) {
            // Si el archivo está bloqueado o el disco lleno, atrapamos la Excepción aquí
            // 'System.err' imprime el mensaje en color rojo (error) por la consola.
            System.err.println("Error al guardar en el archivo de texto: " + e.getMessage());
        }

        // ==========================================
        // 2. GUARDAR EN LA BASE DE DATOS (JDBC)
        // ==========================================

        // Preparamos los datos de conexión que nos dio el enunciado
        String url = "jdbc:mysql://localhost:3306/logistica_db";
        String usuari = "alumne";
        String contrasenya = "Logistica26!";

        // Consulta SQL. Usamos interrogantes (?) por seguridad (evita inyecciones SQL)
        String sql = "INSERT INTO enviaments (codi, destinatari, asseguranca) VALUES (?, ?, ?)";

        // Volvemos a usar el "try con recursos" para la conexión a la base de datos
        try (Connection conn = DriverManager.getConnection(url, usuari, contrasenya);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Sustituimos el primer '?' por el código del envío
            pstmt.setString(1, enviament.getCodi());

            // Sustituimos el segundo '?' por el destinatario
            pstmt.setString(2, enviament.getDestinatari());

            // Para el tercer '?' (el seguro), necesitamos saber si el paquete tiene seguro
            // o no
            if (enviament instanceof Asegurable) {
                // Si es asegurable, hacemos el casting y le guardamos el valor numérico de la
                // prima
                pstmt.setDouble(3, ((Asegurable) enviament).obtenirPrima());
            } else {
                // Si es un paquete estándar, le decimos a la base de datos que guarde un valor
                // nulo (NULL)
                pstmt.setNull(3, java.sql.Types.DOUBLE);
            }

            // Una vez rellenados los 3 interrogantes, ejecutamos la orden para que se
            // guarde
            pstmt.executeUpdate();

        } catch (SQLException e) {
            // Si la contraseña de MySQL está mal o el servidor apagado, capturamos el error
            // aquí
            System.err.println("Error al conectar o insertar en la Base de Datos: " + e.getMessage());
        }
    }
}
