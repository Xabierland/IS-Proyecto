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

public class Config extends JFrame {

	private JPanel pnl_main;
	private JPanel pnl_configuracion;
	private JLabel lbl_TamanoTablero;
	private JPanel pnl_start;
	private static JButton btn_start;
	private JLabel lbl_dificultad;
	private JSlider slider;
	private JLabel lbl_multiplicadorBarcos;
	private JLabel lblNewLabel;
	private static JTextField textField;
	private static JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JLabel lblNewLabel_1;
	private JLabel lbl_num_Dificult;
	private JLabel lbl_cheats;
	private JCheckBox chckbxNewCheckBox;
	private static Config miConfig=null;
	/**
	 * Create the frame.
	 */
	private Config() {
		setResizable(false);
		setTitle("Configuracion");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Config.class.getResource("/resource/ajustes.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(500, 100, 440, 400);
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
			pnl_configuracion.setLayout(new MigLayout("", "[142px,grow][30px,grow,right][142px,center]", "[230][230px,grow][230,grow][230][230][230]"));
			pnl_configuracion.add(getLbl_TamanoTablero(), "cell 0 0,grow");
			pnl_configuracion.add(getTextField_1(), "cell 2 0,growx");
			pnl_configuracion.add(getLbl_dificultad(), "cell 0 1");
			pnl_configuracion.add(getLbl_num_Dificult(), "cell 1 1");
			pnl_configuracion.add(getSlider(), "cell 2 1");
			pnl_configuracion.add(getLbl_multiplicadorBarcos(), "cell 0 2");
			pnl_configuracion.add(getTextField(), "cell 2 2,growx");
			pnl_configuracion.add(getLblNewLabel(), "cell 0 3");
			pnl_configuracion.add(getTextField_2(), "cell 2 3,growx");
			pnl_configuracion.add(getLblNewLabel_1(), "cell 0 4");
			pnl_configuracion.add(getTextField_3(), "cell 2 4,growx");
			pnl_configuracion.add(getLbl_cheats(), "cell 0 5");
			pnl_configuracion.add(getChckbxNewCheckBox(), "cell 2 5");
		}
		return pnl_configuracion;
	}
	private JLabel getLbl_TamanoTablero() {
		if (lbl_TamanoTablero == null) {
			lbl_TamanoTablero = new JLabel("Tama\u00F1o del tablero:");
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
	private JSlider getSlider() {
		if (slider == null) {
			slider = new JSlider();
			slider.setPaintTicks(true);
			slider.setMinorTickSpacing(1);
			slider.setValue(10);
			slider.setMinimum(1);
			slider.setMaximum(10);
		}
		return slider;
	}
	private JLabel getLbl_multiplicadorBarcos() {
		if (lbl_multiplicadorBarcos == null) {
			lbl_multiplicadorBarcos = new JLabel("Multiplicar numero de barcos por:");
		}
		return lbl_multiplicadorBarcos;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Dinero inicial:");
		}
		return lblNewLabel;
	}
	public static JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setText("1");
			textField.setHorizontalAlignment(SwingConstants.RIGHT);
			textField.setColumns(10);
		}
		return textField;
	}
	public static JTextField getTextField_1() {
		if (textField_1 == null) {
			textField_1 = new JTextField();
			textField_1.addFocusListener(Controlador.getControlador());
			textField_1.setHorizontalAlignment(SwingConstants.RIGHT);
			textField_1.setText("10");
			textField_1.setColumns(10);
		}
		return textField_1;
	}
	private JTextField getTextField_2() {
		if (textField_2 == null) {
			textField_2 = new JTextField();
			textField_2.setHorizontalAlignment(SwingConstants.RIGHT);
			textField_2.setText("3000");
			textField_2.setColumns(10);
		}
		return textField_2;
	}
	private JTextField getTextField_3() {
		if (textField_3 == null) {
			textField_3 = new JTextField();
			textField_3.setText("500");
			textField_3.setHorizontalAlignment(SwingConstants.RIGHT);
			textField_3.setColumns(10);
		}
		return textField_3;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("Dinero por barco hundido");
		}
		return lblNewLabel_1;
	}
	private JLabel getLbl_num_Dificult() {
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
	private JCheckBox getChckbxNewCheckBox() {
		if (chckbxNewCheckBox == null) {
			chckbxNewCheckBox = new JCheckBox("");
		}
		return chckbxNewCheckBox;
	}
}
