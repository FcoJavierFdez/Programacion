package EXAMENORDINARIA;

public abstract class Punt {
    private String id;
    private String ubicacio;
    private int recarregesMensuals;

    public Punt(String id, String ub, int rec) {
        if (id.length() == 5 && id.startsWith("PC"))
            this.id = id;
        else
            this.id = "PC000";
        this.ubicacio = ub;

        this.recarregesMensuals = rec;
    }

    public int getRecarregues() {
        return this.recarregesMensuals;
    }

    public String getId() {
        return this.id;
    }

    public String getUbicacio() {
        return this.ubicacio;
    }

    public void setUbicacion(String novaUbicacio) {
        this.ubicacio = novaUbicacio;
    }

    public abstract double calcularCost();
}
