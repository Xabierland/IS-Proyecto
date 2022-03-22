package is.modelo;

import javax.swing.*;
import java.awt.*;

public abstract class Tablero {
    protected boolean[][] tablero_barcos;
    protected JLabel[][] tablero_casilla;
    protected boolean[][] tablero_disparos;
    protected boolean[][] tablero_escudo;
    protected Flota flota;

    public void addCasilla(JLabel casilla, int x, int y)
    {
        tablero_casilla[x][y]=casilla;
    }

    /*
    * Coloca el barco en una direccion y en las cordenadas pasadas
    * Si ia es true el barco no se mostrara
     */
    public void addBarco(int dir,int type, int x, int y,boolean ia)
    {
        //ANNADIR BARCOS A LA FLOTA DEL JUGADOR
        flota.annadirBarcos(dir, type, x, y);
        for(int i=0; i<type; i++)
        {
            switch (dir)
            {
                case 0: //PARA ARR
                {
                    tablero_barcos[x][y-i]=true;
                    if(!ia)
                        tablero_casilla[x][y-i].setBackground(Color.black);
                    break;
                }
                case 1: //PARA DER
                {
                    tablero_barcos[x+i][y]=true;
                    if(!ia)
                        tablero_casilla[x+i][y].setBackground(Color.black);
                    break;
                }
                case 2: //PARA ABJ
                {
                    tablero_barcos[x][y+i]=true;
                    if(!ia)
                        tablero_casilla[x][y+i].setBackground(Color.black);
                    break;
                }
                case 3: //PARA IZQ
                {
                    tablero_barcos[x-i][y]=true;
                    if(!ia)
                        tablero_casilla[x-i][y].setBackground(Color.black);
                    break;
                }
            }
        }
        System.out.printf("Barco de longitud %d añadido exitosamente en x:%d y:%d\n",type,x,y);
    }

    /*
    * Segun el tipo de arma seleccionada hace un ataque diferente
     */
    public void atacar(int arma, int x, int y)
    {
        switch (arma)
        {
            case 0: //bomba
            {
                if(getIfBarcoByPos(x,y,false))
                {
                    tablero_casilla[x][y].setBackground(Color.red);
                    
                }
                else
                {
                    tablero_casilla[x][y].setBackground(Color.white);
                }
                tablero_disparos[x][y]=true;
                break;
            }
            case 1://misil
            {
                if (getIfBarcoByPos(x, y, false))
                {
                    for (Coordenada c : flota.getBarcoporPos(x, y).calcularCoordenadas())
                    {
                        tablero_casilla[c.getX()][c.getY()].setBackground(Color.red);
                        tablero_disparos[c.getX()][c.getY()] = true;
                    }
                }
                else
                {
                    tablero_casilla[x][y].setBackground(Color.white);
                    tablero_disparos[x][y] = true;
                }
                break;
            }
            case 2://radar
            {
                try {
                    if (getIfBarcoByPos(x, y, false)) {
                        tablero_casilla[x][y].setBackground(Color.green);
                    } else {
                        tablero_casilla[x][y].setBackground(Color.white);
                    }
                }catch (Exception ignore){}
                try {
                    if (getIfBarcoByPos(x + 1, y , false)) {
                        tablero_casilla[x + 1][y].setBackground(Color.green);
                    } else {
                        tablero_casilla[x + 1][y].setBackground(Color.white);
                    }
                }catch (Exception ignore){}
                try {
                    if (getIfBarcoByPos(x, y + 1, false)) {
                        tablero_casilla[x][y + 1].setBackground(Color.green);
                    } else {
                        tablero_casilla[x][y + 1].setBackground(Color.white);
                    }
                }catch (Exception ignore){}
                try {
                    if (getIfBarcoByPos(x - 1, y, false)) {
                        tablero_casilla[x - 1][y].setBackground(Color.green);
                    } else {
                        tablero_casilla[x - 1][y].setBackground(Color.white);
                    }
                }catch (Exception ignore){}
                try {
                    if (getIfBarcoByPos(x, y - 1, false)) {
                        tablero_casilla[x][y - 1].setBackground(Color.green);
                    } else {
                        tablero_casilla[x][y - 1].setBackground(Color.white);
                    }
                }catch (Exception ignore){}
                try {
                    if (getIfBarcoByPos(x + 1, y + 1, false)) {
                        tablero_casilla[x + 1][y + 1].setBackground(Color.green);
                    } else {
                        tablero_casilla[x + 1][y + 1].setBackground(Color.white);
                    }
                }catch (Exception ignore){}
                try {
                    if (getIfBarcoByPos(x - 1, y - 1, false)) {
                        tablero_casilla[x - 1][y - 1].setBackground(Color.green);
                    } else {
                        tablero_casilla[x - 1][y - 1].setBackground(Color.white);
                    }
                }catch (Exception ignore){}
                try {
                    if (getIfBarcoByPos(x + 1, y - 1, false)) {
                        tablero_casilla[x + 1][y - 1].setBackground(Color.green);
                    } else {
                        tablero_casilla[x + 1][y - 1].setBackground(Color.white);
                    }
                }catch (Exception ignore){}
                try {
                    if (getIfBarcoByPos(x - 1, y + 1, false)) {
                        tablero_casilla[x - 1][y + 1].setBackground(Color.green);
                    } else {
                        tablero_casilla[x - 1][y + 1].setBackground(Color.white);
                    }
                }catch (Exception ignore){}
                break;
            }
            case 3://Escudo
            {
                for (Coordenada c : flota.getBarcoporPos(x,y).calcularCoordenadas())
                {
                    tablero_casilla[c.getX()][c.getY()].setBackground(Color.darkGray);
                    tablero_escudo[c.getX()][c.getY()]=true;
                }
            }
        }
    }


    /*
    * Devuelve True si ha terminado y False si no ha terminado
     */
    public boolean getIfEndGame()
    {
        boolean termina=true;
        for(int i=0;i<10;i++)
        {
            for(int j=0;j<10;j++)
            {
                if(tablero_barcos[j][i])
                {
                    if(!tablero_disparos[j][i])
                    {
                        termina=false;
                    }
                }
            }
        }

        return termina;
    }

    /*
    * Nos dice si en la posicion que se le pasa hay un barco o no
    * El print es para imprimir o no por consola
     */
    public boolean getIfBarcoByPos(int x, int y, boolean print)
    {
        if(print) {
            if (tablero_barcos[x][y]) {
                System.out.println("BARCO\n");
            } else {
                System.out.println("AGUA\n");
            }
        }
        return tablero_barcos[x][y];
    }

    /*
    * Mira si un un tipo de barco en una direccion se puede colocar en unas cordenadas
     */
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

    /* ME HE FUMADO UN PORRO!!!!!!!
    * Comprueba que siempre haya un bloque de agua rodeando a un barco
     */
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

    /*
    * Comprueba que se pueda atacar con ese arma en la cordenada pasada
     */
    public boolean sePuedeAtacar(int arma, int x, int y)
    {
        boolean atacable=false;
        if(!tablero_disparos[x][y])
        {
            if(arma!=3)
            {
                atacable=true;
            }
            else
            {
                System.out.println("NO SE PUEDE PONER ESCUDO AL ENEMIGO");
            }
        }
        else
        {
            System.out.println("NO SE PUEDE ATACAR");
        }
        return atacable;
    }
}