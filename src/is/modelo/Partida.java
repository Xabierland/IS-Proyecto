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

    //FUNCIONES
    public void jugar(int pJugador, int pX, int pY)
    {
        if(!disparando)     //ESTAMOS COLOCANDO LOS BARCOS
        {
            if(pJugador==0) //ES UN CLICK EN EL TABLERO JUGADOR
            {
                Tablero_Jugador.getTableroJugador().addBarco(direccion,tipoBarco,pX,pY,false);
                if(Tablero_Jugador.getTableroJugador().getIfPosibleIniciarJuego())
                {
                    Tablero_IA.getTableroIA().addBarcoAutomatico(true);
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
                        //defenderJugador(pX, pY);
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
                    if(tipoArma!=3 || tipoArma!=4) //ESCUDO & REPARAR
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
        System.out.println("ATAQUE DEL JUGADOR \n");

        if(Tablero_IA.getTableroIA().sePuedeAtacar(tipoArma,pX,pY))
        {
            Tablero_IA.getTableroIA().atacar(tipoArma,pX,pY,false);
            if(Tablero_IA.getTableroIA().getIfEndGame())
            {
                JFrame winMess = new JFrame();
                JOptionPane.showMessageDialog(winMess, "EL JUGADOR GANA");
                System.exit(0);
            }
            Jturno=false;
            ataqueIA();
        }
        else
        {
            System.out.printf("No se puede ataque en x:%d y:%d\n",pX,pY);
        }


    }

    private void ataqueIA()
    {
        System.out.println("ATAQUE DE LA IA \n");


        if(Tablero_IA.getTableroIA().getIfEndGame())
        {
            JFrame winMess = new JFrame();
            JOptionPane.showMessageDialog(winMess, "LA IA GANA");
            System.exit(0);
        }
        Jturno=true;
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
