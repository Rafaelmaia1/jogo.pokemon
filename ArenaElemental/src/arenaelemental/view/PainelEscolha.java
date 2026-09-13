package arenaelemental.view;

import arenaelemental.modelo.Monstrinho;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

public class PainelEscolha extends PainelImagemFundo {
    private final List<Monstrinho> equipe;
    private final Component janela;
    private final Runnable aoConfirmar;
    private final JLabel contador;
    private final List<CartaoCriatura> cartas = new ArrayList<>();

    public PainelEscolha(List<Monstrinho> banco, List<Monstrinho> equipe,
            Component janela, Runnable aoConfirmar) {
        super("/sprite/Plano_de_fundo.jpeg");
        limitarAlturaImagemFundo(Constantes.ALTURA_JANELA);
        this.equipe = equipe;
        this.janela = janela;
        this.aoConfirmar = aoConfirmar;
        setLayout(null);

        JLabel titulo = new JLabel("ESCOLHA 3 MONSTRINHOS", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 32));
        titulo.setForeground(Color.WHITE);
        titulo.setBounds(150, 15, 800, 50);
        add(titulo);

        contador = new JLabel("Selecionados: " + equipe.size() + " / 3", SwingConstants.CENTER);
        contador.setFont(new Font("Arial", Font.BOLD, 19));
        contador.setForeground(Color.WHITE);
        contador.setBounds(350, 65, 400, 30);
        add(contador);

        for (int indice = 0; indice < banco.size(); indice++) {
            int coluna = indice % 4;
            int linha = indice / 4;
            Monstrinho criatura = banco.get(indice);
            CartaoCriatura[] referencia = new CartaoCriatura[1];
            referencia[0] = new CartaoCriatura(criatura, 35 + coluna * 250,
                    110 + linha * 155, () -> alternarSelecao(criatura, referencia[0]));
            add(referencia[0]);
            cartas.add(referencia[0]);
        }

        JButton confirmar = criarBotao("CONFIRMAR EQUIPE", 350, 580, 400, 55);
        confirmar.addActionListener(evento -> confirmarEquipe());
        add(confirmar);
    }

    public void resetarSelecao() {
        equipe.clear();
        for (CartaoCriatura carta : cartas) {
            carta.setSelecionado(false);
        }
        contador.setText("Selecionados: 0 / 3");
    }

    private void alternarSelecao(Monstrinho criatura, CartaoCriatura carta) {
        if (equipe.contains(criatura)) {
            equipe.remove(criatura);
            carta.setSelecionado(false);
        } else {
            if (equipe.size() >= 3) {
                JOptionPane.showMessageDialog(janela, "Você já escolheu 3!");
                return;
            }
            equipe.add(criatura);
            carta.setSelecionado(true);
        }
        contador.setText("Selecionados: " + equipe.size() + " / 3");
    }

    private void confirmarEquipe() {
        if (equipe.size() != 3) {
            JOptionPane.showMessageDialog(janela, "Escolha exatamente 3 monstrinhos.");
            return;
        }
        aoConfirmar.run();
    }

    private JButton criarBotao(String texto, int x, int y, int largura, int altura) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Arial", Font.BOLD, 18));
        botao.setBounds(x, y, largura, altura);
        botao.setFocusPainted(false);
        return botao;
    }
}
