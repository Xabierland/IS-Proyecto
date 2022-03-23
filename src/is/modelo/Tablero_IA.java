package is.modelo;

import javax.swing.*;
import java.awt.*;

public class Tablero_IA extends Tablero
{
    private static Tablero_IA miTableroIA=null;

    private Tablero_IA()
    {
        tablero_barcos =new boolean[10][10];
        tablero_casilla=new JLabel[10][10];
        tablero_disparos=new boolean[10][10];
        tablero_escudo=new boolean[10][10];
        flota=new Flota();
    }

    public static Tablero_IA getTableroIA()
    {
        if(miTableroIA==null)
        {
            miTableroIA=new Tablero_IA();
        }
        return miTableroIA;
    }

    public void mostrarTablero()
    {
        int i,j;
        for(i=0;i<10;i++)
            for(j=0; j<10;j++)
            {
                if(getIfBarcoByPos(i,j,false))
                {
                	if(tablero_disparos[i][j])
                	{
                        setChanged();
                        Object[] objetos=new Object[3];
                        objetos[0]=tablero_casilla[i][j];
                        objetos[1]=Color.red;
                        objetos[2]=1;
                        this.notifyObservers(objetos);
                		
                	}
                	else
                	{
                        setChanged();
                        Object[] objetos=new Object[3];
                        objetos[0]=tablero_casilla[i][j];
                        objetos[1]=Color.black;
                        objetos[2]=1;
                        this.notifyObservers(objetos);
                    	
                	}
                }
                else
                {
                    setChanged();
                    Object[] objetos=new Object[3];
                    objetos[0]=tablero_casilla[i][j];
                    objetos[1]=Color.white;
                    objetos[2]=1;
                    this.notifyObservers(objetos);
                    
                }
            }
    }
}
