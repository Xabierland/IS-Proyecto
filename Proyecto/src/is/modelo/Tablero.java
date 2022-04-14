package is.modelo;

import is.vista.Juego;

import javax.swing.*;

import java.awt.*;
import java.util.HashMap;
import java.util.Observable;


public class Tablero extends Observable {

    //Variable tablero
    protected int tTablero=Variables.getMisVariables().getTamanoTablero();

    protected HashMap<Object,Coordenada> coordenadasObjetos = new HashMap<>();
    protected JLabel[][] tablero_casilla;
    protected boolean[][] tablero_barcos;
    protected boolean[][] tablero_disparos;
    protected boolean[][] tablero_escudo;
    protected boolean changed=false;

    //Barcos
    private int totalBarcos=Variables.getMisVariables().getNumBarcos();
    private int fragata=Variables.getMisVariables().getNumFragata();
    private int destructor=Variables.getMisVariables().getNumDestructor();
    private int submarino=Variables.getMisVariables().getNumSubmarino();
    private int portavion=Variables.getMisVariables().getNumPortavion();


    //TABLERO
    public Tablero()
    {
        tablero_barcos =new boolean[tTablero][tTablero];
        tablero_casilla=new JLabel[tTablero][tTablero];
        tablero_disparos=new boolean[tTablero][tTablero];
        tablero_escudo=new boolean[tTablero][tTablero];
    }


    /*
    * Añade la casilla en su posicion
     */
    public void addCasilla(JLabel casilla, int x, int y)
    {
        tablero_casilla[x][y]=casilla;
        coordenadasObjetos.put(casilla,new Coordenada(x,y));
    }

    /*
    * Coloca el barco en una direccion y en las cordenadas pasadas
    * Si ia es true el barco no se mostrara
     */
    public boolean addBarco(int dir, int type, int x, int y, boolean ia, Flota flota)
    {
        boolean colocado=false;
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
                            Object[] objetos = new Object[3];
                            objetos[0] = "CASILLA";
                            objetos[1] = tablero_casilla[x][y - i];
                            objetos[2] = Color.black;
                            this.notifyObservers(objetos);
                        }
                        break;
                    }
                    case 1: //PARA DER
                    {
                        tablero_barcos[x + i][y] = true;
                        if (!ia) {
                            setChanged();
                            Object[] objetos = new Object[3];
                            objetos[0] = "CASILLA";
                            objetos[1] = tablero_casilla[x + i][y];
                            objetos[2] = Color.black;
                            this.notifyObservers(objetos);
                        }
                        break;
                    }
                    case 2: //PARA ABJ
                    {
                        tablero_barcos[x][y + i] = true;
                        if (!ia) {
                            setChanged();
                            Object[] objetos = new Object[3];
                            objetos[0] = "CASILLA";
                            objetos[1] = tablero_casilla[x][y + i];
                            objetos[2] = Color.black;
                            this.notifyObservers(objetos);
                        }
                        break;
                    }
                    case 3: //PARA IZQ
                    {
                        tablero_barcos[x - i][y] = true;
                        if (!ia) {
                            setChanged();
                            Object[] objetos = new Object[3];
                            objetos[0] = "CASILLA";
                            objetos[1] = tablero_casilla[x - i][y];
                            objetos[2] = Color.black;
                            this.notifyObservers(objetos);
                        }
                        break;
                    }
                }
            }
            colocado=true;
            restarBarco(type);
            System.out.printf("Barco de longitud %d añadido exitosamente en x:%d y:%d\n",type,x,y);
        }
        return colocado;
    }

    /*
    * Coloca los barcos de forma automatica
     */
    public void addBarcoAutomatico(boolean ia, Flota flota)
    {
        int pDir,pX,pY;
        while(totalBarcos>0){
            while (portavion>0)
            {
                pDir=getRandomInteger(3,0);
                pX=getRandomInteger(tTablero,0);
                pY=getRandomInteger(tTablero,0);
                if(sePuedeColocar(pDir,4,pX,pY)){
                    addBarco(pDir, 4, pX, pY, ia, flota);
                }
            }
            while (submarino>0)
            {
                pDir=getRandomInteger(3,0);
                pX=getRandomInteger(tTablero,0);
                pY=getRandomInteger(tTablero,0);
                if(sePuedeColocar(pDir,3,pX,pY)){
                    addBarco(pDir, 3, pX, pY, ia, flota);
                }
            }
            while (destructor>0)
            {
                pDir=getRandomInteger(3,0);
                pX=getRandomInteger(tTablero,0);
                pY=getRandomInteger(tTablero,0);
                if(sePuedeColocar(pDir,2,pX,pY)){
                    addBarco(pDir, 2, pX, pY, ia, flota);
                }
            }
            while (fragata>0)
            {
                pDir=getRandomInteger(3,0);
                pX=getRandomInteger(tTablero,0);
                pY=getRandomInteger(tTablero,0);
                if(sePuedeColocar(pDir,1,pX,pY)){
                    addBarco(pDir, 1, pX, pY, ia, flota);
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

    public void mostrarTablero()
    {
        int i,j;
        for(i=0;i<tTablero;i++) {
            for (j = 0; j < tTablero; j++) {
                if (getIfBarcoByPos(i, j)) {
                    if (tablero_disparos[i][j]) {
                        setChanged();
                        Object[] objetos = new Object[4];
                        objetos[0] = "CASILLA";
                        objetos[1] = tablero_casilla[i][j];
                        objetos[2] = Color.red;
                        objetos[3] = 1;
                        this.notifyObservers(objetos);

                    } else {
                        setChanged();
                        Object[] objetos = new Object[4];
                        objetos[0] = "CASILLA";
                        objetos[1] = tablero_casilla[i][j];
                        objetos[2] = Color.black;
                        objetos[3] = 1;
                        this.notifyObservers(objetos);

                    }
                } else {
                    setChanged();
                    Object[] objetos = new Object[4];
                    objetos[0] = "CASILLA";
                    objetos[1] = tablero_casilla[i][j];
                    objetos[2] = Color.white;
                    objetos[3] = 1;
                    this.notifyObservers(objetos);

                }
            }
        }
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
                    Partida.getMiPartida().setTipoBarco(0);
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
                    Partida.getMiPartida().setTipoBarco(0);
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
                    Partida.getMiPartida().setTipoBarco(0);
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

    public JLabel getCasilla(int x, int y)
    {
        return tablero_casilla[x][y];
    }

    public Coordenada getCoordenadasDeCasilla(JLabel casilla)
    {
        return coordenadasObjetos.get(casilla);
    }

    public boolean getIfEscudo(int x, int y)
    {
        return tablero_escudo[x][y];
    }

    public boolean getIfDisparo(int x, int y)
    {
        return  tablero_disparos[x][y];
    }

    public void setEscudo(boolean pEstado, int x, int y)
    {
        tablero_escudo[x][y]=pEstado;
    }

    public void setDisparo(boolean pEstado, int x, int y)
    {
        tablero_disparos[x][y]=pEstado;
    }
    /*
     * Nos dice si en la posicion que se le pasa hay un barco o no
     * El print es para imprimir o no por consola
     */
    public boolean getIfBarcoByPos(int x, int y)
    {
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
            setChanged();
            Object[] lista=new Object[3];
            lista[0]="ESTADO";
            lista[1]=Juego.getDisplayState();
            lista[2]="BOMBARDEA A TU ENEMIGO";
            notifyObservers(lista);
        }
        return res;
    }

    /*
     * Devuelve True si ha terminado y False si no ha terminado
     */
    public boolean getIfEndGame()
    {
        boolean termina=true;
        for(int i=0;i<tTablero;i++)
        {
            for(int j=0;j<tTablero;j++)
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
    public boolean barcoHundido(int x, int y, Flota flota)
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
                        if (!getIfBarcoByPos(x, y - i))
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
                    if (x + i <= tTablero-1)
                    {
                        if (!getIfBarcoByPos(x + i, y))
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
                    if (y + i <= tTablero-1)
                    {
                        if (!getIfBarcoByPos(x, y + i))
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
                        if (!getIfBarcoByPos(x - i, y)) {
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

    public Coordenada getCoordenadasDeUnBarco()
    {
        int x,y;
        boolean encontrado=false;
        Coordenada c=null;

        while(!encontrado)
        {
            x=getRandomInteger(Variables.getMisVariables().getTamanoTablero(), 0);
            y=getRandomInteger(Variables.getMisVariables().getTamanoTablero(), 0);
            if(tablero_barcos[x][y])
            {
                if(!tablero_disparos[x][y])
                {
                    c=new Coordenada(x,y);
                    encontrado=true;
                }
            }
        }
        return c;
    }

    /* ME HE FUMADO UN PORRO!!!!!!!
    * Comprueba que siempre haya un bloque de agua rodeando a un barco
     */
    private boolean aguaAlrededor(int x, int y) {
        boolean sinAgua = false;

        if(x==0&&y==0)
        {
            if (!getIfBarcoByPos(x, y))
                if (!getIfBarcoByPos(x + 1, y))
                    if (!getIfBarcoByPos(x, y + 1))
                        if (!getIfBarcoByPos(x + 1, y + 1))
                            sinAgua = true;
        }
        else if(x==0&&y==tTablero-1)
        {
            if (!getIfBarcoByPos(x, y))
                if (!getIfBarcoByPos(x + 1, y))
                    if (!getIfBarcoByPos(x, y - 1))
                        if (!getIfBarcoByPos(x + 1, y - 1))
                            sinAgua = true;
        }
        else if(x==tTablero-1&&y==0)
        {
            if (!getIfBarcoByPos(x, y))
                if (!getIfBarcoByPos(x, y + 1))
                    if (!getIfBarcoByPos(x - 1, y))
                        if (!getIfBarcoByPos(x - 1, y + 1))
                            sinAgua = true;
        }
        else if(x==tTablero-1&&y==tTablero-1)
        {
            if (!getIfBarcoByPos(x, y))
                if (!getIfBarcoByPos(x - 1, y))
                    if (!getIfBarcoByPos(x, y - 1))
                        if (!getIfBarcoByPos(x - 1, y - 1))
                            sinAgua = true;
        }
        else if(x==0)
        {
            if (!getIfBarcoByPos(x, y))
                if (!getIfBarcoByPos(x + 1, y))
                    if (!getIfBarcoByPos(x, y + 1))
                        if (!getIfBarcoByPos(x + 1, y + 1))
                            if (!getIfBarcoByPos(x, y - 1))
                                if (!getIfBarcoByPos(x + 1, y - 1))
                                    sinAgua = true;
        }
        else if(x==tTablero-1)
        {
            if (!getIfBarcoByPos(x, y))
                if (!getIfBarcoByPos(x, y + 1))
                    if (!getIfBarcoByPos(x - 1, y))
                        if (!getIfBarcoByPos(x, y - 1))
                            if (!getIfBarcoByPos(x - 1, y - 1))
                                if (!getIfBarcoByPos(x - 1, y + 1))
                                    sinAgua = true;
        }
        else if(y==0)
        {
            if (!getIfBarcoByPos(x, y))
                if (!getIfBarcoByPos(x + 1, y))
                    if (!getIfBarcoByPos(x, y + 1))
                        if (!getIfBarcoByPos(x + 1, y + 1))
                            if (!getIfBarcoByPos(x - 1, y))
                                if (!getIfBarcoByPos(x - 1, y + 1))
                                    sinAgua = true;
        }
        else if(y==tTablero-1)
        {
            if (!getIfBarcoByPos(x, y))
                if (!getIfBarcoByPos(x + 1, y))
                    if (!getIfBarcoByPos(x - 1, y))
                        if (!getIfBarcoByPos(x, y - 1))
                            if (!getIfBarcoByPos(x - 1, y - 1))
                                if (!getIfBarcoByPos(x + 1, y - 1))
                                    sinAgua = true;
        }
        else
        {
            if (!getIfBarcoByPos(x, y))
                if (!getIfBarcoByPos(x + 1, y))
                    if (!getIfBarcoByPos(x, y + 1))
                        if (!getIfBarcoByPos(x + 1, y + 1))
                            if (!getIfBarcoByPos(x - 1, y))
                                if (!getIfBarcoByPos(x, y - 1))
                                    if (!getIfBarcoByPos(x - 1, y - 1))
                                        if (!getIfBarcoByPos(x + 1, y - 1))
                                            if (!getIfBarcoByPos(x - 1, y + 1))
                                                sinAgua = true;
        }
        return sinAgua;
    }

    /*
     * Da un numero aleatorio entre MAX y MIN
     * Max no incluido!!!
     */
    private static int getRandomInteger(int max, int min)
    {
        return ((int)(Math.random()*(max-min)))+min;
    }

    /*
    * Cambia la flag de cambio a true
     */
    public void setChanged()
    {
        changed=true;
    }

    /*
    * Notifica de los cambios a la vista
     */
    public void notifyObservers(Object g)
    {
        if (changed)
        {
            Juego.getMiJuego().update(this, g);
        }
        changed = false;
    }
}