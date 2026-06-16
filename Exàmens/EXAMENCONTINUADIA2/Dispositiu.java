package EXAMENCONTINUADIA2;

// Classe abstracta base
public abstract class Dispositiu {
    private String codi; // Alfanumèric de 7 posicions
    private String ubicacio;

    public Dispositiu(String codi, String ubicacio) {
        setCodi(codi);
        this.ubicacio = ubicacio;
    }

    public abstract double calcularCostMensual();

    public String getCodi() {
        return codi;
    }

    public void setCodi(String codi) {
        // Validació: exactament 7 caràcters i alfanumèric
        if (codi != null && codi.matches("^[a-zA-Z0-9]{7}$")) {
            this.codi = codi;
        } else {
            throw new IllegalArgumentException("El codi ha de ser una cadena alfanumèrica de 7 posicions.");
        }
    }

    public String getUbicacio() {
        return ubicacio;
    }

    public void setUbicacio(String ubicacio) {
        this.ubicacio = ubicacio;
    }
}