import java.util.Scanner;

public class Vector5 {

    public static void main(String[] args) {
        /*
         * Un sistema ha de controlar un conjunto de usuarios que se
         * autentican con nombre y contraseña. Solo hace falta almacenar
         * y comprobar estos datos sin cifrar
         */

        /*
         * Declara e inicializa dos vectores paralelos con 5 nombres
         * de usuario y sus contraseñas. Genera las contraseñas como un
         * conjunto aleatorio de enteros.
         */

        String[] usuarios = { "Pepe", "José", "", "Juan", "Elisabet" };
        int[] contrasenyas = new int[5];

        // Genera las contraseñas aleatorias
        for (int i = 0; i < contrasenyas.length; i++) {
            contrasenyas[i] = (int) (Math.random() * 9000) + 1000; // Contraseñas de 4 dígitos
        }

        /*
         * Permite iniciar sesion: pide nombre y contraseña, y
         * valida si coinciden. Muestra un mensaje informando del
         * resultado.
         */

        Scanner scanner = new Scanner(System.in);
        boolean autenticado = false;

        System.out.print("Introduce el nombre de usuario: ");
        String nombreUsuario = scanner.nextLine();
        System.out.print("Introduce la contraseña: ");
        int contrasenyaIntroducida = scanner.nextInt();

        for (int i = 0; i < usuarios.length; i++) {
            if (usuarios[i].equals(nombreUsuario) && contrasenyas[i] == contrasenyaIntroducida) {
                autenticado = true;
                break;
            }
        }
        if (autenticado) {
            System.out.println("Autenticación exitosa. Bienvenido " + nombreUsuario + "!");
        } else {
            System.out.println("Error de autenticación. Nombre de usuario o contraseña incorrectos.");
        }

        /*
         * Mientras la sesión no se inicie correctamente, el programa
         * solicitará usuario y contraseña constantemente, hasta que se
         * introduzca "fin" como nombre de usuario.
         */

        while (!autenticado) {

            System.out.print("Introduce el nombre de usuario (o 'fin' para salir): ");
            String nombreUsuario1 = scanner.nextLine();

            if (nombreUsuario1.equals("fin")) {
                System.out.println("Saliendo del sistema.");
                break;
            }
            scanner.close();

            /*
             * Si has iniciado correctamente la sesión, muestra un
             * menú de 2 opciones: una opción para cerrar la sesión y
             * otra para que permita cambiar la contraseña actual.
             */

            System.out.print("Introduce la contraseña: ");
            int contrasenyaIntroducida1 = scanner.nextInt();

            int indiceUsuario = -1;
            boolean autenticado1 = false;
            for (int i = 0; i < usuarios.length; i++) {
                if (usuarios[i].equals(nombreUsuario1) && contrasenyas[i] == contrasenyaIntroducida1) {
                    autenticado1 = true;
                    indiceUsuario = i;
                    break;
                }
            }
            if (autenticado1) {

                System.out.println("Autenticación exitosa. Bienvenido " + nombreUsuario1 + "!");
                boolean sesionActiva = true;
                while (sesionActiva) {
                    System.out.println("Menú:");
                    System.out.println("1. Cerrar sesión");
                    System.out.println("2. Cambiar contraseña");
                    System.out.print("Selecciona una opción (1 o 2): ");
                    int opcion = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea

                    switch (opcion) {
                        case 1:
                            System.out.print("Introduce la nueva contraseña (4 dígitos): ");
                            contrasenyas[indiceUsuario] = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println("Contraseña actualizada correctamente.");
                            break;
                        case 2:
                            System.out.println("Sesión cerrada.");
                            sesionActiva = false;
                            break;
                        default:
                            System.out.println("Opción no válida.");
                    }
                }
            } else {
                System.out.println("Error de autenticación. Nombre de usuario o contraseña incorrectos.");
            }
        }
        scanner.close();
    }
}
