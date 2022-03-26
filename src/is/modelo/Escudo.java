package is.modelo;

import java.awt.*;

public class Escudo extends Arma{

    public Escudo(int pTipoArma,boolean finita) {
        super(pTipoArma,finita);
    }

    @Override
    public boolean atacar(int pTablero, int x, int y) {
        boolean atacado=false;
        Tablero afectado;
        afectado = getTablero(pTablero);

        if(!afectado.getIfEscudo(x, y))
        {
            if(afectado.getIfBarcoByPos(x,y,false))
            {
                if(!afectado.barcoHundido(x, y)) {
                    for (Coordenada c : afectado.getFlota().getBarcoporPos(x, y).calcularCoordenadas()) {
                        afectado.setEscudo(true, c.getX(), c.getY());

                        if (afectado instanceof Tablero_Jugador || afectado.getIfDisparo(c.getX(), c.getY())) {
                            setChanged();
                            Object[] objetos = new Object[3];
                            objetos[0] = "CASILLA";
                            objetos[1] = afectado.getCasilla(c.getX(), c.getY());
                            objetos[2] = Color.magenta;
                            this.notifyObservers(objetos);
                        }
                    }
                    atacado = true;
                }
            }
        }
        return atacado;
    }
}
