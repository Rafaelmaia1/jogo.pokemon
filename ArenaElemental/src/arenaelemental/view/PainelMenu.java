package arenaelemental.view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;

public class PainelMenu extends PainelImagemFundo {
    public PainelMenu(Runnable aoIniciar, Runnable aoSair) {
        super("/sprite/Plano_de_fundo.jpeg");
        limitarAlturaImagemFundo(Constantes.ALTURA_JANELA);
        setLayout(null);

        JLabel titulo = new JLabel("ARENA ELEMENTAL", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 52));
        titulo.setForeground(Color.WHITE);
        titulo.setBounds(100, 70, 900, 80);
        add(titulo);

        JLabel subtitulo = new JLabel("CAPTURE • TREINE • BATALHE", SwingConstants.CENTER);
        subtitulo.setFont(new Font("Arial", Font.BOLD, 20));
        subtitulo.setForeground(new Color(180, 190, 210));
        subtitulo.setBounds(200, 150, 700, 40);
        add(subtitulo);

        JButton jogar = criarBotao("NOVA JORNADA", 350, 260, 400, 65);
        jogar.addActionListener(evento -> aoIniciar.run());
        add(jogar);

        JButton sair = criarBotao("SAIR", 350, 350, 400, 55);
        sair.addActionListener(evento -> aoSair.run());
        add(sair);
    }

    private JButton criarBotao(String texto, int x, int y, int largura, int altura) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Arial", Font.BOLD, 18));
        botao.setBounds(x, y, largura, altura);
        botao.setFocusPainted(false);
        return botao;
    }
}
