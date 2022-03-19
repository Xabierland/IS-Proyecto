package is.controlador;
import is.modelo.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.*;

import is.vista.Vista;

public class Controlador implements ActionListener
{
	private static Controlador controler =null;
	private int pos_flecha=0;
	private boolean jugando;

	private int arma=0;
	private int Jbarco=0;
	private boolean Jturno=true;//True - Jugador|False - IA

	private int JtotalBarcos=10;
	private int JFragata=4;
	private int JDestructor=3;
	private int JSubmarino=2;
	private int JPortavion=1;

	private int ItotalBarcos=10;
	private int IFragata=4;
	private int IDestructor=3;
	private int ISubmarino=2;
	private int IPortavion=1;

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

	public Tablero getTablero(int pTablero)
	{
		Tablero t=null;
		switch (pTablero)
		{
			case 0: //JUGADOR
			{
				t= Tablero_Jugador.getTableroJugador();
				break;
			}
			case 1: //IA
			{
				t= Tablero_IA.getTableroIA();
				break;
			}
			default:
			{
				System.out.println("ERROR en Controlador, getTablero()");
			}
		}
		return t;
	}

	public void addCasilla(int pTablero, JLabel casilla, int x, int y)
	{
		getTablero(pTablero).addCasilla(casilla,x,y);
	}

	private void addBarcoJugador(int x, int y)
	{
		Tablero_Jugador.getTableroJugador().addBarco(pos_flecha, Jbarco, x, y,false);
	}

	public void addBarcoIA()
	{
		int pDir,pX,pY;
		while(ItotalBarcos>0){
			while (IPortavion>0)
			{
				pDir=getRandomInteger(3,0);
				pX=getRandomInteger(9,0);
				pY=getRandomInteger(9,0);
				if(Tablero_IA.getTableroIA().sePuedeColocar(pDir,4,pX,pY)){
					Tablero_IA.getTableroIA().addBarco(pDir, 4, pX, pY, true);
					IPortavion--;
					ItotalBarcos--;
				}
			}
			while (ISubmarino>0)
			{
				pDir=getRandomInteger(3,0);
				pX=getRandomInteger(9,0);
				pY=getRandomInteger(9,0);
				if(Tablero_IA.getTableroIA().sePuedeColocar(pDir,3,pX,pY)){
					Tablero_IA.getTableroIA().addBarco(pDir, 3, pX, pY, true);
					ISubmarino--;
					ItotalBarcos--;
				}
			}
			while (IDestructor>0)
			{
				pDir=getRandomInteger(3,0);
				pX=getRandomInteger(9,0);
				pY=getRandomInteger(9,0);
				if(Tablero_IA.getTableroIA().sePuedeColocar(pDir,2,pX,pY)){
					Tablero_IA.getTableroIA().addBarco(pDir, 2, pX, pY, true);
					IDestructor--;
					ItotalBarcos--;
				}
			}
			while (IFragata>0)
			{
				pDir=getRandomInteger(3,0);
				pX=getRandomInteger(9,0);
				pY=getRandomInteger(9,0);
				if(Tablero_IA.getTableroIA().sePuedeColocar(pDir,1,pX,pY)){
					Tablero_IA.getTableroIA().addBarco(pDir, 1, pX, pY, true);
					IFragata--;
					ItotalBarcos--;
				}
			}
		}
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
				System.out.println("Fragata restante: "+ JFragata+"\n");
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
				System.out.println("Destructor restante: "+ JDestructor+"\n");
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
				System.out.println("Submarino restante: "+ JSubmarino+"\n");
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
				System.out.println("Portavion restante: "+ JPortavion+"\n");
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
			System.out.println("BOMBA SELECCIONADA\n");
			arma=0;
		}
		if(e.getSource().equals(Vista.Misil))
		{
			System.out.println("MISIL SELECCIONADA\n");
			arma=1;
		}
		if(e.getSource().equals(Vista.Radar))
		{
			System.out.println("RADAR SELECCIONADA\n");
			arma=2;
		}
		if(e.getSource().equals(Vista.Escudo))
		{
			System.out.println("ESCUDO SELECCIONADA\n");
			arma=3;
		}
		if(e.getSource().equals(Vista.boton_fragata))
		{
			System.out.println("FRAGATA SELECCIONADO\n");
			Jbarco =1;
		}
		if(e.getSource().equals(Vista.boton_destructor))
		{
			System.out.println("DESTRUCTOR SELECCIONADO\n");
			Jbarco =2;
		}
		if(e.getSource().equals(Vista.boton_portaaviones))
		{
			System.out.println("PORTAAVIONES SELECCIONADO\n");
			Jbarco =4;
		}
		if(e.getSource().equals(Vista.boton_submarino))
		{
			System.out.println("SUBMARINO SELECCIONADO\n");
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
		System.out.printf("PLAYER|x:%d|y:%d\n",x,y);
		Tablero_Jugador.getTableroJugador().getIfBarcoByPos(x,y,true);
		if(!jugando) //COLOCANDO BARCOS
		{
			if(Jbarco !=0)
			{
				if(Tablero_Jugador.getTableroJugador().sePuedeColocar(pos_flecha, Jbarco,x,y)){
					addBarcoJugador(x,y);
					restarBarcos(Jbarco);
				}
				else
					System.out.println("NO SE PUEDE COLOR EN ESTA POSICION\n");
			}
			else
			{
				System.out.println("SELECCIONA UN BARCO\n");
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
			if(Jturno) {
				getTablero(1).atacar(arma, x, y);
				if(getTablero(1).getIfEndGame())
				{
					JFrame winMess=new JFrame();
					JOptionPane.showMessageDialog(winMess, "EL JUGADOR GANA");
					System.exit(0);
				}
				Jturno=false;
			}
			getTablero(0).atacar(0,getRandomInteger(9,0),getRandomInteger(9,0));
			if(getTablero(0).getIfEndGame())
			{
				JFrame winMess=new JFrame();
				JOptionPane.showMessageDialog(winMess, "LA IA GANA");
			}
			Jturno=true;
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
			case "see_all" :
			{
				System.out.println("CheatCode: "+cheatCode);
				Tablero_IA.getTableroIA().mostrarTablero();
				break;
			}
		}

	}
}
