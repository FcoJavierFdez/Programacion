package SIMULACROEXAMEN;

// 3. PRIMER TIPO DE PAQUETE (Hereda de Enviament)
class PaquetStandard extends Enviament {
    private double pes;

    public PaquetStandard(String codi, String destinatari, double pes) {
        super(codi, destinatari); // Llamamos al constructor de la clase padre (Enviament)
        this.pes = pes;
    }

    // Sobrescribimos el método abstracto usando la fórmula del paquete estándar
    @Override
    public double calcularCost() {
        return pes * 2.5;
    }
}