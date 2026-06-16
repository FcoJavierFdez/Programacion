package EXAMENORDINARIA;

public class PuntBateria extends Punt implements Revisable {

    private int bateriaRestant;
    private boolean perRevisar;


    public PuntBateria(String id, String ubicacio, int recarregesMensuals, int bateria, boolean perRevisar){
        super(id,ubicacio,recarregesMensuals);
        setBateriaRestant(bateria);
        this.perRevisar = perRevisar;
    }

    public boolean perRevisar() { return perRevisar; }
    public void setBateriaRestant(int bat) {
        if ( bat < 0 || bat > 100)
            throw new RuntimeException("La bateria va entre 0% y 100%");
        this.bateriaRestant = bat;
        if (this.bateriaRestant < 20)
            this.perRevisar = true;
        else
            this.perRevisar = false;
    }

    public void ferRevisio(String novaUbicacio){
        if (perRevisar) {
            this.bateriaRestant = 100;
            this.setUbicacion(novaUbicacio);
        }
    }

    public double calcularCost(){
        return this.getRecarregues() * 0.35 + 90;
    }
}
