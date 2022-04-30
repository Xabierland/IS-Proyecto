package is.modelo;

import java.util.Locale;

public class Cheats
{
    private ListaJugadores lj=ListaJugadores.getMiListaJugadores();
    private static Cheats myCheats=null;

    private Cheats() {}

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
                lj.getJugador(1).getTablero().mostrarTablero();
                break;
            }
            case "random" : //COLOCA LOS BARCOS ALEATORIAMENTE
            {
                System.out.println("CheatCode: "+cheatCode);
                lj.getJugador(0).anadirBarcoAuto();
                lj.getJugador(1).anadirBarcoAuto();
                lj.getJugador(0).getTablero().getIfPosibleIniciarJuego();
                Partida.getMiPartida().setPreparado(true);
                break;
            }
            case "idkfa" : //ARMAS INFINITAS
            {
                System.out.println("CheatCode: "+cheatCode);
                for(int i=1;i<Variables.getMisVariables().getNumArmas();i++)
                    lj.getJugador(0).getArmamento().addArma(i,false,false);
                break;
            }
            case "motherlode" : //50.000 DE ORO
            {
                System.out.println("CheatCode: "+cheatCode);
                lj.getJugador(0).setDinero(lj.getJugador(0).getDinero()+50000);
                break;
            }
        }
    }
}
