package is.modelo;

import is.vista.Tienda;

import java.util.Observable;

public class Shop extends Observable {
    private static Shop miShop =null;
    private Variables var=Variables.getMisVariables();
    private boolean changed=false;
    //Max compras
    int nmisil=Variables.getMisVariables().getnMaxMisil();
    int nradar=Variables.getMisVariables().getnMaxRadar();
    int nescudo=Variables.getMisVariables().getnMaxEscudo();
    int nreparacion =Variables.getMisVariables().getnMaxReparacion();

    private Shop(){}

    public static Shop getTienda()
    {
        if(miShop ==null)
        {
            miShop =new Shop();
        }
        return miShop;
    }

    public boolean comprarArma(int pJugador, int pArma)
    {
        Jugador j=Partida.getMiPartida().getJugador(pJugador);
        boolean exito=true;
        if(j.getDinero() >= var.getPrecioMisil())
        {
            j.setDinero(j.getDinero()-var.getPrecioMisil());
            j.getArmamento().addArma(pArma, true, j.getIfIa());
            reducirInventario(pArma);
        }
        else
        {
            if(!j.getIfIa()) {
                dineroInsuficiente();
            }
            exito=false;
        }
        return exito;
    }

    /*
    * Este metodo se encarga de reducir el numero de unidades disponibles de un arma y en caso de ser 0 desactivar la compra de dicha arma
     */
    private void reducirInventario(int pArma)
    {
        switch (pArma)
        {
            case 1:
            {
                nmisil--;
                Object[] list = new Object[3];
                list[1]=Tienda.getBtn_misil();
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
                break;
            }
            case 2:
            {
                nradar--;
                Object[] list = new Object[3];
                list[1]=Tienda.getBtn_radar();
                list[0]="reducir";
                list[2]="\u00A0\u00A0\u00A0Radar ("+this.nradar+")";
                notifyObservers(list);
                if(nradar==0){//desactivar compra
                    setChanged();
                    Object[] lista = new Object[2];
                    lista[1]=Tienda.getBtn_misil();
                    lista[0]="desactivar";
                    notifyObservers(lista);
                }
                break;

            }
            case 3:
            {
                nescudo--;
                Object[] list = new Object[3];
                list[1]=Tienda.getBtn_escudo();
                list[0]="reducir";
                list[2]="\u00A0\u00A0\u00A0Escudo ("+this.nescudo+")";
                notifyObservers(list);
                if(nescudo==0){//desactivar compra
                    setChanged();
                    Object[] lista = new Object[2];
                    lista[1]=Tienda.getBtn_misil();
                    lista[0]="desactivar";
                    notifyObservers(lista);
                }
                break;
            }
            case 4:
            {
                nreparacion--;
                Object[] list = new Object[3];
                list[1]=Tienda.getBtn_reparacion();
                list[0]="reducir";
                list[2]="\u00A0\u00A0\u00A0Reparacion ("+this.nreparacion+")";
                notifyObservers(list);
                if(nreparacion==0){//desactivar compra
                    setChanged();
                    Object[] lista = new Object[2];
                    lista[1]=Tienda.getBtn_misil();
                    lista[0]="desactivar";
                    notifyObservers(lista);
                }
                break;
            }
        }
    }

    /*
    * En caso de no tener el dinero necesario para comprar un arma se mostrara un mensaje para advertir al jugador
     */
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
        if (changed)
        {
            Tienda.getTienda().update(this, g);
        }
        changed = false;
    }
}
