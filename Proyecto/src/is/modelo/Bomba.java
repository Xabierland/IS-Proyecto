package is.modelo;

import is.vista.Juego;

import java.awt.*;

public class Bomba extends Arma
{

    public Bomba(int pTipoArma,boolean finita) {
        super(pTipoArma,finita);
    }

    @Override
    public boolean usar(int pJugador, int x, int y)
    {
        boolean atacado=false;

        Jugador efectuante,afectado;
        if(pJugador==0) {
             efectuante = ListaJugadores.getMiListaJugadores().getJugador(1);
             afectado = ListaJugadores.getMiListaJugadores().getJugador(0);
        }
        else
        {
            afectado = ListaJugadores.getMiListaJugadores().getJugador(1);
            efectuante = ListaJugadores.getMiListaJugadores().getJugador(0);
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
                    cambiar("CASILLA", afectado.getTablero().getCasilla(x, y),Color.red);
                    afectado.getTablero().setDisparo(true, x, y);
                    atacado=true;
                    //Comprobar si hundido
                    if(!afectado.getTablero().barcoHundido(x,y,afectado.getFlota()))
                    {
                        //TOCADO
                        if(!(efectuante instanceof NPC))
                        {
                            cambiar("ESTADO", Juego.getDisplayState(), "TOCADO");
                        }
                    }
                    else
                    {
                        //HUNDIDO
                        efectuante.setDinero(efectuante.getDinero() + Variables.getMisVariables().getDineroPorHundir());
                        if(!(efectuante instanceof NPC))
                        {
                            cambiar("ESTADO", Juego.getDisplayState(), "TOCADO Y HUNDIDO");
                            cambiar("dinero", Juego.getDisplayState(), efectuante.getDinero());
                        }
                    }
                }
                else
                {
                    //ESCUDO
                    cambiar("CASILLA", afectado.getTablero().getCasilla(x, y),Color.black);
                    desactivarEscudos(afectado,x,y);
                    if(!(efectuante instanceof NPC))
                    {
                        cambiar("ESTADO", Juego.getDisplayState(), "ESCUDO");
                    }
                    atacado=true;
                }
            }
            else
            {
                //AGUA
                cambiar("CASILLA", afectado.getTablero().getCasilla(x, y),Color.white);
                afectado.getTablero().setDisparo(true, x, y);
                if(!(efectuante instanceof NPC))
                {
                    cambiar("ESTADO", Juego.getDisplayState(), "AGUA");
                }
                atacado=true;
            }
        }
        else
        {
            //YA SE HA DISPARADO
            atacado=false;
        }
        return atacado;
    }
}