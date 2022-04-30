package is.modelo;

import java.util.ArrayList;
import java.util.Iterator;

public class ListaJugadores
{
    private ArrayList<Jugador> lista;
    private static ListaJugadores miListaJugadores=null;

    private ListaJugadores()
    {
        lista=new ArrayList<>();
    }

    public static ListaJugadores getMiListaJugadores()
    {
        if(miListaJugadores==null)
        {
            miListaJugadores=new ListaJugadores();
        }
        return miListaJugadores;
    }

    public void addJugador(Jugador pJugador)
    {
        lista.add(pJugador);
    }
    private Iterator<Jugador> getIterator()
    {
        return lista.iterator();
    }

    /*
    * Nos devuelve al jugador con el mismo indice que el pasado como parametro
    *
    * @param    numJugador  El numero de indice por el que buscar
    * @return               El jugador con el indice indicado
     */
    public Jugador getJugador(int numJugador)
    {
        Jugador unJugador=null;
        Iterator<Jugador> itr=getIterator();
        boolean enc=false;

        while(itr.hasNext() && !enc)
        {
            unJugador=itr.next();
            if(unJugador.getIndex()==numJugador)
            {
                enc=true;
            }
        }
        if(!enc)
        {
            unJugador=null;
        }
        return unJugador;
    }
}
