package arenaelemental.view;

import arenaelemental.batalha.EstadoJogo;
import arenaelemental.modelo.Monstrinho;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.stream.Collectors;

public class PainelEquipe extends JPanel {
    private final EstadoJogo estado;
    private final JLabel resumo = new JLabel();
    private final JList<String> lista = new JList<>();

    public PainelEquipe(JanelaPrincipal janela, EstadoJogo estado) {
        this.estado = estado;
        setLayout(new BorderLayout(8, 8));
        add(resumo, BorderLayout.NORTH);
        add(new JScrollPane(lista), BorderLayout.CENTER);
        JPanel botoes = new JPanel(new FlowLayout());
        JButton batalha = new JButton("VOLTAR À BATALHA");
        batalha.addActionListener(evento -> janela.mostrar("BATALHA"));
        botoes.add(batalha);
        JButton bag = new JButton("ABRIR BAG");
        bag.addActionListener(evento -> arenaelemental.treinador.Bestiario.exibir(
                this, estado.getBag(), estado.getEquipe()));
        botoes.add(bag);
        add(botoes, BorderLayout.SOUTH);
    }

    public void atualizar() {
        resumo.setText("Equipe: " + estado.getEquipe().size() + "/"
                + arenaelemental.treinador.Treinador.getTamanhoMaxEquipe()
                + " | Bag: " + estado.getBag().size() + "/" + EstadoJogo.LIMITE_BAG);
        lista.setListData(estado.getEquipe().stream()
                .map(this::descricao)
                .collect(Collectors.toList()).toArray(new String[0]));
    }

    private String descricao(Monstrinho criatura) {
        return criatura.nome + " | " + criatura.tipo + " | Nv. " + criatura.nivel
                + " | Vida " + criatura.vida + "/" + criatura.vidaMaxima;
    }
}
