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
            if(getTablero(0).armamento.usarArma(tipoArma, 1, pX, pY)) {
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
                if (getTablero(0).armamento.usarArma(tipoArma, 0, pX, pY)) {
                    Jturno = false;
                    ataqueIA();
                }
            }
        }
    }

    private void ataqueIA()
    {
        int pX,pY;
        boolean atacado=false;
        while(!atacado) {
            pX = getRandomInteger(Variables.getMisVariables().getTamanoTablero(), 0);
            pY = getRandomInteger(Variables.getMisVariables().getTamanoTablero(), 0);
            if (getTablero(1).getArmamento().existeMunicion(0)) {
                if (getTablero(1).armamento.usarArma(0, 0, pX, pY)) {
                    atacado=true;
                    if (getTablero(0).getIfEndGame()) {
                        JFrame winMess = new JFrame();
                        JOptionPane.showMessageDialog(winMess, "LA IA GANA");
                        System.exit(0);
                    }
                    Jturno = true;
                }
            }
        }
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
