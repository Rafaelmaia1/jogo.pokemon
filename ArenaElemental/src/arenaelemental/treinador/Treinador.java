package arenaelemental.treinador;

import arenaelemental.modelo.Monstrinho;
import java.util.ArrayList;
import java.util.List;

public class Treinador {
    private static final int TAMANHO_MAX_EQUIPE = 6;
    private final String nome;
    private final ArrayList<Monstrinho> equipe = new ArrayList<>();
    private final ArrayList<Monstrinho> bag = new ArrayList<>();

    public Treinador() {
        this("Treinador");
    }

    public Treinador(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public List<Monstrinho> getEquipe() {
        return equipe;
    }

    public List<Monstrinho> getBag() {
        return bag;
    }

    public static int getTamanhoMaxEquipe() {
        return TAMANHO_MAX_EQUIPE;
    }

    public boolean adicionarNaEquipe(Monstrinho criatura) {
        if (equipe.size() >= TAMANHO_MAX_EQUIPE) {
            return false;
        }
        equipe.add(criatura);
        return true;
    }

    public Monstrinho getAtiva() {
        for (Monstrinho criatura : equipe) {
            if (criatura.vida > 0) {
                return criatura;
            }
        }
        return null;
    }

    public void novaJornada() {
        equipe.clear();
        bag.clear();
    }

}
