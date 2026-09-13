package arenaelemental.view;

import arenaelemental.batalha.Batalha;
import arenaelemental.batalha.Captura;
import arenaelemental.batalha.EstadoJogo;
import arenaelemental.modelo.Monstrinho;
import arenaelemental.modelo.Ataque;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PainelBatalha extends JPanel {
    private final JanelaPrincipal janela;
    private final EstadoJogo estado;
    private final Random random;
    private final PainelArena arena = new PainelArena();
    private final JTextArea log = new JTextArea();
    private final JComboBox<String> ataques = new JComboBox<>();
    private Monstrinho jogador;
    private Monstrinho selvagem;

    public PainelBatalha(JanelaPrincipal janela, EstadoJogo estado) {
        this.janela = janela;
        this.estado = estado;
        this.random = estado.getRandom();
        setLayout(new BorderLayout(8, 8));
        add(arena, BorderLayout.CENTER);
        log.setEditable(false);
        log.setRows(5);
        add(new JScrollPane(log), BorderLayout.SOUTH);
        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
        botoes.add(ataques);
        adicionarBotao(botoes, "ATACAR", this::atacar);
        adicionarBotao(botoes, "CAPTURAR", this::capturar);
        adicionarBotao(botoes, "BAG", () -> arenaelemental.treinador.Bestiario.exibir(
                janela, estado.getBag(), estado.getEquipe()));
        adicionarBotao(botoes, "EXPLORAR", this::explorar);
        add(botoes, BorderLayout.NORTH);
    }

    public void iniciar() {
        jogador = janela.getTreinador().getAtiva();
        atualizarAtaques();
        explorar();
    }

    private void explorar() {
        if (estado.getBanco().isEmpty() || jogador == null) {
            log.append("\nEscolha uma equipe antes de explorar.");
            return;
        }
        Monstrinho encontro = estado.getBanco().get(random.nextInt(estado.getBanco().size()));
        selvagem = encontro.copiar();
        Batalha.fortalecerPorRank(selvagem, jogador.nivel, estado.getRank(), random);
        arena.atualizar(jogador, selvagem);
        log.append("\nUm " + selvagem.nome + " apareceu no rank " + estado.getRank() + "!");
    }

    private void atualizarAtaques() {
        ataques.removeAllItems();
        if (jogador == null) {
            return;
        }
        for (Ataque ataque : jogador.ataques) {
            ataques.addItem(ataque.nome);
        }
    }

    private void atacar() {
        if (!batalhaDisponivel())
            return;
        Ataque ataque = jogador.ataques[ataques.getSelectedIndex()];
        log.append("\n" + jogador.nome + " usou " + ataque.nome + ".");
        log.append(Batalha.aplicarEfeito(ataque, jogador, selvagem, random));
        Batalha.ResultadoDano resultado = null;
        if (causaDano(ataque)) {
            resultado = Batalha.atacar(jogador, selvagem, random);
            log.append("\nCausou " + resultado.valor + " de dano.");
        }
        if (selvagem.vida <= 0) {
            jogador.ganharXP(25);
            estado.avancarRank();
            log.append("\n" + selvagem.nome + " foi derrotado! XP e rank aumentaram.");
            trocarOponente();
        } else {
            Ataque contraAtaque = selvagem.ataques[random.nextInt(selvagem.ataques.length)];
            log.append("\n" + selvagem.nome + " usou " + contraAtaque.nome + ".");
            log.append(Batalha.aplicarEfeito(contraAtaque, selvagem, jogador, random));
            if (causaDano(contraAtaque)) {
                Batalha.atacar(selvagem, jogador, random);
            }
        }
        trocarSeDerrotado();
        arena.atualizar(jogador, selvagem);
    }

    private boolean causaDano(Ataque ataque) {
        return !ataque.nome.equals("Intimidar")
                && !ataque.nome.equals("Reduzir Ataque")
                && !ataque.nome.equals("Enfraquecer")
                && !ataque.nome.equals("Reduzir Defesa")
                && !ataque.nome.equals("Fúria")
                && !ataque.nome.equals("Crescer")
                && !ataque.nome.equals("Carga")
                && !ataque.nome.equals("Concentração")
                && !ataque.nome.equals("Aumentar Ataque")
                && !ataque.nome.equals("Fortificar")
                && !ataque.nome.equals("Endurecer")
                && !ataque.nome.equals("Acelerar")
                && !ataque.nome.equals("Distrair");
    }

    private void capturar() {
        if (!batalhaDisponivel())
            return;
        if (Captura.tentar(estado.getBag(), selvagem, random)) {
            Monstrinho capturado = estado.getBag().get(estado.getBag().size() - 1);
            log.append("\nCaptura realizada! " + capturado.nome
                    + " foi para o final da bag.");
            explorar();
        } else {
            log.append("\nA captura falhou (chance: "
                    + Math.round(Captura.calcularChance(selvagem) * 100) + "%).");
            contraAtacar();
            trocarSeDerrotado();
            arena.atualizar(jogador, selvagem);
        }
    }

    private void contraAtacar() {
        if (jogador == null || selvagem == null || jogador.vida <= 0 || selvagem.vida <= 0) {
            return;
        }
        Ataque contraAtaque = selvagem.ataques[random.nextInt(selvagem.ataques.length)];
        log.append("\n" + selvagem.nome + " usou " + contraAtaque.nome + ".");
        log.append(Batalha.aplicarEfeito(contraAtaque, selvagem, jogador, random));
        if (causaDano(contraAtaque)) {
            Batalha.ResultadoDano resultado = Batalha.atacar(selvagem, jogador, random);
            log.append("\n" + jogador.nome + " sofreu " + resultado.valor + " de dano.");
        }
    }

    private boolean batalhaDisponivel() {
        if (jogador == null || selvagem == null) {
            log.append("\nExplore para iniciar uma batalha.");
            return false;
        }
        if (jogador.vida <= 0) {
            trocarSeDerrotado();
            return false;
        }
        return jogador.vida > 0 && selvagem.vida > 0;
    }

    private void trocarSeDerrotado() {
        if (jogador == null || jogador.vida > 0) {
            return;
        }

        estado.getBag().removeIf(criatura -> criatura.vida <= 0);
        List<Monstrinho> disponiveis = new ArrayList<>();
        for (Monstrinho criatura : estado.getBag()) {
            if (criatura != jogador && criatura.vida > 0 && !disponiveis.contains(criatura)) {
                disponiveis.add(criatura);
            }
        }

        if (disponiveis.isEmpty()) {
            log.append("\nTodos os seus monstrinhos foram derrotados.");
            log.append("\nA batalha terminou.");
            jogador = null;
            ataques.removeAllItems();
            janela.mostrar("GAME_OVER");
            return;
        }

        String[] opcoes = new String[disponiveis.size()];
        for (int indice = 0; indice < disponiveis.size(); indice++) {
            Monstrinho criatura = disponiveis.get(indice);
            opcoes[indice] = criatura.nome + " | Nv. " + criatura.nivel
                    + " | Vida " + criatura.vida + "/" + criatura.vidaMaxima;
        }

        int escolha = JOptionPane.showOptionDialog(janela,
                jogador.nome + " foi derrotado. Escolha o próximo monstrinho:",
                "Trocar monstrinho", JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);
        if (escolha < 0) {
            escolha = 0;
        }

        jogador = disponiveis.get(escolha);
        atualizarAtaques();
        log.append("\n" + jogador.nome + " entrou na batalha!");
    }

    private void trocarOponente() {
        List<Monstrinho> candidatos = new ArrayList<>();
        for (Monstrinho criatura : estado.getBanco()) {
            if (!criatura.nome.equals(selvagem.nome)) {
                candidatos.add(criatura);
            }
        }

        if (candidatos.isEmpty()) {
            log.append("\nVocê venceu a batalha!");
            selvagem = null;
            return;
        }

        Monstrinho proximo = candidatos.get(random.nextInt(candidatos.size())).copiar();
        Batalha.fortalecerPorRank(proximo, jogador.nivel, estado.getRank(), random);
        selvagem = proximo;
        log.append("\nOutro oponente apareceu: " + selvagem.nome + "!");
    }

    private void adicionarBotao(JPanel painel, String texto, Runnable acao) {
        JButton botao = new JButton(texto);
        botao.addActionListener(evento -> acao.run());
        painel.add(botao);
    }
}
