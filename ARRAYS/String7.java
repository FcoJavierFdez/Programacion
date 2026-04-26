public class String7 {
    public static void main(String[] args) {
        String frase = "La vida es bella";
        String palabraBuscada = "es";
        String nuevaPalabra = "fascinante";

        System.out.println("Frase original: " + frase);

        // Reemplazar la palabra buscada por la nueva palabra
        String fraseModificada = frase.replace(palabraBuscada, nuevaPalabra);

        System.out.println("Frase modificada: " + fraseModificada);
    }
}
