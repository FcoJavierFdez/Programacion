package HOSPITAL;

/*Gestor Hospital */
import java.util.Scanner;

public class App {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Demo scenario
        Hospital h = new Hospital("Hospital Sant Joan", "València", 5);

        Unitat uci = new Unitat("UCI");
        Unitat pediatria = new Unitat("Pediatria");
        Unitat traumatologia = new Unitat("Traumatologia");

        h.afegirUnitat(uci);
        h.afegirUnitat(pediatria);
        h.afegirUnitat(traumatologia);

        Pacient p1 = new Pacient("Anna");
        p1.setDiesIngres(10);
        h.ingressar(p1, "UCI");

        Pacient p2 = new Pacient("Joan");
        p2.setDiesIngres(5);
        h.ingressar(p2, "Pediatria");

        Pacient p3 = new Pacient("María");
        p3.setDiesIngres(45); // ingrés llarg
        h.ingressar(p3, "Traumatologia");

        Pacient p4 = new Pacient("Pere");
        p4.setDiesIngres(60); // ingrés llarg
        h.ingressar(p4, "Traumatologia");

        Pacient p5 = new Pacient("Laia");
        p5.setDiesIngres(2);
        h.ingressar(p5, "Pediatria");

        // Intentem ingressar quan no hi ha llits
        Pacient p6 = new Pacient("Oriol");
        p6.setDiesIngres(3);
        boolean ingressat = h.ingressar(p6, "UCI");
        if (!ingressat)
            System.out.println("No s'ha pogut ingressar " + p6.getNom() + " per falta de llits.");

        // Transferència
        h.transferir(p1.getCodi(), "UCI", "Traumatologia");

        // Alta
        h.donarAlta(p2.getCodi());

        // Consultes i informes
        System.out.println("\n-- Informació pacient (p3) --");
        h.mostrarInfoPacient(p3.getCodi());

        System.out.println("\n-- Estat unitat Traumatologia --");
        h.mostrarEstatUnitat("Traumatologia");

        System.out.println("\n-- Pacients amb ingressos llargs (>30 dies) --");
        h.listarIngressosLlargs();

        System.out.println("\n-- Comparació entre p3 i p4 --");
        h.compararPacients(p3.getCodi(), p4.getCodi());

        System.out.println("\n-- Extrems a Traumatologia --");
        h.mostrarExtremsUnitat("Traumatologia");

        System.out.println("\n-- Pacient aleatori a Traumatologia --");
        Pacient alea = h.pacientAleatoriUnitat("Traumatologia");
        if (alea != null)
            System.out.println("Pacient seleccionat: " + alea.getNom());

        System.out.println("\nTotal pacients a l'hospital: " + h.totalPacientsHospital());

        // Nota: per a interacció real, utilitzar sc i un bucle amb menús.

    }
}