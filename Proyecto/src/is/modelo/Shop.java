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

    public int getNArma(int pArma)
    {
        int i=0;
        switch (pArma)
        {
            case 1: i=nmisil;break;
            case 2: i=nradar;break;
            case 3: i=nescudo;break;
            case 4: i=nreparacion;break;
        }
        return i;
    }

    public boolean comprarArma(int pJugador, int pArma)
    {
        Jugador j=ListaJugadores.getMiListaJugadores().getJugador(pJugador);
        boolean exito=true;
        if(getNArma(pArma)>0)
            if(j.getDinero() >= getPrecio(pArma))
            {
                j.setDinero(j.getDinero()-getPrecio(pArma));
                reducirInventario(pArma);
                if(j instanceof PC) {
                    Sound.getMiSound().moneySound();
                    j.getArmamento().addArma(pArma, true, false);
                }
                else
                {
                    j.getArmamento().addArma(pArma, true, true);
                }
            }
            else
            {
                if(j instanceof PC) {
                    dineroInsuficiente();
                }
                exito=false;
            }
        else
        {
            System.out.println("No queda de esta arma");
            exito=false;
        }
        return exito;
    }

    private int getPrecio(int pArma)
    {
        int precio=0;
        switch (pArma) {
            case 1 : precio=var.getPrecioMisil();break;
            case 2 : precio=var.getPrecioRadar();break;
            case 3 : precio=var.getPrecioEscudo();break;
            case 4 : precio=var.getPrecioReparacion();break;
        }
        return precio;
    }

    /*
    * Este metodo se encarga de reducir el numero de unidades disponibles de un arma y en caso de ser 0 desactivar la compra de dicha arma
     */
    private void reducirInventario(int pArma)
    {
        switch (pArma) {
            case 1 :
            {
                nmisil--;
                setChanged();
                Object[] list = new Object[3];
                list[1] = Tienda.getLbl_misil();
                list[0] = "reducir";
                list[2] = "\u00A0\u00A0\u00A0Misil (" + this.nmisil + ")";
                notifyObservers(list);
                if (nmisil == 0) {//desactivar compra
                    setChanged();
                    Object[] lista = new Object[2];
                    lista[1] = Tienda.getBtn_misil();
                    lista[0] = "desactivar";
                    notifyObservers(lista);
                }
                break;
            }
            case 2 :
            {
                nradar--;
                setChanged();
                Object[] list = new Object[3];
                list[1] = Tienda.getLbl_radar();
                list[0] = "reducir";
                list[2] = "\u00A0\u00A0\u00A0Radar (" + this.nradar + ")";
                notifyObservers(list);
                if (nradar == 0) {//desactivar compra
                    setChanged();
                    Object[] lista = new Object[2];
                    lista[1] = Tienda.getBtn_radar();
                    lista[0] = "desactivar";
                    notifyObservers(lista);
                }
                break;
            }
            case 3:
            {
                nescudo--;
                setChanged();
                Object[] list = new Object[3];
                list[1] = Tienda.getLbl_escudo();
                list[0] = "reducir";
                list[2] = "\u00A0\u00A0\u00A0Escudo (" + this.nescudo + ")";
                notifyObservers(list);
                if (nescudo == 0) {//desactivar compra
                    setChanged();
                    Object[] lista = new Object[2];
                    lista[0] = "desactivar";
                    lista[1] = Tienda.getBtn_escudo();
                    notifyObservers(lista);
                }
                break;
            }
            case 4 :
            {
                nreparacion--;
                setChanged();
                Object[] list = new Object[3];
                list[0] = "reducir";
                list[1] = Tienda.getLblReparacion();
                list[2] = "\u00A0\u00A0\u00A0Reparacion (" + this.nreparacion + ")";
                notifyObservers(list);
                if (nreparacion == 0) {//desactivar compra
                    setChanged();
                    Object[] lista = new Object[2];
                    lista[1] = Tienda.getBtn_reparacion();
                    lista[0] = "desactivar";
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
    public void notifyObservers(Object g) {
        if (changed) {
            Tienda.getTienda().update(this, g);
        }
        changed = false;
    }
}
