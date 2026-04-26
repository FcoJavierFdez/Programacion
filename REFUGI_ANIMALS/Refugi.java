package REFUGI_ANIMALS;

import java.util.*;

class Refugi {
    // Estructura Clau (Espècie) - Valor (LArrayLista d'animals)
    private HashMap<String, ArrayList<Animal>> inventari;

    public Refugi() {
        this.inventari = new HashMap<>();
        // Inicialitzem les lArrayListes per evitar NullPointerExceptions
        inventari.put("Gos", new ArrayList<>());
        inventari.put("Gat", new ArrayList<>());
        inventari.put("Ocell", new ArrayList<>());
    }

    public void afegirAnimal(String especie, Animal a) {
        if (inventari.containsKey(especie)) {
            inventari.get(especie).add(a);
            System.out.println("• " + especie + " afegit correctament amb ID: " + a.getId());
        } else {
            System.out.println("Error: Espècie desconeguda.");
        }
    }

    public Animal buscarAnimalPerId(int id) {
        // Recorrem totes les lArrayListes del Map
        for (ArrayList<Animal> lArrayLista : inventari.values()) {
            for (Animal a : lArrayLista) {
                if (a.getId() == id) {
                    return a;
                }
            }
        }
        return null;
    }

    public void eliminarAnimal(Animal a) {
        // Busquem en quina lArrayLista està i l'eliminem
        for (ArrayList<Animal> lArrayLista : inventari.values()) {
            if (lArrayLista.remove(a)) {
                return; // Si l'hem trobat i esborrat, eixim
            }
        }
    }

    public ArrayList<Animal> getAnimalsPerEspecie(String especie) {
        ArrayList<Animal> a = inventari.get(especie);
        return (a != null) ? a : new ArrayList<Animal>();
    }

    public ArrayList<Animal> getTotsElsAnimals() {
        ArrayList<Animal> tots = new ArrayList<>();
        for (ArrayList<Animal> lArrayLista : inventari.values()) {
            tots.addAll(lArrayLista);
        }
        return tots;
    }
}