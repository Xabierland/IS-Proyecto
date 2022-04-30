package is;

import is.modelo.Partida;
import is.vista.*;

import java.awt.*;

public class main
{
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Config frame = Config.getMiConfig();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
