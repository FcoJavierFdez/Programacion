public class String4 {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("Hola");
        System.out.println("Cadena original: " + sb.toString());

        // Agregar texto al final
        sb.append(", ¿cómo estás?");
        System.out.println("Después de append: " + sb.toString());

        // Insertar texto en una posición específica
        sb.insert(5, " amigo");
        System.out.println("Después de insert: " + sb.toString());

        // Reemplazar una parte de la cadena
        sb.replace(0, 4, "Hello");
        System.out.println("Después de replace: " + sb.toString());

        // Eliminar una parte de la cadena
        sb.delete(5, 11);
        System.out.println("Después de delete: " + sb.toString());

        // Invertir la cadena
        sb.reverse();
        System.out.println("Después de reverse: " + sb.toString());
    }
}
