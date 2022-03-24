package is.modelo;

import is.vista.Juego;

import java.util.Locale;

public class Cheats
{
    private static Cheats myCheats=null;

    private Cheats()
    {

    }

    public static Cheats getMyCheats()
    {
        if(myCheats==null)
        {
            myCheats=new Cheats();
        }
        return myCheats;
    }

    public void execute(String cheatCode)
    {
        cheatCode=cheatCode.toLowerCase(Locale.ROOT);
        switch (cheatCode)
        {
            case "see_all" : //MUESTRA EL TABLERO ENEMIGO
            {
                System.out.println("CheatCode: "+cheatCode);
                Tablero_IA.getTableroIA().mostrarTablero();
                break;
            }
            case "random" : //COLOCA LOS BARCOS ALEATORIAMENTE
            {
                System.out.println("CheatCode: "+cheatCode);
                Tablero_Jugador.getTableroJugador().addBarcoAutomatico(false);
                Tablero_IA.getTableroIA().addBarcoAutomatico(true);
                Partida.getMiPartida().setDisparando(true);
                break;
            }
            case "iddqd" : //ARMAS INFINITAS
            {
                System.out.println("CheatCode: "+cheatCode);

                break;
            }
            case "motherlode" : //50.000 DE ORO
            {
                System.out.println("CheatCode: "+cheatCode);
                Tablero_Jugador.getTableroJugador().setDinero(Tablero_Jugador.getTableroJugador().getDinero()+50000);
                break;
            }
        }
    }
}
