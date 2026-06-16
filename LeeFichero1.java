import java.io.*;

public class LeeFichero1 {
    public static void main(String[] arg) {

        // 1. EL NOMBRE DEL ARCHIVO
        // Buscamos "prueba.txt" que está en la carpeta raíz PROGRAMACION.
        String nombreFichero = "prueba.txt";

        // 2. REFERENCIA AL ARCHIVO
        // File no abre el archivo, solo le dice a Java dónde está en el disco.
        File archivo = new File(nombreFichero);

        // 3. FLUJO DE LECTURA (Try-with-resources)
        // FileReader: Abre el archivo para leer caracteres.
        // BufferedReader: Lee líneas completas (más rápido).
        // El try(...) cierra ambos automáticamente al terminar.
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {

            String linea;
            System.out.println("--- Leyendo archivo: " + archivo.getAbsolutePath() + " ---");

            // 4. BUCLE DE LECTURA
            // br.readLine() saca una línea. Si es null, el archivo terminó.
            while ((linea = br.readLine()) != null) {
                // Imprime el contenido en la consola de VS Code.
                System.out.println(linea);
            }

        } catch (IOException e) {
            // 5. CONTROL DE ERRORES
            // Si el archivo no existe o no hay permisos, salta aquí.
            System.err.println("Error de lectura: " + e.getMessage());
        }
    }
}