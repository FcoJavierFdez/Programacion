public class Strings4 {
    public static void main(String[] args) {

        // Guarda 5 correos en un vector de String.

        String correus[];
        correus = new String[5];

        correus[0] = "javier@gmail.com";
        correus[1] = "ana@gmail.com";
        correus[2] = "pepito@gmx.com";
        correus[3] = "torito@europcar.com";
        correus[4] = "periquito@yahoo.es";

        // Muestralos
        for (int i = 0; i < correus.length; i++) {
            System.out.println("Correo en la posicion " + i + " : " + correus[i]);
        }

        // Muestra que correos contienen @ y acaban en .com o .es

        for (int i = 0; i < correus.length; i++) {
            if (correus[i].contains("@") && (correus[i].endsWith(".com") || correus[i].endsWith(".es"))) {
                System.out.println("El correo " + correus[i] + " es valido.");
            } else {
                System.out.println("El correo " + correus[i] + " no es valido.");
            }
        }

        // Muestra el dominio de cada correo.

        for (int i = 0; i < correus.length; i++) {
            int posArroba = correus[i].indexOf("@");
            int posPunto = correus[i].lastIndexOf(".");
            if (posArroba != -1 && posPunto != -1 && posPunto > posArroba) {
                String dominio = correus[i].substring(posArroba + 1, posPunto);
                System.out.println("El dominio del correo " + correus[i] + " es: " + dominio);
            } else {
                System.out.println("No se pudo extraer el dominio del correo " + correus[i]);
            }
        }
    }
}
