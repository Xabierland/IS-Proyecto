package is.modelo;

import javax.swing.*;

public class Partida
{
    private static Partida miPartida=null;

    //Variables Globales
    private ListaJugadores lj;
    private int direccion =0;
    private int tipoBarco =0;
    private int tipoArma =0;
    private boolean preparado;
    private boolean turno;



    //CONSTRUCTORA
    private Partida()
    {
        preparado =false;
        turno =true;
        lj=ListaJugadores.getMiListaJugadores();
        lj.addJugador(new PC(0));
        lj.addJugador(new NPC(1));
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

    public void setDireccion(int direccion) {
        this.direccion = direccion;
    }

    public void setTipoArma(int tipoArma) {
        this.tipoArma = tipoArma;
    }

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
                lj.getJugador(0).anadirBarco(direccion,tipoBarco,pX, pY);
                if(lj.getJugador(0).getTablero().getIfPosibleIniciarJuego())
                {
                    lj.getJugador(1).anadirBarcoAuto();
                    PC jugador = (PC) lj.getJugador(0);
                    jugador.setCoorRadar();
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
                        if(lj.getJugador(0).defensa(pX, pY, tipoArma)) //ATAQUE DEL JUGADOR
                        {
                            setTurno(false);
                            lj.getJugador(1).defensa();
                            if(lj.getJugador(0).getTablero().getIfEndGame())
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
                        if(tipoArma==2)
                        {
                            PC jugador = (PC) lj.getJugador(0);
                            Coordenada coor = jugador.getCoorRadar();
                            pX=coor.getX();
                            pY=coor.getY();
                            jugador.setCoorRadar();
                        }

                        if(lj.getJugador(0).ataque(pX, pY, tipoArma))
                        {
                            if(lj.getJugador(1).getTablero().getIfEndGame())
                            {
                                JFrame winMess = new JFrame();
                                JOptionPane.showMessageDialog(winMess, "EL JUGADOR GANA");
                                System.exit(0);
                            }
                            else
                            {
                                setTurno(false);
                                lj.getJugador(1).ataque();
                                if(lj.getJugador(0).getTablero().getIfEndGame())
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
