package ARCHIVOS;

import java.io.File;

public class CreaCarpetas {

    public static void main(String[] args) {

        // 1. Crear carpetas principales
        File misCosas = new File("Documentos/Mis Cosas");
        File alfabeto = new File("Documentos/Alfabeto");

        // mkdirs() crea toda la ruta si no existe
        misCosas.mkdirs();
        alfabeto.mkdirs();

        System.out.println("Carpetas principales creadas");

        // 2. Mover carpetas
        File fotosOrigen = new File("Documentos/Fotografias");
        File fotosDestino = new File("Documentos/Mis Cosas/Fotografias");

        File librosOrigen = new File("Documentos/Libros");
        File librosDestino = new File("Documentos/Mis Cosas/Libros");

        // Solo mover si existen
        if (fotosOrigen.exists()) {
            fotosOrigen.renameTo(fotosDestino);
            System.out.println("Fotografias movidas");
        } else {
            System.out.println("No existe la carpeta Fotografias");
        }

        if (librosOrigen.exists()) {
            librosOrigen.renameTo(librosDestino);
            System.out.println("Libros movidos");
        } else {
            System.out.println("No existe la carpeta Libros");
        }

        // 3. Crear carpetas de la A a la Z
        for (char letra = 'A'; letra <= 'Z'; letra++) {
            File carpeta = new File("Documentos/Alfabeto/" + letra);
            carpeta.mkdir();
        }

        System.out.println("Carpetas del alfabeto creadas");
    }
}