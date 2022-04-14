package is.modelo;

import is.vista.Juego;

import java.awt.*;

public class Bomba extends Arma
{

    public Bomba(int pTipoArma,boolean finita) {
        super(pTipoArma,finita);
    }

    @Override
    public boolean atacar(int pJugador, int x, int y)
    {
        boolean atacado=false;

        Jugador efectuante,afectado;
        if(pJugador==0) {
             efectuante = Partida.getMiPartida().getJugador(1);
             afectado = Partida.getMiPartida().getJugador(0);
        }
        else
        {
            afectado = Partida.getMiPartida().getJugador(1);
            efectuante = Partida.getMiPartida().getJugador(0);
        }

        //Comprobar si la posicion ya ha sido atacada
        if(!afectado.getTablero().getIfDisparo(x, y))
        {
            //Comprobar si barco
            if(afectado.getTablero().getIfBarcoByPos(x, y))
            {
                //Comprobar si escudo
                if(!afectado.getTablero().getIfEscudo(x,y))
                {
                    //Comprobar si hundido
                    if(!afectado.getTablero().barcoHundido(x,y,afectado.getFlota()))
                    {
                        cambiar("CASILLA", afectado.getTablero().getCasilla(x, y),Color.red);
                        afectado.getTablero().setDisparo(true, x, y);
                        if(!efectuante.getIfIa())
                        {
                            cambiar("ESTADO", Juego.getDisplayState(), "TOCADO");
                        }
                        atacado=true;
                    }
                    else
                    {
                        cambiar("CASILLA", afectado.getTablero().getCasilla(x, y),Color.red);
                        efectuante.setDinero(efectuante.getDinero() + Variables.getMisVariables().getDineroPorHundir());
                        afectado.getTablero().setDisparo(true, x, y);
                        if(!efectuante.getIfIa())
                        {
                            cambiar("ESTADO", Juego.getDisplayState(), "TOCADO Y HUNDIDO");
                            cambiar("dinero", Juego.getDisplayState(), efectuante.getDinero());
                        }
                        atacado=true;
                    }
                }
                else
                {
                    cambiar("CASILLA", afectado.getTablero().getCasilla(x, y),Color.black);
                    //desactivarEscudo(x,y);
                    atacado=true;
                }
            }
            else
            {
                //AGUA
                cambiar("CASILLA", afectado.getTablero().getCasilla(x, y),Color.white);
                atacado=true;
            }
        }
        else
        {
            atacado=false;
        }



        /*
        if (!afectado.getTablero().getIfEscudo(x, y))
        {
            if(!afectado.getTablero().getIfDisparo(x,y)) {
                if (afectado.getTablero().getIfBarcoByPos(x, y))
                {
                    cambiar("CASILLA", afectado.getTablero().getCasilla(x, y),Color.red);
                    afectado.getTablero().setDisparo(true, x, y);

                    if (afectado.getTablero().barcoHundido(x, y, afectado.getFlota()))
                    {
                        efectuante.setDinero(efectuante.getDinero() + Variables.getMisVariables().getDineroPorHundir());
                        if(!efectuante.getIfIa())
                        {
                            cambiar("ESTADO", Juego.getDisplayState(), "TOCADO Y HUNDIDO");
                            cambiar("dinero", Juego.getDisplayState(), efectuante.getDinero());
                        }
                    }
                    else
                    {
                        if(!efectuante.getIfIa()) {
                            cambiar("ESTADO", Juego.getDisplayState(), "TOCADO");
                        }
                    }
                }
                else
                {
                    cambiar("CASILLA", afectado.getTablero().getCasilla(x, y),Color.white);
                    if(!efectuante.getIfIa())
                    {
                        cambiar("ESTADO", Juego.getDisplayState(), "AGUA");
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
            if(!afectado.getIfIa()) {
                for (Coordenada c : afectado.getFlota().getBarcoporPos(x, y).calcularCoordenadas()) {
                    if (!afectado.getTablero().getIfDisparo(c.getX(), c.getY())) {
                        cambiar("CASILLA",afectado.getTablero().getCasilla(c.getX(),c.getY()),Color.black);
                    } else {
                        cambiar("CASILLA",afectado.getTablero().getCasilla(c.getX(),c.getY()),Color.red);
                    }
                    afectado.getTablero().setEscudo(false, c.getX(), c.getY());
                }
            }
            else
            {
                for (Coordenada c : afectado.getFlota().getBarcoporPos(x, y).calcularCoordenadas()) {
                    afectado.getTablero().setEscudo(false, c.getX(), c.getY());
                }
                cambiar("ESTADO", Juego.getDisplayState(), "ESCUDO");
            }
            atacado=true;
        }
        */
        return atacado;
    }
}