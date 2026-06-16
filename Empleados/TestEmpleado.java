package Empleados;

import java.util.ArrayList;
import java.util.Iterator;

public class TestEmpleado {
  public static void main(String[] args) {
    EmpleadoFijo empleado1 = new EmpleadoFijo("Laura Martinez", 2145);
    empleado1.salario = 2145;
    System.out.println("El salario de la empleada fija es: " + empleado1.salario + " euros.");

    EmpleadoPorHora empleado2 = new EmpleadoPorHora("David Ruiz", 14, 25.63);
    System.out.println("El salaraio de: " + empleado2.nombre + " durante este mes, ha sido de: "
        + empleado2.calcularSalario() + " euros.");

    ArrayList<String> cars = new ArrayList<String>();
    cars.add("Volvo");
    cars.add("BMW");
    cars.add("Ford");
    cars.add("Mazda");

    // Get the iterator
    Iterator<String> it = cars.iterator();

    // To loop through a collection, use the hasNext() and next() methods of the
    // Iterator:
    while (it.hasNext()) {
      System.out.println(it.next());
    }

  }
}
