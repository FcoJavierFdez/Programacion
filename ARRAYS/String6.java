public class String6 {
    public static void main(String[] args) {
        String str = "El rápido zorro marrón salta sobre el perro perezoso";
        String vocales = "aeiouáéíóúAEIOUÁÉÍÓÚ";
        StringBuilder strSinVocales = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (vocales.indexOf(c) == -1) {
                strSinVocales.append(c);
            }
        }

        System.out.println("Cadena original: " + str);
        System.out.println("Cadena sin vocales: " + strSinVocales.toString());
    }

    
}
