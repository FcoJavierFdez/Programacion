import java.util.Scanner;

public void main(String[] args) {

    /*
     * Declara y llena un vector con N palabras, donde N es un valor
     * ingresado por teclado y las palabras son cadenas tambien
     * introducidas por el teclado
     */

    System.out.println("Introduce el numero de palabras que deseas ingresar:");
    Scanner scanner = new Scanner(System.in);
    int N = scanner.nextInt();
    scanner.nextLine(); // Consumir la nueva linea

    String[] palabras = new String[N];

    for (int i = 0; i < N; i++) {
        System.out.println("Introduce la palabra " + (i + 1) + ":");
        palabras[i] = scanner.nextLine();
    }

    System.out.println("Las palabras introducidas son:");
    for (int i = 0; i < N; i++) {
        System.out.println(palabras[i]);
    }

    scanner.close();

    // Muestra aquellas palabras que empiezan por vocal
    System.out.println("Palabras que empiezan por vocal:");
    for (int i = 0; i < N; i++) {
        char primeraLetra = Character.toLowerCase(palabras[i].charAt(0));
        if (primeraLetra == 'a' || primeraLetra == 'e' || primeraLetra == 'i' || primeraLetra == 'o'
                || primeraLetra == 'u') {
            System.out.println(palabras[i]);
        }
    }

    // Muestra aquellas palabras que comienzan por consonante
    System.out.println("Palabras que empiezan por consonante:");
    for (int i = 0; i < N; i++) {
        char primeraLetra = Character.toLowerCase(palabras[i].charAt(0));
        if (!(primeraLetra == 'a' || primeraLetra == 'e' || primeraLetra == 'i' || primeraLetra == 'o'
                || primeraLetra == 'u')) {
            System.out.println(palabras[i]);
        }
    }

    // Muestra cual es la palabra mas larga y también indica su tamaño.
    String palabraMasLarga = "";
    for( String palabra : palabras ) {
        if( palabra.length() > palabraMasLarga.length() ) {
            palabraMasLarga = palabra;
        }
    }
    System.out.println("La palabra mas larga es: " + palabraMasLarga + " y su tamaño es: " + palabraMasLarga.length());
    System.out.println("Tamaño del vector de palabras: " + palabras.length);

    
}
