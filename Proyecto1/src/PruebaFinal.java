// Clase principal para realizar pruebas completas del sistema
public class PruebaFinal {

    public static void main(String[] args) {

        // Instancia de la clase de gestión (acceso a la BD)
        GestionVehiculos gestion = new GestionVehiculos();

        System.out.println("\n============================================");
        System.out.println("   SISTEMA DE GESTIÓN DE FLOTA - PRUEBAS    ");
        System.out.println("============================================");

        try {

            // 1. LIMPIEZA DE LA BASE DE DATOS
            System.out.println("\n[1] Reiniciando base de datos...");
            gestion.reiniciarTablas();

            // 2. INSERCIÓN DE DATOS
            System.out.println("\n[2] Insertando vehículos...");

            Vehiculo v1 = new Vehiculo("1234-BBB", "Seat Leon", 1);
            Vehiculo v2 = new Vehiculo("5678-CCC", "Ford Transit", 2);
            Vehiculo v3 = new Vehiculo("9012-DDD", "Toyota Auris", 1);

            gestion.insertarVehiculo(v1);
            gestion.insertarVehiculo(v2);
            gestion.insertarVehiculo(v3);

            // 3. LISTADO INICIAL
            System.out.println("\n[3] Listado inicial:");
            gestion.listarVehiculos();

            // 4. PRUEBA DE ACTUALIZACIÓN
            System.out.println("\n[4] Actualizando vehículo ID 1...");
            Vehiculo actualizado = new Vehiculo("1234-BBB", "Seat Ibiza NUEVO", 2);
            gestion.actualizarVehiculo(actualizado, 1);

            System.out.println("\nDespués de actualizar:");
            gestion.listarVehiculos();

            // 5. PRUEBA DE ELIMINACIÓN
            System.out.println("\n[5] Eliminando vehículo ID 2...");
            gestion.eliminarVehiculo(2);

            System.out.println("\nDespués de eliminar:");
            gestion.listarVehiculos();

            // 6. PRUEBA DE ERROR (ID inexistente)
            System.out.println("\n[6] Intentando eliminar ID inexistente (99)...");
            gestion.eliminarVehiculo(99);

            // 7. PRUEBA DE DUPLICADO (matrícula única)
            System.out.println("\n[7] Intentando insertar matrícula duplicada...");
            Vehiculo duplicado = new Vehiculo("1234-BBB", "Duplicado", 1);
            gestion.insertarVehiculo(duplicado);

        } catch (Exception e) {

            // Captura de errores generales
            System.err.println("\nERROR CRÍTICO DURANTE LAS PRUEBAS:");
            e.printStackTrace();
        }

        System.out.println("\n============================================");
        System.out.println("      FIN DE LAS PRUEBAS GLOBALES           ");
        System.out.println("============================================");
    }
}