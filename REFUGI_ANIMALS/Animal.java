package REFUGI_ANIMALS;

// Classe base
abstract class Animal implements Adoptable {
    private static int contadorId = 1; // Per a generar ID automàtic

    protected int id;
    protected String nom;
    protected int edat;
    protected boolean vacunat;

    public Animal(String nom, int edat, boolean vacunat) {
        this.id = contadorId++;
        this.nom = nom;
        this.edat = edat;
        this.vacunat = vacunat;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getEdat() {
        return edat;
    }

    public void envellir() {
        this.edat++;
        System.out.println("L'animal " + nom + " ara té " + edat + " anys.");
    }

    // Lògica comuna d'adopció (regla general)
    @Override
    public boolean esAptaPerAdopcio() {
        return this.edat < 10 && this.vacunat;
    }

    @Override
    public void adoptar() throws AdopcioDenegadaException {

        if (this.edat >= 10) {
            throw new AdopcioDenegadaException("L'animal és massa major per a ser adoptat.");
        }

        if (!this.vacunat) {
            throw new AdopcioDenegadaException("L'animal no està vacunat.");
        }

        System.out.println("--------------------------------------------------");
        System.out.println("ADÉU! L'animal " + nom + " ha sigut adoptat! Feliç vida nova!");
        System.out.println("--------------------------------------------------");
    }

    // Mètode per mostrar informació bàsica (Llistats generals)
    public String informacioBasica() {
        return "ID: " + id + " | Nom: " + nom + " | Edat: " + edat + " | Espècie: " + getEspecie();
    }

    // Mètode abstracte per a informació detallada (cada fill l'implementa)
    public abstract String informacioDetallada();

    // Métode abstracte per a qu cda fill diga el que es
    public abstract String getEspecie();
}