public static void main(String[] args) {

    // Declaracion e inicializacion del vector
    int vectores[];
    vectores = new int[8];

    vectores[0] = 1;
    vectores[1] = 2;
    vectores[2] = 3;
    vectores[3] = 4;
    vectores[4] = 5;
    vectores[5] = 6;
    vectores[6] = 7;
    vectores[7] = 8;

    // Muestra la longitud del vector
    System.out.println("La longitud del vector es: " + vectores.length);

    // Muestra el contenido del vector
    for (int i = 0; i < vectores.length; i++) {
        System.out.println("Elemento en la posicion " + i + ": " + vectores[i]);
    }

    // Muestra cuantos valores son positivos y cuantos negativos
    int positivos = 0;
    int negativos = 0;
    for (int i = 0; i < vectores.length; i++) {
        if (vectores[i] >= 0) {
            positivos++;
        } else {
            negativos++;
        }
    }
    System.out.println("Cantidad de valores positivos: " + positivos);
    System.out.println("Cantidad de valores negativos: " + negativos);

    // Muestra la suma y la mediana de los valores del vector
    int suma = 0;
    for (int i = 0; i < vectores.length; i++) {
        suma += vectores[i];
    }
    double mediana = (double) suma / vectores.length;
    System.out.println("La suma de los valores del vector es: " + suma);
    System.out.println("La mediana de los valores del vector es: " + mediana);

    // Indica cual es el valor maximo y en que posicion se encuentra
    int maximo = vectores[0];
    int posicionMaximo = 0;
    for (int i = 1; i < vectores.length; i++) {
        if (vectores[i] > maximo) {
            maximo = vectores[i];
            posicionMaximo = i;
        }
    }
    System.out.println("El valor maximo es: " + maximo + " y se encuentra en la posicion: " + posicionMaximo);

}