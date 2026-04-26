package VEHICLES;

/* ===== SUBCLASSES ===== */
class Camio extends Vehicle {
    protected final int pesMaxKg;

    public Camio(String matricula, String marca, String model) throws MatriculaInvalidaException {
        super(matricula, marca, model);
        this.pesMaxKg = Aleatoris.pesMaxKg();
    }

    @Override
    public void mostrarInfo() {
        System.out.println(matricula + " - " + marca + " " + model + " (Camió)");
        System.out.println("  Pes màxim: " + pesMaxKg + " kg");
    }
}