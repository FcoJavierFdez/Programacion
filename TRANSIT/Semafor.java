package TRANSIT;

public class Semafor {

    public enum ColorSemafor {
        VERD, GROC, ROIG
    }

    // Estat Intern
    private ColorSemafor estatActual;

    // Protocol d'Inici: Per defecte en ROIG
    public Semafor() {
        this.estatActual = ColorSemafor.ROIG;
    }

    // Mòdul de Canvi
    public void canviarEstat(ColorSemafor nouColor) {
        this.estatActual = nouColor;
    }

    // Interfície d'Usuari
    public void instruccioConductor() {
        switch (this.estatActual) {
            case VERD:
                System.out.println("Pots passar.");
                break;
            case GROC:
                System.out.println("Atenció! Frena si pots.");
                break;
            case ROIG:
                System.out.println("Parar.");
                break;
            default:
                // Encara que amb Enum és difícil arribar ací, és bona pràctica
                System.out.println("Error de sistema.");
                break;
        }
    }
}
