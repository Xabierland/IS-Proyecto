package is.modelo;

import is.vista.Juego;
import is.vista.Tienda;

import java.util.ArrayList;
import java.util.Observable;

public class Armamento extends Observable {
    private ArrayList<Arma> armamento;
    private boolean changeJuego =false;
    private boolean changeTienda =false;

    public Armamento()
    {
        armamento=new ArrayList<>();
    }

    public void addArma(int tipoArma, boolean finita, boolean ia)
    {
        Arma nArma=ArmaFactory.getBarcosFactory().hacerArma(tipoArma,finita);
        this.armamento.add(nArma);
        System.out.print("Se ha añadido el arma\n");

        //ACTUALIZAR BOTONES DE ARMA Y TIENDA
        if(!ia && tipoArma!=0) {
            actualizarArmamento(tipoArma);
            actualizarTienda(tipoArma);
        }
    }

    public boolean usarArma(int tipoArma, int pTablero, int x, int y, boolean ia)
    {
        Arma nArma=getArma(tipoArma);

        boolean exito = nArma.usar(pTablero,x,y);
        if(exito)
        {
            if(!ia) {
                actualizarArmamento(tipoArma);
                Sound.getMiSound().bomSound();
            }
        }
        return exito;
    }

    public void deleteArma(Arma pArma)
    {
        if(pArma.finita) {
            armamento.remove(pArma);
        }
    }

    public Arma getArma(int tipoArma)
    {
        Arma nArma=null;
        for(Arma a : armamento)
        {
            if(a.getTipoArma()==tipoArma)
            {
                nArma=a;
                deleteArma(a);
                break;
            }
        }
        return nArma;
    }

    public boolean existeMunicion(int tipoArma)
    {
        boolean existe=false;
        for(Arma a : armamento)
        {
            if(a.getTipoArma()==tipoArma)
            {
                existe=true;
                break;
            }
        }
        return existe;
    }

    private int numDeUnArma(int tipoArma)
    {
        int i=0;
        for(Arma a: armamento)
        {
            if(a.getTipoArma()==tipoArma)
            {
                i++;
            }
        }
        return i;
    }

    public void actualizarArmamento(int tipoArma)
    {
        switch (tipoArma)
        {
            case 1:
            {
                if(numDeUnArma(tipoArma) == 0)
                {
                    setChangedJuego();
                    Object[] lista= new Object[5];
                    lista[0]="ARMA";
                    lista[1]=Juego.getBtn_misil();
                    lista[2]=false;
                    lista[3]="Misil";
                    lista[4]=numDeUnArma(tipoArma);
                    notifyJuego(lista);
                    Partida.getMiPartida().setTipoArma(0);
                }
                else
                {
                    setChangedJuego();
                    Object[] lista= new Object[5];
                    lista[0]="ARMA";
                    lista[1]=Juego.getBtn_misil();
                    lista[2]=true;
                    lista[3]="Misil";
                    lista[4]=numDeUnArma(tipoArma);
                    notifyJuego(lista);

                }
                break;
            }
            case 2:
            {
                if(numDeUnArma(tipoArma) == 0)
                {
                    setChangedJuego();
                    Object[] lista= new Object[5];
                    lista[0]="ARMA";
                    lista[1]=Juego.getBtn_radar();
                    lista[2]=false;
                    lista[3]="Radar";
                    lista[4]=numDeUnArma(tipoArma);
                    notifyJuego(lista);
                    Partida.getMiPartida().setTipoArma(0);
                }
                else
                {
                    setChangedJuego();
                    Object[] lista= new Object[5];
                    lista[0]="ARMA";
                    lista[1]=Juego.getBtn_radar();
                    lista[2]=true;
                    lista[3]="Radar";
                    lista[4]=numDeUnArma(tipoArma);
                    notifyJuego(lista);
                }
                break;
            }
            case 3:
            {
                if(numDeUnArma(tipoArma) == 0)
                {
                    setChangedJuego();
                    Object[] lista= new Object[5];
                    lista[0]="ARMA";
                    lista[1]=Juego.getBtn_escudo();
                    lista[2]=false;
                    lista[3]="Escudo";
                    lista[4]=numDeUnArma(tipoArma);
                    notifyJuego(lista);
                    Partida.getMiPartida().setTipoArma(0);
                }
                else
                {
                    setChangedJuego();
                    Object[] lista= new Object[5];
                    lista[0]="ARMA";
                    lista[1]=Juego.getBtn_escudo();
                    lista[2]=true;
                    lista[3]="Escudo";
                    lista[4]=numDeUnArma(tipoArma);
                    notifyJuego(lista);
                }
                break;
            }
            case 4:
            {
                if(numDeUnArma(tipoArma) == 0)
                {
                    setChangedJuego();
                    Object[] lista= new Object[5];
                    lista[0]="ARMA";
                    lista[1]=Juego.getBtn_reparacion();
                    lista[2]=false;
                    lista[3]="Reparacion";
                    lista[4]=numDeUnArma(tipoArma);
                    notifyJuego(lista);
                    Partida.getMiPartida().setTipoArma(0);
                }
                else
                {
                    setChangedJuego();
                    Object[] lista= new Object[5];
                    lista[0]="ARMA";
                    lista[1]=Juego.getBtn_reparacion();
                    lista[2]=true;
                    lista[3]="Reparacion";
                    lista[4]=numDeUnArma(tipoArma);
                    notifyJuego(lista);
                }
                break;
            }
        }
    }

    public void actualizarTienda(int tipoArma)
    {
        setChangedJuego();
        Object[] lista=new Object[3];
        lista[0]="DINERO";
        lista[1]=Juego.getLblDinero();
        lista[2]=ListaJugadores.getMiListaJugadores().getJugador(0).getDinero();
        notifyJuego(lista);
        switch (tipoArma)
        {
            case 1:
            {
                setChangeTienda();
                Object[] lista1 = new Object[3];
                lista1[0]="TIENDA";
                lista1[1]=Tienda.getLbl_compra();
                lista1[2]="Has comprado un misil";
                notifyTienda(lista1);
                break;
            }
            case 2:
            {
                setChangeTienda();
                Object[] lista1 = new Object[3];
                lista1[0]="TIENDA";
                lista1[1]=Tienda.getLbl_compra();
                lista1[2]="Has comprado un radar";
                notifyTienda(lista1);
                break;
            }
            case 3:
            {
                setChangeTienda();
                Object[] lista1 = new Object[3];
                lista1[0]="TIENDA";
                lista1[1]=Tienda.getLbl_compra();
                lista1[2]="Has comprado un escudo";
                notifyTienda(lista1);
                break;
            }
            case 4:
            {
                setChangeTienda();
                Object[] lista1 = new Object[3];
                lista1[0]="TIENDA";
                lista1[1]=Tienda.getLbl_compra();
                lista1[2]="Has comprado una reparacion";
                notifyTienda(lista1);
                break;
            }
        }
    }

    public void setChangedJuego()
    {
        changeJuego =true;
    }

    public void setChangeTienda()
    {
        changeTienda=true;
    }

    public void notifyTienda(Object g)
    {
        if (changeTienda)
        {
            Tienda.getTienda().update(this, g);
            changeTienda = false;
        }
    }
    public void notifyJuego(Object g)
    {
        if (changeJuego)
        {
            Juego.getMiJuego().update(this, g);
            changeJuego = false;

        }
    }
}
