package EXAMENORDINARIA;

public class PuntSolar extends Punt {

    private int bicicletesCarregant;

    public PuntSolar(String id, String ubicacio, int recarregues, int carregant){
        super(id,ubicacio,recarregues);
        setCarregant(carregant);
    }

    public int getBicicletesCarregant() { return bicicletesCarregant; }
    public void setCarregant(int bicicletesCarregant){ 
        if (bicicletesCarregant < 0 || bicicletesCarregant > 5)
            throw new RuntimeException("Nombre erroni de bicicletes");
        
        this.bicicletesCarregant = bicicletesCarregant;
    }

    public double calcularCost(){
        return this.getRecarregues() * 0.35;
    }
}
