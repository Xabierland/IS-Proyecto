package is.modelo;

import javax.swing.*;
import java.awt.*;

public abstract class Tablero {
    protected boolean[][] tablero_aux;
    protected JLabel[][] tablero_casilla;

    public void addCasilla(JLabel casilla, int x, int y)
    {
        tablero_casilla[x][y]=casilla;
    }

    public void addBarco(int dir,int type, int x, int y,boolean ia)
    {
        for(int i=0; i<type; i++)
        {
            switch (dir)
            {
                case 0: //PARA ARR
                {
                    tablero_aux[x][y-i]=true;
                    if(!ia)
                        tablero_casilla[x][y-i].setBackground(Color.black);
                    break;
                }
                case 1: //PARA DER
                {
                    tablero_aux[x+i][y]=true;
                    if(!ia)
                        tablero_casilla[x+i][y].setBackground(Color.black);
                    break;
                }
                case 2: //PARA ABJ
                {
                    tablero_aux[x][y+i]=true;
                    if(!ia)
                        tablero_casilla[x][y+i].setBackground(Color.black);
                    break;
                }
                case 3: //PARA IZQ
                {
                    tablero_aux[x-i][y]=true;
                    if(!ia)
                        tablero_casilla[x-i][y].setBackground(Color.black);
                    break;
                }
            }
        }
        System.out.printf("Barco de longitud %d añadido exitosamente en x:%d y:%d\n",type,x,y);
    }

    public boolean getIfBarcoByPos(int x, int y, boolean print)
    {
        if(print) {
            if (tablero_aux[x][y]) {
                System.out.println("BARCO");
            } else {
                System.out.println("AGUA");
            }
        }
        return tablero_aux[x][y];
    }

    public boolean sePuedeColocar(int dir, int type, int x, int y)
    {
        boolean posible=false;
        boolean imposible=false;
        for(int i=0; i<type; i++)
        {
            switch (dir)
            {
                case 0:
                {
                    if (y - i >= 0)
                    {
                        if (!getIfBarcoByPos(x, y - i,false))
                            if (aguaAlrededor(x,y-i))
                                posible = true;
                            else
                                imposible=true;
                        else
                            imposible = true;
                    }
                    else
                    {
                        posible = false;
                    }
                    break;
                }
                case 1:
                {
                    if (x + i <= 9)
                    {
                        if (!getIfBarcoByPos(x + i, y,false))
                            if(aguaAlrededor(x+i,y))
                                posible = true;
                            else
                                imposible=true;
                        else
                            imposible = true;
                    }
                    else
                    {
                        posible = false;
                    }
                    break;
                }
                case 2: {
                    if (y + i <= 9)
                    {
                        if (!getIfBarcoByPos(x, y + i,false))
                        {
                            if(aguaAlrededor(x,y+i))
                                posible = true;
                            else
                                imposible=true;
                        }
                        else
                        {
                            imposible = true;
                        }
                    }
                    else
                    {
                        posible = false;
                    }
                    break;
                }
                case 3: {
                    if (x - i >= 0) {
                        if (!getIfBarcoByPos(x - i, y,false)) {
                            if(aguaAlrededor(x-i,y))
                                posible = true;
                            else
                                imposible =true;
                        }
                        else
                        {
                            imposible = true;
                        }
                    }
                    else
                    {
                        posible = false;
                    }
                    break;
                }
            }
        }
        if(imposible)
        {
            posible=false;
        }
        return posible;
    }

    // ME HE FUMADO UN PORRO!!!!!!!
    private boolean aguaAlrededor(int x, int y) {
        boolean sinAgua = false;

        if(x==0&&y==0)
        {
            if (!getIfBarcoByPos(x, y,false))
                if (!getIfBarcoByPos(x + 1, y,false))
                    if (!getIfBarcoByPos(x, y + 1,false))
                        if (!getIfBarcoByPos(x + 1, y + 1,false))
                            sinAgua = true;
        }
        else if(x==0&&y==9)
        {
            if (!getIfBarcoByPos(x, y,false))
                if (!getIfBarcoByPos(x + 1, y,false))
                    if (!getIfBarcoByPos(x, y - 1,false))
                        if (!getIfBarcoByPos(x + 1, y - 1,false))
                            sinAgua = true;
        }
        else if(x==9&&y==0)
        {
            if (!getIfBarcoByPos(x, y,false))
                if (!getIfBarcoByPos(x, y + 1,false))
                    if (!getIfBarcoByPos(x - 1, y,false))
                        if (!getIfBarcoByPos(x - 1, y + 1,false))
                            sinAgua = true;
        }
        else if(x==9&&y==9)
        {
            if (!getIfBarcoByPos(x, y,false))
                if (!getIfBarcoByPos(x - 1, y,false))
                    if (!getIfBarcoByPos(x, y - 1,false))
                        if (!getIfBarcoByPos(x - 1, y - 1,false))
                            sinAgua = true;
        }
        else if(x==0)
        {
            if (!getIfBarcoByPos(x, y,false))
                if (!getIfBarcoByPos(x + 1, y,false))
                    if (!getIfBarcoByPos(x, y + 1,false))
                        if (!getIfBarcoByPos(x + 1, y + 1,false))
                            if (!getIfBarcoByPos(x, y - 1,false))
                                if (!getIfBarcoByPos(x + 1, y - 1,false))
                                    sinAgua = true;
        }
        else if(x==9)
        {
            if (!getIfBarcoByPos(x, y,false))
                if (!getIfBarcoByPos(x, y + 1,false))
                    if (!getIfBarcoByPos(x - 1, y,false))
                        if (!getIfBarcoByPos(x, y - 1,false))
                            if (!getIfBarcoByPos(x - 1, y - 1,false))
                                if (!getIfBarcoByPos(x - 1, y + 1,false))
                                    sinAgua = true;
        }
        else if(y==0)
        {
            if (!getIfBarcoByPos(x, y,false))
                if (!getIfBarcoByPos(x + 1, y,false))
                    if (!getIfBarcoByPos(x, y + 1,false))
                        if (!getIfBarcoByPos(x + 1, y + 1,false))
                            if (!getIfBarcoByPos(x - 1, y,false))
                                if (!getIfBarcoByPos(x - 1, y + 1,false))
                                    sinAgua = true;
        }
        else if(y==9)
        {
            if (!getIfBarcoByPos(x, y,false))
                if (!getIfBarcoByPos(x + 1, y,false))
                    if (!getIfBarcoByPos(x - 1, y,false))
                        if (!getIfBarcoByPos(x, y - 1,false))
                            if (!getIfBarcoByPos(x - 1, y - 1,false))
                                if (!getIfBarcoByPos(x + 1, y - 1,false))
                                    sinAgua = true;
        }
        else
        {
            if (!getIfBarcoByPos(x, y,false))
                if (!getIfBarcoByPos(x + 1, y,false))
                    if (!getIfBarcoByPos(x, y + 1,false))
                        if (!getIfBarcoByPos(x + 1, y + 1,false))
                            if (!getIfBarcoByPos(x - 1, y,false))
                                if (!getIfBarcoByPos(x, y - 1,false))
                                    if (!getIfBarcoByPos(x - 1, y - 1,false))
                                        if (!getIfBarcoByPos(x + 1, y - 1,false))
                                            if (!getIfBarcoByPos(x - 1, y + 1,false))
                                                sinAgua = true;
        }
        return sinAgua;
    }
}
