package is.controlador;

import is.modelo.*;
import is.vista.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Controlador implements ActionListener, MouseListener, KeyListener
{
	private static Controlador controler =null;

	private int dir=0;

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
	@Override
	public void actionPerformed(ActionEvent e)
	{
		//CONFIG ===================================================================
		if(e.getSource().equals(Config.getBtn_start()))
		{
			start();
		}
		//ARMAS ===============================================================================================================================================================
		if(e.getSource().equals(Juego.getBtn_bomba()))
		{
			System.out.println("BOMBA SELECCIONADA\n");
			Partida.getMiPartida().setTipoArma(0);
		}
		if(e.getSource().equals(Juego.getBtn_misil()))
		{
			System.out.println("MISIL SELECCIONADA\n");
			Partida.getMiPartida().setTipoArma(1);
		}
		if(e.getSource().equals(Juego.getBtn_radar()))
		{
			System.out.println("RADAR SELECCIONADA\n");
			Partida.getMiPartida().setTipoArma(2);
		}
		if(e.getSource().equals(Juego.getBtn_escudo()))
		{
			System.out.println("ESCUDO SELECCIONADA\n");
			Partida.getMiPartida().setTipoArma(3);
		}
		if(e.getSource().equals(Juego.getBtn_reparacion()))
		{
			System.out.println("REPARACION SELECCIONADA\n");
			Partida.getMiPartida().setTipoArma(4);
		}
		if(e.getSource().equals(Juego.getBtnCambiarRadar()))
		{
			PC jugador = (PC) ListaJugadores.getMiListaJugadores().getJugador(0);
			jugador.setCoorRadar();
		}
		//BARCOS ===============================================================================================================================================================
		if(e.getSource().equals(Juego.getBtn_fragata()))
		{
			System.out.println("FRAGATA SELECCIONADO\n");
			Partida.getMiPartida().setTipoBarco(1);
		}
		if(e.getSource().equals(Juego.getBtn_destructor()))
		{
			System.out.println("DESTRUCTOR SELECCIONADO\n");
			Partida.getMiPartida().setTipoBarco(2);
		}
		if(e.getSource().equals(Juego.getBtn_submarino()))
		{
			System.out.println("SUBMARINO SELECCIONADO\n");
			Partida.getMiPartida().setTipoBarco(3);
		}
		if(e.getSource().equals(Juego.getBtn_portavion()))
		{
			System.out.println("PORTAAVIONES SELECCIONADO\n");
			Partida.getMiPartida().setTipoBarco(4);
		}
		//TIENDA ===============================================================================================================================================================
		if(e.getSource().equals(Juego.getBtn_tienda()))
		{
			Tienda tienda = Tienda.getTienda();
			tienda.setVisible(true);
		}
		if(e.getSource().equals(Tienda.getBtn_misil()))
		{
			Shop.getTienda().comprarArma(0,1);
		}
		if(e.getSource().equals(Tienda.getBtn_radar()))
		{
			Shop.getTienda().comprarArma(0,2);
		}
		if(e.getSource().equals(Tienda.getBtn_escudo()))
		{
			Shop.getTienda().comprarArma(0,3);
		}
		if(e.getSource().equals(Tienda.getBtn_reparacion()))
		{
			Shop.getTienda().comprarArma(0,4);
		}
		//CHEATS ===============================================================================================================================================================
		if(e.getSource().equals(Juego.getCheatConsole()))
		{
			String cad = Juego.getCheatConsole().getText();
			Juego.getCheatConsole().setText(null);
			Cheats.getMyCheats().execute(cad);
		}
	}
	@Override
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode()==KeyEvent.VK_E)
		{
			start();
		}
	}
	@Override
	public void mouseClicked(MouseEvent e)
	{
		if(e.getSource().equals(Juego.getFlecha()))
		{
			Partida.getMiPartida().setDireccion(++dir);

			if(dir >=4)
			{
				Partida.getMiPartida().setDireccion(dir=0);
			}
			switch(dir)
			{
				case 0 : {
					Juego.getFlecha().setIcon(new ImageIcon(Juego.class.getResource("/resource/flecha_arr.png")));System.out.println("ARRIBA "+ dir);break;}
				case 1 : {
					Juego.getFlecha().setIcon(new ImageIcon(Juego.class.getResource("/resource/flecha_der.png")));System.out.println("DERECHA "+ dir);break;}
				case 2 : {
					Juego.getFlecha().setIcon(new ImageIcon(Juego.class.getResource("/resource/flecha_abj.png")));System.out.println("ABAJO "+ dir);break;}
				case 3 : {
					Juego.getFlecha().setIcon(new ImageIcon(Juego.class.getResource("/resource/flecha_izq.png")));System.out.println("IZQUIERDA "+ dir);break;}
				default : {
					Juego.getFlecha().setIcon(new ImageIcon(Juego.class.getResource("/resource/flecha_arr.png")));System.out.println("DEFAULT");break;}
			}
		}


	}
	@Override
	public void mousePressed(MouseEvent e) {
		if(perteneceJugador(e.getSource()))
		{
			Coordenada coor=ListaJugadores.getMiListaJugadores().getJugador(0).getTablero().getCoordenadasDeCasilla((JLabel) e.getSource());
			int x=coor.getX();
			int y=coor.getY();
			System.out.printf("PLAYER|x:%d|y:%d\n",x,y);
			Partida.getMiPartida().jugar(0,x,y);
		}
		if(perteneceIA(e.getSource())) {
			Coordenada coor = ListaJugadores.getMiListaJugadores().getJugador(1).getTablero().getCoordenadasDeCasilla((JLabel) e.getSource());
			int x = coor.getX();
			int y = coor.getY();
			System.out.printf("IA    |x:%d|y:%d\n", x, y);
			Partida.getMiPartida().jugar(1, x, y);
		}
	}
	private void start()
	{
		Variables var=Variables.getMisVariables();
		//RECOGER PARAMETROS
		var.setEscala(Config.getSlider_escala().getValue());
		var.setDificultadIA(Config.getSlider_ia().getValue());
		try {
			var.setDineroInicial(Integer.parseInt(Config.getText_money().getText()));
		}catch (Exception i){}
		try {
			var.setDineroPorHundir(Integer.parseInt(Config.getTxt_regard().getText()));
		}catch (Exception ii){}

		//INICIAR JUEGO
		Config.getMiConfig().setVisible(false);
		try {
			Partida.getMiPartida();
			Juego frame = Juego.getMiJuego();
			frame.setVisible(true);
		} catch (Exception i) {
			i.printStackTrace();
		}
	}
	private boolean perteneceJugador(Object obj)
	{
		JLabel label=(JLabel) obj;
		return label.getParent()==Juego.getTablero_jugador();
	}
	private boolean perteneceIA(Object obj)
	{
		JLabel label=(JLabel) obj;
		return label.getParent()==Juego.getTablero_ia();
	}

	//ME OBLIGA A PONER LAS CABECERAS :(
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyReleased(KeyEvent e) {}
}
