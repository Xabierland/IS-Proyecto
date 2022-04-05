package is.modelo;

import is.vista.Juego;

import java.awt.*;

public class Misil extends Arma
{
    public Misil(int pTipoArma, boolean finita) {
        super(pTipoArma,finita);
    }

    @Override
    public boolean atacar(int pTablero, int x, int y) {
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
                if (afectado.getIfBarcoByPos(x, y))
                {
                    for(Coordenada c : afectado.getFlota().getBarcoporPos(x, y).calcularCoordenadas())
                    {
                        setChanged();
                        Object[] objetos = new Object[4];
                        objetos[0] = "CASILLA";
                        objetos[1] = getTablero(pTablero).getCasilla(c.getX(), c.getY());
                        objetos[2] = Color.red;
                        this.notifyObservers(objetos);
                        afectado.setDisparo(true, c.getX(), c.getY());
                    }
                    if (afectado.barcoHundido(x, y))
                    {
                        efectuante.setDinero(efectuante.getDinero() + Variables.getMisVariables().getDineroPorHundir());
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
                        setChanged();
                        Object[] LISTA = new Object[3];
                        LISTA[0] = "estado";
                        LISTA[1] = Juego.getDisplayState();
                        LISTA[2] = "TOCADO";
                        this.notifyObservers(LISTA);
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

                    if(afectado instanceof Tablero_IA)
                    {
                        setChanged();
                        Object[] LISTA = new Object[3];
                        LISTA[0] = "estado";
                        LISTA[1] = Juego.getDisplayState();
                        LISTA[2] = "AGUA";
                        this.notifyObservers(LISTA);
                    }
                }
                afectado.setDisparo(true, x, y);
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