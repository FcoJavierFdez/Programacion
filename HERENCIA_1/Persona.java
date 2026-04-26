package HERENCIA_1;

public class Persona {

    private String nom;
    private String dni;
    private String adreca;
    private int telefon;

    public Persona(String nom, String dni, String adreca, int telefon) {
        this.nom = nom;
        this.dni = dni;
        this.adreca = adreca;
        this.telefon = telefon;
    }

    public void mostrarPersona() {
        System.out.println("Nom: " + this.nom);
        System.out.println("DNI: " + this.dni);
        System.out.println("Adreça: " + this.adreca);
        System.out.println("Telèfon: " + this.telefon);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getAdreca() {
        return adreca;
    }

    public void setAdreca(String adreca) {
        this.adreca = adreca;
    }

    public int getTelefon() {
        return telefon;
    }

    public void setTelefon(int telefon) {
        this.telefon = telefon;
    }
}