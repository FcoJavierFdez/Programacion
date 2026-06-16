package UF9;

public class Reserva {
    private String nomAlumne;
    private String franja;
    private Ordinador ordinador;

    public Reserva(String nomAlumne, String franja, Ordinador ordinador) {
        this.nomAlumne = nomAlumne;
        this.franja = franja;
        this.ordinador = ordinador;
    }

    public String getNomAlumne() {
        return nomAlumne;
    }

    public String getFranja() {
        return franja;
    }

    public Ordinador getOrdinador() {
        return ordinador;
    }

    public void mostrarReserva() {
        System.out.println("Alumne: " + nomAlumne + " | Franja: " + franja + " | Ordinador: " + ordinador);
    }

}
