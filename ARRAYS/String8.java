public class String8 {
    public static void main(String[] args) {
        String str = "El rápido zorro marrón salta sobre el perro perezoso";
        String palabraBuscada = "perro";
        int indice = str.indexOf(palabraBuscada);

        if (indice != -1) {
            System.out.println("La palabra '" + palabraBuscada + "' se encuentra en la posición: " + indice);
        } else {
            System.out.println("La palabra '" + palabraBuscada + "' no se encontró en la cadena.");
        }
    }
}
