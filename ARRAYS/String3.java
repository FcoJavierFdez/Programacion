public class String3 {
    public static void main(String[] args) {
        String frase = "  Aprendiendo Java es divertido y emocionante  ";
        System.out.println("Frase original: '" + frase + "'");

        // Eliminar espacios en blanco al inicio y al final
        String fraseTrimmed = frase.trim();
        System.out.println("Frase sin espacios al inicio y al final: '" + fraseTrimmed + "'");

        // Verificar si la frase comienza con "Aprendiendo"
        boolean empiezaCon = fraseTrimmed.startsWith("Aprendiendo");
        System.out.println("¿La frase comienza con 'Aprendiendo'? " + empiezaCon);

        // Verificar si la frase termina con "emocionante"
        boolean terminaCon = fraseTrimmed.endsWith("emocionante");
        System.out.println("¿La frase termina con 'emocionante'? " + terminaCon);
    }
}
