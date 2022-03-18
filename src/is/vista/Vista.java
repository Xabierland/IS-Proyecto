package is.vista;
import is.controlador.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;

public class Vista extends JFrame {

	private JPanel contentPane;
	private JPanel tableros;
	private JPanel arma_panel;
	public static JButton Bomba;
	public static JButton Misil;
	public static JButton Radar;
	public static JButton Escudo;
	private JPanel tablero_jugador;
	private JPanel tablero_ia;
	private JPanel barco_panel;
	private JPanel relleno_abajo;
	private JPanel relleno_arriba;
	private Component horizontalStrut;
	private JLabel lblPlayer;
	private JLabel lblIa;
	private Component rigidArea;
	public static JLabel flecha;
	public static JRadioButton boton_portaaviones;
	public static JRadioButton boton_submarino;
	public static JRadioButton boton_destructor;
	public static JRadioButton boton_fragata;
	private JTextField textField;
	private JPanel panel;
	private JLabel lblDinero;
	private JButton btnNewButton;
	private Component horizontalStrut_1;
	private Component verticalStrut;
	private ButtonGroup grupo_barcos = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vista frame = new Vista();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Vista() {
		setAlwaysOnTop(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Vista.class.getResource("/resource/icon.png")));
		setTitle("Hundir la flota");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getTableros(), BorderLayout.CENTER);
		contentPane.add(getArma_panel(), BorderLayout.WEST);
		contentPane.add(getBarco_panel(), BorderLayout.EAST);
		contentPane.add(getRelleno_abajo(), BorderLayout.SOUTH);
		contentPane.add(getRelleno_arriba(), BorderLayout.NORTH);
		grupo_barcos.add(boton_destructor);
		grupo_barcos.add(boton_fragata);
		grupo_barcos.add(boton_portaaviones);
		grupo_barcos.add(boton_submarino);
	}
	
	
	public JLabel getCasillaJugador(int x, int y)
	{
		JLabel casillaJugador = new JLabel();
		int pos_x=x;
		int pos_y=y;
		casillaJugador.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		casillaJugador.setOpaque(true);
		casillaJugador.setBackground(Color.blue);

		casillaJugador.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0)
			{
				Controlador.getControlador().casillaJugadorClick(casillaJugador,pos_x,pos_y);
			}
		});
		return casillaJugador;
	}

	public JLabel getCasillaIA(int x, int y)
	{
		JLabel casillaIA = new JLabel();
		int pos_x=x;
		int pos_y=y;
		casillaIA.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		casillaIA.setOpaque(true);
		casillaIA.setBackground(Color.blue);

		casillaIA.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0)
			{
				Controlador.getControlador().casillaIAClick(casillaIA,pos_x,pos_y);
			}
		});
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
			arma_panel.setLayout(new MigLayout("", "[130px]", "[][84px][84px][84px][84px]"));
			arma_panel.add(getVerticalStrut(), "cell 0 0");
			arma_panel.add(getBomba(), "cell 0 1,growx,aligny center");
			arma_panel.add(getMisil(), "cell 0 2,growx,aligny center");
			arma_panel.add(getRadar(), "cell 0 3,growx,aligny center");
			arma_panel.add(getEscudo(), "cell 0 4,growx,aligny center");
		}
		return arma_panel;
	}
	public JButton getBomba() {
		if (Bomba == null) {
			Bomba = new JButton("Bomba");
			Bomba.addActionListener(Controlador.getControlador());
		}
		return Bomba;
	}
	private JButton getMisil() {
		if (Misil == null) {
			Misil = new JButton("Misil");
			Misil.addActionListener(Controlador.getControlador());
		}
		return Misil;
	}
	private JButton getRadar() {
		if (Radar == null) {
			Radar = new JButton("Radar");
			Radar.addActionListener(Controlador.getControlador());
		}
		return Radar;
	}
	private JButton getEscudo() {
		if (Escudo == null) {
			Escudo = new JButton("Escudo");
			Escudo.addActionListener(Controlador.getControlador());
		}
		return Escudo;
	}
	private JPanel getTablero_jugador() {
		if (tablero_jugador == null) {
			tablero_jugador = new JPanel();
			tablero_jugador.setLayout(new GridLayout(10, 10, 0, 0));
			int i,j;
			for(i=0;i<10;i++)
				for(j=0; j<10;j++)
				{
					JLabel unaCasilla= getCasillaJugador(j,i);
					tablero_jugador.add(unaCasilla);
					Controlador.getControlador().addCasillaJugador(unaCasilla,j,i);
				}
		}
		return tablero_jugador;
	}
	private JPanel getTablero_ia() {
		if (tablero_ia == null) {
			tablero_ia = new JPanel();
			tablero_ia.setLayout(new GridLayout(10, 10, 0, 0));
			int i,j;
			for(i=0;i<10;i++)
				for(j=0; j<10;j++)
				{
					JLabel unaCasilla=getCasillaIA(j,i);
					tablero_ia.add(unaCasilla);
					//Controlador.getControlador().addCasillaIA(unaCasilla,i,j);
				}
		}
		return tablero_ia;
	}
	private JPanel getBarco_panel() {
		if (barco_panel == null) {
			barco_panel = new JPanel();
			barco_panel.setLayout(new MigLayout("", "[130px]", "[84px][84px][84px][84px][84px]"));
			barco_panel.add(getFlecha(), "cell 0 0,alignx center,growy");
			barco_panel.add(getBoton_portaaviones(), "cell 0 1,grow");
			barco_panel.add(getBoton_submarino(), "cell 0 2,grow");
			barco_panel.add(getBoton_destructor(), "cell 0 3,grow");
			barco_panel.add(getBoton_fragata(), "cell 0 4,grow");
		}
		return barco_panel;
	}
	private JPanel getRelleno_abajo() {
		if (relleno_abajo == null) {
			relleno_abajo = new JPanel();
			relleno_abajo.setLayout(new GridLayout(0, 1, 0, 0));
			relleno_abajo.add(getPanel());
			relleno_abajo.add(getTextField());
		}
		return relleno_abajo;
	}
	private JPanel getRelleno_arriba() {
		if (relleno_arriba == null) {
			relleno_arriba = new JPanel();
			relleno_arriba.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			relleno_arriba.add(getLblPlayer());
			relleno_arriba.add(getRigidArea());
			relleno_arriba.add(getLblIa());
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
			rigidArea = Box.createRigidArea(new Dimension(350, 30));
		}
		return rigidArea;
	}
	private JLabel getFlecha() {
		if (flecha == null) {
			flecha = new JLabel("");
			flecha.setIcon(new ImageIcon(Vista.class.getResource("/resource/flecha_arr.png")));
			flecha.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent arg0) 
				{
					Controlador.getControlador().flechaClick();
				}
			});
			
		}
		return flecha;
	}
	private JRadioButton getBoton_portaaviones() {
		if (boton_portaaviones == null) {
			boton_portaaviones = new JRadioButton("Portaaviones");
			boton_portaaviones.addActionListener(Controlador.getControlador());
		}
		return boton_portaaviones;
	}
	private JRadioButton getBoton_submarino() {
		if (boton_submarino == null) {
			boton_submarino = new JRadioButton("Submarino");
			boton_submarino.addActionListener(Controlador.getControlador());
		}
		return boton_submarino;
	}
	private JRadioButton getBoton_destructor() {
		if (boton_destructor == null) {
			boton_destructor = new JRadioButton("Destructor");
			boton_destructor.addActionListener(Controlador.getControlador());
		}
		return boton_destructor;
	}
	private JRadioButton getBoton_fragata() {
		if (boton_fragata == null) {
			boton_fragata = new JRadioButton("Fragata");
			boton_fragata.addActionListener(Controlador.getControlador());
		}
		return boton_fragata;
	}
	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setColumns(10);
		}
		return textField;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.add(getLblDinero());
			panel.add(getHorizontalStrut_1());
			panel.add(getBtnNewButton());
		}
		return panel;
	}
	private JLabel getLblDinero() {
		if (lblDinero == null) {
			lblDinero = new JLabel("Dinero: 0000");
		}
		return lblDinero;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Tienda");
		}
		return btnNewButton;
	}
	private Component getHorizontalStrut_1() {
		if (horizontalStrut_1 == null) {
			horizontalStrut_1 = Box.createHorizontalStrut(500);
		}
		return horizontalStrut_1;
	}
	private Component getVerticalStrut() {
		if (verticalStrut == null) {
			verticalStrut = Box.createVerticalStrut(90);
		}
		return verticalStrut;
	}
}
