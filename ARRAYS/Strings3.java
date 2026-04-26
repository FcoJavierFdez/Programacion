public class Strings3 {
    public static void main(String[] args) {
        /*
         * Se quieren hacer una serie de comprobaciones
         * en una frase. Realizalos y muestra el resultado.
         */

        // Introduce una frase y guardala en una variable cadena.

        String frase = "El rápido zorro marrón salta sobre el perro perezoso";
        System.out.println("Frase: " + frase);

        // Indica si comienza por mayuscula y termina por punto.
        // Si este no es el caso, cambia la cadena.

        boolean comienzaMayuscula = Character.isUpperCase(frase.charAt(0));
        boolean terminaPunto = frase.endsWith(".");
        System.out.println("Comienza por mayúscula: " + comienzaMayuscula);
        System.out.println("Termina por punto: " + terminaPunto);
        if (!comienzaMayuscula) {
            frase = Character.toUpperCase(frase.charAt(0)) + frase.substring(1);
        }

        if (!terminaPunto) {
            frase = frase + ".";
        }
        System.out.println("Frase corregida: " + frase);

        // Muestra la longitud de la frase y cuantas palabras contiene.
        int longitud = frase.length();
        String[] palabras = frase.trim().split("\\s+");
        int numeroPalabras = palabras.length;
        System.out.println("Longitud de la frase: " + longitud);
        System.out.println("Número de palabras: " + numeroPalabras);

        // Reemplaza todas las apariciones de la palabra "zorro" por "cabrito". Y muestra el resultado.
        String fraseModificada = frase.replace("zorro", "cabrito");
        System.out.println("Frase modificada: " + fraseModificada); 

        // Muestra la frase final.
        System.out.println("Frase final: " + fraseModificada);
        
    }
}
