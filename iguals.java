/**
 * Escriu una funció que rebrà dos cadenes i retornarà true o false en funció de
 * si són iguals o no.
 */

public class iguals {

    public static void main(String[] args) {

        System.out.println("");
        System.out.println(igualitos("Hola", "javier")); // Llamada a la funcion igualitos, no son iguales --> false.
        System.out.println(igualitos("hola", "hola")); // Llamamda a la funcion igualitos, son iguales -- > true.
    }

    public static boolean igualitos(String a, String b) {
        // Comparamos el contenido de las cadenas con a.equals(b).
        return a.equals(b);
    }

}
