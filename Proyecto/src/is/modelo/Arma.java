package is.modelo;

import is.vista.Juego;

import java.awt.*;
import java.util.Observable;

public abstract class Arma extends Observable {
    boolean changed=false;

    int tipoArma;
    boolean finita;

    public Arma(int pTipoArma, boolean pFinita)
    {
        tipoArma=pTipoArma;
        finita=pFinita;
    }

    public abstract boolean usar(int pTablero, int x, int y);

    protected int getTipoArma()
    {
        return tipoArma;
    }

    protected void desactivarEscudos(Jugador pJugador, int pX, int pY)
    {
        for (Coordenada c: pJugador.getFlota().getBarcoporPos(pX,pY).calcularCoordenadas())
        {
            if(!pJugador.getTablero().getIfDisparo(c.getX(),c.getY()))
            {
                cambiar("CASILLA",pJugador.getTablero().getCasilla(c.getX(),c.getY()),Color.black);
            }
            else
            {
                cambiar("CASILLA",pJugador.getTablero().getCasilla(c.getX(),c.getY()),Color.red);
            }
            pJugador.getTablero().setEscudo(false,c.getX(),c.getY());
        }
    }

    protected void cambiar(String pTipoCambio, Object pObjetivoCambio, Object pOtroParametro)
    {
        setChanged();
        Object[] objetos = new Object[3];
        objetos[0] = pTipoCambio;
        objetos[1] = pObjetivoCambio;
        objetos[2] = pOtroParametro;
        this.notifyObservers(objetos);
    }

    protected void setChanged()
    {
        changed=true;
    }

    public void notifyObservers(Object g)
    {
        if (changed == true)
        {
            Juego.getMiJuego().update(this, g);
        }
        changed = false;
    }
}
