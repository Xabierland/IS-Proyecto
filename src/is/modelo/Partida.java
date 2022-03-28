package is.modelo;

import javax.swing.*;

public class Partida
{
    private static Partida miPartida=null;

    //Variables Globales
    private int direccion =0;
    private int tipoBarco =0;
    private int tipoArma =0;

    private boolean disparando;
    private boolean Jturno;

    //CONSTRUCTORA
    private Partida()
    {
        disparando =false;
        Jturno=true;
    }

    //GETTERS - SETTERS
    public static Partida getMiPartida()
    {
        if(miPartida==null)
        {
            miPartida=new Partida();
        }
        return miPartida;
    }

    public int getDireccion() {
        return direccion;
    }

    public void setDireccion(int direccion) {
        this.direccion = direccion;
    }

    public void setTipoArma(int tipoArma) {
        this.tipoArma = tipoArma;
    }

    public void setTipoBarco(int tipoBarco) {
        this.tipoBarco = tipoBarco;
    }

    public void setDisparando(boolean disparando) {
        this.disparando = disparando;
    }

    public Tablero getTablero(int pTablero)
    {
        Tablero tab=null;
        if(pTablero==0)
        {
            tab=Tablero_Jugador.getTableroJugador();
        }
        else
        {
            tab=Tablero_IA.getTableroIA();
        }
        return tab;
    }

    //FUNCIONES
    public void jugar(int pJugador, int pX, int pY)
    {
        if(!disparando)     //ESTAMOS COLOCANDO LOS BARCOS
        {
            if(pJugador==0) //ES UN CLICK EN EL TABLERO JUGADOR
            {
                getTablero(0).addBarco(direccion,tipoBarco,pX,pY,false);
                if(getTablero(0).getIfPosibleIniciarJuego())
                {
                    getTablero(1).addBarcoAutomatico(true);
                    disparando=true;
                }
            }
            else            //ES UN CLICK EN EL TABLERO IA
            {
                System.out.println("DEBES COLOCAR LOS BARCOS EN TU TABLERO");
            }
        }
        else                //LOS BARCOS ESTAN COLOCADOS Y SE ESTA ATACANDO
        {
            if(pJugador==0) //ES UN CLICK EN EL TABLERO JUGADOR
            {
                if(Jturno)  //TURNO DEL JUGADOR
                {
                    if(tipoArma==3 || tipoArma==4) //ESCUDO & REPARAR
                    {
                        defenderJugador(pX, pY);
                    }
                }
                else        //TURNO DE LA IA
                {
                    System.out.println("Espera a tu turno");
                }
            }
            else            //ES UN CLICK EN EL TABLERO IA
            {
                if(Jturno)  //TURNO DEL JUGADOR
                {
                    if(tipoArma!=3 && tipoArma!=4) //ESCUDO & REPARAR
                    {
                        ataqueJugador(pX, pY);
                    }
                }
                else        //TURNO DE LA IA
                {
                    System.out.println("Espera a tu turno");
                }
            }
        }
    }



    private void ataqueJugador(int pX, int pY)
    {
        if(getTablero(0).getArmamento().existeMunicion(tipoArma))
        {
            if(getTablero(0).armamento.usarArma(tipoArma, 1, pX, pY,false)) {
                if (getTablero(1).getIfEndGame()) {
                    JFrame winMess = new JFrame();
                    JOptionPane.showMessageDialog(winMess, "EL JUGADOR GANA");
                    System.exit(0);
                }
                Jturno = false;
                ataqueIA();
            }
        }
    }

    private void defenderJugador(int pX, int pY)
    {
        if(getTablero(0).getIfBarcoByPos(pX,pY,false)) {
            if (getTablero(0).getArmamento().existeMunicion(tipoArma)) {
                if (getTablero(0).armamento.usarArma(tipoArma, 0, pX, pY,false)) {
                    Jturno = false;
                    ataqueIA();
                }
            }
        }
    }

    private void ataqueIA() {
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
                    if(getTablero(1).getArmamento().usarArma(pArma,0,c.getX(),c.getY(),true))
                    {
                        atacado=true;
                    }
                    break;
                }
                case 6: //MISIL 10%
                {
                    pArma = 1;
                    if (getTablero(1).getArmamento().existeMunicion(pArma))
                    {
                        c=IAgetCoordenadas(cualCoor);
                        if(getTablero(1).getArmamento().usarArma(pArma,0,c.getX(),c.getY(),true))
                        {
                            atacado=true;
                        }
                    }
                    else
                    {
                        if (!Shop.getTienda().comprarMisil(1))
                        {
                            tipoAtaque = getRandomInteger(10, 0);
                        }
                    }
                    break;
                }
                case 7: //RADAR 10%
                {
                    pArma = 2;
                    if (getTablero(1).getArmamento().existeMunicion(pArma))
                    {
                        c=IAgetCoordenadas(1);
                        if(getTablero(1).getArmamento().usarArma(pArma,0,c.getX(),c.getY(),true))
                        {
                            atacado=true;
                        }
                    }
                    else
                    {
                        if (!Shop.getTienda().comprarRadar(1))
                        {
                            tipoAtaque = getRandomInteger(10, 0);
                        }
                    }
                    break;
                }
                case 8: //ESCUDO 10%
                {
                    pArma = 3;
                    if (getTablero(1).getArmamento().existeMunicion(pArma))
                    {
                        c=IAgetCoordenadas(1);
                        if(getTablero(1).getIfBarcoByPos(c.getX(),c.getY(),false)) {
                            if (getTablero(1).getArmamento().usarArma(pArma, 1, c.getX(), c.getY(),true)) {
                                atacado = true;
                            }
                        }
                    }
                    else
                    {
                        if (!Shop.getTienda().comprarEscudo(1))
                        {
                            tipoAtaque = getRandomInteger(10, 0);
                        }
                    }
                    break;
                }
                case 9: //REPARACION 10%
                {
                    pArma = 4;
                    if (getTablero(1).getArmamento().existeMunicion(pArma))
                    {
                        c=IAgetCoordenadas(1);
                        if(getTablero(1).getIfBarcoByPos(c.getX(),c.getY(),false)) {
                            if (getTablero(1).getArmamento().usarArma(pArma, 1, c.getX(), c.getY(),true)) {
                                atacado = true;
                            }
                        }
                    }
                    else
                    {
                        if (!Shop.getTienda().comprarReparacion(1))
                        {
                            tipoAtaque = getRandomInteger(10, 0);
                        }
                    }
                    break;
                }
            }
        }
        if (getTablero(0).getIfEndGame()) {
            JFrame winMess = new JFrame();
            JOptionPane.showMessageDialog(winMess, "LA IA GANA");
            System.exit(0);
        }
        Jturno = true;
    }

    private Coordenada IAgetCoordenadas(int factor)
    {
        Coordenada c;
        switch (factor)
        {
            case 0:
            {
                c=Tablero_Jugador.getTableroJugador().getCoordenadasDeUnBarco();
                break;
            }
            default:
            {
                c=new Coordenada(getRandomInteger(Variables.getMisVariables().getTamanoTablero(), 0),getRandomInteger(Variables.getMisVariables().getTamanoTablero(), 0));
                break;
            }
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
}
