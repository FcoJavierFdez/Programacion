package UF8;

public class Canco {
    private String titol;
    private String artista;
    private int duracio; // en segons

    public Canco(String artista) {
        this.artista = artista;
        this.titol = "";
        this.duracio = 0;
    }

    public boolean setDuracio(int duracio) {
        if (duracio >= 10 && duracio <= 600) {
            this.duracio = duracio;
            return true;
        } else {
            System.out.println("Error: La durada ha de ser entre 10 i 600.");
            return false;
        }
    }

    public int getDuracio() {
        return duracio;
    }

    public void setTitol(String titol) {
        if (titol.length() > 50) {
            this.titol = titol.substring(0, 50);
            System.out.println("S'ha reduït el titol.");
        } else {
            this.titol = titol;
        }
    }

    // Getters
    public String getTitol() {
        return titol;
    }

    public void mostrarInfo() {
        int minuts = duracio / 60;
        int segons = duracio % 60;
        System.out.println("- " + titol + " (" + artista + ") [" + minuts + ":" + segons + "]");
    }

    // Fase 4: Comparació
    public static Canco quinaEsMesLlarga(Canco c1, Canco c2) {
        return (c1.getDuracio() >= c2.getDuracio()) ? c1 : c2;
    }

}