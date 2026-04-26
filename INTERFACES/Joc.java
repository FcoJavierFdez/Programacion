package INTERFACES;

import java.util.ArrayList;

public class Joc {
    public static void main(String[] args) {

        ArrayList<Personatge> equip = new ArrayList<>();
        Personatge tor= new Guerrer("Thor");
        Personatge legolas= new Arquer("Legolas");
        Personatge elrond= new Sanador("Elrond");
        Personatge boss= new BossFinal("Sauron", "Anell Únic");
        equip.add(tor);
        equip.add(legolas);
        equip.add(elrond);
        equip.add(boss);

        for (Personatge p : equip) {
            
            // 1. Mètodes comuns (heretats de Personatge)
            p.mostrarEstat();
            p.pujarNivell(); 

            // 2. Mètodes específics (requereixen instanceof)
            
            // Si implementa la interfície Atacant, pot atacar
            if (p instanceof Atacant) {
                // Fem "càsting" per tractar-lo com a Atacant
                Atacant atacant = (Atacant) p; 
                atacant.atacar();
            }

            // Si és un Sanador (classe específica), pot curar
            if (p instanceof Sanador) {
                Sanador metge = (Sanador) p;
                metge.curar();
            }

            // Si implementa la interfície Volador
            if (p instanceof Volador) {
                System.out.println("  [Info] Aquest personatge es mou volant.");
            }

            System.out.println("--------------------");
        }
    }
}
