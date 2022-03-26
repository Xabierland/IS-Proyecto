package is.controlador;
import is.modelo.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;

import javax.swing.*;

import is.vista.Tienda;
import is.vista.Juego;

public class Controlador implements ActionListener, MouseListener
{
	private static Controlador controler =null;

	private Tablero Jugador=Tablero_Jugador.getTableroJugador();
	private Tablero IA=Tablero_IA.getTableroIA();
	private Variables var=Variables.getMisVariables();
	private Partida partida=Partida.getMiPartida();

	private Controlador() {};
	
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
		//ARMAS ===============================================================================================================================================================
		if(e.getSource().equals(Juego.getBtn_bomba()))
		{
			System.out.println("BOMBA SELECCIONADA\n");
			partida.setTipoArma(0);
		}
		if(e.getSource().equals(Juego.getBtn_misil()))
		{
			System.out.println("MISIL SELECCIONADA\n");
			partida.setTipoArma(1);
		}
		if(e.getSource().equals(Juego.getBtn_radar()))
		{
			System.out.println("RADAR SELECCIONADA\n");
			partida.setTipoArma(2);
		}
		if(e.getSource().equals(Juego.getBtn_escudo()))
		{
			System.out.println("ESCUDO SELECCIONADA\n");
			partida.setTipoArma(3);
		}
		if(e.getSource().equals(Juego.getBtn_reparacion()))
		{
			System.out.println("REPARACION SELECCIONADA\n");
			partida.setTipoArma(4);
		}
		//BARCOS ===============================================================================================================================================================
		if(e.getSource().equals(Juego.getBtn_fragata()))
		{
			System.out.println("FRAGATA SELECCIONADO\n");
			partida.setTipoBarco(1);
		}
		if(e.getSource().equals(Juego.getBtn_destructor()))
		{
			System.out.println("DESTRUCTOR SELECCIONADO\n");
			partida.setTipoBarco(2);
		}
		if(e.getSource().equals(Juego.getBtn_submarino()))
		{
			System.out.println("SUBMARINO SELECCIONADO\n");
			partida.setTipoBarco(3);
		}
		if(e.getSource().equals(Juego.getBtn_portavion()))
		{
			System.out.println("PORTAAVIONES SELECCIONADO\n");
			partida.setTipoBarco(4);
		}
		//TIENDA ===============================================================================================================================================================
		if(e.getSource().equals(Juego.getBtn_tienda()))
		{
			Tienda tienda = Tienda.getTienda();
			tienda.setVisible(true);
		}
		if(e.getSource().equals(Tienda.getBtn_misil()))
		{
			Shop.getTienda().comprarMisil(0);
		}
		if(e.getSource().equals(Tienda.getBtn_radar()))
		{
			Shop.getTienda().comprarRadar(0);
		}
		if(e.getSource().equals(Tienda.getBtn_escudo()))
		{
			Shop.getTienda().comprarEscudo(0);
		}
		if(e.getSource().equals(Tienda.getBtn_reparacion()))
		{
			Shop.getTienda().comprarReparacion(0);
		}
		//CHEATS ===============================================================================================================================================================
		if(e.getSource().equals(Juego.getCheatConsole()))
		{
			String cad = Juego.getCheatConsole().getText();
			Juego.getCheatConsole().setText(null);
			Cheats.getMyCheats().execute(cad);
		}
	}

	public void mouseClicked(MouseEvent e)
	{
		if(e.getSource().equals(Juego.getFlecha()))
		{
			partida.setDireccion(partida.getDireccion()+1);
			if(partida.getDireccion() >=4)
			{
				partida.setDireccion(0);
			}
			switch(partida.getDireccion())
			{
				case 0 : {
					Juego.getFlecha().setIcon(new ImageIcon(Juego.class.getResource("/resource/flecha_arr.png")));System.out.println("ARRIBA "+ partida.getDireccion());break;}
				case 1 : {
					Juego.getFlecha().setIcon(new ImageIcon(Juego.class.getResource("/resource/flecha_der.png")));System.out.println("DERECHA "+ partida.getDireccion());break;}
				case 2 : {
					Juego.getFlecha().setIcon(new ImageIcon(Juego.class.getResource("/resource/flecha_abj.png")));System.out.println("ABAJO "+ partida.getDireccion());break;}
				case 3 : {
					Juego.getFlecha().setIcon(new ImageIcon(Juego.class.getResource("/resource/flecha_izq.png")));System.out.println("IZQUIERDA "+ partida.getDireccion());break;}
				default : {
					Juego.getFlecha().setIcon(new ImageIcon(Juego.class.getResource("/resource/flecha_arr.png")));System.out.println("DEFAULT");break;}
			}
		}
	}

	/*
	 * Se activa al hacer click en una casilla del tablero del Jugador
	 */
	public void casillaJugadorClick(int x, int y)
	{
		System.out.printf("PLAYER|x:%d|y:%d\n",x,y);
		Jugador.getIfBarcoByPos(x,y,true);

		partida.jugar(0,x,y);
	}

	public void casillaIAClick(int x, int y)
	{
		System.out.printf("IA    |x:%d|y:%d\n",x,y);
		IA.getIfBarcoByPos(x,y,true);

		partida.jugar(1,x,y);
	}

	//ME OBLIGA A PONER LAS CABECERAS :(
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
}
