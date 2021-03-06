package is.vista;

import is.controlador.Controlador;
import is.modelo.Variables;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.BoxLayout;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionListener;

public class Tienda extends JFrame implements Observer {

	private static Tienda miTienda=null;

	private JPanel contentPane;
	private static JLabel lbl_misil;
	private static JLabel lbl_radar;
	private static JLabel lbl_escudo;
	private static JLabel lblReparacion;
	private static JButton btn_misil;
	private static JButton btn_radar;
	private static JButton btn_escudo;
	private static JButton btn_reparacion;
	private JLabel fotoMisil;
	private JLabel fotoRadar;
	private JLabel fotoEscudo;
	private JLabel fotoReparacion;
	private static JLabel lbl_compra;
	private JPanel panel;

	/**
	 * Create the frame.
	 */
	private Tienda() {
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		int a_width = 460;
		int a_height = 360;
		int height = pantalla.height/2-a_height/2;
		int width = pantalla.width/2-a_width/2;


		setIconImage(Toolkit.getDefaultToolkit().getImage(Tienda.class.getResource("/resource/tienda.png")));
		setTitle("Tienda");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setAlwaysOnTop(false);
		setBounds(width, height, a_width, a_height);
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
	public static JLabel getLbl_compra() {
		if (lbl_compra == null) {
			lbl_compra = new JLabel("Bienvenido a la tienda", SwingConstants.CENTER);
		}
		return lbl_compra;
	}
	public static JLabel getLbl_misil() {
		if (lbl_misil == null) {
			lbl_misil = new JLabel("\u00A0\u00A0\u00A0Misil ("+Variables.getMisVariables().getnMaxMisil()+")");
		}
		return lbl_misil;
	}
	public static JLabel getLbl_radar() {
		if (lbl_radar == null) {
			lbl_radar = new JLabel("\u00A0\u00A0\u00A0Radar ("+Variables.getMisVariables().getnMaxRadar()+")");
		}
		return lbl_radar;
	}
	public static JLabel getLbl_escudo() {
		if (lbl_escudo == null) {
			lbl_escudo = new JLabel("\u00A0\u00A0\u00A0Escudo ("+Variables.getMisVariables().getnMaxEscudo()+")");
		}
		return lbl_escudo;
	}
	public static JLabel getLblReparacion() {
		if (lblReparacion == null) {
			lblReparacion = new JLabel("\u00A0\u00A0Reparacion ("+Variables.getMisVariables().getnMaxReparacion()+")");
		}
		return lblReparacion;
	}
	public static JButton getBtn_misil() {
		if (btn_misil == null) {
			btn_misil = new JButton(Variables.getMisVariables().getPrecioMisil() +"g");
			btn_misil.addActionListener(Controlador.getControlador());
		}
		return btn_misil;
	}
	public static JButton getBtn_radar() {
		if (btn_radar == null) {
			btn_radar = new JButton(Variables.getMisVariables().getPrecioRadar() +"g");
			btn_radar.addActionListener(Controlador.getControlador());
		}
		return btn_radar;
	}
	public static JButton getBtn_escudo() {
		if (btn_escudo == null) {
			btn_escudo = new JButton(Variables.getMisVariables().getPrecioEscudo() +"g");
			btn_escudo.addActionListener(Controlador.getControlador());
		}
		return btn_escudo;
	}
	public static JButton getBtn_reparacion() {
		if (btn_reparacion == null) {
			btn_reparacion = new JButton(Variables.getMisVariables().getPrecioReparacion()+"g");
			btn_reparacion.addActionListener(Controlador.getControlador());
		}
		return btn_reparacion;
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
	private JLabel getFotoReparacion() {
		if (fotoReparacion == null) {
			fotoReparacion = new JLabel("");
			fotoReparacion.setIcon(new ImageIcon(Tienda.class.getResource("/resource/repair64.png")));
		}
		return fotoReparacion;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new GridLayout(4, 3, 0, 0));
			panel.add(getLbl_misil());
			panel.add(getFotoMisil());
			panel.add(getBtn_misil());
			panel.add(getLbl_radar());
			panel.add(getFotoRadar());
			panel.add(getBtn_radar());
			panel.add(getLbl_escudo());
			panel.add(getFotoEscudo());
			panel.add(getBtn_escudo());
			panel.add(getLblReparacion());
			panel.add(getFotoReparacion());
			panel.add(getBtn_reparacion());
		}
		return panel;
	}

	@Override
	public void update(Observable o, Object arg)
	{
		Object[] lista=(Object[]) arg;
		String cad=(String) lista[0];

		if(cad.equalsIgnoreCase("TIENDA"))
		{
			((JLabel) lista[1]).setText((String) lista[2]);
		}
		else if(cad.equalsIgnoreCase("DESACTIVAR"))
		{
			((JButton) lista[1]).setEnabled(false);
		}
		else if(cad.equalsIgnoreCase("REDUCIR"))
		{
			((JLabel) lista[1]).setText((String) lista[2]);
		}
	}
}
