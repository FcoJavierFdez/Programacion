package Programacion25.Biblioteca; // Indica que este archivo pertenece a la carpeta/paquete "Biblioteca"

// --- IMPORTACIONES ---
import java.io.*; // Importa todo lo necesario para leer/escribir archivos (File, Writer, Reader)
import java.util.ArrayList; // Importa la lista dinámica para guardar los objetos en memoria
import java.util.Scanner; // Importa la herramienta para leer lo que el usuario escribe en consola
import java.util.Iterator; // Importa el iterador, vital para poder eliminar elementos de una lista mientras la recorres

// ==========================================
// 1. CLASE ABSTRACTA (El "Molde" Genérico)
// ==========================================
/* * Se define como 'abstract' porque no queremos crear objetos genéricos de tipo "Publicacion".
 * Solo queremos crear "Libros" o "Revistas". Esta clase sirve de base para compartir atributos.
 */
abstract class Publicacion {
    // 'protected' significa que solo esta clase y sus hijas (Libro, Revista) pueden
    // acceder directamente a estas variables.
    protected String titulo;
    protected String autor;

    // Constructor: Se ejecuta al crear un objeto para darle valores iniciales.
    public Publicacion(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }

    // Getters: Métodos para leer los valores desde fuera de la clase.
    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    // Método abstracto: Obliga a que cualquier clase hija (Libro/Revista) tenga su
    // propia versión de este método.
    // Esto es POLIMORFISMO: cada uno muestra la información a su manera.
    public abstract void mostrarInformacion();
}

// ==========================================
// 2. CLASE LIBRO (Hija de Publicacion)
// ==========================================
class Libro extends Publicacion { // 'extends' significa HERENCIA (Libro hereda de Publicacion)
    private int numeroPaginas; // Atributo exclusivo de los libros

    public Libro(String titulo, String autor, int numeroPaginas) {
        super(titulo, autor); // 'super' llama al constructor de la clase padre (Publicacion) para guardar
                              // título y autor
        this.numeroPaginas = numeroPaginas; // Guardamos el atributo propio del libro
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    @Override // Indica que estamos sobrescribiendo el método abstracto del padre
    public void mostrarInformacion() {
        System.out.println("Libro [Título: " + titulo + ", Autor: " + autor + ", Páginas: " + numeroPaginas + "]");
    }
}

// ==========================================
// 3. CLASE REVISTA (Hija de Publicacion)
// ==========================================
class Revista extends Publicacion {
    private int numeroEdicion; // Atributo exclusivo de las revistas

    public Revista(String titulo, String autor, int numeroEdicion) {
        super(titulo, autor); // Reutilizamos el constructor del padre
        this.numeroEdicion = numeroEdicion;
    }

    public int getNumeroEdicion() {
        return numeroEdicion;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Revista [Título: " + titulo + ", Autor: " + autor + ", Edición: " + numeroEdicion + "]");
    }
}

// ==========================================
// 4. CLASE BIBLIOTECA (Gestor de la lógica)
// ==========================================
public class Biblioteca {
    // Usamos ArrayList porque es una lista que puede crecer indefinidamente.
    // <Publicacion> indica que guardará tanto Libros como Revistas (gracias a la
    // herencia).
    private ArrayList<Publicacion> publicaciones;

    // Constante para el nombre del archivo. 'static final' significa que no cambia.
    private static final String FICHERO_TEXTO = "biblioteca.txt";

    public Biblioteca() {
        publicaciones = new ArrayList<>(); // Inicializamos la lista vacía
    }

    // Añade cualquier cosa que sea una Publicación (Libro o Revista)
    public void añadirPublicacion(Publicacion p) {
        publicaciones.add(p);
    }

    public void listarPublicaciones() {
        if (publicaciones.isEmpty()) {
            System.out.println("La biblioteca está vacía.");
        } else {
            // Bucle for-each: Recorre cada publicación 'p' en la lista 'publicaciones'
            // Aquí actúa el polimorfismo: si 'p' es libro, usa el mostrarInformacion de
            // Libro, etc.
            for (Publicacion p : publicaciones)
                p.mostrarInformacion();
        }
    }

    // --- GUARDAR EN ARCHIVO ---
    public void guardarEnFicheroTexto() {
        // "try-with-resources": Esto (try (...)) asegura que el fichero se CIERRE
        // automáticamente al terminar,
        // aunque haya errores. Evita que el archivo se quede bloqueado.
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FICHERO_TEXTO))) {

            for (Publicacion p : publicaciones) {
                // 'instanceof' pregunta: ¿Eres un Libro o una Revista?
                if (p instanceof Libro) {
                    Libro l = (Libro) p; // CASTING: Convertimos la variable genérica 'p' a tipo 'Libro' para acceder a
                                         // 'getNumeroPaginas'
                    // Escribimos en formato CSV (separado por comas) para facilitar la lectura
                    // después
                    bw.write("Libro," + l.getTitulo() + "," + l.getAutor() + "," + l.getNumeroPaginas());
                } else if (p instanceof Revista) {
                    Revista r = (Revista) p;
                    bw.write("Revista," + r.getTitulo() + "," + r.getAutor() + "," + r.getNumeroEdicion());
                }
                bw.newLine(); // Salto de línea para el siguiente registro
            }
            System.out.println("Datos guardados con éxito.");
        } catch (IOException e) {
            // Si falla (ej. disco lleno, sin permisos), capturamos el error aquí.
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    // --- CARGAR DESDE ARCHIVO ---
    public void cargarDesdeFicheroTexto() {
        File file = new File(FICHERO_TEXTO);
        if (!file.exists())
            return; // Si el archivo no existe, no hacemos nada y salimos.

        try (BufferedReader br = new BufferedReader(new FileReader(FICHERO_TEXTO))) {
            String linea;
            publicaciones.clear(); // Borramos la lista actual para no duplicar datos al cargar

            // Leemos línea por línea hasta que no haya más (null)
            while ((linea = br.readLine()) != null) {
                // 'split(",")' corta el texto por las comas y crea un array.
                // Ejemplo: "Libro,El Quijote,Cervantes,500" -> ["Libro", "El Quijote",
                // "Cervantes", "500"]
                String[] partes = linea.split(",");

                if (partes[0].equals("Libro")) {
                    // Integer.parseInt convierte el texto "500" al número 500
                    publicaciones.add(new Libro(partes[1], partes[2], Integer.parseInt(partes[3])));
                } else if (partes[0].equals("Revista")) {
                    publicaciones.add(new Revista(partes[1], partes[2], Integer.parseInt(partes[3])));
                }
            }
            System.out.println("Datos cargados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al cargar: " + e.getMessage());
        }
    }

    public void buscarPorTitulo(String titulo) {
        for (Publicacion p : publicaciones) {
            // 'equalsIgnoreCase' compara textos ignorando mayúsculas/minúsculas
            if (p.getTitulo().equalsIgnoreCase(titulo)) {
                p.mostrarInformacion();
                return; // 'return' detiene la función en cuanto lo encuentra
            }
        }
        System.out.println("No se encontró: " + titulo);
    }

    // --- ELIMINAR (Punto Crítico) ---
    public void eliminarPorTitulo(String titulo) {
        // Usamos un ITERATOR. No se debe borrar dentro de un bucle 'for' normal porque
        // altera el tamaño de la lista mientras la recorres y causa error
        // (ConcurrentModificationException).
        Iterator<Publicacion> it = publicaciones.iterator();
        while (it.hasNext()) { // Mientras haya elementos siguientes...
            if (it.next().getTitulo().equalsIgnoreCase(titulo)) { // Mira el siguiente elemento y compara
                it.remove(); // El iterador elimina el elemento de forma segura
                System.out.println("Eliminado con éxito.");
                return;
            }
        }
        System.out.println("No se pudo eliminar: " + titulo);
    }

    // ==========================================
    // 5. MÉTODO MAIN (Menú principal)
    // ==========================================
    public static void main(String[] args) {
        Biblioteca biblio = new Biblioteca();
        Scanner sc = new Scanner(System.in);
        int opcion;

        // Bucle 'do-while': Se ejecuta al menos una vez y repite hasta que el usuario
        // elija Salir (8)
        do {
            System.out.println("\n--- MENU BIBLIOTECA ---");
            System.out.println("1. Añadir Libro ");
            System.out.println("2. Añadir Revista ");
            System.out.println("3. Listar");
            System.out.println("4. Guardar (En archivo txt)");
            System.out.println("5. Cargar (Desde archivo txt)");
            System.out.println("6. Buscar ");
            System.out.println("7. Eliminar ");
            System.out.println("8. Salir");
            System.out.print("Opción: ");

            opcion = sc.nextInt(); // Lee el número
            sc.nextLine(); // IMPORTANTE: Limpia el "Enter" que queda en el buffer después de leer el
                           // número.
                           // Si no pones esto, el siguiente sc.nextLine() leerá una cadena vacía.

            switch (opcion) {
                case 1:
                    System.out.print("Título: ");
                    String tL = sc.nextLine();
                    System.out.print("Autor: ");
                    String aL = sc.nextLine();
                    System.out.print("Páginas: ");
                    int pL = sc.nextInt();
                    // Crea el objeto Libro y lo manda a la lista
                    biblio.añadirPublicacion(new Libro(tL, aL, pL));
                    break;
                case 2:
                    System.out.print("Título: ");
                    String tR = sc.nextLine();
                    System.out.print("Autor: ");
                    String aR = sc.nextLine();
                    System.out.print("Edición: ");
                    int eR = sc.nextInt();
                    // Crea el objeto Revista y lo manda a la lista
                    biblio.añadirPublicacion(new Revista(tR, aR, eR));
                    break;
                case 3:
                    biblio.listarPublicaciones();
                    break;
                case 4:
                    biblio.guardarEnFicheroTexto();
                    break;
                case 5:
                    biblio.cargarDesdeFicheroTexto();
                    break;
                case 6:
                    System.out.print("Título a buscar: ");
                    biblio.buscarPorTitulo(sc.nextLine());
                    break;
                case 7:
                    System.out.print("Título a eliminar: ");
                    biblio.eliminarPorTitulo(sc.nextLine());
                    break;
            }
        } while (opcion != 8);
        sc.close(); // Buena práctica: cerrar el Scanner al terminar el programa
    }
}