package is.Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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
	private JPanel panel;
	private JPanel panel_1;
	private JLabel lblNewLabel;
	private JPanel panel_2;
	private JPanel panel_3;

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
		setBounds(100, 100, 751, 468);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getTableros(), BorderLayout.CENTER);
		contentPane.add(getArma_panel(), BorderLayout.WEST);
		contentPane.add(getPanel(), BorderLayout.EAST);
		contentPane.add(getPanel_2(), BorderLayout.SOUTH);
		contentPane.add(getPanel_3(), BorderLayout.NORTH);
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
			tableros.add(getLblNewLabel());
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
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new GridLayout(10, 1, 0, 0));
			panel.add(getPanel_1());
		}
		return panel;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
		}
		return panel_1;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("       ");
		}
		return lblNewLabel;
	}
	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
		}
		return panel_2;
	}
	private JPanel getPanel_3() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
		}
		return panel_3;
	}
}
