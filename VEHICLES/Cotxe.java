package VEHICLES;

class Cotxe extends Vehicle {
    private final int places;

    public Cotxe(String matricula, String marca, String model)throws MatriculaInvalidaException {
        super(matricula, marca, model);
        this.places = Aleatoris.places();
    }

    @Override
    public void mostrarInfo() {
        System.out.println(matricula + " - " + marca + " " + model + " (Cotxe)");
        System.out.println("  Places: " + places);
    }
}