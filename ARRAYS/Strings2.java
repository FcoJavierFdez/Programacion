public class Strings2 {
    public static void main(String[] args) {

        /*
         * En una carpeta hay diversos numeros de ficheros.
         * Hace falta analizarlos para identificar sus tipos y
         * validad nombres validos.
         */

        // Guarda 8 nombres de ficheros(con extension) en un vector cadena. Muestralas
        String[] ficheros = {
                "documento.txt",
                "imagen.jpeg",
                "video.mp4",
                "archivo.pdf",
                "musica.mp3",
                "presentacion.pptx",
                "datos.csv",
                "script.js"
        };
        System.out.println("Nombres de ficheros:");
        for (String fichero : ficheros) {
            System.out.println(fichero);
        }

        // Muestra aquellos que no tienen extensión.

        System.out.println("\nFicheros sin extensión:");
        for (String fichero : ficheros) {
            if (!fichero.contains(".")) {
                System.out.println(fichero);
            }
        }

        /*
         * Muestra aquellos nombres que terminan en .txt.
         */
        System.out.println("\nFicheros con extensión .txt:");
        for (String fichero1 : ficheros) {
            if (fichero1.endsWith(".txt")) {
                System.out.println(fichero1);
            }
        }

        // Muestra que nombres contienen espacios.
        System.out.println("\nFicheros que contienen espacios:");
        for (String fichero2 : ficheros) {
            if (fichero2.contains(" ")) {
                System.out.println(fichero2);
            }
        }

        // Cambia todos los nombres para que esten en minusculas.
        System.out.println("\nNombres de ficheros en minúsculas:");
        for (String fichero3 : ficheros) {
            System.out.println(fichero3.toLowerCase());
        }

        // Muestra solo nombres de archivos sin la extensión.
        System.out.println("\nNombres de ficheros sin extensión:");
        for (String fichero4 : ficheros) {
            int indicePunto = fichero4.lastIndexOf(".");
            if (indicePunto != -1) {
                String nombreSinExtension = fichero4.substring(0, indicePunto);
                System.out.println(nombreSinExtension);
            } else {
                System.out.println(fichero4); // Si no hay extensión, muestra el nombre completo
            }
        }

    }
}
