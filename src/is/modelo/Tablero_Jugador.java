package is.modelo;

import javax.swing.*;

public class Tablero_Jugador extends Tablero {
    private static Tablero_Jugador miTableroJugador=null;

    private Tablero_Jugador()
    {
        tablero_barcos =new boolean[10][10];
        tablero_casilla=new JLabel[10][10];
        tablero_disparos=new boolean[10][10];
        flota=new Flota();
    }

    public static Tablero_Jugador getTableroJugador()
    {
        if(miTableroJugador==null)
        {
            miTableroJugador=new Tablero_Jugador();
        }
        return miTableroJugador;
    }
}
