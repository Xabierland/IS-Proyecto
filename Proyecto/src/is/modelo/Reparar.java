package is.modelo;

import java.awt.*;

public class Reparar extends Arma {

    public Reparar(int tipoArma, boolean finito)
    {
        super(tipoArma,finito);
    }

    @Override
    public boolean usar(int pJugador, int x, int y) {
        boolean atacado = false;
        Jugador jugador;Tablero tablero;
        jugador = ListaJugadores.getMiListaJugadores().getJugador(pJugador);
        tablero = jugador.getTablero();

        if(tablero.getIfBarcoByPos(x,y))
        {
            boolean dannado = false;
            for (Coordenada c : jugador.getFlota().getBarcoporPos(x, y).calcularCoordenadas()) {
                if (tablero.getIfDisparo(c.getX(), c.getY())) {
                    dannado = true;
                    atacado = true;
                }
            }

            if (dannado) {
                for (Coordenada c : jugador.getFlota().getBarcoporPos(x, y).calcularCoordenadas()) {
                    if (!(jugador instanceof NPC) || tablero.getIfDisparo(c.getX(), c.getY())) {
                        setChanged();
                        Object[] objetos = new Object[3];
                        objetos[0] = "CASILLA";
                        objetos[1] = tablero.getCasilla(c.getX(), c.getY());
                        objetos[2] = Color.black;
                        this.notifyObservers(objetos);
                    }
                    tablero.setDisparo(false, c.getX(), c.getY());
                }
            }
        }
        return atacado;
    }
}
