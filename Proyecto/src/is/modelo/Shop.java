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
    private int nmisil=5;
    private int nRadar=3;
    private int nEscudo=3;
    private int nReparacion=3;
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

    public boolean comprarMisil(int pTablero, boolean ia)
    {
        boolean exito=true;
        if(getTablero(pTablero).getDinero() >= var.getPrecioMisil())
        {
            this.nmisil--;
            getTablero(pTablero).setDinero(getTablero(pTablero).getDinero()-var.getPrecioMisil());
            getTablero(pTablero).getArmamento().addArma(1, true,ia);
            setChanged();
            //reducir numero de compras
            Object[] list = new Object[3];
            list[1]=Tienda.getLblMisil();
            list[0]="reducir";  
            list[2]="\u00A0\u00A0\u00A0Misil ("+this.nmisil+")";
            notifyObservers(list); 
            if(nmisil==0){//desactivar compra
                setChanged();
                Object[] lista = new Object[2];
                lista[1]=Tienda.getBtn_misil();
                lista[0]="desactivar";
                notifyObservers(lista);
            }
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

    public boolean comprarRadar(int pTablero, boolean ia)
    {
        boolean exito=true;
        if(getTablero(pTablero).getDinero() >= var.getPrecioRadar())
        {
            this.nRadar--;
            getTablero(pTablero).setDinero(getTablero(pTablero).getDinero()-var.getPrecioRadar());
            getTablero(pTablero).getArmamento().addArma(2, true,ia);
            setChanged();
            if(nRadar==0){
                Object[] lista = new Object[2];
                lista[1]=Tienda.getBtn_radar();
                lista[0]="desactivar";
                notifyObservers(lista);
            }
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

    public boolean comprarEscudo(int pTablero, boolean ia)
    {
        boolean exito=true;
        if(getTablero(pTablero).getDinero() >= var.getPrecioEscudo())
        {
            this.nEscudo--;
            getTablero(pTablero).setDinero(getTablero(pTablero).getDinero()-var.getPrecioEscudo());
            getTablero(pTablero).getArmamento().addArma(3, true,ia);
            setChanged();
            if(nEscudo==0){
                Object[] lista = new Object[2];
                lista[1]=Tienda.getBtn_escudo();
                lista[0]="desactivar";
                notifyObservers(lista);
            }
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

    public boolean comprarReparacion(int pTablero, boolean ia)
    {
        boolean exito=true;
        if(getTablero(pTablero).getDinero() >= var.getPrecioReparacion())
        {
            this.nReparacion--;
            getTablero(pTablero).setDinero(getTablero(pTablero).getDinero()-var.getPrecioReparacion());
            getTablero(pTablero).getArmamento().addArma(4, true,ia);
            setChanged();
            if(nReparacion==0){
                Object[] lista = new Object[2];
                lista[1]=Tienda.getBtn_reparacion();
                lista[0]="desactivar";
                notifyObservers(lista);
            }
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
    public int getNMisiles(){
        return this.nmisil;
    }
}
