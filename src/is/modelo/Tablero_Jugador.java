package is.modelo;

import is.vista.Juego;

import javax.swing.*;

public class Tablero_Jugador extends Tablero {
    private static Tablero_Jugador miTableroJugador=null;

    private Tablero_Jugador()
    {
        tablero_barcos =new boolean[tTablero][tTablero];
        tablero_casilla=new JLabel[tTablero][tTablero];
        tablero_disparos=new boolean[tTablero][tTablero];
        tablero_escudo=new boolean[tTablero][tTablero];
        flota=new Flota();
        armamento=new Armamento();
        armamento.addArma(0,false,false);
    }

    public static Tablero_Jugador getTableroJugador()
    {
        if(miTableroJugador==null)
        {
            miTableroJugador=new Tablero_Jugador();
        }
        return miTableroJugador;
    }

    public Coordenada getCoordenadasDeUnBarco()
    {
        int x,y;
        boolean encontrado=false;
        Coordenada c=null;

        while(!encontrado)
        {
            x=getRandomInteger(Variables.getMisVariables().getTamanoTablero(), 0);
            y=getRandomInteger(Variables.getMisVariables().getTamanoTablero(), 0);
            if(tablero_barcos[x][y])
            {
                if(!tablero_disparos[x][y])
                {
                    c=new Coordenada(x,y);
                    encontrado=true;
                }
            }
        }
        return c;
    }

    public void setDinero(int pDinero)
    {
        dinero=pDinero;

        setChanged();
        Object[] lista=new Object[3];
        lista[0] = "DINERO";
        lista[1] = Juego.getLblDinero();
        lista[2] = dinero;
        notifyObservers(lista);
    }

    /*
     * Da un numero aleatorio entre MAX y MIN
     * Max no incluido!!!
     */
    private static int getRandomInteger(int max, int min)
    {
        return ((int)(Math.random()*(max-min)))+min;
    }
}
