package is.vista;
import is.controlador.*;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import is.modelo.ListaJugadores;
import is.modelo.Partida;
import is.modelo.Variables;
import net.miginfocom.swing.MigLayout;

import java.awt.Font;

public class Juego extends JFrame implements Observer {
	private static Juego miJuego=null;
	private JPanel contentPane;
	private JPanel tableros;
	private JPanel arma_panel;
	private static JButton btn_bomba;
	private static JButton btn_misil;
	private static JButton btn_radar;
	private static JButton btn_escudo;
	private static JPanel tablero_jugador;
	private static JPanel tablero_ia;
	private JPanel barco_panel;
	private JPanel relleno_abajo;
	private JPanel relleno_arriba;
	private Component horizontalStrut;
	private JLabel lblPlayer;
	private JLabel lblIa;
	private Component rigidArea;
	private static JLabel flecha;
	private static JRadioButton btn_portavion;
	private static JRadioButton btn_submarino;
	private static JRadioButton btn_destructor;
	private static JRadioButton btn_fragata;
	private static JTextField cheatConsole;
	private JPanel panel;
	private static JLabel lblDinero;
	private static JButton btn_tienda;
	private ButtonGroup grupo_btn_barcos = new ButtonGroup();
	private JPanel panel_1;
	private JLabel lblNewLabel;
	private Component horizontalStrut_1;
	private static JLabel displayState;
	private JPanel panel_2;
	private JPanel panel_3;
	private Component horizontalStrut_2;
	private static JButton btn_reparacion;
	private Component verticalStrut;



	/**
	 * Create the frame.
	 */
	private Juego() {
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		int a_width = 1280;
		int a_height = 720;
		int height = pantalla.height/2-a_height/2;
		int width = pantalla.width/2-a_width/2;

		setAlwaysOnTop(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Juego.class.getResource("/resource/icon.png")));
		setTitle("Hundir la flota");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(width, height, a_width, a_height);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getTableros(), BorderLayout.CENTER);
		contentPane.add(getArma_panel(), BorderLayout.WEST);
		contentPane.add(getBarco_panel(), BorderLayout.EAST);
		contentPane.add(getRelleno_abajo(), BorderLayout.SOUTH);
		contentPane.add(getRelleno_arriba(), BorderLayout.NORTH);
		grupo_btn_barcos.add(btn_destructor);
		grupo_btn_barcos.add(btn_fragata);
		grupo_btn_barcos.add(btn_portavion);
		grupo_btn_barcos.add(btn_submarino);
	}
	public static Juego getMiJuego()
	{
		if(Juego.miJuego==null){
			Juego.miJuego=new Juego();
		}
		return miJuego;
	}
	
	public static JLabel getCasillaJugador(int x, int y)
	{
		JLabel casillaJugador = new JLabel();
		casillaJugador.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		casillaJugador.setOpaque(true);
		casillaJugador.setBackground(new Color(0,153,204));

		casillaJugador.addMouseListener(Controlador.getControlador());
		return casillaJugador;
	}

	public static JLabel getCasillaIA(int x, int y)
	{
		JLabel casillaIA = new JLabel();
		casillaIA.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		casillaIA.setOpaque(true);
		casillaIA.setBackground(new Color(0,153,204));

		casillaIA.addMouseListener(Controlador.getControlador());
		return casillaIA;
	}
	
	private JPanel getTableros() {
		if (tableros == null) {
			tableros = new JPanel();
			tableros.setLayout(new BoxLayout(tableros, BoxLayout.X_AXIS));
			tableros.add(getTablero_jugador());
			tableros.add(getHorizontalStrut());
			tableros.add(getTablero_ia());
		}
		return tableros;
	}
	private JPanel getArma_panel() {
		if (arma_panel == null) {
			arma_panel = new JPanel();
			arma_panel.setLayout(new MigLayout("", "[130px,center]", "[][90px,center][90px,center][90px,center][90px,center][90px,center]"));
			arma_panel.add(getVerticalStrut(), "cell 0 0");
			arma_panel.add(getBtn_bomba(), "cell 0 1,growx,aligny center");
			arma_panel.add(getBtn_misil(), "cell 0 2,growx,aligny center");
			arma_panel.add(getBtn_radar(), "cell 0 3,growx,aligny center");
			arma_panel.add(getBtn_escudo(), "cell 0 4,growx,aligny center");
			arma_panel.add(getBtn_reparacion(), "cell 0 5,growx,aligny center");
		}
		return arma_panel;
	}
	public static JButton getBtn_bomba() {
		if (btn_bomba == null) {
			btn_bomba = new JButton("Bomba");
			btn_bomba.addActionListener(Controlador.getControlador());
		}
		return btn_bomba;
	}
	public static JButton getBtn_misil() {
		if (btn_misil == null) {
			btn_misil = new JButton("Misil");
			btn_misil.setEnabled(false);
			btn_misil.addActionListener(Controlador.getControlador());
		}
		return btn_misil;
	}
	public static JButton getBtn_radar() {
		if (btn_radar == null) {
			btn_radar = new JButton("Radar");
			btn_radar.setEnabled(false);
			btn_radar.addActionListener(Controlador.getControlador());
		}
		return btn_radar;
	}
	public static JButton getBtn_escudo() {
		if (btn_escudo == null) {
			btn_escudo = new JButton("Escudo");
			btn_escudo.setEnabled(false);
			btn_escudo.addActionListener(Controlador.getControlador());
		}
		return btn_escudo;
	}
	public static JButton getBtn_reparacion() {
		if (btn_reparacion == null) {
			btn_reparacion = new JButton("Reparacion");
			btn_reparacion.setEnabled(false);
			btn_reparacion.addActionListener(Controlador.getControlador());
		}
		return btn_reparacion;
	}
	public static JPanel getTablero_jugador() {
		if (tablero_jugador == null) {
			tablero_jugador = new JPanel();
			tablero_jugador.setLayout(new GridLayout(Variables.getMisVariables().getTamanoTablero(), Variables.getMisVariables().getTamanoTablero(), 0, 0));
			int i,j;
			for(i=0;i<Variables.getMisVariables().getTamanoTablero();i++)
				for(j=0; j<Variables.getMisVariables().getTamanoTablero();j++)
				{
					JLabel unaCasilla= getCasillaJugador(j,i);
					tablero_jugador.add(unaCasilla);
					ListaJugadores.getMiListaJugadores().getJugador(0).getTablero().addCasilla(unaCasilla,j,i);
				}
		}
		return tablero_jugador;
	}
	public static JPanel getTablero_ia() {
		if (tablero_ia == null) {
			tablero_ia = new JPanel();
			tablero_ia.setLayout(new GridLayout(Variables.getMisVariables().getTamanoTablero(), Variables.getMisVariables().getTamanoTablero(), 0, 0));
			int i,j;
			for(i=0;i<Variables.getMisVariables().getTamanoTablero();i++)
				for(j=0; j<Variables.getMisVariables().getTamanoTablero();j++)
				{
					JLabel unaCasilla=getCasillaIA(j,i);
					tablero_ia.add(unaCasilla);
					ListaJugadores.getMiListaJugadores().getJugador(1).getTablero().addCasilla(unaCasilla,j,i);
				}
		}
		return tablero_ia;
	}
	private JPanel getBarco_panel() {
		if (barco_panel == null) {
			barco_panel = new JPanel();
			barco_panel.setLayout(new MigLayout("", "[130px]", "[84px][84px][84px][84px][84px]"));
			barco_panel.add(getFlecha(), "cell 0 0,alignx center,growy");
			barco_panel.add(getBtn_portavion(), "cell 0 1,grow");
			barco_panel.add(getBtn_submarino(), "cell 0 2,grow");
			barco_panel.add(getBtn_destructor(), "cell 0 3,grow");
			barco_panel.add(getBtn_fragata(), "cell 0 4,grow");
		}
		return barco_panel;
	}
	private JPanel getRelleno_abajo() {
		if (relleno_abajo == null) {
			relleno_abajo = new JPanel();
			relleno_abajo.setLayout(new GridLayout(0, 1, 0, 0));
			relleno_abajo.add(getPanel());
			relleno_abajo.add(getCheatConsole());
		}
		return relleno_abajo;
	}
	private JPanel getRelleno_arriba() {
		if (relleno_arriba == null) {
			relleno_arriba = new JPanel();
			relleno_arriba.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			relleno_arriba.add(getPanel_1());
			relleno_arriba.add(getLblNewLabel());
		}
		return relleno_arriba;
	}
	private Component getHorizontalStrut() {
		if (horizontalStrut == null) {
			horizontalStrut = Box.createHorizontalStrut(30);
		}
		return horizontalStrut;
	}
	private JLabel getLblPlayer() {
		if (lblPlayer == null) {
			lblPlayer = new JLabel("Player");
		}
		return lblPlayer;
	}
	private JLabel getLblIa() {
		if (lblIa == null) {
			lblIa = new JLabel("IA");
		}
		return lblIa;
	}
	private Component getRigidArea() {
		if (rigidArea == null) {
			rigidArea = Box.createRigidArea(new Dimension(500, 30));
		}
		return rigidArea;
	}
	public static JLabel getFlecha() {
		if (flecha == null) {
			flecha = new JLabel("");
			flecha.setIcon(new ImageIcon(Juego.class.getResource("/resource/flecha_arr.png")));
			flecha.addMouseListener(Controlador.getControlador());
			
		}
		return flecha;
	}
	public static JRadioButton getBtn_portavion() {
		if (btn_portavion == null) {
			btn_portavion = new JRadioButton("Portaaviones"+" "+Variables.getMisVariables().getNumPortavion());
			btn_portavion.addActionListener(Controlador.getControlador());
		}
		return btn_portavion;
	}
	public static JRadioButton getBtn_submarino() {
		if (btn_submarino == null) {
			btn_submarino = new JRadioButton("Submarino"+" "+Variables.getMisVariables().getNumSubmarino());
			btn_submarino.addActionListener(Controlador.getControlador());
		}
		return btn_submarino;
	}
	public static JRadioButton getBtn_destructor() {
		if (btn_destructor == null) {
			btn_destructor = new JRadioButton("Destructor"+" "+Variables.getMisVariables().getNumDestructor());
			btn_destructor.addActionListener(Controlador.getControlador());
		}
		return btn_destructor;
	}
	public static JRadioButton getBtn_fragata() {
		if (btn_fragata == null) {
			btn_fragata = new JRadioButton("Fragata"+" "+Variables.getMisVariables().getNumFragata());
			btn_fragata.addActionListener(Controlador.getControlador());
		}
		return btn_fragata;
	}
	public static JTextField getCheatConsole() {
		if (cheatConsole == null) {
			cheatConsole = new JTextField();
			cheatConsole.setFont(new Font("Consolas", Font.PLAIN, 12));
			cheatConsole.setForeground(Color.GREEN);
			cheatConsole.setBackground(Color.BLACK);
			cheatConsole.setColumns(10);
			cheatConsole.setEnabled(Config.getBtn_cheats().isSelected());
			cheatConsole.setVisible(Config.getBtn_cheats().isSelected());
			cheatConsole.addActionListener(Controlador.getControlador());
		}
		return cheatConsole;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
			panel.add(getPanel_2());
			panel.add(getPanel_3());
		}
		return panel;
	}
	public static JLabel getLblDinero() {
		if (lblDinero == null) {
			lblDinero = new JLabel("GOLD: "+Variables.getMisVariables().getDineroInicial());
		}
		return lblDinero;
	}
	public static JButton getBtn_tienda() {
		if (btn_tienda == null) {
			btn_tienda = new JButton("Tienda");
			btn_tienda.addActionListener(Controlador.getControlador());
		}
		return btn_tienda;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.add(getLblPlayer());
			panel_1.add(getRigidArea());
			panel_1.add(getLblIa());
		}
		return panel_1;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("");
		}
		return lblNewLabel;
	}
	private Component getHorizontalStrut_1_1() {
		if (horizontalStrut_1 == null) {
			horizontalStrut_1 = Box.createHorizontalStrut(20);
		}
		return horizontalStrut_1;
	}
	public static JLabel getDisplayState() {
		if (displayState == null) {
			displayState = new JLabel("Coloca tu flota");
			displayState.setForeground(Color.RED);
		}
		return displayState;
	}
	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.add(getLblDinero());
			panel_2.add(getHorizontalStrut_1_1());
			panel_2.add(getBtn_tienda());
		}
		return panel_2;
	}
	private JPanel getPanel_3() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
			panel_3.add(getDisplayState());
			panel_3.add(getHorizontalStrut_2());
		}
		return panel_3;
	}
	private Component getHorizontalStrut_2() {
		if (horizontalStrut_2 == null) {
			horizontalStrut_2 = Box.createHorizontalStrut(80);
		}
		return horizontalStrut_2;
	}
	private Component getVerticalStrut() {
		if (verticalStrut == null) {
			verticalStrut = Box.createVerticalStrut(30);
		}
		return verticalStrut;
	}
	@Override
	public void update(Observable o,  Object arg)
	 {
		Object[] lista=(Object[]) arg;
		String cad=(String) lista[0];

		if(cad.equalsIgnoreCase("CASILLA"))
		{
			JLabel label = (JLabel) lista[1];
			Color color = (Color) lista[2];
			label.setBackground(color);
		}
		if(cad.equalsIgnoreCase("BARCO"))
		{
			JRadioButton rBtn = (JRadioButton) lista[1];
			boolean enable = (boolean) lista[2];
			rBtn.setEnabled(enable);
			rBtn.setText((String) lista[3]);
		}

		if(cad.equalsIgnoreCase("ARMA"))
		{
			JButton btn = (JButton) lista[1];
			boolean enable = (boolean) lista[2];
			btn.setEnabled(enable);
			btn.setText((String) lista[3]+" "+(int)lista[4]);
		}
		if(cad.equalsIgnoreCase("DINERO"))
		{
			JLabel label = (JLabel) lista[1];
			label.setText("GOLD: "+lista[2]);
		}
		if(cad.equalsIgnoreCase("ESTADO"))
		{
			JLabel label = (JLabel) lista[1];
			label.setText((String) lista[2]);
		}
		if(cad.equalsIgnoreCase("Btn"))
		{
			JRadioButton b =(JRadioButton) lista[2];
			b.setText((String) lista[1]);
		}
	}
}
