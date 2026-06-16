import java.sql.Date;

public class Contact {

    private String nom;
    private String cognoms;
    private String domicili;
    private String ciutat;
    private int codi_postal;
    private Date data_de_naixement;

    public Contact(String nom, String cognoms, String domicili, String ciutat, int codi_postal,
            Date data_de_naixement) {
        this.nom = nom;
        this.cognoms = cognoms;
        this.domicili = domicili;
        this.ciutat = ciutat;
        this.codi_postal = codi_postal;
        this.data_de_naixement = data_de_naixement;
    }

    public String getNom() {
        return nom;
    }

    public String getCognoms() {
        return cognoms;
    }

    public String getDomicili() {
        return domicili;
    }

    public String getCiutat() {
        return ciutat;
    }

    public int getCodi_postal() {
        return codi_postal;
    }

    public Date getData_de_naixement() {
        return data_de_naixement;
    }

}
