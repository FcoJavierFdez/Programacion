public class String12 {
    public static void main(String[] args) {

        // Declaracion e inicializacion del array de cadenas
        String cadenas[];
        cadenas = new String[5];

        cadenas[0] = "Hola";
        cadenas[1] = "Mundo";
        cadenas[2] = "Java";
        cadenas[3] = "Programacion";
        cadenas[4] = "Arrays";

        // Muestra la longitud del array
        System.out.println("La longitud del array es: " + cadenas.length);

        // Muestra el contenido del array
        for (int i = 0; i < cadenas.length; i++) {
            System.out.println("Elemento en la posicion " + i + ": " + cadenas[i]);
        }

        // Muestra la cadena mas larga y su posicion
        String cadenaMasLarga = cadenas[0];
        int posicionCadenaMasLarga = 0;
        for (int i = 1; i < cadenas.length; i++) {
            if (cadenas[i].length() > cadenaMasLarga.length()) {
                cadenaMasLarga = cadenas[i];
                posicionCadenaMasLarga = i;
            }
        }
        System.out.println("La cadena mas larga es: \"" + cadenaMasLarga + "\" y se encuentra en la posicion: " + posicionCadenaMasLarga);
    }
    
}
