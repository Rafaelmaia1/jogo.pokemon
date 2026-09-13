package arenaelemental.view;

import arenaelemental.batalha.EstadoJogo;
import arenaelemental.treinador.Treinador;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.CardLayout;

public class JanelaPrincipal extends JFrame {
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel cartas = new JPanel(cardLayout);
    private final EstadoJogo estado = new EstadoJogo();
    private final Treinador treinador = estado.getTreinador();
    private final PainelBatalha painelBatalha;
    private final PainelEquipe painelEquipe;
    private final PainelEscolha painelEscolha;

    public JanelaPrincipal() {
        setTitle("Arena Elemental - " + treinador.getNome());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Constantes.LARGURA_JANELA, Constantes.ALTURA_JANELA);
        setLocationRelativeTo(null);
        setResizable(false);

        painelBatalha = new PainelBatalha(this, estado);
        painelEquipe = new PainelEquipe(this, estado);
        painelEscolha = new PainelEscolha(estado.getBanco(), estado.getEquipe(), this,
                this::iniciarJornada);
        cartas.add(new PainelMenu(this::novaJornada, () -> System.exit(0)), "MENU");
        cartas.add(painelEscolha, "SELECAO");
        cartas.add(painelBatalha, "BATALHA");
        cartas.add(painelEquipe, "EQUIPE");
        cartas.add(new PainelGameOver(() -> mostrar("MENU")), "GAME_OVER");
        add(cartas);
        mostrar("MENU");
    }

    public void mostrar(String nome) {
        if ("EQUIPE".equals(nome)) {
            painelEquipe.atualizar();
        }
        cardLayout.show(cartas, nome);
    }

    public EstadoJogo getEstado() {
        return estado;
    }

    public Treinador getTreinador() {
        return treinador;
    }

    private void novaJornada() {
        estado.novaJornada();
        painelEscolha.resetarSelecao();
        mostrar("SELECAO");
    }

    private void iniciarJornada() {
        estado.getBag().clear();
        estado.getBag().addAll(estado.getEquipe());
        painelBatalha.iniciar();
        mostrar("BATALHA");
    }

    public static void abrir() {
        SwingUtilities.invokeLater(() -> new JanelaPrincipal().setVisible(true));
    }
}
