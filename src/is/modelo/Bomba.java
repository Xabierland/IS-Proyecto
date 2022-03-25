package is.modelo;

import java.awt.*;

public class Bomba extends Arma
{

    public Bomba(int pTipoArma,boolean finita) {
        super(pTipoArma,finita);
    }

    @Override
    public boolean atacar(int pTablero, int x, int y)
    {
        boolean atacado=false;
        Tablero tab=getTablero(pTablero);
        if (!tab.getIfEscudo(x, y))
        {
            if(!tab.getIfDisparo(x,y)) {
                if (tab.getIfBarcoByPos(x, y, false)) {
                    setChanged();
                    Object[] objetos = new Object[4];
                    objetos[0] = "CASILLA";
                    objetos[1] = getTablero(pTablero).getCasilla(x, y);
                    objetos[2] = Color.red;
                    this.notifyObservers(objetos);
                } else {
                    setChanged();
                    Object[] objetos = new Object[4];
                    objetos[0] = "CASILLA";
                    objetos[1] = getTablero(pTablero).getCasilla(x, y);
                    objetos[2] = Color.white;
                    this.notifyObservers(objetos);
                }
                tab.setDisparo(true, x, y);
                atacado=true;
            }
            else
            {
                atacado=false;
            }
        }
        else
        {
            System.out.println("ESCUDO DESACTIVADO");
            for (Coordenada c: tab.getFlota().getBarcoporPos(x,y).calcularCoordenadas())
            {
                if(!tab.getIfDisparo(c.getX(),c.getY()))
                {
                    tab.getCasilla(c.getX(),c.getY()).setBackground(Color.black);
                }
                else
                {
                    tab.getCasilla(c.getX(),c.getY()).setBackground(Color.red);
                }
                tab.setEscudo(false,x,y);
            }
            atacado=true;
        }
        return atacado;
    }
}
