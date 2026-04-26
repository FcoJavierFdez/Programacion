package HOSPITAL;

import java.util.ArrayList;

public class Unitat {
    private String nom;
    private ArrayList<Pacient> llistaPacients;

    public Unitat(String nom) {
        this.nom = nom;
        this.llistaPacients = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public ArrayList<Pacient> getLlista() {
        return llistaPacients;
    }

    public void afegir(Pacient p) {
        llistaPacients.add(p);
    }

    public void eliminar(String codi) {
        for (int i = 0; i < llistaPacients.size(); i++) {
            if (llistaPacients.get(i).getCodi().equals(codi)) {
                llistaPacients.remove(i);
                break;
            }
        }
    }
}
