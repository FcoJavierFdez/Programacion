import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import uf12addressapp.models.Contact;

public class GestorContactes {

    // Connexión a la BD
    public static Connection connexio;
    // PreparedStatements
    private static PreparedStatement stmtInserir;
    private static PreparedStatement stmtActualitzar;
    private static PreparedStatement stmtEliminar;
    private static Integer id;

    // Metodo para conectarse y crear la bd
    public static void connectarDB() throws SQLException {
        try {

            // Usuario y contraseña de MySQL
            String user = "root";
            String password = "Fernan.74";
            // Conectar sin especificar la base de datos.
            Connection tempConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "Fernan.74");

            // Comprobar si existe la base de datos con ese nombre
            Statement stmt = tempConn.createStatement();
            ResultSet rs = stmt.executeQuery("SHOW DATABASES LIKE 'addressapp'");

            if (!rs.next()) {
                // Si no existe la tabla, la creamos 
                stmt.executeUpdate("CREATE DATABASE uf12addressapp");
                // Creamos la tabla
                tempConn.createStatement().executeUpdate(
                        "CREATE TABLE addressapp.contactes ("
                        + "id INT PRIMARY KEY AUTO_INCREMENT,"
                        + "nom VARCHAR(50) NOT NULL,"
                        + "cognoms VARCHAR(50) NOT NULL,"
                        + "domicili VARCHAR(100) NOT NULL,"
                        + "ciutat VARCHAR(50) NOT NULL,"
                        + "codi_postal INT NOT NULL,"
                        + "data_naix DATE NOT NULL"
                        + ")"
                );
            }
            // Cerramos rs, stmt y tempConn
            rs.close();
            stmt.close();
            tempConn.close();

            // Conectando con la base de datos, con usuario y contraseña.
            connexio = DriverManager.getConnection("jdbc:mysql://localhost:3306/uf12addressapp", "root", "Fernan.74");

            // Preparación de los prepareStatement para la insercion, actualizacion y eliminación.
            stmtInserir = connexio.prepareStatement(
                    "INSERT INTO contactes (nom, cognoms, domicili, ciutat, codi_postal, data_naix) VALUES (?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );

            stmtActualitzar = connexio.prepareStatement(
                    "UPDATE contactes SET nom = ?, cognoms = ?, domicili = ?, ciutat = ?, codi_postal = ?, data_naix = ? WHERE id = ?"
            );

            stmtEliminar = connexio.prepareStatement(
                    "DELETE FROM contactes WHERE id = ?"
            );

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Método para cargar los contactos.
     *
     * @return
     * @throws java.sql.SQLException
     */
    public static ArrayList<Contact> carregarContactes() throws SQLException {
        ArrayList<Contact> contactes = new ArrayList<>();

        String query = "SELECT *, DAY(data_naix) AS dia, MONTH(data_naix) AS mes, YEAR(data_naix) AS any FROM contactes";
        try (Statement stmt = connexio.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            // Iteramos sobre la consulta.
            while (rs.next()) {
                //El id se puede ignorar
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String cognoms = rs.getString("cognoms");
                String domicili = rs.getString("domicili");
                String ciutat = rs.getString("ciutat");
                int codiPostal = rs.getInt("codi_postal");

                int dia = rs.getInt("dia");
                int mes = rs.getInt("mes");
                int any = rs.getInt("any");
                // Creem un objecte LocalDate amb la data de naixement
                LocalDate dataNaixement = LocalDate.of(any, mes, dia);

                Contact contact = new Contact(nom, cognoms, domicili, ciutat, codiPostal, dia, mes, any);
                contact.setId(id);
                contactes.add(contact);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return contactes;
    }

    /**
     * Método para insertar un contacto
     *
     * @param contact
     * @return
     * @throws SQLException
     */
    public static int inserirContacte(Contact contact) throws SQLException {
        int idGenerat = -1;
        try {
            String sql = " INSERT INTO contactes (nom, cognoms, domicili, ciutat, codi_postal, data_naix) VALUES (?,?,?,?,?,?)";
            PreparedStatement pstmt = connexio.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmtInserir.setString(1, contact.getNom());
            stmtInserir.setString(2, contact.getCognoms());
            stmtInserir.setString(3, contact.getDomicili());
            stmtInserir.setString(4, contact.getCiutat());
            stmtInserir.setInt(5, contact.getCodi_postal());
            stmtInserir.setDate(6, java.sql.Date.valueOf(contact.getData_de_naixement()));

            int filesInserides = stmtInserir.executeUpdate();

            if (filesInserides > 0) {
                try (ResultSet rs = stmtInserir.getGeneratedKeys()) {
                    if (rs.next()) {
                        idGenerat = rs.getInt(1);
                        contact.setId(idGenerat);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return idGenerat;
    }

    /**
     * Método para actualizar un contacto.
     *
     * @param contact
     * @throws SQLException
     */
    public static void actualitzarContacte(Contact contact) throws SQLException {
        try {
            stmtActualitzar.setString(1, contact.getNom());
            stmtActualitzar.setString(2, contact.getCognoms());
            stmtActualitzar.setString(3, contact.getDomicili());
            stmtActualitzar.setString(4, contact.getCiutat());
            stmtActualitzar.setInt(5, contact.getCodi_postal());
            stmtActualitzar.setDate(6, java.sql.Date.valueOf(contact.getData_de_naixement()));
            stmtActualitzar.setInt(7, contact.getId());

            stmtActualitzar.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Método para eliminar contactos por id.
     *
     * @param ids
     */
    public static void eliminarContactes(ArrayList<Integer> ids) {
        try {
            // Obtenemos los id de los contactos BD
            String sqlSelectIds = "SELECT id FROM contactes";
            java.sql.PreparedStatement pstmtSelect = connexio.prepareStatement(sqlSelectIds);
            java.sql.ResultSet rs = pstmtSelect.executeQuery();

            // Creamos ArrayList con los id de la BD
            ArrayList<Integer> idsBD = new ArrayList<>();
            while (rs.next()) {
                idsBD.add(rs.getInt("id"));
            }

            // Eliminamos los ids de los contactos presentes en uf12addressapp
            idsBD.removeAll(ids);

            // Eliminamos los contactos que queden en la BD
            String sqlDelete = "DELETE FROM contactes WHERE id = ?";
            for (Integer id : idsBD) {
                java.sql.PreparedStatement pstmtDelete = connexio.prepareStatement(sqlDelete);
                pstmtDelete.setInt(1, id);
                // Ejecutamos la eliminación.
                pstmtDelete.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    /**
     * Método para desconectarnos de la BD
     */
    public static void desconnectarBD() {
        try {
            if (stmtInserir != null) {
                stmtInserir.close();
            }
            if (stmtActualitzar != null) {
                stmtActualitzar.close();
            }
            if (stmtEliminar != null) {
                stmtEliminar.close();
            }
            if (connexio != null) {
                connexio.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}