import java.util.Scanner;

public class Strings1 {
    public static void main(String[] args) {
        String str = "Hello, World!";
        System.out.println("Original String: " + str);
        System.out.println("Length of String: " + str.length());
        System.out.println("Character at index 7: " + str.charAt(7));
        System.out.println("Substring from index 7 to 12: " + str.substring(7, 12));
        System.out.println("Uppercase String: " + str.toUpperCase());
        System.out.println("Lowercase String: " + str.toLowerCase());

        /*
         * Una aplicacioón web quiere validar contraseñas segun ciertas
         * normas antes de registrarlas. Un valor numérico indicará la
         * fiabilidad de la contraseña segun los siguientes criterios:
         * -Valor 0 indica contraseña muy segura.
         * -Por cada comprobación que no supere, aumentara en 1 el valor
         */
        /*
         * Pide al usuario una contraseña. Indica si la longitud es superior o inferior
         * a 8 caracteres.
         */
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese una contraseña: ");
        String password = scanner.nextLine();

        if (password.length() < 8) {
            System.out.println("La contraseña es inferior a 8 caracteres.");
        } else {
            System.out.println("La contraseña es superior o igual a 8 caracteres.");
        }
        // Indica si continene al menos una letra mayúscula.
        boolean tieneMayuscula = false;
        for (int i = 0; i < password.length(); i++) {
            if (Character.isUpperCase(password.charAt(i))) {
                tieneMayuscula = true;
                break;
            }
        }
        if (tieneMayuscula) {
            System.out.println("La contraseña contiene al menos una letra mayúscula.");
        } else {
            System.out.println("La contraseña no contiene letras mayúsculas.");
        }
        // Indica al menos si contiene un digito.
        boolean tieneDigito = false;
        for (int i = 0; i < password.length(); i++) {
            if (Character.isDigit(password.charAt(i))) {
                tieneDigito = true;
                break;
            }
        }
        if (tieneDigito) {
            System.out.println("La contraseña contiene al menos un dígito.");
        } else {
            System.out.println("La contraseña no contiene dígitos.");
        }
        /*
         * Indica el nivel de seguridad, mostrando el mensaje "Muy segura!",
         * "Segura!" o "Muy insegura!. Cambialá" segun las comprobaciones superadas.
         */
        int nivelSeguridad = 0;

        if (password.length() >= 8) {
            nivelSeguridad++;
        }
        if (tieneMayuscula) {
            nivelSeguridad++;
        }
        if (tieneDigito) {
            nivelSeguridad++;
        }
        if (nivelSeguridad == 3) {
            System.out.println("Nivel de seguridad: Muy segura!");
        } else if (nivelSeguridad == 2) {
            System.out.println("Nivel de seguridad: Segura!");
        } else {
            System.out.println("Nivel de seguridad: Muy insegura!. Cambialá");
        }
        scanner.close();
    }
}
