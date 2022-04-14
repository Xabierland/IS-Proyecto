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
        Jugador efectuante,afectado;
        if(pTablero==0) {
            efectuante = Partida.getMiPartida().getJugador(1);
            afectado = Partida.getMiPartida().getJugador(0);
        }
        else
        {
            afectado = Partida.getMiPartida().getJugador(1);
            efectuante = Partida.getMiPartida().getJugador(0);
        }
        if (!afectado.getTablero().getIfEscudo(x, y))
        {
            if(!afectado.getTablero().getIfDisparo(x,y)) {
                if (afectado.getTablero().getIfBarcoByPos(x, y))
                {
                    for(Coordenada c : afectado.getFlota().getBarcoporPos(x, y).calcularCoordenadas())
                    {
                        cambiar("CASILLA",afectado.getTablero().getCasilla(c.getX(),c.getY()),Color.red);
                        afectado.getTablero().setDisparo(true, c.getX(), c.getY());
                    }
                    if (afectado.getTablero().barcoHundido(x, y, afectado.getFlota()))
                    {
                        efectuante.setDinero(efectuante.getDinero() + Variables.getMisVariables().getDineroPorHundir());
                        if(!efectuante.getIfIa())
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
                            LISTA6[2] = Partida.getMiPartida().getJugador(0).getDinero();
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
                    cambiar("CASILLA", afectado.getTablero().getCasilla(x, y),Color.white);

                    if(afectado.getIfIa())
                    {
                        setChanged();
                        Object[] LISTA = new Object[3];
                        LISTA[0] = "estado";
                        LISTA[1] = Juego.getDisplayState();
                        LISTA[2] = "AGUA";
                        this.notifyObservers(LISTA);
                    }
                }
                afectado.getTablero().setDisparo(true, x, y);
                atacado=true;
            }
            else
            {
                atacado=false;
            }
        }
        else
        {
            //todo echarle un ojo a esto que creo que hay algo mal
            System.out.println("ESCUDO DESACTIVADO");
            for (Coordenada c: afectado.getFlota().getBarcoporPos(x,y).calcularCoordenadas())
            {
                if(!afectado.getTablero().getIfDisparo(c.getX(),c.getY()))
                {
                    cambiar("CASILLA",afectado.getTablero().getCasilla(c.getX(),c.getY()),Color.black);
                }
                else
                {
                    cambiar("CASILLA",afectado.getTablero().getCasilla(c.getX(),c.getY()),Color.red);
                }
                afectado.getTablero().setEscudo(false,x,y);
            }
            atacado=true;
        }
        return atacado;
    }
}
