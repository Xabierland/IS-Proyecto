package is.vista;

import is.controlador.Controlador;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;

public class Tienda extends JFrame {

	private static Tienda miTienda=null;

	private JPanel contentPane;
	private JLabel lbl_misil;
	private JLabel lbl_radar;
	private JLabel lbl_escudo;
	private static JButton btn_misil;
	private static JButton btn_radar;
	private static JButton btn_escudo;
	private JLabel fotoMisil;
	private JLabel fotoRadar;
	private JLabel fotoEscudo;
	private static JLabel lbl_compra;
	private JPanel panel;

	/**
	 * Create the frame.
	 */
	private Tienda() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Tienda.class.getResource("/resource/tienda.png")));
		setTitle("Tienda");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 457, 311);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPanel(), BorderLayout.CENTER);
		contentPane.add(getLbl_compra(), BorderLayout.SOUTH);
	}

	public static Tienda getTienda()
	{
		if(miTienda==null)
		{
			miTienda=new Tienda();
		}
		return miTienda;
	}

	private JLabel getLbl_misil() {
		if (lbl_misil == null) {
			lbl_misil = new JLabel("\u00A0\u00A0\u00A0Misil");
		}
		return lbl_misil;
	}
	private JLabel getLbl_radar() {
		if (lbl_radar == null) {
			lbl_radar = new JLabel("\u00A0\u00A0\u00A0Radar");
		}
		return lbl_radar;
	}
	private JLabel getLbl_escudo() {
		if (lbl_escudo == null) {
			lbl_escudo = new JLabel("\u00A0\u00A0\u00A0Escudo");
		}
		return lbl_escudo;
	}
	public static JButton getBtn_misil() {
		if (btn_misil == null) {
			btn_misil = new JButton("500g");
			btn_misil.addActionListener(Controlador.getControlador());
		}
		return btn_misil;
	}
	public static JButton getBtn_radar() {
		if (btn_radar == null) {
			btn_radar = new JButton("1000g");
			btn_radar.addActionListener(Controlador.getControlador());
		}
		return btn_radar;
	}
	public static JButton getBtn_escudo() {
		if (btn_escudo == null) {
			btn_escudo = new JButton("1000g");
			btn_escudo.addActionListener(Controlador.getControlador());
		}
		return btn_escudo;
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
	public static JLabel getLbl_compra() {
		if (lbl_compra == null) {
			lbl_compra = new JLabel("Bienvenido a la tienda", SwingConstants.CENTER);
		}
		return lbl_compra;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new GridLayout(3, 3, 0, 0));
			panel.add(getLbl_misil());
			panel.add(getFotoMisil());
			panel.add(getBtn_misil());
			panel.add(getLbl_radar());
			panel.add(getFotoRadar());
			panel.add(getBtn_radar());
			panel.add(getLbl_escudo());
			panel.add(getFotoEscudo());
			panel.add(getBtn_escudo());
		}
		return panel;
	}
}
