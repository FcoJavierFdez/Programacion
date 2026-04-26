package ABSTRACT;

public class Main {
    public static void main(String[] args) {

        Treballador t1 = new TreballadorFix("Anna", 1800);
        Treballador t2 = new TreballadorPerHores("Marc", 120, 12.5);

        t1.mostrarNom();
        System.out.println("Sou: " + t1.calcularSou());

        t2.mostrarNom();
        System.out.println("Sou: " + t2.calcularSou());
    }
}