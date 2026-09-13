package arenaelemental.view;

import arenaelemental.modelo.Monstrinho;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

public class PainelArena extends PainelImagemFundo {
    private final BarraVida vidaJogador;
    private final BarraVida vidaSelvagem;
    private final JLabel nomeJogador = new JLabel();
    private final JLabel nomeSelvagem = new JLabel();

    public PainelArena() {
        super("/sprite/Plano_de_fundo.jpeg");
        setLayout(null);
        vidaJogador = new BarraVida(80, 55, 300, 18);
        vidaSelvagem = new BarraVida(720, 55, 300, 18);
        configurarNome(nomeJogador, 80, 25);
        configurarNome(nomeSelvagem, 720, 25);
        add(nomeJogador);
        add(nomeSelvagem);
        add(vidaJogador);
        add(vidaSelvagem);
    }

    public void atualizar(Monstrinho jogador, Monstrinho selvagem) {
        removerSprites();
        if (jogador != null) {
            CriaturaSprite sprite = new CriaturaSprite(jogador, 100, 90, 280, 230);
            add(sprite);
            spriteJogadorRef = sprite;
            nomeJogador.setText(jogador.nome);
            vidaJogador.atualizar(jogador);
        }
        if (selvagem != null) {
            CriaturaSprite sprite = new CriaturaSprite(selvagem, 700, 90, 280, 230);
            add(sprite);
            spriteSelvagemRef = sprite;
            nomeSelvagem.setText(selvagem.nome);
            vidaSelvagem.atualizar(selvagem);
        }
        revalidate();
        repaint();
    }

    private CriaturaSprite spriteJogadorRef;
    private CriaturaSprite spriteSelvagemRef;

    private void removerSprites() {
        if (spriteJogadorRef != null) {
            remove(spriteJogadorRef);
        }
        if (spriteSelvagemRef != null) {
            remove(spriteSelvagemRef);
        }
    }

    private void configurarNome(JLabel label, int x, int y) {
        label.setBounds(x, y, 300, 25);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("SansSerif", Font.BOLD, 18));
        label.setOpaque(true);
        label.setBackground(new Color(20, 28, 38, 190));
    }

}
