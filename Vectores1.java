import java.util.Arrays;

/**
 * a) Declara i inicialitza un vector de 8 enters. Mostra el contingut del
 * vector per pantalla.
 * b) Mostra quants valors són positius i quants negatius.
 * c) Mostra la suma i la mitjana dels valors del vector.
 * d) Indica quin és el valor màxim i en quina posició es troba.
 */

public class Vectores1 {

    public static void main(String[] args) {
        // Declaramos e inicializamos un vector de 8 enteros.
        int[] vector = { 0, 3, -9, 5, 7, -12, 5, 26 };

        // Mostramos el contenido del vector por pantalla
        // System.out.print("El contenido del vector es: ");

        // Recorremos el vector con un bucle
        /*
         * for (int i = 0; i < vector.length; i++) {
         * System.out.print(vector[i] + ", ");}
         */
        // Otra opción sería utilizar el metodo Arrays.toString para que nos devolviese
        // un array.
        System.out.print("\nEl contenido del vector es: " + Arrays.toString(vector));

        // Inicializamos contador de numeros positivos y numeros negativos.
        int positivo = 0;
        int negativo = 0;
        // Recorremos cada elemento del vector desde la posición 0 del vector.
        for (int i = 0; i < vector.length; i++) {
            // Ponemos condicion preguntando si el numero que ocupa cada posición del vector
            // es mayor a 0.
            if (vector[i] > 0)
                // Entonces hacemos que sume en 1 el contador de positivo
                positivo++;
            // Sino cumple la condicion y entonces ese numero que ocupa la posición es menor
            // a 0.
            else if (vector[i] < 0)
                // Entonces hacemosque que aumente en 1 el contador de negativo.
                negativo++;
        }
        // Los mostramos por pantalla.
        System.out.println("\n----------------------------------------------------------------------");
        System.out.println("Los numeros positivos son: " + positivo);
        System.out.println("Y los nuemros negativos son: " + negativo);

        // Muestra la suma de los valores del vector.
        // Inicializamos la variable suma.
        int suma = 0;
        int resta = 0;
        double mediana = (double) suma / vector.length;
        // Recorremos el vector haciendo que suma añada cada vez un numero de cada
        // posición desde la 0 a la posición final.
        for (int i = 0; i < vector.length; i++) {
            suma += vector[i];
            resta -= vector[i];
        }
        // Imprimimos el valor de la suma de los numeros de los vectores
        System.out.println("Suma: " + suma + " | Resta: " + resta + "| Mediana: " + mediana);

        // Es busca el màxim valor del vector i també la posició on està.
        int maximo = vector[0];
        int posicion = 0;
        // Se recorre de nuevo el vector comparando si el valor es mayor al maximo, solo entonces lo mostrará
        // justo con su posición
        for (int i = 0; i < vector.length; i++) {
            if (vector[i] > maximo) {
                maximo = vector[i];
                posicion = i;
            }
        }
        System.out.println("Valor maximo del vector: " + maximo + " | Posición en el vector: " + posicion);
    }
}
