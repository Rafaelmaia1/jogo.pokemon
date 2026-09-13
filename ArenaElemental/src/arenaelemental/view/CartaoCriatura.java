package arenaelemental.view;

import arenaelemental.modelo.Monstrinho;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;

public class CartaoCriatura extends JPanel {
    private static final Color BORDA_PADRAO = new Color(80, 90, 115);
    private final JButton botao;

    public CartaoCriatura(Monstrinho criatura, int x, int y, Runnable aoSelecionar) {
        super(null);
        setBackground(new Color(42, 48, 65));
        setBorder(BorderFactory.createLineBorder(BORDA_PADRAO, 2));
        setBounds(x, y, 220, 150);

        JLabel nome = new JLabel(criatura.nome, SwingConstants.CENTER);
        nome.setFont(new Font("Arial", Font.BOLD, 18));
        nome.setForeground(Color.WHITE);
        nome.setBounds(0, 5, 220, 25);
        add(nome);

        JLabel tipo = new JLabel(criatura.tipo, SwingConstants.CENTER);
        tipo.setFont(new Font("Arial", Font.BOLD, 13));
        tipo.setForeground(criatura.getCor());
        tipo.setBounds(0, 30, 220, 20);
        add(tipo);
        add(new CriaturaSprite(criatura, 50, 48, 120, 65));

        botao = new JButton("ESCOLHER");
        botao.setBounds(35, 115, 150, 28);
        botao.addActionListener(evento -> aoSelecionar.run());
        add(botao);
    }

    public void setSelecionado(boolean selecionado) {
        setBorder(BorderFactory.createLineBorder(selecionado ? Color.GREEN : BORDA_PADRAO,
                selecionado ? 4 : 2));
        botao.setText(selecionado ? "SELECIONADO" : "ESCOLHER");
    }
}
