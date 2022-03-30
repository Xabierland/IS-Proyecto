package is.vista;

import is.controlador.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JFormattedTextField;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JButton;

import is.modelo.Variables;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSlider;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class Config extends JFrame {

	private static JPanel pnl_main;
	private JPanel pnl_configuracion;
	private JLabel lbl_TamanoTablero;
	private JPanel pnl_start;
	private static JButton btn_start;
	private JLabel lbl_dificultad;
	private static JSlider slider_ia;
	private JLabel lbl_dinero_init;
	private static JTextField text_money;
	private static JTextField txt_regard;
	private JLabel lbl_dinero_barco;
	private static JLabel lbl_num_Dificult;
	private JLabel lbl_cheats;
	private static JCheckBox btn_cheats;
	private static Config miConfig=null;
	private static JSlider slider_escala;
	private static JLabel lbl_escala;
	/**
	 * Create the frame.
	 */
	private Config() {
		setResizable(false);
		setTitle("Configuracion");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Config.class.getResource("/resource/ajustes.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(500, 100, 397, 400);
		pnl_main = new JPanel();
		pnl_main.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnl_main);
		pnl_main.setLayout(new BorderLayout(0, 0));
		pnl_main.add(getPnl_configuracion(), BorderLayout.CENTER);
		pnl_main.add(getPnl_start(), BorderLayout.SOUTH);
	}
	public static Config getMiConfig()
	{
		if(miConfig==null)
		{
			miConfig=new Config();
		}
		return miConfig;
	}
	private JPanel getPnl_configuracion() {
		if (pnl_configuracion == null) {
			pnl_configuracion = new JPanel();
			pnl_configuracion.setLayout(new MigLayout("", "[50px,grow][30px,grow,center][142px,center]", "[230][230px,grow][230][230][230]"));
			pnl_configuracion.add(getLbl_TamanoTablero(), "cell 0 0,grow");
			pnl_configuracion.add(getLbl_escala(), "cell 1 0");
			pnl_configuracion.add(getSlider_escala(), "cell 2 0");
			pnl_configuracion.add(getLbl_dificultad(), "cell 0 1");
			pnl_configuracion.add(getLbl_num_Dificult(), "cell 1 1");
			pnl_configuracion.add(getSlider_ia(), "cell 2 1");
			pnl_configuracion.add(getLbl_dinero_init(), "cell 0 2");
			pnl_configuracion.add(getText_money(), "cell 2 2,growx");
			pnl_configuracion.add(getLbl_dinero_barco(), "cell 0 3");
			pnl_configuracion.add(getTxt_regard(), "cell 2 3,growx");
			pnl_configuracion.add(getLbl_cheats(), "cell 0 4");
			pnl_configuracion.add(getBtn_cheats(), "cell 2 4");
		}
		return pnl_configuracion;
	}
	private JLabel getLbl_TamanoTablero() {
		if (lbl_TamanoTablero == null) {
			lbl_TamanoTablero = new JLabel("Escala del juego");
		}
		return lbl_TamanoTablero;
	}
	private JPanel getPnl_start() {
		if (pnl_start == null) {
			pnl_start = new JPanel();
			pnl_start.add(getBtn_start());
		}
		return pnl_start;
	}
	public static JButton getBtn_start() {
		if (btn_start == null) {
			btn_start = new JButton("EMPIEZA");
			btn_start.addActionListener(Controlador.getControlador());
		}
		return btn_start;
	}
	private JLabel getLbl_dificultad() {
		if (lbl_dificultad == null) {
			lbl_dificultad = new JLabel("Dificultad de la IA:");
		}
		return lbl_dificultad;
	}
	public static JSlider getSlider_ia() {
		if (slider_ia == null) {
			slider_ia = new JSlider();
			slider_ia.setSnapToTicks(true);
			slider_ia.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					getLbl_num_Dificult().setText(String.valueOf(11-slider_ia.getValue()));
				}
			});
			slider_ia.setPaintTicks(true);
			slider_ia.setMinorTickSpacing(1);
			slider_ia.setValue(10);
			slider_ia.setMinimum(1);
			slider_ia.setMaximum(10);
		}
		return slider_ia;
	}
	private JLabel getLbl_dinero_init() {
		if (lbl_dinero_init == null) {
			lbl_dinero_init = new JLabel("Dinero inicial:");
		}
		return lbl_dinero_init;
	}
	public static JTextField getText_money() {
		if (text_money == null) {
			text_money = new JTextField();
			text_money.setHorizontalAlignment(SwingConstants.RIGHT);
			text_money.setText(String.valueOf(Variables.getMisVariables().getDineroInicial()));
			text_money.setColumns(10);
		}
		return text_money;
	}
	public static JTextField getTxt_regard() {
		if (txt_regard == null) {
			txt_regard = new JTextField();
			txt_regard.setText(String.valueOf(Variables.getMisVariables().getDineroPorHundir()));
			txt_regard.setHorizontalAlignment(SwingConstants.RIGHT);
			txt_regard.setColumns(10);
		}
		return txt_regard;
	}
	private JLabel getLbl_dinero_barco() {
		if (lbl_dinero_barco == null) {
			lbl_dinero_barco = new JLabel("Dinero por barco hundido");
		}
		return lbl_dinero_barco;
	}
	private static JLabel getLbl_num_Dificult() {
		if (lbl_num_Dificult == null) {
			lbl_num_Dificult = new JLabel("10");
			lbl_num_Dificult.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lbl_num_Dificult.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		return lbl_num_Dificult;
	}
	private JLabel getLbl_cheats() {
		if (lbl_cheats == null) {
			lbl_cheats = new JLabel("Cheat Codes:");
		}
		return lbl_cheats;
	}
	public static JCheckBox getBtn_cheats() {
		if (btn_cheats == null) {
			btn_cheats = new JCheckBox("");
			btn_cheats.setSelected(true);
		}
		return btn_cheats;
	}
	public static JSlider getSlider_escala() {
		if (slider_escala == null) {
			slider_escala = new JSlider();
			slider_escala.setToolTipText("");
			slider_escala.setSnapToTicks(true);
			slider_escala.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent arg0) {
					getLbl_escala().setText("x"+slider_escala.getValue());
				}
			});
			slider_escala.setMinorTickSpacing(1);
			slider_escala.setPaintTicks(true);
			slider_escala.setValue(1);
			slider_escala.setMaximum(3);
			slider_escala.setMinimum(1);
		}
		return slider_escala;
	}
	private static JLabel getLbl_escala() {
		if (lbl_escala == null) {
			lbl_escala = new JLabel("1");
			lbl_escala.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		return lbl_escala;
	}
}
