package Empleados;

public class Empleados {

    protected String nombre;
    protected double salario;

    public Empleados(String nombre) {
        this.nombre = nombre;
        this.salario = 0.0;
    }

    public String getNombre() {
        return nombre;
    }

    public double getSalario() {
        return salario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public double calcularSalario() {
        return salario;
    }
}

class EmpleadoPorHora extends Empleados {

    private int horasTrabajadas;
    private double tarifaPorHora;

    public EmpleadoPorHora(String nombre, int horasTrabajadas, double tarifaPorHora) {
        super(nombre);
        this.horasTrabajadas = horasTrabajadas;
        this.tarifaPorHora = tarifaPorHora;

    }

    @Override
    public double calcularSalario() {
        return horasTrabajadas * tarifaPorHora;
    }

}

class EmpleadoFijo extends Empleados {

    public EmpleadoFijo(String nombre, double salario) {
        super(nombre);

    }

    @Override
    public String toString() {
        return "EmpleadoFijo [" + this.nombre + "tiene un salario de: " + this.salario + "€. ]";
    }
}
