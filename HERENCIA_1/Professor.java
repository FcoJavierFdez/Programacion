package HERENCIA_1;

import java.util.ArrayList;

public class Professor extends Persona {

    private int codi;
    private String departament;
    private ArrayList<String> moduls;
    private String horari;

    public Professor(String nom, String dni, String adreca, int telefon,
            int codi, String departament, ArrayList<String> moduls, String horari) {

        super(nom, dni, adreca, telefon);

        this.codi = codi;
        this.departament = departament;
        this.moduls = moduls;
        this.horari = horari;
    }

    public void mostrarPersona() {
        super.mostrarPersona();

        System.out.println("Codi: " + this.codi);
        System.out.println("Departament: " + this.departament);
        System.out.println("Horari: " + this.horari);
        System.out.println("Mòduls:");

        for (String modul : this.moduls) {
            System.out.println("Modul: " + modul);
        }
    }

    public int getCodi() {
        return codi;
    }

    public void setCodi(int codi) {
        this.codi = codi;
    }

    public String getDepartament() {
        return departament;
    }

    public void setDepartament(String departament) {
        this.departament = departament;
    }

    public ArrayList<String> getModuls() {
        return moduls;
    }

    public void setModuls(ArrayList<String> moduls) {
        this.moduls = moduls;
    }

    public String getHorari() {
        return horari;
    }

    public void setHorari(String horari) {
        this.horari = horari;
    }
}