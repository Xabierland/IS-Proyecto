package is.vista;

import is.controlador.Controlador;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class Tienda extends JFrame {

	private static Tienda miTienda=null;

	private JPanel contentPane;
	private JLabel botonBuyMisil;
	private JLabel botonBuyRadar;
	private JLabel lblNewEscudo;
	public static JButton comprarMisil;
	public static JButton comprarRadar;
	public static JButton comprarEscudo;
	private JLabel fotoMisil;
	private JLabel fotoRadar;
	private JLabel fotoEscudo;
	public static JLabel buy_msg;
	private JPanel EMPTY1;
	private JPanel EMPTY2;

	/**
	 * Create the frame.
	 */
	private Tienda() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Tienda.class.getResource("/resource/tienda.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 457, 311);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(4, 3, 0, 0));
		contentPane.add(getBotonBuyMisil());
		contentPane.add(getFotoMisil());
		contentPane.add(getComprarMisil());
		contentPane.add(getBotonBuyRadar());
		contentPane.add(getFotoRadar());
		contentPane.add(getComprarRadar());
		contentPane.add(getLblNewEscudo());
		contentPane.add(getFotoEscudo());
		contentPane.add(getComprarEscudo());
		contentPane.add(getEMPTY1());
		contentPane.add(getBuy_msg());
		contentPane.add(getEMPTY2());
	}

	public static Tienda getTienda()
	{
		if(miTienda==null)
		{
			miTienda=new Tienda();
		}
		return miTienda;
	}

	private JLabel getBotonBuyMisil() {
		if (botonBuyMisil == null) {
			botonBuyMisil = new JLabel("\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0Misil");
		}
		return botonBuyMisil;
	}
	private JLabel getBotonBuyRadar() {
		if (botonBuyRadar == null) {
			botonBuyRadar = new JLabel("\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0Radar");
		}
		return botonBuyRadar;
	}
	private JLabel getLblNewEscudo() {
		if (lblNewEscudo == null) {
			lblNewEscudo = new JLabel("\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0Escudo");
		}
		return lblNewEscudo;
	}
	private JButton getComprarMisil() {
		if (comprarMisil == null) {
			comprarMisil = new JButton("500g");
			comprarMisil.addActionListener(Controlador.getControlador());
		}
		return comprarMisil;
	}
	private JButton getComprarRadar() {
		if (comprarRadar == null) {
			comprarRadar = new JButton("1000g");
			comprarRadar.addActionListener(Controlador.getControlador());
		}
		return comprarRadar;
	}
	private JButton getComprarEscudo() {
		if (comprarEscudo == null) {
			comprarEscudo = new JButton("1000g");
			comprarEscudo.addActionListener(Controlador.getControlador());
		}
		return comprarEscudo;
	}
	private JLabel getFotoMisil() {
		if (fotoMisil == null) {
			fotoMisil = new JLabel("");
			fotoMisil.setIcon(new ImageIcon(Tienda.class.getResource("/resource/misil64.png")));
		}
		return fotoMisil;
	}
	private JLabel getFotoRadar() {
		if (fotoRadar == null) {
			fotoRadar = new JLabel("");
			fotoRadar.setIcon(new ImageIcon(Tienda.class.getResource("/resource/radar64.png")));
		}
		return fotoRadar;
	}
	private JLabel getFotoEscudo() {
		if (fotoEscudo == null) {
			fotoEscudo = new JLabel("");
			fotoEscudo.setIcon(new ImageIcon(Tienda.class.getResource("/resource/shield64.png")));
		}
		return fotoEscudo;
	}
	private JLabel getBuy_msg() {
		if (buy_msg == null) {
			buy_msg = new JLabel("");
		}
		return buy_msg;
	}
	private JPanel getEMPTY1() {
		if (EMPTY1 == null) {
			EMPTY1 = new JPanel();
		}
		return EMPTY1;
	}
	private JPanel getEMPTY2() {
		if (EMPTY2 == null) {
			EMPTY2 = new JPanel();
		}
		return EMPTY2;
	}
}
