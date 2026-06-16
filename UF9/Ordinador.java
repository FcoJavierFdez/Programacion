package UF9;

public class Ordinador {
    private int id;
    private String model;
    private int ram;

    public Ordinador(int id, String model, int ram) {
        this.id = id;
        this.model = model;
        this.ram = ram;
    }

    public int getId() {
        return id;
    }

    public int getRam() {
        return ram;
    }

    public String getModel() {
        return model;
    }

    @Override
    public String toString() {
        return "Ordinador{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", RAM='" + ram + '\'' +
                '}';
    }

}
