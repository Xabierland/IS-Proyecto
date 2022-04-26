package is.modelo;

import is.vista.Juego;

import javax.swing.*;
import java.util.Observable;

public class Jugador extends Observable {
    //Variables
    private int index;              //El numero del usuario
    private boolean ia;             //Indica si el jugador es controlado o no por el ordenador
    private Tablero tablero;        //Tablero del jugador
    private Flota flota;
    private Armamento armamento;    //Armamento del jugador
    private int dinero=Variables.getMisVariables().getDineroInicial();
    private boolean changed=false;
    //Constructor
    public Jugador(int pIndex, boolean pIa)
    {
        index=pIndex;
        ia=pIa;
        tablero=new Tablero();
        flota=new Flota();
        armamento=new Armamento();
        armamento.addArma(0,false,true);
    }
    //GETTERS & SETTERS
    public Tablero getTablero()
    {
        return tablero;
    }
    public Flota getFlota() {return flota;}
    public Armamento getArmamento() {return armamento;}
    public boolean getIfIa() {return ia;}


    //Metodos
    // BARCOS
    public void anadirBarco(int pX, int pY)
    {
        tablero.addBarco(Partida.getMiPartida().getDireccion(), Partida.getMiPartida().getTipoBarco(), pX, pY, ia, flota);
    }

    public void anadirBarcoAuto()
    {
        tablero.addBarcoAutomatico(ia, flota);
    }

    // ATAQUES
    public boolean ataqueJugador(int pX, int pY, int tipoArma)
    {
        boolean atacado=false;
        if(armamento.existeMunicion(tipoArma))
        {
            if(armamento.usarArma(tipoArma, 1, pX, pY,false)) {
                atacado=true;
            }
        }
        return atacado;
    }

    public boolean defenderJugador(int pX, int pY, int tipoArma)
    {
        boolean defendido=false;
        if(tablero.getIfBarcoByPos(pX,pY)) {
            if (armamento.existeMunicion(tipoArma)) {
                if (armamento.usarArma(tipoArma, 0, pX, pY,false)) {
                    defendido=true;
                }
            }
        }
        return defendido;
    }

    public void ataqueIA() {
        int pArma, tipoAtaque, cualCoor;
        Coordenada c;
        boolean atacado = false;
        tipoAtaque = getRandomInteger(10, 0);
        cualCoor = getRandomInteger(Variables.getMisVariables().getDificultadIA(), 0);
        while (!atacado)
        {
            switch (tipoAtaque)
            {
                default: //BOMBA 60%
                {
                    pArma = 0;
                    c=IAgetCoordenadas(cualCoor);
                    if(armamento.usarArma(pArma,0,c.getX(),c.getY(),true))
                    {
                        atacado=true;
                    }
                    break;
                }
                case 6: //MISIL 10%
                {
                    pArma = 1;
                    if (armamento.existeMunicion(pArma))
                    {
                        c=IAgetCoordenadas(cualCoor);
                        if(armamento.usarArma(pArma,0,c.getX(),c.getY(),true))
                        {
                            atacado=true;
                        }
                    }
                    else
                    {
                        if (!Shop.getTienda().comprarArma(1,1))
                        {
                            tipoAtaque = getRandomInteger(10, 0);
                        }
                    }
                    break;
                }
                case 7: //RADAR 10%
                {
                    pArma = 2;
                    if (armamento.existeMunicion(pArma))
                    {
                        c=IAgetCoordenadas(1);
                        if(armamento.usarArma(pArma,0,c.getX(),c.getY(),true))
                        {
                            atacado=true;
                        }
                    }
                    else
                    {
                        if (!Shop.getTienda().comprarArma(1,2))
                        {
                            tipoAtaque = getRandomInteger(10, 0);
                        }
                    }
                    break;
                }
                case 8: //ESCUDO 10%
                {
                    pArma = 3;
                    if (armamento.existeMunicion(pArma))
                    {
                        c=IAgetCoordenadas(1);
                        if(tablero.getIfBarcoByPos(c.getX(),c.getY())) {
                            if (armamento.usarArma(pArma, 1, c.getX(), c.getY(),true)) {
                                atacado = true;
                            }
                        }
                    }
                    else
                    {
                        if (!Shop.getTienda().comprarArma(1,3))
                        {
                            tipoAtaque = getRandomInteger(10, 0);
                        }
                    }
                    break;
                }
                case 9: //REPARACION 10%
                {
                    pArma = 4;
                    if (armamento.existeMunicion(pArma))
                    {
                        c=IAgetCoordenadas(1);
                        if(tablero.getIfBarcoByPos(c.getX(),c.getY())) {
                            if (armamento.usarArma(pArma, 1, c.getX(), c.getY(),true)) {
                                atacado = true;
                            }
                            else
                            {
                                tipoAtaque = getRandomInteger(10, 0);
                            }
                        }
                    }
                    else
                    {
                        if (!Shop.getTienda().comprarArma(1,4))
                        {
                            tipoAtaque = getRandomInteger(10, 0);
                        }
                    }
                    break;
                }
            }
        }
    }

    private Coordenada IAgetCoordenadas(int factor)
    {
        Coordenada c;
        if (factor == 0) {
            c = Partida.getMiPartida().getJugador(0).getTablero().getCoordenadasDeUnBarco();
        } else {
            c = new Coordenada(getRandomInteger(Variables.getMisVariables().getTamanoTablero(), 0), getRandomInteger(Variables.getMisVariables().getTamanoTablero(), 0));
        }
        return c;
    }

    /*
     * Da un numero aleatorio entre MAX y MIN
     * Max no incluido!!!
     */
    private static int getRandomInteger(int max, int min)
    {
        return ((int)(Math.random()*(max-min)))+min;
    }

    //Dinero
    public int getDinero()
    {
        return dinero;
    }

    public void setDinero(int pDinero)
    {
        dinero = pDinero;
        if (!ia) {
            setChanged();
            Object[] lista = new Object[3];
            lista[0] = "DINERO";
            lista[1] = Juego.getLblDinero();
            lista[2] = dinero;
            notifyObservers(lista);
        }
    }

    //Observer
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
