import java.sql.*;

/*
*Clase que gestiona las operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
*sobre la tabla Vehiculos utilizando JDBC para conectarse a una base de datos MySQL.
*/

public class GestionVehiculos {

    // Datos de conexión a la base de datos MySQL
    // URL incluye el nombre de la BD y la zona horaria
    private static final String URL = "jdbc:mysql://localhost:3306/GestionFlota?serverTimezone=UTC";
    private static final String USER = "root"; // Usuario de MySQL
    private static final String PASS = ""; // Contraseña

    /*
     * Método para limpiar la tabla Vehiculos (útil para pruebas)
     */
    public void reiniciarTablas() {

        // try-with-resources: abre la conexión y el Statement y los cierra
        // automáticamente
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
                Statement stmt = con.createStatement()) {

            // Se desactivan las claves foráneas para evitar errores al borrar datos
            stmt.executeUpdate("SET FOREIGN_KEY_CHECKS = 0");

            // TRUNCATE elimina todos los registros y reinicia el AUTO_INCREMENT
            stmt.executeUpdate("TRUNCATE TABLE Vehiculos");

            // Se vuelven a activar las claves foráneas
            stmt.executeUpdate("SET FOREIGN_KEY_CHECKS = 1");

            System.out.println("Base de datos limpia.");

        } catch (SQLException e) {
            // Captura de errores de conexión o SQL
            System.err.println("Error al limpiar: " + e.getMessage());
        }
    }

    /*
     * Método para insertar un nuevo vehículo en la base de datos
     */
    public void insertarVehiculo(Vehiculo v) {

        // Consulta SQL parametrizada (evita inyección SQL)
        String sql = "INSERT INTO Vehiculos (matricula, modelo, id_dept) VALUES (?, ?, ?)";

        // try-with-resources asegura que la conexión y el PreparedStatement se cierren
        // automáticamente
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
                PreparedStatement pstmt = con.prepareStatement(sql)) {

            // Se asignan los valores del objeto Vehiculo a la consulta
            pstmt.setString(1, v.getMatricula());
            pstmt.setString(2, v.getModelo());
            pstmt.setInt(3, v.getIdDept());

            // Se ejecuta la inserción en la base de datos
            pstmt.executeUpdate();

            System.out.println("Vehículo " + v.getMatricula() + " insertado.");

        } catch (SQLException e) {
            System.err.println("Error al insertar: " + e.getMessage());
        }
    }

    /*
     * Método para obtener y mostrar todos los vehículos
     */
    public void listarVehiculos() {

        // Consulta SQL para seleccionar todos los registros
        String sql = "SELECT id_vehiculo, matricula, modelo, id_dept FROM Vehiculos";

        // try-with-resources también gestiona el ResultSet automáticamente
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
                PreparedStatement pstmt = con.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {

            System.out.println("\n--- LISTADO ACTUAL DE LA FLOTA ---");

            // Recorre cada fila del resultado
            while (rs.next()) {

                // Obtiene los valores de cada columna por su nombre
                System.out.println(
                        "ID: " + rs.getInt("id_vehiculo") +
                                " | Matrícula: " + rs.getString("matricula") +
                                " | Modelo: " + rs.getString("modelo") +
                                " | Dept ID: " + rs.getInt("id_dept"));
            }

        } catch (SQLException e) {
            System.err.println("Error al listar: " + e.getMessage());
        }
    }

    /*
     * Método para eliminar un vehículo por su ID
     */
    public void eliminarVehiculo(int id) {

        // Consulta SQL con parámetro
        String sql = "DELETE FROM Vehiculos WHERE id_vehiculo = ?";

        // try-with-resources garantiza el cierre automático de recursos
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
                PreparedStatement pstmt = con.prepareStatement(sql)) {

            // Se asigna el ID a eliminar
            pstmt.setInt(1, id);

            // Se ejecuta la eliminación
            int filas = pstmt.executeUpdate();

            // Se comprueba si se ha eliminado algún registro
            if (filas > 0) {
                System.out.println("Vehículo ID " + id + " eliminado.");
            } else {
                System.out.println("No se encontró el ID: " + id);
            }

        } catch (SQLException e) {
            System.err.println("Error al eliminar: " + e.getMessage());
        }
    }

    /*
     * Método para actualizar los datos de un vehículo existente
     */
    public void actualizarVehiculo(Vehiculo v, int idAActualizar) {

        // Consulta SQL para modificar un registro
        String sql = "UPDATE Vehiculos SET matricula = ?, modelo = ?, id_dept = ? WHERE id_vehiculo = ?";

        // try-with-resources gestiona automáticamente la conexión y el
        // PreparedStatement
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
                PreparedStatement pstmt = con.prepareStatement(sql)) {

            // Se asignan los nuevos valores
            pstmt.setString(1, v.getMatricula());
            pstmt.setString(2, v.getModelo());
            pstmt.setInt(3, v.getIdDept());
            pstmt.setInt(4, idAActualizar);

            // Se ejecuta la actualización
            int filas = pstmt.executeUpdate();

            // Se comprueba si se ha actualizado correctamente
            if (filas > 0) {
                System.out.println("Vehículo ID " + idAActualizar + " actualizado.");
            }

        } catch (SQLException e) {
            System.err.println("Error al actualizar: " + e.getMessage());
        }
    }
}