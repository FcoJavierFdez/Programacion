import java.io.*;

public class EscribeFichero {
    // Definimos un array de contactos (lo inicializamos vacío para evitar errores
    // de ejecución)
    private Contact[] contactes = new Contact[0];
    private File rutaActual;

    public static void main(String[] args) {
        // "try-with-resources": Abre el archivo y se asegura de cerrarlo solo al
        // terminar
        try (FileWriter fichero = new FileWriter("prueba.txt")) {
            // PrintWriter nos permite usar println() como si fuera la consola
            PrintWriter pw = new PrintWriter(fichero);

            // Bucle que escribe 10 líneas en el archivo
            for (int i = 0; i < 10; i++)
                pw.println("Linea " + i); // Escribe el texto y añade un salto de línea

            System.out.println("Prueba completada: Archivo 'prueba.txt' creado.");

        } catch (Exception e) {
            // Si hay un error (permisos, disco lleno, etc.), lo imprime
            e.printStackTrace();
        }
    }

    /**
     * Guarda los datos del array 'contactes' en un archivo específico.
     */
    public void saveContactDataToFile(File arxiu) {
        // Usamos try-with-resources para manejar el FileWriter de forma segura
        try (FileWriter fitxer = new FileWriter(arxiu)) {

            // Recorremos el array de contactos
            for (Contact contact : this.contactes) {
                // Construimos una cadena de texto separada por comas (formato CSV)
                String str = contact.getNom() + ","
                        + contact.getCognoms() + ","
                        + contact.getDomicili() + ","
                        + contact.getCiutat() + ","
                        + String.valueOf(contact.getCodi_postal()) + ","
                        + DateUtil.format(contact.getData_de_naixement());

                // Escribimos la línea del contacto
                fitxer.write(str);
                // System.lineSeparator() pone el salto de línea correcto según el sistema
                // (Windows/Linux)
                fitxer.write(System.lineSeparator());
            }

            // Llamamos al método para actualizar la ruta actual donde hemos guardado
            this.setContactFilePath(arxiu);

        } catch (Exception ex) {
            // Mensaje de error personalizado indicando el nombre del archivo fallido
            System.err.println("Error al guardar els contacts en l'arxiu: " + arxiu.getName());
        }
    }

    /**
     * Actualiza la variable interna con la ubicación del archivo de contactos.
     */
    private void setContactFilePath(File arxiu) {
        // Guardamos la referencia del archivo en nuestra variable de clase
        this.rutaActual = arxiu;

        // Imprimimos una confirmación para saber dónde se guardó
        if (arxiu != null) {
            System.out.println("Ruta de contactos actualizada a: " + arxiu.getAbsolutePath());
        }
    }
}