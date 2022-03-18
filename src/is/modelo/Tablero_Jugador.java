package is.modelo;

import javax.swing.*;
import java.awt.*;

public class Tablero_Jugador
{
    private static Tablero_Jugador miTableroJugador=null;
    private boolean[][] tablero_aux;
    private JLabel[][] tablero_casilla;

    private Tablero_Jugador()
    {
        tablero_aux=new boolean[10][10];
        tablero_casilla=new JLabel[10][10];
    }

    public static Tablero_Jugador getTableroJugador()
    {
        if(miTableroJugador==null)
        {
            miTableroJugador=new Tablero_Jugador();
        }
        return miTableroJugador;
    }

    public void addCasilla(JLabel casilla, int x, int y)
    {
        tablero_casilla[x][y]=casilla;
    }

    public boolean sePuedeColocar(int dir, int type, int x, int y)
    {
        boolean sol=false;
        switch (dir)
        {
            case 0:
            {
                if(0<y-type && y-type<10)
                    sol= true;
                else
                    sol= false;
                break;
            }
            case 1:
            {
                if(0<x+type && x+type<10)
                    sol= true;
                else
                    sol= false;
                break;
            }
            case 2:
            {
                if(0<y+type && y+type<10)
                    sol= true;
                else
                    sol= false;
                break;
            }
            case 3:
            {
                if(0<x-type && x-type<10)
                    sol= true;
                else
                    sol= false;
                break;
            }
        }
        return sol;
    }

    public void addBarco(int dir,int type, int x, int y)
    {
        for(int i=0; i<type; i++)
        {
            switch (dir)
            {
                case 0: //PARA ARR
                {
                    tablero_aux[x][y-i]=true;
                    tablero_casilla[x][y-i].setBackground(Color.black);
                    break;
                }
                case 1: //PARA DER
                {
                    tablero_aux[x+i][y]=true;
                    tablero_casilla[x+i][y].setBackground(Color.black);
                    break;
                }
                case 2: //PARA ABJ
                {
                    tablero_aux[x][y+i]=true;
                    tablero_casilla[x][y+i].setBackground(Color.black);
                    break;
                }
                case 3: //PARA IZQ
                {
                    tablero_aux[x-i][y]=true;
                    tablero_casilla[x-i][y].setBackground(Color.black);
                    break;
                }
            }
        }

        System.out.printf("Barco de longitud %d añadido exitosamente en x:%d y:%d\n",type,x,y);
    }


    public boolean getIfBarcoByPos(int x, int y)
    {
        if(tablero_aux[x][y])
        {
            System.out.println("BARCO");
        }
        else
        {
            System.out.println("AGUA");
        }

        return tablero_aux[x][y];
    }
}
