package is.modelo;

import javax.swing.*;

public class Tablero_IA extends Tablero
{
    private static Tablero_IA miTableroIA=null;

    private Tablero_IA()
    {
        tablero_aux=new boolean[10][10];
        tablero_casilla=new JLabel[10][10];
    }

    public static Tablero_IA getTableroIA()
    {
        if(miTableroIA==null)
        {
            miTableroIA=new Tablero_IA();
        }
        return miTableroIA;
    }
}
