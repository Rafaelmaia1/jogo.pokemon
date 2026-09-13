package arenaelemental.batalha;

import arenaelemental.modelo.FabricaCriaturas;
import arenaelemental.modelo.Monstrinho;
import arenaelemental.treinador.Treinador;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EstadoJogo {
    public static final int LIMITE_BAG = 7;

    private final Random random = new Random();
    private final ArrayList<Monstrinho> banco;
    private final Treinador treinador = new Treinador();
    private int rank = 1;

    public EstadoJogo() {
        banco = FabricaCriaturas.criarBanco();
    }

    public Random getRandom() {
        return random;
    }

    public ArrayList<Monstrinho> getBanco() {
        return banco;
    }

    public List<Monstrinho> getEquipe() {
        return treinador.getEquipe();
    }

    public List<Monstrinho> getBag() {
        return treinador.getBag();
    }

    public Treinador getTreinador() {
        return treinador;
    }

    public int getRank() {
        return rank;
    }

    public void avancarRank() {
        rank++;
    }

    public void novaJornada() {
        for (Monstrinho criatura : banco) {
            criatura.recuperar();
        }
        treinador.novaJornada();
        rank = 1;
    }
}
