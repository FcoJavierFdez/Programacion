package PREPAREDSTATEMENT;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {

    private Connection connection;

    public UsuarioDao(Connection connection) {
        this.connection = connection;
    }

    // ➕ CREATE (Insertar usuario)
    public void insertarUsuario(String nombre) {
        String sql = "INSERT INTO usuarios (nombre) VALUES (?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.executeUpdate();
            System.out.println("Usuario insertado correctamente");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 📖 READ (Obtener todos los usuarios)
    public List<String> obtenerUsuarios() {
        List<String> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";

        try (PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(rs.getInt("id") + " - " + rs.getString("nombre"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // 🔍 READ (Buscar por ID)
    public String buscarPorId(int id) {
        String sql = "SELECT * FROM usuarios WHERE id = ?";
        String resultado = null;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                resultado = rs.getInt("id") + " - " + rs.getString("nombre");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultado;
    }

    // ✏️ UPDATE (Actualizar usuario)
    public void actualizarUsuario(int id, String nuevoNombre) {
        String sql = "UPDATE usuarios SET nombre = ? WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, nuevoNombre);
            ps.setInt(2, id);

            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println("Usuario actualizado correctamente");
            } else {
                System.out.println("No se encontró el usuario");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ❌ DELETE (Eliminar usuario)
    public void eliminarUsuario(int id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);
            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println("Usuario eliminado correctamente");
            } else {
                System.out.println("No se encontró el usuario");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}