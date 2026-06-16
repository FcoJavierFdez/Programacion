package REFUGI_ANIMALS;

class Gat extends Animal {
    private boolean problemesConducta;

    public Gat(String nom, int edat, boolean vacunat, boolean problemesConducta) throws AdopcioDenegadaException{
        super(nom, edat, vacunat);
        this.problemesConducta = problemesConducta;
    }

    public boolean teProblemesConducta() {
        return problemesConducta;
    }

    public void canviarConducta() {
        this.problemesConducta = !this.problemesConducta;
        System.out.println("Conducta actualitzada. Problemes: " + this.problemesConducta);
    }

    // Lògica específica: Teràpia
    public void ferTerapia() {
        if (!problemesConducta) {
            System.out.println("• El gat " + nom + " està realitzant una sessió de teràpia amb humans.");
        } else {
            System.out.println("• El gat " + nom + " no pot fer teràpia per problemes de conducta.");
        }
    }

    @Override
    public String informacioDetallada() {
        return informacioBasica() + " | Problemes Conducta: " + (problemesConducta ? "Sí" : "No") + " | Vacunat: "
                + vacunat;
    }
    @Override
    public String getEspecie() {
        return "Gat";
    }
}