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

	private int JtotalBarcos=10;
	private int Jbarco=0;
	private int JFragata=4;
	private int JDestructor=3;
	private int JSubmarino=2;
	private int JPortavion=1;

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
	
	/*
	* True si estamos jugando
	* False si estamos colocando los barcos
	*/
	public void addCasillaJugador(JLabel casilla, int x, int y)
	{
		Tablero_Jugador.getTableroJugador().addCasilla(casilla,x,y);
	}

	public void addCasillaIA(JLabel casilla, int x, int y)
	{
		Tablero_IA.getTableroIA().addCasilla(casilla,x,y);
	}

	private void addBarcoJugador(int x, int y)
	{
		Tablero_Jugador.getTableroJugador().addBarco(pos_flecha, Jbarco, x, y,false);
	}

	public void addBarcoIA()
	{
		//PORTAVIONES
		Tablero_IA.getTableroIA().addBarco(getRandomInteger(3,0),1,getRandomInteger(10,0), getRandomInteger(10,0),true);
	}

	private void restarBarcos(int pBarco)
	{
		switch (pBarco)
		{
			case 1 :
			{
				JFragata--;
				if(JFragata <=0)
				{
					Vista.boton_fragata.setEnabled(false);
					Jbarco =0;
				}
				System.out.println("Fragata restante: "+ JFragata);
				break;
			}
			case 2 :
			{
				JDestructor--;
				if(JDestructor <=0)
				{
					Vista.boton_destructor.setEnabled(false);
					Jbarco =0;
				}
				System.out.println("Destructor restante: "+ JDestructor);
				break;
			}
			case 3 :
			{
				JSubmarino--;
				if(JSubmarino <=0)
				{
					Vista.boton_submarino.setEnabled(false);
					Jbarco =0;
				}
				System.out.println("Submarino restante: "+ JSubmarino);
				break;
			}
			case 4 :
			{
				JPortavion--;
				if(JPortavion <=0)
				{
					Vista.boton_portaaviones.setEnabled(false);
					Jbarco =0;
				}
				System.out.println("Portavion restante: "+ JPortavion);
				break;
			}
		}
		JtotalBarcos--;
		if(JtotalBarcos <=0) {
			jugando=true;
			addBarcoIA();
		}
	}

	/*
	* Se activa al pulsar un boton en la vista
	 */
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
			Jbarco =1;
		}
		if(e.getSource().equals(Vista.boton_destructor))
		{
			System.out.println("DESTRUCTOR SELECCIONADO");
			Jbarco =2;
		}
		if(e.getSource().equals(Vista.boton_portaaviones))
		{
			System.out.println("PORTAAVIONES SELECCIONADO");
			Jbarco =4;
		}
		if(e.getSource().equals(Vista.boton_submarino))
		{
			System.out.println("SUBMARINO SELECCIONADO");
			Jbarco =3;
		}
		if(e.getSource().equals(Vista.textField))
		{
			String cad=Vista.textField.getText();
			Vista.textField.setText(null);
			cheats(cad);
		}
	}

	/*
	* Se activa al hacer click en una casilla del tablero del Jugador
	 */
	public void casillaJugadorClick(JLabel casilla, int x, int y)
	{
		System.out.printf("\nPLAYER|x:%d|y:%d\n",x,y);
		if(!jugando) //COLOCANDO BARCOS
		{
			if(Jbarco !=0)
			{
				if(Tablero_Jugador.getTableroJugador().sePuedeColocar(pos_flecha, Jbarco,x,y)){
					addBarcoJugador(x,y);
					restarBarcos(Jbarco);
				}
				else
					System.out.println("NO SE PUEDE COLOR EN ESTA POSICION");
			}
			else
			{
				System.out.println("SELECCIONA UN BARCO");
			}
		}
		else
		{
			//ESCUDOS ?
		}
	}

	/*
	* SOLO SI YA HA EMPEZADO EL JUEGO
	* Se activa al hacer click en una casilla del tablero de la IA
	 */
	public void casillaIAClick(JLabel casilla, int x, int y)
	{
		System.out.printf("\nIA    |x:%d|y:%d\n",x,y);
		Tablero_IA.getTableroIA().getIfBarcoByPos(x,y,true);
		if(jugando)
		{

		}
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

	private static int getRandomInteger(int max, int min)
	{
		return ((int)(Math.random()*(max-min)))+min;
	}

	private void cheats(String cheatCode)
	{
		switch (cheatCode)
		{
			case "seeall" :
			{
				System.out.println("CheatCode: seeall");
				Tablero_IA.getTableroIA().mostrarTablero();
			}
		}
	}
}
