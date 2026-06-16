package EXAMENCONTINUADIA1.SOLUCIONS;

public abstract class Empleado {

    protected String id;
    protected String nom;
    protected String torn;

    public Empleado(String id, String nom, String torn) {
        setId(id);
        this.nom = nom;
        setTorn(torn);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        // .matches() verifica tanto la longitud {9} como los caracteres permitidos
        // [a-zA-Z0-9]
        if (id != null && id.matches("^[a-zA-Z0-9]{9}$")) {
            this.id = id;
        } else {
            throw new IllegalArgumentException("L'ID ha de tindre exactament 9 caràcters alfanumèrics.");
        }
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTorn() {
        return torn;
    }

    public void setTorn(String torn) {
        if (torn != null && (torn.equalsIgnoreCase("dia") || torn.equalsIgnoreCase("nit"))) {
            this.torn = torn.toLowerCase();
        } else {
            throw new IllegalArgumentException("El torn només pot ser 'dia' o 'nit'.");
        }
    }

    public abstract double calcularSou();

    @Override
    public String toString() {
        return "ID: " + id + " | Nom: " + nom + " | Torn: " + torn;
    }
}