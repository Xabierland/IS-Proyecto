package is.modelo;

import is.vista.Juego;

public class PC extends Jugador
{
    //Constructor
    public PC(int pIndex) {
        super(pIndex);
        armamento.addArma(0,false,false);
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

    @Override
    public boolean ataque() {
        return false;
    }

    // Ataque
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
