package VEHICLES;

class Moto extends Vehicle  {
    private final boolean potAutopista;

    public Moto(String matricula, String marca, String model) throws MatriculaInvalidaException {
        super(matricula, marca, model);
        this.potAutopista = Aleatoris.boolea();
    }

    @Override
    public void mostrarInfo() {
        System.out.println(matricula + " - " + marca + " " + model + " (Moto)");
        System.out.println("  Accés a autopista: " + (potAutopista ? "Sí" : "No"));
    }
}