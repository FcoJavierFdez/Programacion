package VEHICLES;

/* ===== CLASSE ABSTRACTA BASE ===== */
abstract class Vehicle {
    protected final String matricula;
    protected final String marca;
    protected final String model;

    protected Vehicle(String matricula, String marca, String model) throws MatriculaInvalidaException {

        if (!validarMatricula(matricula)) {
            throw new MatriculaInvalidaException("Matrícula invàlida: " + matricula);
        }

        this.matricula = matricula;
        this.marca = marca;
        this.model = model;
    }

    // Cada subclasse ha de definir com es mostra
    public abstract void mostrarInfo();

    private boolean validarMatricula(String matricula) {
        return matricula != null && (matricula.length() == 7 || matricula.length() == 8);
    }
}