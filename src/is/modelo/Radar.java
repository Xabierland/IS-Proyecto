package is.modelo;

import java.awt.*;

public class Radar extends Arma
{
    public Radar(int pTipoArma,boolean finita) {
        super(pTipoArma,finita);
    }

    @Override
    public boolean atacar(int pTablero, int pX, int pY)
    {
        Tablero tab=getTablero(pTablero);
        boolean atacado=true;
        int x,y;

        try {
            x=pX;
            y=pY;
            if (tab.getIfBarcoByPos(x, y, false)) {
                if(!tab.getIfDisparo(x,y)) {
                    if(!tab.getIfEscudo(x,y)) {
                        setChanged();
                        Object[] objetos = new Object[3];
                        objetos[0] = "CASILLA";
                        objetos[1] = tab.getCasilla(x, y);
                        objetos[2] = Color.green;
                        this.notifyObservers(objetos);
                    }
                    else
                    {
                        setChanged();
                        Object[] objetos = new Object[3];
                        objetos[0] = "CASILLA";
                        objetos[1] = tab.getCasilla(x, y);
                        objetos[2] = Color.magenta;
                        this.notifyObservers(objetos);
                    }
                }
                else
                {
                    setChanged();
                    Object[] objetos = new Object[3];
                    objetos[0] = "CASILLA";
                    objetos[1] = tab.getCasilla(x, y);
                    objetos[2] = Color.red;
                    this.notifyObservers(objetos);
                }
            } else {
                setChanged();
                Object[] objetos = new Object[3];
                objetos[0] = "CASILLA";
                objetos[1] = tab.getCasilla(x, y);
                objetos[2] = Color.white;
                this.notifyObservers(objetos);
                tab.setDisparo(true,x,y);
            }
        } catch (Exception ignore) {}
        try {
            x=pX+1;
            y=pY;
            if (tab.getIfBarcoByPos(x, y, false)) {
                if(!tab.getIfDisparo(x,y)) {
                    if(!tab.getIfEscudo(x,y)) {
                        setChanged();
                        Object[] objetos = new Object[3];
                        objetos[0] = "CASILLA";
                        objetos[1] = tab.getCasilla(x, y);
                        objetos[2] = Color.green;
                        this.notifyObservers(objetos);
                    }
                    else
                    {
                        setChanged();
                        Object[] objetos = new Object[3];
                        objetos[0] = "CASILLA";
                        objetos[1] = tab.getCasilla(x, y);
                        objetos[2] = Color.magenta;
                        this.notifyObservers(objetos);
                    }
                }
                else
                {
                    setChanged();
                    Object[] objetos = new Object[3];
                    objetos[0] = "CASILLA";
                    objetos[1] = tab.getCasilla(x, y);
                    objetos[2] = Color.red;
                    this.notifyObservers(objetos);
                }
            } else {
                setChanged();
                Object[] objetos = new Object[3];
                objetos[0] = "CASILLA";
                objetos[1] = tab.getCasilla(x, y);
                objetos[2] = Color.white;
                this.notifyObservers(objetos);
                tab.setDisparo(true,x,y);
            }
        } catch (Exception ignore) {}
        try {
            x=pX-1;
            y=pY;
            if (tab.getIfBarcoByPos(x, y, false)) {
                if(!tab.getIfDisparo(x,y)) {
                    if(!tab.getIfEscudo(x,y)) {
                        setChanged();
                        Object[] objetos = new Object[3];
                        objetos[0] = "CASILLA";
                        objetos[1] = tab.getCasilla(x, y);
                        objetos[2] = Color.green;
                        this.notifyObservers(objetos);
                    }
                    else
                    {
                        setChanged();
                        Object[] objetos = new Object[3];
                        objetos[0] = "CASILLA";
                        objetos[1] = tab.getCasilla(x, y);
                        objetos[2] = Color.magenta;
                        this.notifyObservers(objetos);
                    }
                }
                else
                {
                    setChanged();
                    Object[] objetos = new Object[3];
                    objetos[0] = "CASILLA";
                    objetos[1] = tab.getCasilla(x, y);
                    objetos[2] = Color.red;
                    this.notifyObservers(objetos);
                }
            } else {
                setChanged();
                Object[] objetos = new Object[3];
                objetos[0] = "CASILLA";
                objetos[1] = tab.getCasilla(x, y);
                objetos[2] = Color.white;
                this.notifyObservers(objetos);
                tab.setDisparo(true,x,y);
            }
        } catch (Exception ignore) {}
        try {
            x=pX;
            y=pY+1;
            if (tab.getIfBarcoByPos(x, y, false)) {
                if(!tab.getIfDisparo(x,y)) {
                    if(!tab.getIfEscudo(x,y)) {
                        setChanged();
                        Object[] objetos = new Object[3];
                        objetos[0] = "CASILLA";
                        objetos[1] = tab.getCasilla(x, y);
                        objetos[2] = Color.green;
                        this.notifyObservers(objetos);
                    }
                    else
                    {
                        setChanged();
                        Object[] objetos = new Object[3];
                        objetos[0] = "CASILLA";
                        objetos[1] = tab.getCasilla(x, y);
                        objetos[2] = Color.magenta;
                        this.notifyObservers(objetos);
                    }
                }
                else
                {
                    setChanged();
                    Object[] objetos = new Object[3];
                    objetos[0] = "CASILLA";
                    objetos[1] = tab.getCasilla(x, y);
                    objetos[2] = Color.red;
                    this.notifyObservers(objetos);
                }
            } else {
                setChanged();
                Object[] objetos = new Object[3];
                objetos[0] = "CASILLA";
                objetos[1] = tab.getCasilla(x, y);
                objetos[2] = Color.white;
                this.notifyObservers(objetos);
                tab.setDisparo(true,x,y);
            }
        } catch (Exception ignore) {}
        try {
            x=pX;
            y=pY-1;
            if (tab.getIfBarcoByPos(x, y, false)) {
                if(!tab.getIfDisparo(x,y)) {
                    if(!tab.getIfEscudo(x,y)) {
                        setChanged();
                        Object[] objetos = new Object[3];
                        objetos[0] = "CASILLA";
                        objetos[1] = tab.getCasilla(x, y);
                        objetos[2] = Color.green;
                        this.notifyObservers(objetos);
                    }
                    else
                    {
                        setChanged();
                        Object[] objetos = new Object[3];
                        objetos[0] = "CASILLA";
                        objetos[1] = tab.getCasilla(x, y);
                        objetos[2] = Color.magenta;
                        this.notifyObservers(objetos);
                    }
                }
                else
                {
                    setChanged();
                    Object[] objetos = new Object[3];
                    objetos[0] = "CASILLA";
                    objetos[1] = tab.getCasilla(x, y);
                    objetos[2] = Color.red;
                    this.notifyObservers(objetos);
                }
            } else {
                setChanged();
                Object[] objetos = new Object[3];
                objetos[0] = "CASILLA";
                objetos[1] = tab.getCasilla(x, y);
                objetos[2] = Color.white;
                this.notifyObservers(objetos);
                tab.setDisparo(true,x,y);
            }
        } catch (Exception ignore) {}
        try {
            x=pX+1;
            y=pY+1;
            if (tab.getIfBarcoByPos(x, y, false)) {
                if(!tab.getIfDisparo(x,y)) {
                    if(!tab.getIfEscudo(x,y)) {
                        setChanged();
                        Object[] objetos = new Object[3];
                        objetos[0] = "CASILLA";
                        objetos[1] = tab.getCasilla(x, y);
                        objetos[2] = Color.green;
                        this.notifyObservers(objetos);
                    }
                    else
                    {
                        setChanged();
                        Object[] objetos = new Object[3];
                        objetos[0] = "CASILLA";
                        objetos[1] = tab.getCasilla(x, y);
                        objetos[2] = Color.magenta;
                        this.notifyObservers(objetos);
                    }
                }
                else
                {
                    setChanged();
                    Object[] objetos = new Object[3];
                    objetos[0] = "CASILLA";
                    objetos[1] = tab.getCasilla(x, y);
                    objetos[2] = Color.red;
                    this.notifyObservers(objetos);
                }
            } else {
                setChanged();
                Object[] objetos = new Object[3];
                objetos[0] = "CASILLA";
                objetos[1] = tab.getCasilla(x, y);
                objetos[2] = Color.white;
                this.notifyObservers(objetos);
                tab.setDisparo(true,x,y);
            }
        } catch (Exception ignore) {}
        try {
            x=pX-1;
            y=pY-1;
            if (tab.getIfBarcoByPos(x, y, false)) {
                if(!tab.getIfDisparo(x,y)) {
                    if(!tab.getIfEscudo(x,y)) {
                        setChanged();
                        Object[] objetos = new Object[3];
                        objetos[0] = "CASILLA";
                        objetos[1] = tab.getCasilla(x, y);
                        objetos[2] = Color.green;
                        this.notifyObservers(objetos);
                    }
                    else
                    {
                        setChanged();
                        Object[] objetos = new Object[3];
                        objetos[0] = "CASILLA";
                        objetos[1] = tab.getCasilla(x, y);
                        objetos[2] = Color.magenta;
                        this.notifyObservers(objetos);
                    }
                }
                else
                {
                    setChanged();
                    Object[] objetos = new Object[3];
                    objetos[0] = "CASILLA";
                    objetos[1] = tab.getCasilla(x, y);
                    objetos[2] = Color.red;
                    this.notifyObservers(objetos);
                }
            } else {
                setChanged();
                Object[] objetos = new Object[3];
                objetos[0] = "CASILLA";
                objetos[1] = tab.getCasilla(x, y);
                objetos[2] = Color.white;
                this.notifyObservers(objetos);
                tab.setDisparo(true,x,y);
            }
        } catch (Exception ignore) {}
        try {
            x=pX+1;
            y=pY-1;
            if (tab.getIfBarcoByPos(x, y, false)) {
                if(!tab.getIfDisparo(x,y)) {
                    if(!tab.getIfEscudo(x,y)) {
                        setChanged();
                        Object[] objetos = new Object[3];
                        objetos[0] = "CASILLA";
                        objetos[1] = tab.getCasilla(x, y);
                        objetos[2] = Color.green;
                        this.notifyObservers(objetos);
                    }
                    else
                    {
                        setChanged();
                        Object[] objetos = new Object[3];
                        objetos[0] = "CASILLA";
                        objetos[1] = tab.getCasilla(x, y);
                        objetos[2] = Color.magenta;
                        this.notifyObservers(objetos);
                    }
                }
                else
                {
                    setChanged();
                    Object[] objetos = new Object[3];
                    objetos[0] = "CASILLA";
                    objetos[1] = tab.getCasilla(x, y);
                    objetos[2] = Color.red;
                    this.notifyObservers(objetos);
                }
            } else {
                setChanged();
                Object[] objetos = new Object[3];
                objetos[0] = "CASILLA";
                objetos[1] = tab.getCasilla(x, y);
                objetos[2] = Color.white;
                this.notifyObservers(objetos);
                tab.setDisparo(true,x,y);
            }
        } catch (Exception ignore) {}
        try {
            x=pX-1;
            y=pY+1;
            if (tab.getIfBarcoByPos(x, y, false)) {
                if(!tab.getIfDisparo(x,y)) {
                    if(!tab.getIfEscudo(x,y)) {
                        setChanged();
                        Object[] objetos = new Object[3];
                        objetos[0] = "CASILLA";
                        objetos[1] = tab.getCasilla(x, y);
                        objetos[2] = Color.green;
                        this.notifyObservers(objetos);
                    }
                    else
                    {
                        setChanged();
                        Object[] objetos = new Object[3];
                        objetos[0] = "CASILLA";
                        objetos[1] = tab.getCasilla(x, y);
                        objetos[2] = Color.magenta;
                        this.notifyObservers(objetos);
                    }
                }
                else
                {
                    setChanged();
                    Object[] objetos = new Object[3];
                    objetos[0] = "CASILLA";
                    objetos[1] = tab.getCasilla(x, y);
                    objetos[2] = Color.red;
                    this.notifyObservers(objetos);
                }
            } else {
                setChanged();
                Object[] objetos = new Object[3];
                objetos[0] = "CASILLA";
                objetos[1] = tab.getCasilla(x, y);
                objetos[2] = Color.white;
                this.notifyObservers(objetos);
                tab.setDisparo(true,x,y);
            }
        } catch (Exception ignore) {}
        return true;
    }
}
