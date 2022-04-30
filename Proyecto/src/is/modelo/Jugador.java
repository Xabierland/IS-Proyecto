package is.modelo;

import is.vista.Juego;

import java.util.Observable;

public abstract class Jugador extends Observable {
    //Variables
    protected int index;              //El numero del usuario
    protected Tablero tablero;        //Tablero del jugador
    protected Flota flota;
    protected Armamento armamento;    //Armamento del jugador
    protected int dinero=Variables.getMisVariables().getDineroInicial();
    protected boolean changed=false;
    //Constructor
    public Jugador(int pIndex)
    {
        index=pIndex;
        tablero=new Tablero();
        flota=new Flota();
        armamento=new Armamento();
    }
    //GETTERS & SETTERS
    public int getIndex() {return index;}
    public Tablero getTablero()
    {
        return tablero;
    }
    public Flota getFlota() {return flota;}
    public Armamento getArmamento() {return armamento;}
    public int getDinero() {return dinero;}
    public abstract void setDinero(int pDinero);

    // BARCOS
    public void anadirBarco(int pX, int pY)
    {
        tablero.addBarco(Partida.getMiPartida().getDireccion(), Partida.getMiPartida().getTipoBarco(), pX, pY, false, flota);
    }

    public void anadirBarcoAuto()
    {
        tablero.addBarcoAutomatico(false, flota);
    }



    //Ataque
    public abstract boolean ataque();
    public abstract boolean ataque(int pX, int pY, int tipoArma);
    public abstract boolean defensa();
    public abstract boolean defensa(int pX, int pY, int tipoArma);



    //Utiles
    /*
     * Da un numero aleatorio entre MAX y MIN
     * Max no incluido!!!
     */
    protected static int getRandomInteger(int max, int min)
    {
        return ((int)(Math.random()*(max-min)))+min;
    }

    //Observer
    /*
     * Cambia la flag de cambio a true
     */
    protected void setChanged()
    {
        changed=true;
    }

    /*
     * Notifica de los cambios a la vista
     */
    public void notifyObservers(Object g)
    {
        if (changed)
        {
            Juego.getMiJuego().update(this, g);
        }
        changed = false;
    }
}
