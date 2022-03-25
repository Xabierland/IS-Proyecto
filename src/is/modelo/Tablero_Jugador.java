package is.modelo;

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
