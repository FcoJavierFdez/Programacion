package HERENCIA_1;

import java.util.ArrayList;

public class Alumne extends Persona {

    private int expedient;
    private String cicle;
    private int curs;
    private ArrayList<Integer> notes;

    public Alumne(String nom, String dni, String adreca, int telefon,
            int expedient, String cicle, int curs, ArrayList<Integer> notes) {

        super(nom, dni, adreca, telefon);

        this.expedient = expedient;
        this.cicle = cicle;
        this.curs = curs;
        this.notes = notes;
    }

    public void mostrarPersona() {
        super.mostrarPersona();

        System.out.println("Núm. expedient: " + this.expedient);
        System.out.println("Cicle: " + this.cicle);
        System.out.println("Curs: " + this.curs);
        System.out.println("Notes:");

        for (int nota : this.notes) {
            System.out.println("Nota: " + nota);
        }
    }

    public int getExpedient() {
        return expedient;
    }

    public void setExpedient(int expedient) {
        this.expedient = expedient;
    }

    public String getCicle() {
        return cicle;
    }

    public void setCicle(String cicle) {
        this.cicle = cicle;
    }

    public int getCurs() {
        return curs;
    }

    public void setCurs(int curs) {
        this.curs = curs;
    }

    public ArrayList<Integer> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<Integer> notes) {
        this.notes = notes;
    }
}
