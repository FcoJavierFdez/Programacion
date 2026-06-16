import java.util.ArrayList;

// 1. La clase madre debe ser abstract porque calcularSalario() no tiene código aquí
abstract class Empleado {
    protected String nombre;

    public Empleado(String nombre) {
        this.nombre = nombre;
    }

    // Al ser abstracto, obligamos a los hijos a implementar su propia versión
    public abstract double calcularSalario();
}

// 2. Clase para empleados con sueldo fijo
class EmpleadoFijo extends Empleado {
    private double salarioFijo;

    // CONSTRUCTOR CORREGIDO: quitamos la palabra 'class'
    public EmpleadoFijo(String nombre, double salarioFijo) {
        super(nombre);
        this.salarioFijo = salarioFijo;
    }

    @Override
    public double calcularSalario() {
        return salarioFijo;
    }
}

// 3. Clase para empleados que cobran por horas
class EmpleadoPorHoras extends Empleado {
    private int horasTrabajadas;
    private double tarifaPorHoras;

    // CONSTRUCTOR CORREGIDO: quitamos la palabra 'class'
    public EmpleadoPorHoras(String nombre, int horasTrabajadas, double tarifaPorHoras) {
        super(nombre);
        this.horasTrabajadas = horasTrabajadas;
        this.tarifaPorHoras = tarifaPorHoras;
    }

    @Override
    public double calcularSalario() {
        return horasTrabajadas * tarifaPorHoras;
    }
}

// 4. CLASE PRINCIPAL: Debe ser la única 'public' y coincidir con el nombre del archivo
public class TestEmpleado {
    public static void main(String[] args) {
        ArrayList<Empleado> empleados = new ArrayList<>();
        
        // Polimorfismo: tratamos a todos como "Empleado" aunque sean de tipos distintos
        empleados.add(new EmpleadoFijo("Carlos", 3000));
        empleados.add(new EmpleadoPorHoras("Laura", 160, 15.5));

        double salarioTotal = 0;

        for (Empleado emp : empleados) {
            // Java decide en tiempo de ejecución qué método usar (Fijo o Horas)
            salarioTotal += emp.calcularSalario();
        }

        System.out.println("El salario total de todos los empleados es: " + salarioTotal);
    }
}