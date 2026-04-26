package REFUGI_ANIMALS;

// Interfície que defineix si una entitat es pot adoptar
interface Adoptable {
    boolean esAptaPerAdopcio();

    void adoptar() throws AdopcioDenegadaException;
}