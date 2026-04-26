package VEHICLES;

class CamioPerillos extends Camio implements MercaderiaPerillosa {

    public CamioPerillos(String matricula, String marca, String model) throws MatriculaInvalidaException {
        super(matricula, marca, model);
    }
}