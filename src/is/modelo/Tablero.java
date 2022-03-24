package is.modelo;

import is.vista.Juego;

import javax.swing.*;

import java.awt.*;
import java.util.Observable;


public abstract class Tablero extends Observable {

    //Variable tablero
    protected JLabel[][] tablero_casilla;
    protected boolean[][] tablero_barcos;
    protected boolean[][] tablero_disparos;
    protected boolean[][] tablero_escudo;
    protected boolean changed=false;
    protected Flota flota;

    //Variables armas
    private int[] misArmas=new int[Variables.getMisVariables().getNumArmas()];
    private int dinero=Variables.getMisVariables().getDineroInicial();

    //Barcos
    private int totalBarcos=Variables.getMisVariables().getNumBarcos();
    private int fragata=Variables.getMisVariables().getNumFragata();
    private int destructor=Variables.getMisVariables().getNumDestructor();
    private int submarino=Variables.getMisVariables().getNumSubmarino();
    private int portavion=Variables.getMisVariables().getNumPortavion();


    //TABLERO
    /*
    * Añade la casilla en su posicion
     */
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
        if(sePuedeColocar(dir, type, x, y)) {
            //ANNADIR BARCOS A LA FLOTA
            flota.annadirBarcos(dir, type, x, y);
            for (int i = 0; i < type; i++) {
                switch (dir) {
                    case 0: //PARA ARR
                    {
                        tablero_barcos[x][y - i] = true;
                        if (!ia) {
                            setChanged();
                            Object[] objetos = new Object[4];
                            objetos[0] = "CASILLA";
                            objetos[1] = tablero_casilla[x][y - i];
                            objetos[2] = Color.black;
                            objetos[3] = 1;
                            this.notifyObservers(objetos);
                        }
                        break;
                    }
                    case 1: //PARA DER
                    {
                        tablero_barcos[x + i][y] = true;
                        if (!ia) {
                            setChanged();
                            Object[] objetos = new Object[4];
                            objetos[0] = "CASILLA";
                            objetos[1] = tablero_casilla[x + i][y];
                            objetos[2] = Color.black;
                            objetos[3] = 1;
                            this.notifyObservers(objetos);
                        }
                        break;
                    }
                    case 2: //PARA ABJ
                    {
                        tablero_barcos[x][y + i] = true;
                        if (!ia) {
                            setChanged();
                            Object[] objetos = new Object[4];
                            objetos[0] = "CASILLA";
                            objetos[1] = tablero_casilla[x][y + i];
                            objetos[2] = Color.black;
                            objetos[3] = 1;
                            this.notifyObservers(objetos);
                        }
                        break;
                    }
                    case 3: //PARA IZQ
                    {
                        tablero_barcos[x - i][y] = true;
                        if (!ia) {
                            setChanged();
                            Object[] objetos = new Object[4];
                            objetos[0] = "CASILLA";
                            objetos[1] = tablero_casilla[x - i][y];
                            objetos[2] = Color.black;
                            objetos[3] = 1;
                            this.notifyObservers(objetos);
                        }
                        break;
                    }
                }
            }
            restarBarco(type);
            System.out.printf("Barco de longitud %d añadido exitosamente en x:%d y:%d\n",type,x,y);
        }
    }

    /*
    * Coloca los barcos de forma automatica
     */
    public void addBarcoAutomatico(boolean ia)
    {
        int pDir,pX,pY;
        while(totalBarcos>0){
            while (portavion>0)
            {
                pDir=getRandomInteger(3,0);
                pX=getRandomInteger(10,0);
                pY=getRandomInteger(10,0);
                if(sePuedeColocar(pDir,4,pX,pY)){
                    addBarco(pDir, 4, pX, pY, ia);
                }
            }
            while (submarino>0)
            {
                pDir=getRandomInteger(3,0);
                pX=getRandomInteger(10,0);
                pY=getRandomInteger(10,0);
                if(sePuedeColocar(pDir,3,pX,pY)){
                    addBarco(pDir, 3, pX, pY, ia);
                }
            }
            while (destructor>0)
            {
                pDir=getRandomInteger(3,0);
                pX=getRandomInteger(10,0);
                pY=getRandomInteger(10,0);
                if(sePuedeColocar(pDir,2,pX,pY)){
                    addBarco(pDir, 2, pX, pY, ia);
                }
            }
            while (fragata>0)
            {
                pDir=getRandomInteger(3,0);
                pX=getRandomInteger(10,0);
                pY=getRandomInteger(10,0);
                if(sePuedeColocar(pDir,1,pX,pY)){
                    addBarco(pDir, 1, pX, pY, ia);
                }
            }
        }
    }

    /*
    * Resta un barco cada vez que es añadido
     */
    private void restarBarco(int type)
    {
        switch (type)
        {
            case 1: {
                fragata--;
                totalBarcos--;
                break;
            }
            case 2: {
                destructor--;
                totalBarcos--;
                break;
            }
            case 3: {
                submarino--;
                totalBarcos--;
                break;
            }
            case 4: {
                portavion--;
                totalBarcos--;
                break;
            }
        }
        actualizarBtnBarcos(type);
    }

    private void actualizarBtnBarcos(int pType)
    {
        switch (pType)
        {
            case 1:
            {
                if(fragata<=0)
                {
                    Partida.getMiPartida().setTipoBarco(0);
                    setChanged();
                    Object[] lista=new Object[3];
                    lista[0]="BARCO";
                    lista[1]=Juego.getBtn_fragata();
                    lista[2]=false;
                    notifyObservers(lista);
                }
                break;
            }
            case 2:
            {
                if(destructor<=0)
                {
                    Partida.getMiPartida().setTipoBarco(1);
                    setChanged();
                    Object[] lista=new Object[3];
                    lista[0]="BARCO";
                    lista[1]=Juego.getBtn_destructor();
                    lista[2]=false;
                    notifyObservers(lista);
                }
                break;
            }
            case 3:
            {
                if(submarino<=0)
                {
                    Partida.getMiPartida().setTipoBarco(2);
                    setChanged();
                    Object[] lista=new Object[3];
                    lista[0]="BARCO";
                    lista[1]=Juego.getBtn_submarino();
                    lista[2]=false;
                    notifyObservers(lista);
                }
                break;
            }
            case 4:
            {
                if(portavion<=0)
                {
                    Partida.getMiPartida().setTipoBarco(3);
                    setChanged();
                    Object[] lista=new Object[3];
                    lista[0]="BARCO";
                    lista[1]=Juego.getBtn_portavion();
                    lista[2]=false;
                    notifyObservers(lista);
                }
                break;
            }
        }
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
    * Nos dice si todos los barcos ya han sido colocados
     */
    public boolean getIfPosibleIniciarJuego()
    {
        boolean res=false;
        if(totalBarcos<=0)
        {
            res=true;
        }
        return res;
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
    * Nos dice si un barco se ha hundido
     */
    public boolean barcoHundido(int x, int y)
    {
        boolean hundido=true;
        boolean finBarco=false;
        while(hundido && !finBarco) {
            for (Coordenada c : flota.getBarcoporPos(x, y).calcularCoordenadas()) {
                if (!tablero_disparos[c.getX()][c.getY()])
                {
                    hundido=false;
                }
            }
            finBarco=true;
        }

        return hundido;
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
     * todo
     * Segun el tipo de arma seleccionada hace un ataque diferente
     */
    public void atacar(int arma, int x, int y,boolean ia)
    {
        if(!tablero_escudo[x][y]) {
            switch (arma) {
                case 0: //bomba
                {
                    if (getIfBarcoByPos(x, y, false)) {
                        tablero_casilla[x][y].setBackground(Color.red);
                    } else {
                        tablero_casilla[x][y].setBackground(Color.white);
                    }
                    tablero_disparos[x][y] = true;
                    break;
                }
                case 1://misil
                {
                    if (getIfBarcoByPos(x, y, false)) {
                        for (Coordenada c : flota.getBarcoporPos(x, y).calcularCoordenadas()) {
                            tablero_casilla[c.getX()][c.getY()].setBackground(Color.red);
                            tablero_disparos[c.getX()][c.getY()] = true;
                        }
                    } else {
                        tablero_casilla[x][y].setBackground(Color.white);
                        tablero_disparos[x][y] = true;
                    }
                    break;
                }
                case 2://radar
                {
                    try {
                        if (getIfBarcoByPos(x, y, false)) {
                            if(!tablero_disparos[x][y])
                                tablero_casilla[x][y].setBackground(Color.green);
                        } else {
                            tablero_casilla[x][y].setBackground(Color.white);
                            tablero_disparos[x][y]=true;
                        }
                    } catch (Exception ignore) {
                    }
                    try {
                        if (getIfBarcoByPos(x + 1, y, false)) {
                            if(!tablero_disparos[x+1][y])
                                tablero_casilla[x + 1][y].setBackground(Color.green);
                        } else {
                            tablero_casilla[x + 1][y].setBackground(Color.white);
                            tablero_disparos[x+1][y]=true;
                        }
                    } catch (Exception ignore) {
                    }
                    try {
                        if (getIfBarcoByPos(x, y + 1, false)) {
                            if(!tablero_disparos[x][y+1])
                                tablero_casilla[x][y + 1].setBackground(Color.green);
                        } else {
                            tablero_casilla[x][y + 1].setBackground(Color.white);
                            tablero_disparos[x][y+1]=true;
                        }
                    } catch (Exception ignore) {
                    }
                    try {
                        if (getIfBarcoByPos(x - 1, y, false)) {
                            if(!tablero_disparos[x-1][y])
                                tablero_casilla[x - 1][y].setBackground(Color.green);
                        } else {
                            tablero_casilla[x - 1][y].setBackground(Color.white);
                            tablero_disparos[x-1][y]=true;
                        }
                    } catch (Exception ignore) {
                    }
                    try {
                        if (getIfBarcoByPos(x, y - 1, false)) {
                            if(!tablero_disparos[x][y-1])
                                tablero_casilla[x][y - 1].setBackground(Color.green);
                        } else {
                            tablero_casilla[x][y - 1].setBackground(Color.white);
                            tablero_disparos[x][y-1]=true;
                        }
                    } catch (Exception ignore) {
                    }
                    try {
                        if (getIfBarcoByPos(x + 1, y + 1, false)) {
                            if(!tablero_disparos[x+1][y+1])
                                tablero_casilla[x + 1][y + 1].setBackground(Color.green);
                        } else {
                            tablero_casilla[x + 1][y + 1].setBackground(Color.white);
                            tablero_disparos[x+1][y+1]=true;
                        }
                    } catch (Exception ignore) {
                    }
                    try {
                        if (getIfBarcoByPos(x - 1, y - 1, false)) {
                            if(!tablero_disparos[x-1][y-1])
                                tablero_casilla[x - 1][y - 1].setBackground(Color.green);
                        } else {
                            tablero_casilla[x - 1][y - 1].setBackground(Color.white);
                            tablero_disparos[x-1][y-1]=true;
                        }
                    } catch (Exception ignore) {
                    }
                    try {
                        if (getIfBarcoByPos(x + 1, y - 1, false)) {
                            if(!tablero_disparos[x+1][y-1])
                                tablero_casilla[x + 1][y - 1].setBackground(Color.green);
                        } else {
                            tablero_casilla[x + 1][y - 1].setBackground(Color.white);
                            tablero_disparos[x+1][y-1]=true;
                        }
                    } catch (Exception ignore) {
                    }
                    try {
                        if (getIfBarcoByPos(x - 1, y + 1, false)) {
                            if(!tablero_disparos[x-1][y+1])
                                tablero_casilla[x - 1][y + 1].setBackground(Color.green);
                        } else {
                            tablero_casilla[x - 1][y + 1].setBackground(Color.white);
                            tablero_disparos[x-1][y+1]=true;
                        }
                    } catch (Exception ignore) {
                    }
                    break;
                }
                case 3://Escudo
                {
                    for (Coordenada c : flota.getBarcoporPos(x, y).calcularCoordenadas()) {
                        if(!ia) {
                            if(!tablero_disparos[c.getX()][c.getY()])
                            {
                                tablero_casilla[c.getX()][c.getY()].setBackground(Color.darkGray);
                            }
                            else
                            {
                                tablero_casilla[c.getX()][c.getY()].setBackground(Color.lightGray);
                            }
                        }
                        tablero_escudo[c.getX()][c.getY()] = true;
                    }
                }
            }
        }
        else
        {
            System.out.println("ESCUDO DESACTIVADO");
            for (Coordenada c: flota.getBarcoporPos(x,y).calcularCoordenadas())
            {
                if(ia) {
                    if(!tablero_disparos[c.getX()][c.getY()])
                    {
                        tablero_casilla[c.getX()][c.getY()].setBackground(Color.black);
                    }
                    else
                    {
                        tablero_casilla[c.getX()][c.getY()].setBackground(Color.red);
                    }
                }
                tablero_escudo[c.getX()][c.getY()] = false;
            }
        }
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

    public int getDinero()
    {
        return dinero;
    }

    public void setDinero(int pDinero)
    {
        dinero=pDinero;
    }

    public void anadirMisil()
    {
        misArmas[1]++;
    }

    public void anadirRadar()
    {
        misArmas[2]++;
    }

    public void anadirEscudo()
    {
        misArmas[3]++;
    }

    /*
     * Da un numero aleatorio entre MAX y MIN
     * Max no incluido!!!
     */
    private static int getRandomInteger(int max, int min)
    {
        return ((int)(Math.random()*(max-min)))+min;
    }


    public void setChanged()
    {
        changed=true;
    }

    public void notifyObservers(Object g)
    {
        if (changed == true)
        {
            Juego.getMiJuego().update(this, g);
        }
        changed = false;
    }
}