package arenaelemental.view;

import arenaelemental.modelo.Monstrinho;
import javax.swing.JProgressBar;
import java.awt.Color;

public class BarraVida extends JProgressBar {
    public BarraVida(int x, int y, int largura, int altura) {
        super(0, 100);
        setBounds(x, y, largura, altura);
        setStringPainted(false);
        setForeground(new Color(70, 190, 90));
        setBackground(new Color(80, 35, 45));
    }

    public void atualizar(Monstrinho criatura) {
        int percentual = criatura.vidaMaxima == 0 ? 0 : criatura.vida * 100 / criatura.vidaMaxima;
        setValue(Math.max(0, Math.min(100, percentual)));
        setForeground(percentual <= 25 ? new Color(220, 70, 60) : new Color(70, 190, 90));
    }
}
