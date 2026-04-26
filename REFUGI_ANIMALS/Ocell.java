package REFUGI_ANIMALS;

class Ocell extends Animal {
    private String raca;
    private boolean esSalvatge;
    private boolean potVolar;

    public Ocell(String nom, int edat, boolean vacunat, String raca, boolean esSalvatge, boolean potVolar)
            throws AdopcioDenegadaException {
        super(nom, edat, vacunat);
        this.raca = raca;
        this.esSalvatge = esSalvatge;
        this.potVolar = potVolar;
    }

    public boolean isEsSalvatge() {
        return esSalvatge;
    }

    public boolean isPotVolar() {
        return potVolar;
    }

    public void canviarCapacitatVol() {
        this.potVolar = !this.potVolar;
        System.out.println("Capacitat de vol actualitzada a: " + (potVolar ? "Sí" : "No"));
    }

    public void ferExhibicio() {
        if (potVolar && !esSalvatge) {
            System.out.println("• L'ocell " + nom + " està volant en l'exhibició!");
        } else {
            System.out.println("• L'ocell " + nom + " no pot participar (o no vola o és salvatge).");
        }
    }

    // SOBRESCRIVIM la regla d'adopció: s'afegeix la restricció de "no salvatge"
    @Override
    public boolean esAptaPerAdopcio() {
        return super.esAptaPerAdopcio() && !this.esSalvatge;
    }

    @Override
    public String informacioDetallada() {
        return informacioBasica() + " | Raça: " + raca + " | Salvatge: " + esSalvatge + " | Vola: " + potVolar;
    }

    @Override
    public String getEspecie() {
        return "Ocell";
    }

    @Override
    public void adoptar() throws AdopcioDenegadaException {
        if (this.isEsSalvatge()) {
            throw new AdopcioDenegadaException("No es poden adoptar ocells salvatges.");
        }

        super.adoptar(); // aplica la resta de normes generals
    }
}