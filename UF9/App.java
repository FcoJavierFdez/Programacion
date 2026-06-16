package UF9;
import java.util.ArrayList;
import java.util.HashMap;

public class App {

    // l, m) Constants de restricció
    private static final int MAX_RESERVES_PER_HORA = 5;
    private static final int MAX_RESERVES_PER_ALUMNE = 3;

    // h) Estructures de dades
    private static ArrayList<Ordinador> inventari = new ArrayList<>();
    private static HashMap<String, ArrayList<Reserva>> mapaReserves = new HashMap<>();

    public static void main(String[] args) {

        Ordinador ordinador1 = new Ordinador(111, "model a", 4);
        Ordinador ordinador2 = new Ordinador(222, "model b", 8);
        Ordinador ordinador3 = new Ordinador(333, "model c", 16);

        inventari.add(ordinador1);
        inventari.add(ordinador2);
        inventari.add(ordinador3);

        // Proves de reserva
        realitzarReserva("Blanca", 111, 10); // Correcta
        realitzarReserva("Blanca", 222, 11); // Correcta
        realitzarReserva("Marcos", 111, 10); // Error: Ordinador ja reservat
        realitzarReserva("Blanca", 333, 12); // Correcta
        realitzarReserva("Blanca", 111, 14); // Error: Límit alumne superat (3)

        // j) Consultar reserves per hora
        consultarReservesPerHora("10:00-11:00");

        // k) Consultar reserves d'un alumne
        System.out.println("Reserves de Blanca: " + comptarReservesAlumne("Blanca"));

    }

    // i) Lògica de Reserves
    public static void realitzarReserva(String alumne, int idPc, int horaInici) {
        String franja = horaInici + ":00-" + (horaInici + 1) + ":00";
        Ordinador pc = buscarOrdinador(idPc);
        boolean errorTrobat = false;

        // 1. Validació: Existeix l'ordinador?
        if (pc == null) {
            System.out.println("Error: L'ID " + idPc + " no existeix.");
            errorTrobat = true;
        }
        // 2. Validació: Límit per alumne
        else if (comptarReservesAlumne(alumne) >= MAX_RESERVES_PER_ALUMNE) {
            System.out
                    .println("Error: " + alumne + " ha arribat al límit de " + MAX_RESERVES_PER_ALUMNE + " reserves.");
            errorTrobat = true;
        }

        if (!errorTrobat) {
            // Inicialització de la franja
            ArrayList<Reserva> llistaHora = mapaReserves.get(franja);
            if (llistaHora == null) {
                // Si no existeix, la creem i la posem al mapa
                llistaHora = new ArrayList<>();
                mapaReserves.put(franja, llistaHora);
            }

            // 3. Validació: Límit de reserves per hora
            if (llistaHora.size() >= MAX_RESERVES_PER_HORA) {
                System.out.println("Error: La franja " + franja + " està plena.");
            } else {
                // 4. Validació: Ordinador ja reservat?
                boolean ocupat = false;
                for (Reserva r : llistaHora) {
                    if (r.getOrdinador().getId() == idPc) {
                        ocupat = true;
                    }
                }

                if (ocupat) {
                    System.out.println("Error: L'ordinador " + idPc + " ja està ocupat a les " + franja);
                } else {
                    // Si tot és correcte, crear i afegir
                    Reserva nova = new Reserva(alumne, franja, pc);
                    llistaHora.add(nova);
                    System.out.println("Reserva confirmada: " + alumne + " a les " + franja);
                }
            }
        }
    }

    // Mètodes auxiliars de cerca
    private static Ordinador buscarOrdinador(int id) {
        for (Ordinador o : inventari) {
            if (o.getId() == id)
                return o;
        }
        return null;
    }

    public static int comptarReservesAlumne(String alumne) {
        int total = 0;
        for (ArrayList<Reserva> llista : mapaReserves.values()) {
            for (Reserva r : llista) {
                if (r.getNomAlumne().equalsIgnoreCase(alumne))
                    total++;
            }
        }
        return total;
    }

    public static void consultarReservesPerHora(String franja) {
        System.out.println("\n--- Reserves per a " + franja + " ---");
        if (mapaReserves.containsKey(franja)) {
            for (Reserva r : mapaReserves.get(franja)) {
                r.mostrarReserva();
            }
        } else {
            System.out.println("No hi ha reserves.");
        }
    }
}