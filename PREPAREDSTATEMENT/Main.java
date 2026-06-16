package PREPAREDSTATEMENT;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/mi_base?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String password = "";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {

            UsuarioDao dao = new UsuarioDao(conn);

            // CREATE
            dao.insertarUsuario("Ana");
            dao.insertarUsuario("Luis");

            // READ (todos)
            List<String> usuarios = dao.obtenerUsuarios();
            System.out.println("Lista de usuarios:");
            usuarios.forEach(System.out::println);

            // READ (por ID)
            System.out.println("Buscar ID 1:");
            System.out.println(dao.buscarPorId(1));

            // UPDATE
            dao.actualizarUsuario(1, "Ana María");

            // DELETE
            dao.eliminarUsuario(2);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
