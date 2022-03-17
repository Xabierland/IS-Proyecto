package is.Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sun.prism.Image;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class Menu extends JFrame {

	private JPanel contentPane;
	private JPanel tableros;
	private JPanel arma_panel;
	private JButton Radar;
	private JButton Bomba;
	private JButton Misil;
	private JButton Escudo;
	private JPanel tablero_jugador;
	private JPanel tablero_ia;
	private JPanel barco_panel;
	private JPanel relleno_abajo;
	private JPanel relleno_arriba;
	private Component horizontalStrut;
	private Component verticalStrut_1;
	private JLabel lblPlayer;
	private JLabel lblIa;
	private Component rigidArea;
	private JLabel FLECHA;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
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
	public Menu() {
		setAlwaysOnTop(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Menu.class.getResource("/resource/icon.png")));
		setTitle("Hundir la flota");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1037, 576);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getTableros(), BorderLayout.CENTER);
		contentPane.add(getArma_panel(), BorderLayout.WEST);
		contentPane.add(getBarco_panel(), BorderLayout.EAST);
		contentPane.add(getRelleno_abajo(), BorderLayout.SOUTH);
		contentPane.add(getRelleno_arriba(), BorderLayout.NORTH);
	}
	private JLabel getCasilla()
	{
		JLabel casilla = new JLabel();
		casilla.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		casilla.setOpaque(true);
		casilla.setBackground(Color.blue);
			
		return casilla;
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
			arma_panel.setLayout(new GridLayout(0, 1, 0, 0));
			arma_panel.add(getRadar());
			arma_panel.add(getBomba());
			arma_panel.add(getMisil());
			arma_panel.add(getEscudo());
		}
		return arma_panel;
	}
	private JButton getRadar() {
		if (Radar == null) {
			Radar = new JButton("Radar");
		}
		return Radar;
	}
	private JButton getBomba() {
		if (Bomba == null) {
			Bomba = new JButton("Bomba");
		}
		return Bomba;
	}
	private JButton getMisil() {
		if (Misil == null) {
			Misil = new JButton("Misil");
		}
		return Misil;
	}
	private JButton getEscudo() {
		if (Escudo == null) {
			Escudo = new JButton("Escudo");
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
					tablero_jugador.add(getCasilla());
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
					tablero_ia.add(getCasilla());
				}
		}
		return tablero_ia;
	}
	private JPanel getBarco_panel() {
		if (barco_panel == null) {
			barco_panel = new JPanel();
			barco_panel.setLayout(new GridLayout(0, 1, 0, 0));
			barco_panel.add(getFLECHA());
		}
		return barco_panel;
	}
	private JPanel getRelleno_abajo() {
		if (relleno_abajo == null) {
			relleno_abajo = new JPanel();
			relleno_abajo.setLayout(new GridLayout(0, 1, 0, 0));
			relleno_abajo.add(getVerticalStrut_1());
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
	private Component getVerticalStrut_1() {
		if (verticalStrut_1 == null) {
			verticalStrut_1 = Box.createVerticalStrut(30);
		}
		return verticalStrut_1;
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
			rigidArea = Box.createRigidArea(new Dimension(400, 30));
		}
		return rigidArea;
	}
	private JLabel getFLECHA() {
		if (FLECHA == null) {
			FLECHA = new JLabel("");
			FLECHA.setVerticalAlignment(SwingConstants.TOP);
			FLECHA.setIcon(new ImageIcon(Menu.class.getResource("/resource/flecha_ar.png")));
		}
		return FLECHA;
	}
}
