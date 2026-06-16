import java.util.ArrayList;

public class Vehiculo {
  protected String marca;
  protected String modelo;

  public Vehiculo(String marca, String modelo) {
    this.marca = marca;
    this.modelo = modelo;
  }

  public void arrancar() {
    System.out.println("El vehiculo" + marca + modelo + " está arrancando");
  }

}

class Coche extends Vehiculo {

  public Coche(String marca, String modelo) {
    super(marca, modelo);
  }

  public void abrirMaletero() {
    System.out.println("El coche " + marca + " " + modelo + "está abriendo el maletero");
  }

}

class Motocicleta extends Vehículo {
  public Motocicleta(String marca, String modelo) {
    super(marca, modelo);
  }

  public void hacerCaballito() {
    System.out.println("La motocicleta " + marca + " " + modelo + "está haciendo el caballito");
  }
}

public class TestVehiculo {

  public static void main(String[] args){

    ArrayList<Vehiculo> vehiculos = new ArrayList<>();
	vehiculos.add(new Coche("Toyota", "Corolla");
	  vehiculos.add(new Motocicleta("BMW", "R1");
	
	  for (Vehiculo vehiculo : vehiculos){ 
	  vehiculo.arrancar();
	 
	  if(vehiculo instaceof Coche){
	   ((Coche) vehiculo).abrirMaletero();
	  } else if(vehiculo instanceof Motocicleta){
	   ((Motocicleta) vehiculo).hacerCaballito();
	  } 
	
	}
	
  }
}