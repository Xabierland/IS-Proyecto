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

    public abstract boolean atacar(int pTablero, int x, int y);

    public int getTipoArma()
    {
        return tipoArma;
    }

    public void cambiar(String pTipoCambio, Object pObjetivoCambio, Object pOtroParametro)
    {
        setChanged();
        Object[] objetos = new Object[3];
        objetos[0] = pTipoCambio;
        objetos[1] = pObjetivoCambio;
        objetos[2] = pOtroParametro;
        this.notifyObservers(objetos);
    }

    public void setChanged()
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
