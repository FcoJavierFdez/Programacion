package SIMULACROEXAMEN;

// 2. EL MOLDE BASE (Clase Abstracta)
// La palabra 'abstract' impide hacer un 'new Enviament()'. Obliga a crear tipos específicos (Estándar o Premium).
abstract class Enviament {

    // 'protected' significa que los atributos son privados para fuera,
    // pero las clases "hijas" (PaquetStandard, etc.) sí pueden acceder a ellos.
    protected String codi;
    protected String destinatari;

    // Constructor base
    public Enviament(String codi, String destinatari) {
        this.codi = codi;
        this.destinatari = destinatari;
    }

    // Getters obligatorios según el enunciado
    public String getCodi() {
        return codi;
    }

    public String getDestinatari() {
        return destinatari;
    }

    // MÉTODO ABSTRACTO: Obligamos a que cualquier paquete que se cree en el futuro
    // deba tener su propia fórmula para calcular su coste.
    public abstract double calcularCost();
}