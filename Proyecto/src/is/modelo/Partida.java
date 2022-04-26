package is.modelo;

import javax.swing.*;

public class Partida
{
    private static Partida miPartida=null;

    //Variables Globales
    private int direccion =0;
    private int tipoBarco =0;
    private int tipoArma =0;

    private Jugador jugadores[] = new Jugador[2];
    private boolean preparado;
    private boolean turno;

    //CONSTRUCTORA
    private Partida()
    {
        preparado =false;
        turno =true;
        jugadores[0]=new Jugador(0,false);
        jugadores[1]=new Jugador(1,true);
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

    public Jugador getJugador(int pJugador) {return jugadores[pJugador];}

    public int getDireccion() {
        return direccion;
    }

    public void setDireccion(int direccion) {
        this.direccion = direccion;
    }

    public int getTipoArma() {return tipoArma;}

    public void setTipoArma(int tipoArma) {
        this.tipoArma = tipoArma;
    }

    public int getTipoBarco() {return tipoBarco;}

    public void setTipoBarco(int tipoBarco) {
        this.tipoBarco = tipoBarco;
    }

    public void setPreparado(boolean preparado) {
        this.preparado = preparado;
    }

    public void setTurno(boolean pTurno) {this.turno=pTurno;}
    //FUNCIONES
    public void jugar(int pJugador, int pX, int pY)
    {
        if(!preparado)     //ESTAMOS COLOCANDO LOS BARCOS
        {
            if(pJugador==0) //ES UN CLICK EN EL TABLERO JUGADOR
            {
                jugadores[0].anadirBarco(pX, pY);
                if(getJugador(0).getTablero().getIfPosibleIniciarJuego())
                {
                    jugadores[1].anadirBarcoAuto();
                    preparado =true;
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
                if(turno)  //TURNO DEL JUGADOR
                {
                    if(tipoArma==3 || tipoArma==4) //ESCUDO & REPARAR
                    {
                        if(jugadores[0].defenderJugador(pX, pY, tipoArma)) //ATAQUE DEL JUGADOR
                        {
                            setTurno(false);
                            jugadores[1].ataqueIA();
                            if(jugadores[0].getTablero().getIfEndGame())
                            {
                                JFrame winMess = new JFrame();
                                JOptionPane.showMessageDialog(winMess, "LA IA GANA");
                                System.exit(0);
                            }
                            else
                            {
                                setTurno(true);
                            }
                        }
                    }
                }
                else        //TURNO DE LA IA
                {
                    System.out.println("Espera a tu turno");
                }
            }
            else            //ES UN CLICK EN EL TABLERO IA
            {
                if(turno)  //TURNO DEL JUGADOR
                {
                    if(tipoArma!=3 && tipoArma!=4) //ESCUDO & REPARAR
                    {
                        if(jugadores[0].ataqueJugador(pX, pY, tipoArma))
                        {
                            if(jugadores[1].getTablero().getIfEndGame())
                            {
                                JFrame winMess = new JFrame();
                                JOptionPane.showMessageDialog(winMess, "EL JUGADOR GANA");
                                System.exit(0);
                            }
                            else
                            {
                                setTurno(false);
                                jugadores[1].ataqueIA();
                                if(jugadores[0].getTablero().getIfEndGame())
                                {
                                    JFrame winMess = new JFrame();
                                    JOptionPane.showMessageDialog(winMess, "LA IA GANA");
                                    System.exit(0);
                                }
                                else
                                {
                                    setTurno(true);
                                }
                            }
                        }
                    }
                }
                else        //TURNO DE LA IA
                {
                    System.out.println("Espera a tu turno");
                }
            }
        }
    }










}
