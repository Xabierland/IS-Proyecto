package is.modelo;

import java.awt.*;

public class Escudo extends Arma{

    public Escudo(int pTipoArma,boolean finita) {
        super(pTipoArma,finita);
    }

    @Override
    public boolean usar(int pJugador, int x, int y) {
        boolean atacado=false;
        Jugador afectado= ListaJugadores.getMiListaJugadores().getJugador(pJugador);

        if(!afectado.getTablero().getIfEscudo(x, y))
        {
            if(afectado.getTablero().getIfBarcoByPos(x,y))
            {
                if(!afectado.getTablero().barcoHundido(x, y,afectado.getFlota())) {
                    for (Coordenada c : afectado.getFlota().getBarcoporPos(x, y).calcularCoordenadas()) {
                        afectado.getTablero().setEscudo(true, c.getX(), c.getY());

                        if (!(afectado instanceof NPC) || afectado.getTablero().getIfDisparo(c.getX(), c.getY())) {
                            cambiar("CASILLA",afectado.getTablero().getCasilla(c.getX(),c.getY()),Color.magenta);
                        }
                    }
                    atacado = true;
                }
            }
        }
        return atacado;
    }
}
