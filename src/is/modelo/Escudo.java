package is.modelo;

public class Escudo extends Arma{

    public Escudo(int pTipoArma,boolean finita) {
        super(pTipoArma,finita);
    }

    @Override
    public boolean atacar(int pTablero, int x, int y) {
        return false;
    }
}
