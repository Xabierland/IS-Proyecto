package is.modelo;

import is.vista.Juego;
import is.vista.Tienda;

import java.util.Observable;

public class Shop extends Observable {
    private static Shop miShop =null;
    private Tablero Jugador=Tablero_Jugador.getTableroJugador();
    private Tablero IA=Tablero_IA.getTableroIA();
    private Variables var=Variables.getMisVariables();
    private boolean changed=false;

    private Shop(){}

    public static Shop getTienda()
    {
        if(miShop ==null)
        {
            miShop =new Shop();
        }
        return miShop;
    }

    private Tablero getTablero(int pTablero)
    {
        if(pTablero==0)
        {
            return Jugador;
        }
        else
        {
            return IA;
        }
    }

    public boolean comprarMisil(int pTablero)
    {
        boolean exito=true;
        if(getTablero(pTablero).getDinero() >= var.getPrecioMisil())
        {
            getTablero(pTablero).setDinero(getTablero(pTablero).getDinero()-var.getPrecioMisil());
            getTablero(pTablero).getArmamento().addArma(1, true,false);
        }
        else
        {
            if(pTablero==0) {
                dineroInsuficiente();
            }
            exito=false;
        }
        return exito;
    }

    public boolean comprarRadar(int pTablero)
    {
        boolean exito=true;
        if(getTablero(pTablero).getDinero() >= var.getPrecioRadar())
        {
            getTablero(pTablero).setDinero(getTablero(pTablero).getDinero()-var.getPrecioRadar());
            getTablero(pTablero).getArmamento().addArma(2, true,false);
        }
        else
        {
            if(pTablero==0) {
                dineroInsuficiente();
            }
            exito=false;
        }
        return exito;
    }

    public boolean comprarEscudo(int pTablero)
    {
        boolean exito=true;
        if(getTablero(pTablero).getDinero() >= var.getPrecioEscudo())
        {
            getTablero(pTablero).setDinero(getTablero(pTablero).getDinero()-var.getPrecioEscudo());
            getTablero(pTablero).getArmamento().addArma(3, true,false);
        }
        else
        {
            if(pTablero==0) {
                dineroInsuficiente();
            }
            exito=false;
        }
        return exito;
    }

    public boolean comprarReparacion(int pTablero)
    {
        boolean exito=true;
        if(getTablero(pTablero).getDinero() >= var.getPrecioReparacion())
        {
            getTablero(pTablero).setDinero(getTablero(pTablero).getDinero()-var.getPrecioReparacion());
            getTablero(pTablero).getArmamento().addArma(4, true,false);
        }
        else
        {
            if(pTablero==0) {
                dineroInsuficiente();
            }
            exito=false;
        }
        return exito;
    }

    private void dineroInsuficiente()
    {
        try {
            setChanged();
            Object[] lista = new Object[3];
            lista[0] = "TIENDA";
            lista[1] = Tienda.getLbl_compra();
            lista[2] = "No hay dinero suficiente";
            notifyObservers(lista);
        }catch (Exception ignore){}
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
        if (changed == true)
        {
            Tienda.getTienda().update(this, g);
        }
        changed = false;
    }
}
