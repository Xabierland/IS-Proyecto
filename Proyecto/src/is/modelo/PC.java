package is.modelo;

import is.vista.Juego;

import java.awt.*;
import java.util.Observable;

public class PC extends Jugador
{
    private Coordenada coorRadar;

    private int numMaxUpdate=Variables.getMisVariables().getNumCambiosRadar()+1;

    //Constructor
    public PC(int pIndex) {
        super(pIndex);
        armamento.addArma(0,false,false);
        coorRadar=new Coordenada(0, 0);
    }

    //Getters & Setters
    public void setDinero(int pDinero)
    {
        dinero = pDinero;
        setChanged();
        Object[] lista = new Object[3];
        lista[0] = "DINERO";
        lista[1] = Juego.getLblDinero();
        lista[2] = dinero;
        notifyObservers(lista);
    }
    public Coordenada getCoorRadar()
    {
        return coorRadar;
    }
    public void setCoorRadar()
    {
        ListaJugadores.getMiListaJugadores().getJugador(1).getTablero().colorearRadar(coorRadar,new Color(0,153,204));
        coorRadar.setX(getRandomInteger(Variables.getMisVariables().getTamanoTablero(),0));
        coorRadar.setY(getRandomInteger(Variables.getMisVariables().getTamanoTablero(),0));
        ListaJugadores.getMiListaJugadores().getJugador(1).getTablero().colorearRadar(coorRadar, Color.green);

        setChanged();
        Object[] lista=new Object[3];
        lista[0]="UPRADAR";
        lista[1]=--numMaxUpdate;
        notifyObservers(lista);
    }
    // Ataque
    @Override
    public boolean ataque() {
        return false;
    }
    public boolean ataque(int pX, int pY, int tipoArma)
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
    @Override
    public boolean defensa() {
        return false;
    }
    public boolean defensa(int pX, int pY, int tipoArma)
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
}
