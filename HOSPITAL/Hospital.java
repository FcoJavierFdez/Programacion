package HOSPITAL;

import java.util.ArrayList;
import java.util.Random;

public class Hospital {
    private String nom;
    private String localitzacio;
    private final int llitsMax;
    private ArrayList<Unitat> llistaUnitats;

    public Hospital(String nom, String localitzacio, int llitsMax) {
        this.nom = nom;
        this.localitzacio = localitzacio;
        this.llitsMax = llitsMax;
        this.llistaUnitats = new ArrayList<>();
    }

    public int getLlitsMax() {
        return llitsMax;
    }

    public void afegirUnitat(Unitat u) {
        llistaUnitats.add(u);
    }

    // Fase 2.d: Eliminar una unitat només si està buida
    public boolean eliminarUnitat(String nomUnitat) {
        Unitat unitatTrobat = null;
        int indexTrobat = -1;

        // 1. Busquem la unitat i guardem la seua posició (índex)
        for (int i = 0; i < llistaUnitats.size() && unitatTrobat == null; i++) {
            if (llistaUnitats.get(i).getNom().equalsIgnoreCase(nomUnitat)) {
                unitatTrobat = llistaUnitats.get(i);
                indexTrobat = i;
            }
        }

        // 2. Si l'hem trobat, comprovem si té pacients
        if (unitatTrobat != null) {
            if (unitatTrobat.getLlista().isEmpty()) {
                // Està buida: la podem eliminar
                llistaUnitats.remove(indexTrobat);
                System.out.println("Unitat '" + nomUnitat + "' eliminada correctament.");
                return true;
            } else {
                // Té pacients: no la podem eliminar
                System.out.println("Error: No es pot eliminar la unitat '" + nomUnitat + "' perquè té "
                        + unitatTrobat.getLlista().size() + " pacients ingressats.");
                return false;
            }
        }

        System.out.println("Error: La unitat '" + nomUnitat + "' no existeix.");
        return false;
    }

    public int getOcupacioActual() {
        int total = 0;
        for (Unitat u : llistaUnitats) {
            total += u.getLlista().size();
        }
        return total;
    }

    // Validació de capacitat (Fase 3.f)
    public boolean hiHaLlitDisponible() {
        if (getOcupacioActual() < llitsMax) {
            return true;
        } else {
            System.out.println("HOSPITAL PLE: No queden llits disponibles.");
            return false;
        }
    }

    public boolean ingressar(Pacient p, String nomUnitat) {
        if (getOcupacioActual() < llitsMax) {
            for (Unitat u : llistaUnitats) {
                if (u.getNom().equalsIgnoreCase(nomUnitat)) {
                    u.afegir(p);
                    System.out.println("Ingrés confirmat a " + nomUnitat);
                    return true; // Ha ingressat!
                }
            }
            // Si arribem ací és perquè hem recorregut totes les unitats i cap coincidia
            System.out.println("Error: La unitat '" + nomUnitat + "' no existeix.");
            return false; // L'ingrés ha fallat
        } else {
            System.out.println("Error:No hi ha llits disponibles");
            return false; // L'ingrés ha fallat
        }
    }

    public boolean transferir(String codi, String origen, String desti) {
        Unitat uOrigen = null;
        Unitat uDesti = null;

        // 1. Busquem les dues unitats
        for (Unitat u : llistaUnitats) {
            if (u.getNom().equalsIgnoreCase(origen))
                uOrigen = u;
            if (u.getNom().equalsIgnoreCase(desti))
                uDesti = u;
        }

        // 2. Validem que les unitats existeixen
        if (uOrigen == null || uDesti == null) {
            System.out.println("Error: Una de les unitats no existeix.");
            return false;
        }

        // 3. Busquem el pacient en la unitat d'origen
        Pacient pTrobat = null;
        for (int i = 0; i < uOrigen.getLlista().size() && pTrobat == null; i++) {
            Pacient p = uOrigen.getLlista().get(i);
            if (p.getCodi().equalsIgnoreCase(codi)) {
                pTrobat = p;
            }
        }

        // 4. Si el trobem, fem el canvi
        if (pTrobat != null) {
            uOrigen.eliminar(codi);
            uDesti.afegir(pTrobat);
            System.out.println("Pacient " + pTrobat.getNom() + " mogut correctament.");
            return true;
        } else {
            System.out.println("Error: El pacient amb codi " + codi + " no està en " + origen);
            return false;
        }
    }

    public boolean donarAlta(String codi) {
        Pacient pTrobat = null;
        Unitat unitatOnEstava = null;

        // 1. Busquem el pacient recorrent totes les unitats
        for (int i = 0; i < llistaUnitats.size() && pTrobat == null; i++) {
            Unitat u = llistaUnitats.get(i);

            // Dins de cada unitat, busquem el pacient
            for (int j = 0; j < u.getLlista().size() && pTrobat == null; j++) {
                Pacient p = u.getLlista().get(j);
                if (p.getCodi().equalsIgnoreCase(codi)) {
                    pTrobat = p;
                    unitatOnEstava = u;
                }
            }
        }

        // 2. Si l'hem trobat, l'eliminem de la unitat corresponent
        if (pTrobat != null) {
            unitatOnEstava.eliminar(codi);
            System.out.println(
                    "Alta tramitada: El pacient " + pTrobat.getNom() + " ha eixit de " + unitatOnEstava.getNom());
            return true;
        } else {
            System.out.println("Error: No s'ha trobat cap pacient amb el codi " + codi);
            return false;
        }

    }

    // Fase 5.i: Buscar pacient per codi
    public Pacient buscarPacient(String codi) {
        for (Unitat u : llistaUnitats) {
            for (Pacient p : u.getLlista()) {
                if (p.getCodi().equalsIgnoreCase(codi)) {
                    return p;
                }
            }
        }
        return null;
    }

    public String getUnitatDePacient(String codi) {
        for (Unitat u : llistaUnitats) {
            for (Pacient p : u.getLlista()) {
                if (p.getCodi().equalsIgnoreCase(codi)) {
                    return u.getNom();
                }
            }
        }
        return null;
    }

    public void mostrarInfoPacient(String codi) {
        Pacient p = buscarPacient(codi);
        String unitat = getUnitatDePacient(codi);
        if (p != null && unitat != null) {
            System.out.println("Pacient: " + p.getNom());
            System.out.println("Codi: " + p.getCodi());
            System.out.println("Dies ingressat: " + p.getDiesIngres());
            System.out.println("Unitat: " + unitat);
        } else {
            System.out.println("No s'ha trobat cap pacient amb codi " + codi);
        }
    }

    // Fase 5.j: Mostrar estat d'una unitat
    public void mostrarEstatUnitat(String nomUnitat) {
        Unitat unitatTrobat = null;
        for (Unitat u : llistaUnitats) {
            if (u.getNom().equalsIgnoreCase(nomUnitat)) {
                unitatTrobat = u;
            }
        }
        if (unitatTrobat == null) {
            System.out.println("Unitat '" + nomUnitat + "' no existeix.");
        } else {
            System.out.println("Estat de la unitat '" + unitatTrobat.getNom() + "':");
            for (Pacient p : unitatTrobat.getLlista()) {
                System.out.println(" - " + p.getNom() + " (" + p.getCodi() + ") - Dies: " + p.getDiesIngres());
            }
            System.out.println("Total llits ocupats en unitat: " + unitatTrobat.getLlista().size());
        }
    }

    // Fase 5.k: Pacients amb més de 30 dies
    public void listarIngressosLlargs() {
        System.out.println("Pacients amb més de 30 dies d'ingrés:");
        for (Unitat u : llistaUnitats) {
            for (Pacient p : u.getLlista()) {
                if (p.getDiesIngres() > 30) {
                    System.out.println(" - " + p.getNom() + " (" + p.getCodi() + ") - " + p.getDiesIngres()
                            + " dies - Unitat: " + u.getNom());
                }
            }
        }
    }

    // Fase 6.l: Comparar dos pacients per dies
    public void compararPacients(String codi1, String codi2) {
        Pacient p1 = buscarPacient(codi1);
        Pacient p2 = buscarPacient(codi2);
        if (p1 == null || p2 == null) {
            System.out.println("Un o dos pacients no existeixen.");
        } else {
            if (p1.getDiesIngres() > p2.getDiesIngres()) {
                System.out.println(p1.getNom() + " porta més dies ingressat (" + p1.getDiesIngres() + ") que "
                        + p2.getNom() + " (" + p2.getDiesIngres() + ").");
            } else if (p1.getDiesIngres() < p2.getDiesIngres()) {
                System.out.println(p2.getNom() + " porta més dies ingressat (" + p2.getDiesIngres() + ") que "
                        + p1.getNom() + " (" + p1.getDiesIngres() + ").");
            } else {
                System.out.println("Els dos pacients porten els mateixos dies: " + p1.getDiesIngres());
            }
        }
    }

    // Fase 6.m: totals
    public int totalPacientsUnitat(String nomUnitat) {
        for (Unitat u : llistaUnitats) {
            if (u.getNom().equalsIgnoreCase(nomUnitat))
                return u.getLlista().size();
        }
        return 0;
    }

    public int totalPacientsHospital() {
        return getOcupacioActual();
    }

    // Fase 6.n: extrems dins d'una unitat
    public Pacient pacientMesDiesUnitat(String nomUnitat) {
        Unitat uTrobat = null;
        for (Unitat u : llistaUnitats)
            if (u.getNom().equalsIgnoreCase(nomUnitat))
                uTrobat = u;
        if (uTrobat == null || uTrobat.getLlista().isEmpty())
            return null;
        Pacient millor = uTrobat.getLlista().get(0);
        for (Pacient p : uTrobat.getLlista())
            if (p.getDiesIngres() > millor.getDiesIngres())
                millor = p;
        return millor;
    }

    public Pacient pacientMenysDiesUnitat(String nomUnitat) {
        Unitat uTrobat = null;
        for (Unitat u : llistaUnitats)
            if (u.getNom().equalsIgnoreCase(nomUnitat))
                uTrobat = u;
        if (uTrobat == null || uTrobat.getLlista().isEmpty())
            return null;
        Pacient millor = uTrobat.getLlista().get(0);
        for (Pacient p : uTrobat.getLlista())
            if (p.getDiesIngres() < millor.getDiesIngres())
                millor = p;
        return millor;
    }

    public void mostrarExtremsUnitat(String nomUnitat) {
        Pacient max = pacientMesDiesUnitat(nomUnitat);
        Pacient min = pacientMenysDiesUnitat(nomUnitat);
        if (max == null || min == null) {
            System.out.println("Unitat buida o no existeix: " + nomUnitat);
        } else {
            System.out.println("A la unitat " + nomUnitat + ":");
            System.out.println(" - Més dies: " + max.getNom() + " (" + max.getCodi() + ") - " + max.getDiesIngres());
            System.out.println(" - Menys dies: " + min.getNom() + " (" + min.getCodi() + ") - " + min.getDiesIngres());
        }
    }

    // Fase 7.o: controlar aleatori d'un pacient d'una unitat
    public Pacient pacientAleatoriUnitat(String nomUnitat) {
        Unitat uTrobat = null;
        for (Unitat u : llistaUnitats)
            if (u.getNom().equalsIgnoreCase(nomUnitat))
                uTrobat = u;
        if (uTrobat == null || uTrobat.getLlista().isEmpty())
            return null;
        Random rnd = new Random();
        int idx = rnd.nextInt(uTrobat.getLlista().size());
        return uTrobat.getLlista().get(idx);
    }
}