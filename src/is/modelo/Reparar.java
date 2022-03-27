package is.modelo;

import java.awt.*;

public class Reparar extends Arma {

    public Reparar(int tipoArma, boolean finito)
    {
        super(tipoArma,finito);
    }

    @Override
    public boolean atacar(int pTablero, int x, int y) {
        boolean atacado = false;
        Tablero afectado;
        afectado = getTablero(pTablero);

        if(afectado.getIfBarcoByPos(x,y,false))
        {
            boolean dannado = false;
            for (Coordenada c : afectado.getFlota().getBarcoporPos(x, y).calcularCoordenadas()) {
                if (afectado.getIfDisparo(c.getX(), c.getY())) {
                    dannado = true;
                    atacado = true;
                }
            }

            if (dannado) {
                for (Coordenada c : afectado.getFlota().getBarcoporPos(x, y).calcularCoordenadas()) {
                    if (afectado instanceof Tablero_Jugador || afectado.getIfDisparo(c.getX(), c.getY())) {
                        setChanged();
                        Object[] objetos = new Object[3];
                        objetos[0] = "CASILLA";
                        objetos[1] = afectado.getCasilla(c.getX(), c.getY());
                        objetos[2] = Color.black;
                        this.notifyObservers(objetos);
                    }
                    afectado.setDisparo(false, c.getX(), c.getY());
                }
            }
        }
        return atacado;
    }
}
