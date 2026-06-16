
/**
 *  Enunciado: Crea un programa que invierta el orden de una cadena de texto sin usar funciones 
 * propias del lenguaje que lo hagan de forma automática.
 * - Si le pasamos "Hola mundo" nos retornaría "odnum aloH"
 */
import java.util.Scanner;

class InvierteOrden {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Escribe una frase corta que quieras invertir su orden: ");

        String frase = scanner.nextLine();
        String resultado = invertir(frase);

        System.out.println("La frase invertida es: -->' " + resultado + " '<-- ");
        scanner.close();
    }

    public static String invertir(String texto) {
        // Inicializamos la cadena invertida como una cadena vacía. Aquí se acumulará
        // caracter por
        // caracter la cadena invertida.
        String cadenaInvertida = "";
        /**
         * Iniciamos un bucle for que recorre el texto desde el ultimo caracter hasta el
         * primero.
         * Estableciendo i en el indice del ultimo caracter ya que los indices van de 0
         * a length()-1.
         * i<=0, condicion que mantiene el bucle mientres queden caracteres por
         * procesar.
         * i-- decrementa i en 1 en cada iteración(vamos hacia detras).
         */
        for (int i = texto.length() - 1; i >= 0; i--) {
            /**
             * ¿Qué significa texto.charAt(i)?
             * texto es la cadena original que el usuario escribió.
             * .charAt(i) es un método de String que devuelve el carácter que está en la
             * posición i.
             * Por ejemplo, si texto = "Hola":
             * texto.charAt(0) → 'H'
             * texto.charAt(1) → 'o'
             * texto.charAt(2) → 'l'
             * texto.charAt(3) → 'a'
             * En nuestro caso, como vamos de atrás hacia adelante:
             * Cuando i = texto.length() - 1 → último carácter
             * Luego el penúltimo, etc. y los va concatenando al string llamado
             * cadenaInvertida.
             */
            cadenaInvertida += texto.charAt(i);
        }
        return cadenaInvertida;
    }
}