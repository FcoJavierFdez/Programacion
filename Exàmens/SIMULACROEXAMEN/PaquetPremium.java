package SIMULACROEXAMEN;

// 4. SEGUNDO TIPO DE PAQUETE (Hereda de Enviament Y firma el contrato Assegurable)
class PaquetPremium extends Enviament implements Asegurable {
    private double valorDeclarat;

    public PaquetPremium(String codi, String destinatari, double valorDeclarat) {
        super(codi, destinatari);
        this.valorDeclarat = valorDeclarat;
    }

    // El enunciado pide getter y setter específicos para la clase Premium
    public double getValorDeclarat() {
        return valorDeclarat;
    }

    public void setValorDeclarat(double valorDeclarat) {
        this.valorDeclarat = valorDeclarat;
    }

    // Sobrescribimos el método de coste (el Premium tiene coste fijo de 20€)
    @Override
    public double calcularCost() {
        return 20.0;
    }

    // Al implementar la interfaz, ESTAMOS OBLIGADOS a poner este método
    @Override
    public double obtenirPrima() {
        return valorDeclarat * 0.05; // La prima es el 5% del valor
    }

}