package is.controlador;
import is.modelo.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import is.vista.Vista;

public class Controlador implements ActionListener
{
	private static Controlador controler =null;
	private int pos_flecha=0;
	private boolean jugando;
	private int arma=0;
	private int barco=0;

	private Controlador()
	{
		jugando=false;
	};
	
	public static Controlador getControlador()
	{
		if(controler==null)
		{
			controler = new Controlador();
		}
		return controler;
	}
	
	//True si estamos jugando
	//False si estamos colocando los barcos
	public void addCasillaJugador(JLabel casilla, int x, int y)
	{
		Tablero_Jugador.getTableroJugador().addCasilla(casilla,x,y);
	}

	private void addBarco(int x, int y)
	{
		if(barco!=0) {
			if(Tablero_Jugador.getTableroJugador().sePuedeColocar(pos_flecha,barco,x,y))
				Tablero_Jugador.getTableroJugador().addBarco(pos_flecha, barco, x, y);
			//jugando=true;
		}
		else
			System.out.println("SELECCIONA UN BARCO");
	}

	public boolean getIfBarcoByPos(int x, int y){
		return Tablero_Jugador.getTableroJugador().getIfBarcoByPos(x,y);
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource().equals(Vista.Bomba))
		{
			System.out.println("BOMBA SELECCIONADA");
			arma=0;
		}
		if(e.getSource().equals(Vista.Misil))
		{
			System.out.println("MISIL SELECCIONADA");
			arma=1;
		}
		if(e.getSource().equals(Vista.Radar))
		{
			System.out.println("RADAR SELECCIONADA");
			arma=2;
		}
		if(e.getSource().equals(Vista.Escudo))
		{
			System.out.println("ESCUDO SELECCIONADA");
			arma=3;
		}
		if(e.getSource().equals(Vista.boton_fragata))
		{
			System.out.println("FRAGATA SELECCIONADO");
			barco=1;
		}
		if(e.getSource().equals(Vista.boton_destructor))
		{
			System.out.println("DESTRUCTOR SELECCIONADO");
			barco=2;
		}
		if(e.getSource().equals(Vista.boton_portaaviones))
		{
			System.out.println("PORTAAVIONES SELECCIONADO");
			barco=4;
		}
		if(e.getSource().equals(Vista.boton_submarino))
		{
			System.out.println("SUBMARINO SELECCIONADO");
			barco=3;
		}
	}

	public void casillaJugadorClick(JLabel casilla, int x, int y)
	{
		System.out.printf("x:%d|y:%d\n",x,y);
		if(!jugando) //COLOCANDO BARCOS
		{
			if(barco!=0);
			{
				addBarco(x,y);
			}
		}
		else
		{
			//ESCUDOS ?
		}
	}

	public void casillaIAClick(JLabel casilla, int x, int y)
	{
		//JUGANDO
	}

	/*
	* Detecta cuando se ha echo click a la imagen de la flecha y la actualiza por la siguiente
	* por_fecha valores:
	* 0 - arr | 1 - der | 2 - abj | 3 - izq
	*/
	public void flechaClick()
	{
		pos_flecha++;
		if(pos_flecha>=4)
		{
			pos_flecha=0;
		}
		switch(pos_flecha)
		{
			case 0 : {
				Vista.flecha.setIcon(new ImageIcon(Vista.class.getResource("/resource/flecha_arr.png")));System.out.println("ARRIBA "+ pos_flecha);break;}
			case 1 : {
				Vista.flecha.setIcon(new ImageIcon(Vista.class.getResource("/resource/flecha_der.png")));System.out.println("DERECHA "+ pos_flecha);break;}
			case 2 : {
				Vista.flecha.setIcon(new ImageIcon(Vista.class.getResource("/resource/flecha_abj.png")));System.out.println("ABAJO "+ pos_flecha);break;}
			case 3 : {
				Vista.flecha.setIcon(new ImageIcon(Vista.class.getResource("/resource/flecha_izq.png")));System.out.println("IZQUIERDA "+ pos_flecha);break;}
			default : {
				Vista.flecha.setIcon(new ImageIcon(Vista.class.getResource("/resource/flecha_arr.png")));System.out.println("DEFAULT");break;}
		}
	}
}
