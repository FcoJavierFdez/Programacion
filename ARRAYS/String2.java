public class String2 {
    
    public static void main(String[] args) {
        String str1 = "Java Programming";
        String str2 = "java programming";

        // Comparar cadenas considerando mayúsculas y minúsculas
        boolean sonIguales = str1.equals(str2);
        System.out.println("¿Son iguales (considerando mayúsculas y minúsculas)? " + sonIguales);

        // Comparar cadenas sin considerar mayúsculas y minúsculas
        boolean sonIgualesIgnoreCase = str1.equalsIgnoreCase(str2);
        System.out.println("¿Son iguales (ignorando mayúsculas y minúsculas)? " + sonIgualesIgnoreCase);

        // Buscar la posición de una subcadena
        int indice = str1.indexOf("Program");
        System.out.println("Índice de 'Program' en str1: " + indice);

        // Reemplazar una subcadena por otra
        String strReemplazada = str1.replace("Java", "Python");
        System.out.println("Cadena después del reemplazo: " + strReemplazada);

        // Dividir una cadena en un array de subcadenas
        String[] palabras = str1.split(" ");
        System.out.println("Palabras en str1:");
        for (String palabra : palabras) {
            System.out.println(palabra);
        }
    }
    
}
