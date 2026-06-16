package REFUGI_ANIMALS;

class Gos extends Animal {
    private String raca;

    public Gos(String nom, int edat, boolean vacunat, String raca) throws AdopcioDenegadaException {
        super(nom, edat, vacunat);
        this.raca = raca;
    }

    @Override
    public String informacioDetallada() {
        return informacioBasica() + " | Raça: " + raca + " | Vacunat: " + vacunat;
    }
    @Override
    public String getEspecie() {
        return "Gos";
    }
}