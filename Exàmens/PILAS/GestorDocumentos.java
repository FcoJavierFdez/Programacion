package Exàmens.PILAS;

/*
    EJERCICIO COMPLETO: GESTOR DE DOCUMENTOS CON PILAS Y FICHEROS

    Conceptos trabajados:
    ✓ Stack (Pila)
    ✓ Objetos
    ✓ Menú
    ✓ BufferedReader
    ✓ BufferedWriter
    ✓ Ficheros
    ✓ Excepciones
    ✓ push()
    ✓ pop()
    ✓ peek()
    ✓ isEmpty()
    ✓ Recorrido de estructuras
    ✓ split()
    ✓ Programación Orientada a Objetos

    Formato del fichero documentos.txt:

    1;Java Básico;Francisco
    2;Colecciones Java;Ana
    3;Ficheros en Java;Pedro
*/

import java.io.*;
import java.util.Scanner;
import java.util.Stack;

public class GestorDocumentos {

    /*
     * Clase Documento.
     * 
     * Cada objeto Documento tendrá:
     * - id
     * - titulo
     * - autor
     */
    static class Documento {

        private int id;
        private String titulo;
        private String autor;

        public Documento(int id, String titulo, String autor) {
            this.id = id;
            this.titulo = titulo;
            this.autor = autor;
        }

        public int getId() {
            return id;
        }

        public String getTitulo() {
            return titulo;
        }

        public String getAutor() {
            return autor;
        }

        @Override
        public String toString() {
            return "ID: " + id +
                    " | Título: " + titulo +
                    " | Autor: " + autor;
        }
    }

    // Pila donde almacenaremos los documentos
    static Stack<Documento> pila = new Stack<>();

    // Scanner para leer datos del teclado
    static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {

        int opcion;

        do {

            // Mostrar menú
            System.out.println("\n==============================");
            System.out.println(" GESTOR DE DOCUMENTOS");
            System.out.println("==============================");
            System.out.println("1. Añadir documento");
            System.out.println("2. Ver documento superior");
            System.out.println("3. Revisar documento");
            System.out.println("4. Mostrar documentos");
            System.out.println("5. Guardar en fichero");
            System.out.println("6. Cargar desde fichero");
            System.out.println("7. Salir");
            System.out.print("Opción: ");

            try {

                opcion = Integer.parseInt(teclado.nextLine());

                switch (opcion) {

                    case 1:
                        agregarDocumento();
                        break;

                    case 2:
                        verDocumentoSuperior();
                        break;

                    case 3:
                        revisarDocumento();
                        break;

                    case 4:
                        mostrarDocumentos();
                        break;

                    case 5:
                        guardarFichero();
                        break;

                    case 6:
                        cargarFichero();
                        break;

                    case 7:
                        System.out.println("Programa finalizado.");
                        break;

                    default:
                        System.out.println("Opción incorrecta.");
                }

            } catch (NumberFormatException e) {

                System.out.println("Debes introducir un número.");
                opcion = 0;
            }

        } while (opcion != 7);
    }

    /*
     * MÉTODO PARA AÑADIR DOCUMENTOS
     * 
     * Utilizamos push() porque estamos trabajando
     * con una pila (Stack).
     */
    public static void agregarDocumento() {

        try {

            System.out.print("ID: ");
            int id = Integer.parseInt(teclado.nextLine());

            System.out.print("Título: ");
            String titulo = teclado.nextLine();

            System.out.print("Autor: ");
            String autor = teclado.nextLine();

            Documento doc = new Documento(id, titulo, autor);

            // Inserta el documento en la cima de la pila
            pila.push(doc);

            System.out.println("Documento añadido correctamente.");

        } catch (NumberFormatException e) {

            System.out.println("El ID debe ser numérico.");
        }
    }

    /*
     * MÉTODO PARA VER EL ELEMENTO SUPERIOR
     * 
     * peek() permite consultar la cima
     * sin eliminar el elemento.
     */
    public static void verDocumentoSuperior() {

        if (pila.isEmpty()) {

            System.out.println("La pila está vacía.");
            return;
        }

        System.out.println("\nDocumento superior:");
        System.out.println(pila.peek());
    }

    /*
     * MÉTODO PARA REVISAR DOCUMENTO
     * 
     * pop() elimina y devuelve
     * el último elemento añadido.
     */
    public static void revisarDocumento() {

        if (pila.isEmpty()) {

            System.out.println("No hay documentos pendientes.");
            return;
        }

        Documento revisado = pila.pop();

        System.out.println("\nDocumento revisado:");
        System.out.println(revisado);
    }

    /*
     * MÉTODO PARA MOSTRAR TODOS LOS DOCUMENTOS
     * 
     * Recorremos la pila mediante un foreach.
     */
    public static void mostrarDocumentos() {

        if (pila.isEmpty()) {

            System.out.println("No existen documentos.");
            return;
        }

        System.out.println("\nLISTADO DE DOCUMENTOS:");

        for (Documento doc : pila) {
            System.out.println(doc);
        }
    }

    /*
     * MÉTODO PARA GUARDAR EN FICHERO
     * 
     * Utilizamos BufferedWriter.
     */
    public static void guardarFichero() {

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("documentos.txt"));

            for (Documento doc : pila) {
                bw.write(
                        doc.getId() + ";" + doc.getTitulo() + ";" + doc.getAutor());

                bw.newLine();
            }

            bw.close();
            System.out.println("Datos guardados correctamente.");

        } catch (IOException e) {
            System.out.println("Error al guardar el fichero.");
        }
    }

    /*
     * MÉTODO PARA CARGAR DESDE FICHERO
     * 
     * Utilizamos BufferedReader.
     */
    public static void cargarFichero() {

        try {

            BufferedReader br = new BufferedReader(new FileReader("documentos.txt"));

            // Limpiamos la pila antes de cargar
            pila.clear();

            String linea;

            while ((linea = br.readLine()) != null) {

                /*
                 * Separamos la línea usando ";"
                 * 
                 * Ejemplo:
                 * 1;Java;Francisco
                 * 
                 * datos[0] -> 1
                 * datos[1] -> Java
                 * datos[2] -> Francisco
                 */
                String[] datos = linea.split(";");

                int id = Integer.parseInt(datos[0]);

                String titulo = datos[1];
                String autor = datos[2];

                Documento doc = new Documento(id, titulo, autor);

                // Insertamos en la pila
                pila.push(doc);
            }

            br.close();

            System.out.println("Datos cargados correctamente.");

        } catch (FileNotFoundException e) {
            System.out.println("El fichero no existe.");

        } catch (IOException e) {
            System.out.println("Error de lectura.");

        } catch (NumberFormatException e) {
            System.out.println("Error de formato numérico.");

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Línea corrupta en el fichero.");
        }
    }
}
