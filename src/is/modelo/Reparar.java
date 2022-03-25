package is.modelo;

public class Reparar extends Arma {

    public Reparar(int tipoArma, boolean finito)
    {
        super(tipoArma,finito);
    }

    @Override
    public boolean atacar(int pTablero, int x, int y) {
        return false;
    }
}
