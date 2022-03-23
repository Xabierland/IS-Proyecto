package is.controlador;
import is.modelo.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.Visibility;
import java.util.Locale;

import javax.swing.*;

import is.vista.Tienda;
import is.vista.Juego;

public class Controlador implements ActionListener
{
	private static Controlador controler =null;

	//Variables Globales
	private int pos_flecha=0;
	private boolean jugando;
	private int arma=0;
	private int Jbarco=0;
	private boolean Jturno=true;//True - Jugador|False - IA

	//Variables Jugador
	//Barcos
	private int JtotalBarcos=10;
	private int JFragata=4;
	private int JDestructor=3;
	private int JSubmarino=2;
	private int JPortavion=1;
	//Armas
	private int JDinero=3000;
	private int JMisil=0;
	private int JRadar=0;
	private int JEscudo=0;

	//Variables IA
	//Barcos
	private int ItotalBarcos=10;
	private int IFragata=4;
	private int IDestructor=3;
	private int ISubmarino=2;
	private int IPortavion=1;
	//Armas
	private int IDinero=3000;
	private int IMisil=0;
	private int IRadar=0;
	private int IEscudo=0;

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
	 * Se activa al pulsar un boton en la vista
	 */
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource().equals(Juego.getBtn_bomba()))
		{
			System.out.println("BOMBA SELECCIONADA\n");
			arma=0;
		}
		if(e.getSource().equals(Juego.getBtn_misil()))
		{
			System.out.println("MISIL SELECCIONADA\n");
			arma=1;
		}
		if(e.getSource().equals(Juego.getBtn_radar()))
		{
			System.out.println("RADAR SELECCIONADA\n");
			arma=2;
		}
		if(e.getSource().equals(Juego.getBtn_escudo()))
		{
			System.out.println("ESCUDO SELECCIONADA\n");
			arma=3;
		}
		if(e.getSource().equals(Juego.getBtn_fragata()))
		{
			System.out.println("FRAGATA SELECCIONADO\n");
			Jbarco =1;
		}
		if(e.getSource().equals(Juego.getBtn_destructor()))
		{
			System.out.println("DESTRUCTOR SELECCIONADO\n");
			Jbarco =2;
		}
		if(e.getSource().equals(Juego.getBtn_portavion()))
		{
			System.out.println("PORTAAVIONES SELECCIONADO\n");
			Jbarco =4;
		}
		if(e.getSource().equals(Juego.getBtn_submarino()))
		{
			System.out.println("SUBMARINO SELECCIONADO\n");
			Jbarco =3;
		}
		if(e.getSource().equals(Juego.getCheatConsole()))
		{
			String cad= Juego.getCheatConsole().getText();
			Juego.getCheatConsole().setText(null);
			cheats(cad);
		}
		if(e.getSource().equals(Juego.getBtn_tienda()))
		{
			Tienda tienda = Tienda.getTienda();
			tienda.setVisible(true);
		}
		if(e.getSource().equals(Tienda.getBtn_misil()))
		{
			if(JDinero >= 500)
			{
				JDinero=JDinero-500;
				actualizarDinero();
				JMisil++;
				actualizarArmasEstado();
				Tienda.getLbl_compra().setText("Has comprado un misil");
				System.out.println("Has comprado un misil");
			}
			else
			{
				Tienda.getLbl_compra().setText("No hay dinero suficiente");
				System.out.println("No hay dinero suficiente");
			}
		}
		if(e.getSource().equals(Tienda.getBtn_radar()))
		{
			if(JDinero >= 1000)
			{
				JDinero=JDinero-1000;
				actualizarDinero();
				JRadar++;
				actualizarArmasEstado();
				Tienda.getLbl_compra().setText("Has comprado un radar");
				System.out.println("Has comprado un radar");
			}
			else
			{
				Tienda.getLbl_compra().setText("No hay dinero suficiente");
				System.out.println("No hay dinero suficiente");
			}
		}
		if(e.getSource().equals(Tienda.getBtn_escudo()))
		{
			if(JDinero >= 1000)
			{
				JDinero=JDinero-1000;
				actualizarDinero();
				JEscudo++;
				actualizarArmasEstado();
				Tienda.getLbl_compra().setText("Has comprado un escudo");
				System.out.println("Has comprado un escudo");
			}
			else
			{
				Tienda.getLbl_compra().setText("No hay dinero suficiente");
				System.out.println("No hay dinero suficiente");
			}
		}
	}

	/*
	* 0 - Tablero Jugador
	* 1 - Tablero IA
	 */
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

	/*
	* Añade la casilla al tablero correcto
	 */
	public void addCasilla(int pTablero, JLabel casilla, int x, int y)
	{
		getTablero(pTablero).addCasilla(casilla,x,y);
	}

	/*
	* Añade los barcos de jugador en la posicion dada
	 */
	private void addBarcoJugador(int x, int y)
	{
		Tablero_Jugador.getTableroJugador().addBarco(pos_flecha, Jbarco, x, y,false);
		
	}

	/*
	* Añade todos los barcos automaticamente
	* 0 - Jugador; 1 - IA
	 */
	public void addBarcoRandom(boolean ia)
	{
		int player=0;
		if(ia)
		{
			player=1;
		}
		int pDir,pX,pY;
		while(ItotalBarcos>0){
			while (IPortavion>0)
			{
				pDir=getRandomInteger(3,0);
				pX=getRandomInteger(10,0);
				pY=getRandomInteger(10,0);
				if(getTablero(player).sePuedeColocar(pDir,4,pX,pY)){
					getTablero(player).addBarco(pDir, 4, pX, pY, ia);
					IPortavion--;
					ItotalBarcos--;
				}
			}
			while (ISubmarino>0)
			{
				pDir=getRandomInteger(3,0);
				pX=getRandomInteger(10,0);
				pY=getRandomInteger(10,0);
				if(getTablero(player).sePuedeColocar(pDir,3,pX,pY)){
					getTablero(player).addBarco(pDir, 3, pX, pY, ia);
					ISubmarino--;
					ItotalBarcos--;
				}
			}
			while (IDestructor>0)
			{
				pDir=getRandomInteger(3,0);
				pX=getRandomInteger(10,0);
				pY=getRandomInteger(10,0);
				if(getTablero(player).sePuedeColocar(pDir,2,pX,pY)){
					getTablero(player).addBarco(pDir, 2, pX, pY, ia);
					IDestructor--;
					ItotalBarcos--;
				}
			}
			while (IFragata>0)
			{
				pDir=getRandomInteger(3,0);
				pX=getRandomInteger(10,0);
				pY=getRandomInteger(10,0);
				if(getTablero(player).sePuedeColocar(pDir,1,pX,pY)){
					getTablero(player).addBarco(pDir, 1, pX, pY, ia);
					IFragata--;
					ItotalBarcos--;
				}
			}
			if(!ia)
			{
				ItotalBarcos=10;
				IFragata=4;
				IDestructor=3;
				ISubmarino=2;
				IPortavion=1;

				Juego.getDisplayState().setText("Bombardea a tu enemigo");
				jugando=true;
				addBarcoRandom(true);
			}
		}
	}

	/*
	* Se activa al hacer click en una casilla del tablero del Jugador
	 */
	public void casillaJugadorClick(int x, int y)
	{
		System.out.printf("PLAYER|x:%d|y:%d\n",x,y);
		Tablero_Jugador.getTableroJugador().getIfBarcoByPos(x,y,true);
		if(!jugando) //COLOCANDO BARCOS
		{
			if(Jbarco!=0)
			{
				if(Tablero_Jugador.getTableroJugador().sePuedeColocar(pos_flecha, Jbarco,x,y)){
					addBarcoJugador(x,y);
					actualizarBarco(Jbarco);
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
			if(Jturno)
			{
				if(arma==3 && getTablero(0).getIfBarcoByPos(x,y,false) && getTablero(0).sePuedeAtacar(0,x,y)) {
					getTablero(0).atacar(arma,x,y,false);
					actualizarArmas(arma);
					Jturno=false;
				}
				ataqueIA();
			}
		}
	}

	/*
	* SOLO SI YA HA EMPEZADO EL JUEGO
	* Se activa al hacer click en una casilla del tablero de la IA
	 */
	public void casillaIAClick(int x, int y)
	{
		System.out.printf("\nIA    |x:%d|y:%d\n",x,y);
		Tablero_IA.getTableroIA().getIfBarcoByPos(x,y,true);
		if(jugando)
		{
			ataqueJugador(x,y);
			ataqueIA();
		}
	}

	private void ataqueJugador(int x, int y)
	{
		if(Jturno)
		{
			if(getTablero(1).sePuedeAtacar(arma,x,y))
			{
				getTablero(1).atacar(arma, x, y,false);
				actualizarArmas(arma);
				if(getTablero(1).getIfBarcoByPos(x,y,false)) {
					Juego.getDisplayState().setText("TOCADO");
					if (getTablero(1).barcoHundido(x, y)) {
						JDinero += 500;
						actualizarDinero();
						Juego.getDisplayState().setText("TOCADO Y HUNDIDO");
					}
				}
				else
				{
					Juego.getDisplayState().setText("AGUA");
				}
				if (getTablero(1).getIfEndGame()) {
					JFrame winMess = new JFrame();
					JOptionPane.showMessageDialog(winMess, "EL JUGADOR GANA");
					System.exit(0);
				}
				Jturno = false;
			}
		}
	}

	private void ataqueIA()
	{
		if(!Jturno) {
			boolean IAtacando=true;
			while(IAtacando)
			{
				int pArma = 0;
				int pX = getRandomInteger(10, 0);
				int pY = getRandomInteger(10, 0);
				if(pArma!=3)
				{
					if (getTablero(0).sePuedeAtacar(pArma, pX, pY)) {
						getTablero(0).atacar(pArma, pX, pY,true);
						IAtacando = false;
						if (getTablero(0).getIfEndGame()) {
							JFrame winMess = new JFrame();
							JOptionPane.showMessageDialog(winMess, "LA IA GANA");
							System.exit(0);
						}
						Jturno = true;
					}
				}
				else
				{
					if(getTablero(1).getIfBarcoByPos(pX,pY,false)) {
						getTablero(1).atacar(pArma, pX, pY,true);
						IAtacando = false;
					}
				}
			}
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
				Juego.getFlecha().setIcon(new ImageIcon(Juego.class.getResource("/resource/flecha_arr.png")));System.out.println("ARRIBA "+ pos_flecha);break;}
			case 1 : {
				Juego.getFlecha().setIcon(new ImageIcon(Juego.class.getResource("/resource/flecha_der.png")));System.out.println("DERECHA "+ pos_flecha);break;}
			case 2 : {
				Juego.getFlecha().setIcon(new ImageIcon(Juego.class.getResource("/resource/flecha_abj.png")));System.out.println("ABAJO "+ pos_flecha);break;}
			case 3 : {
				Juego.getFlecha().setIcon(new ImageIcon(Juego.class.getResource("/resource/flecha_izq.png")));System.out.println("IZQUIERDA "+ pos_flecha);break;}
			default : {
				Juego.getFlecha().setIcon(new ImageIcon(Juego.class.getResource("/resource/flecha_arr.png")));System.out.println("DEFAULT");break;}
		}
	}

	public void actualizarDinero()
	{
		Juego.getLblDinero().setText("GOLD: "+JDinero);
	}

	/*
	 * Va restando el numero de barcos hasta que queden 0 y empieze el juego
	 */
	private void actualizarBarco(int pBarco)
	{
		switch (pBarco)
		{
			case 1 :
			{
				JFragata--;
				if(JFragata <=0)
				{
					Juego.getBtn_fragata().setEnabled(false);
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
					Juego.getBtn_destructor().setEnabled(false);
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
					Juego.getBtn_submarino().setEnabled(false);
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
					Juego.getBtn_portavion().setEnabled(false);
					Jbarco =0;
				}
				System.out.println("Portavion restante: "+ JPortavion+"\n");
				break;
			}
		}
		JtotalBarcos--;
		if(JtotalBarcos <=0) {
			Juego.getDisplayState().setText("Bombardea a tu enemigo");
			jugando=true;
			addBarcoRandom(true);
		}
	}

	private void actualizarArmas(int arma)
	{
		switch (arma)
		{
			case 1: JMisil--;break;
			case 2: JRadar--;break;
			case 3: JEscudo--;break;
		}
		actualizarArmasEstado();
	}

	private void actualizarArmasEstado()
	{
		if(JMisil==0)
		{
			Juego.getBtn_misil().setEnabled(false);
			arma=0;
		}
		else
		{
			Juego.getBtn_misil().setEnabled(true);
		}
		if(JRadar==0)
		{
			Juego.getBtn_radar().setEnabled(false);
			arma=0;
		}
		else {
			Juego.getBtn_radar().setEnabled(true);
		}
		if(JEscudo==0)
		{
			Juego.getBtn_escudo().setEnabled(false);
			arma=0;
		}
		else
		{
			Juego.getBtn_escudo().setEnabled(true);
		}
	}

	/*
	* Da un numero aleatorio entre MAX y MIN
	* Max no incluido!!!
	 */
	private static int getRandomInteger(int max, int min)
	{
		return ((int)(Math.random()*(max-min)))+min;
	}

	/*
	* Lo uso para hacer pruebas ;)
	 */
	private void cheats(String cheatCode)
	{
		cheatCode=cheatCode.toLowerCase(Locale.ROOT);
		switch (cheatCode)
		{
			case "see_all" : //MUESTRA EL TABLERO ENEMIGO
			{
				System.out.println("CheatCode: "+cheatCode);
				Tablero_IA.getTableroIA().mostrarTablero();
				break;
			}
			case "random" : //COLOCA LOS BARCOS ALEATORIAMENTE
			{
				System.out.println("CheatCode: "+cheatCode);
				addBarcoRandom(false);
				break;
			}
			case "iddqd" : //ARMAS INFINITAS
			{
				System.out.println("CheatCode: "+cheatCode);
				Juego.getBtn_misil().setEnabled(true);
				Juego.getBtn_radar().setEnabled(true);
				Juego.getBtn_escudo().setEnabled(true);
				JMisil=-999;
				JRadar=-999;
				JEscudo=-999;
				break;
			}
			case "motherlode" : //50.000 DE ORO
			{
				System.out.println("CheatCode: "+cheatCode);
				JDinero=JDinero+50000;
				actualizarDinero();
				break;
			}
		}
	}
}
