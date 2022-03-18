package is.controlador;
import is.vista.*;
import is.modelo.*;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import is.vista.Vista;

public class Controlador implements ActionListener
{
	private static Controlador controler =null;
	private int pos_flecha=0;
	private boolean jugando=true;
	private int arma=0;
	
	
	
	private Controlador(){};
	
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
	public boolean getIfJugando()
	{
		return jugando;
	}
	
	public int getArma()
	{
		return arma;
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
	}
}
