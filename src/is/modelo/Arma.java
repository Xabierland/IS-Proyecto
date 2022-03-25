package is.modelo;

import is.vista.Juego;

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

    public Tablero getTablero(int pTablero)
    {
        Tablero tab=null;
        if(pTablero==0)
        {
            tab=Tablero_Jugador.getTableroJugador();
        }
        else
        {
            tab=Tablero_IA.getTableroIA();
        }
        return tab;
    }

    public int getTipoArma()
    {
        return tipoArma;
    }

    public boolean getIfFiniti()
    {
        return finita;
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
