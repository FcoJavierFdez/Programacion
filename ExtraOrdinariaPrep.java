
public class TEST {
    public static void main(String[] args) {

        // Se pueden crear objetos de tipo Transporte, objeto Transporte de tipo
        // transporte

        Transporte transporteLunes = new Transporte();

        transporteLunes.setCoste();
        transporteLunes.calculoCosteTaxi();

        // Accedemos al metodo calculaCoste que va a calcular el coste
        System.out.println("El coste del viaje es: " + transporteLunes.calculaCoste());

        // Accedemos al metodo getCoste
        System.out.println("El coste del viaje es: " + transporteLunes.getCoste());

        // Accedemos al metodo private
        System.out.println("El coste del viaje por taxi es: " + transporteLunes.calculoCosteTaxi());
    }

    /**
     * Cuando utilizar static en atributos y en metodos, y que tipo de parametros
     * debo pasarle al metodo, que preguntas debo hacerme segun que tipo de
     * enunciado.
     * 
     * Las clases abstractas siempre definen atributos protegidos, porque estos los
     * heredan en sus subclases, y son las superclases
     * las que implementan interfaces? y si hubieran enums? los enums tambien pueden
     * generar sus propios metodos.
     * 
     * Es mejor utilizar booleanos para comprobar siempre las condiciones de alguna
     * propiedad, por ejemplo imaginemos una clase producto en una fabrica de
     * productos que puede ser
     * probable, o empaquetable, en su inicializacion, el valor por defecto siempre
     * es false cuando se instancian?
     */

    class Transporte {

        // Propiedades, atributos

        int id;
        /*
         * private encapsula las propiedades para que no se puedan modificar desde fuera
         * de la clase
         * private double coste;
         * metodos de acceso de propiedades, setter establecen el valor de la propiedad
         * y getter nos permiten obtener el valor de esa propiedad.
         * setCoste(){}
         * getCoste(){}
         */
        int tiempo;

        // Metodo para calcular el coste y no utilizar la propiedad, devuelve coste=45.

        double calculaCoste() {

            int coste = 45;

            return coste;
        }

        // Comportamientos (metodos) en este caso no devuelve ningun valor, por eso
        // "void"

        void reservar() {
            System.out.println("Reserva realizada");
        }

        // Getter y setter, metodos publicos que son accesibles desde otras clases.

        public void setCoste() {
            this.coste = coste;

        }

        public double getCoste(double coste) {
            return coste;
        }

        // metodo privado que solo se puede acceder desde esta clase.
 private double calculoCosteTaxi(){
 
  return= 40;
 }

        // Guardar registro en archivo
        try(

        BufferedWriter writer = new BufferedWriter(new FileWriter("registro_alquileres.txt", true)))
        {
            writer.write(alquiler.generarContrato());
            writer.newLine();
        }catch(
        IOException e)
        {
            e.printStackTrace();
}

// Conexion a una BD
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String URL = "jdbc:mysql://localhost:3306/alquiler_vehiculos";
    private static final String USER = "root";
    private static final String PASSWORD = "tu_contraseña";

    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

import java.sql.*;
import java.util.ArrayList;

public class GestorVehiculosBD {

    public static ArrayList<Vehiculo> obtenerVehiculos() {
        ArrayList<Vehiculo> vehiculos = new ArrayList<>();

        try (Connection conn = ConexionBD.obtenerConexion()) {
            String sql = "SELECT * FROM vehiculos";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String tipo = rs.getString("tipo");
                String matricula = rs.getString("matricula");
                String marca = rs.getString("marca");
                String modelo = rs.getString("modelo");
                double precio = rs.getDouble("precioPorDia");
                double extra = rs.getDouble("extraPorDia");
                boolean disponible = rs.getBoolean("disponible");
                int veces = rs.getInt("vecesAlquilado");

                Vehiculo v = null;
                switch (tipo) {
                    case "Coche":
                        v = new Coche(matricula, marca, modelo, precio);
                        break;
                    case "Moto":
                        v = new Moto(matricula, marca, modelo, precio);
                        break;
                    case "Furgoneta":
                        v = new Furgoneta(matricula, marca, modelo, precio, extra);
                        break;

                }
                if (v != null) {
                    v.disponible = disponible;
                    v.vecesAlquilado = veces;
                    vehiculos.add(v);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehiculos;
    }

    public static void actualizarVehiculo(Vehiculo v) {
        try (Connection conn = ConexionBD.obtenerConexion()) {
            String sql = "UPDATE vehiculos SET disponible = ?, vecesAlquilado = ? WHERE matricula = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setBoolean(1, v.disponible);
            stmt.setInt(2, v.vecesAlquilado);
            stmt.setString(3, v.matricula);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

}
