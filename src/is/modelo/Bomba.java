package is.modelo;

import is.vista.Juego;

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

        Tablero efectuante,afectado;
        if(pTablero==0) {
             efectuante = getTablero(1);
             afectado = getTablero(0);
        }
        else
        {
            afectado = getTablero(1);
            efectuante = getTablero(0);
        }

        if (!afectado.getIfEscudo(x, y))
        {
            if(!afectado.getIfDisparo(x,y)) {
                if (afectado.getIfBarcoByPos(x, y, false))
                {
                    setChanged();
                    Object[] objetos = new Object[3];
                    objetos[0] = "CASILLA";
                    objetos[1] = getTablero(pTablero).getCasilla(x, y);
                    objetos[2] = Color.red;
                    this.notifyObservers(objetos);
                    afectado.setDisparo(true, x, y);

                    if (afectado.barcoHundido(x, y))
                    {
                        efectuante.setDinero(efectuante.getDinero() + 500);
                        if(efectuante instanceof Tablero_Jugador)
                        {
                            setChanged();
                            Object[] LISTA = new Object[3];
                            LISTA[0] = "estado";
                            LISTA[1] = Juego.getDisplayState();
                            LISTA[2] = "TOCADO Y HUNDIDO";
                            this.notifyObservers(LISTA);

                            setChanged();
                            Object[] LISTA6 = new Object[3];
                            LISTA6[0] = "dinero";
                            LISTA6[1] = Juego.getLblDinero();
                            LISTA6[2] = getTablero(0).getDinero();
                            this.notifyObservers(LISTA6);
                        }
                    }
                    else
                    {
                        if(efectuante instanceof Tablero_Jugador) {
                            setChanged();
                            Object[] LISTA = new Object[3];
                            LISTA[0] = "estado";
                            LISTA[1] = Juego.getDisplayState();
                            LISTA[2] = "TOCADO";
                            this.notifyObservers(LISTA);
                        }
                    }
                }
                else
                {
                    setChanged();
                    Object[] objetos = new Object[4];
                    objetos[0] = "CASILLA";
                    objetos[1] = getTablero(pTablero).getCasilla(x, y);
                    objetos[2] = Color.white;
                    this.notifyObservers(objetos);
                    afectado.setDisparo(true, x, y);


                    if(efectuante instanceof Tablero_Jugador)
                    {
                        setChanged();
                        Object[] LISTA = new Object[3];
                        LISTA[0] = "estado";
                        LISTA[1] = Juego.getDisplayState();
                        LISTA[2] = "AGUA";
                        this.notifyObservers(LISTA);
                    }
                }

                atacado=true;
            }
            else
            {
                atacado=false;
            }
        }
        else
        {
            for (Coordenada c: afectado.getFlota().getBarcoporPos(x,y).calcularCoordenadas())
            {
                if(!afectado.getIfDisparo(c.getX(),c.getY()))
                {
                    setChanged();
                    Object[] objetos = new Object[4];
                    objetos[0] = "CASILLA";
                    objetos[1] = getTablero(pTablero).getCasilla(x, y);
                    objetos[2] = Color.black;
                    this.notifyObservers(objetos);
                }
                else
                {
                    setChanged();
                    Object[] objetos = new Object[4];
                    objetos[0] = "CASILLA";
                    objetos[1] = getTablero(pTablero).getCasilla(x, y);
                    objetos[2] = Color.red;
                    this.notifyObservers(objetos);
                }
                afectado.setEscudo(false,x,y);
            }
            atacado=true;
        }
        return atacado;
    }
}
