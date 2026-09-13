package arenaelemental.view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;

public class PainelGameOver extends PainelImagemFundo {
    public PainelGameOver(Runnable voltarAoMenu) {
        super("/sprite/Plano_de_fundo.jpeg");
        limitarAlturaImagemFundo(Constantes.ALTURA_JANELA);
        setLayout(null);

        JLabel titulo = new JLabel("GAME OVER", SwingConstants.CENTER);
        titulo.setBounds(200, 170, 700, 80);
        titulo.setFont(new Font("Arial", Font.BOLD, 58));
        titulo.setForeground(Color.WHITE);
        add(titulo);

        JLabel mensagem = new JLabel("Todos os seus monstrinhos foram derrotados.",
                SwingConstants.CENTER);
        mensagem.setBounds(200, 260, 700, 40);
        mensagem.setFont(new Font("Arial", Font.BOLD, 20));
        mensagem.setForeground(Color.WHITE);
        add(mensagem);

        JButton menu = new JButton("VOLTAR AO MENU");
        menu.setBounds(350, 360, 400, 60);
        menu.setFont(new Font("Arial", Font.BOLD, 18));
        menu.setFocusPainted(false);
        menu.addActionListener(evento -> voltarAoMenu.run());
        add(menu);
    }
}
