package arenaelemental.treinador;

import arenaelemental.modelo.Monstrinho;
import arenaelemental.view.CriaturaSprite;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;

public final class Bestiario {
    private Bestiario() {
    }

    public static void exibir(Component janela, List<Monstrinho> criaturas,
            List<Monstrinho> equipe) {
        JDialog dialogo = new JDialog(javax.swing.SwingUtilities.getWindowAncestor(janela),
                "BAG", java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        JPanel lista = new JPanel();
        lista.setLayout(new javax.swing.BoxLayout(lista, javax.swing.BoxLayout.Y_AXIS));
        JScrollPane rolagem = new JScrollPane(lista);
        rolagem.setPreferredSize(new java.awt.Dimension(560, 430));

        atualizarLista(lista, criaturas, equipe, dialogo);
        dialogo.add(rolagem);
        dialogo.pack();
        dialogo.setLocationRelativeTo(janela);
        dialogo.setVisible(true);
    }

    private static void atualizarLista(JPanel lista, List<Monstrinho> criaturas,
            List<Monstrinho> equipe, JDialog dialogo) {
        lista.removeAll();
        for (int indice = 0; indice < criaturas.size(); indice++) {
            Monstrinho criatura = criaturas.get(indice);
            JPanel linha = new JPanel(new BorderLayout(8, 4));
            linha.setBorder(BorderFactory.createLineBorder(new Color(80, 90, 115), 1));
            linha.setBackground(new Color(42, 48, 65));
            linha.setPreferredSize(new java.awt.Dimension(540, 105));
            linha.add(new CriaturaSprite(criatura, 8, 8, 120, 95), BorderLayout.WEST);

            JLabel nome = new JLabel("<html><b>" + (indice + 1) + ". " + criatura.nome
                    + "</b><br>Nível " + criatura.nivel + "<br>" + criatura.tipo + "</html>");
            nome.setForeground(Color.WHITE);
            nome.setFont(new Font("Arial", Font.PLAIN, 15));
            linha.add(nome, BorderLayout.CENTER);

            JPanel controles = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JButton subir = new JButton("SUBIR");
            JButton descer = new JButton("DESCER");
            int posicao = indice;
            subir.setEnabled(posicao > 0);
            descer.setEnabled(posicao < criaturas.size() - 1);
            subir.addActionListener(evento -> {
                trocar(criaturas, posicao, -1);
                sincronizarOrdemEquipe(criaturas, equipe);
                atualizarLista(lista, criaturas, equipe, dialogo);
                dialogo.pack();
            });
            descer.addActionListener(evento -> {
                trocar(criaturas, posicao, 1);
                sincronizarOrdemEquipe(criaturas, equipe);
                atualizarLista(lista, criaturas, equipe, dialogo);
                dialogo.pack();
            });
            controles.add(subir);
            controles.add(descer);
            JButton soltar = new JButton("SOLTAR");
            soltar.setEnabled(!equipe.contains(criatura));
            soltar.setToolTipText("Soltar esta criatura para liberar espaço na bag");
            soltar.addActionListener(evento -> {
                int resposta = javax.swing.JOptionPane.showConfirmDialog(dialogo,
                        "Soltar " + criatura.nome + "?", "Soltar criatura",
                        javax.swing.JOptionPane.YES_NO_OPTION);
                if (resposta == javax.swing.JOptionPane.YES_OPTION) {
                    criaturas.remove(criatura);
                    atualizarLista(lista, criaturas, equipe, dialogo);
                    dialogo.pack();
                }
            });
            controles.add(soltar);
            linha.add(controles, BorderLayout.EAST);
            lista.add(linha);
        }
        lista.revalidate();
        lista.repaint();
    }

    private static void trocar(List<Monstrinho> criaturas, int indice, int deslocamento) {
        int destino = indice + deslocamento;
        if (destino < 0 || destino >= criaturas.size()) {
            return;
        }
        Monstrinho temporaria = criaturas.get(indice);
        criaturas.set(indice, criaturas.get(destino));
        criaturas.set(destino, temporaria);
    }

    private static void sincronizarOrdemEquipe(List<Monstrinho> criaturas,
            List<Monstrinho> equipe) {
        List<Monstrinho> ordemEquipe = new java.util.ArrayList<>();
        for (Monstrinho criatura : criaturas) {
            if (equipe.contains(criatura)) {
                ordemEquipe.add(criatura);
            }
        }
        equipe.clear();
        equipe.addAll(ordemEquipe);
    }
}
