package arenaelemental.modelo;

import arenaelemental.batalha.Batalha;
import java.util.Random;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BonusEspecialTest {
    @Test
    void braseiroRecebeBonusContraAlvoComPoucaVida() {
        Braseiro atacante = new Braseiro();
        Folharal alvoCheio = new Folharal();
        Folharal alvoFraco = new Folharal();
        alvoFraco.vida = 20;
        int danoCheio = Batalha.calcularDano(atacante, alvoCheio, new RandomFixo()).valor;
        int danoFraco = Batalha.calcularDano(atacante, alvoFraco, new RandomFixo()).valor;
        assertTrue(danoFraco > danoCheio);
    }

    private static final class RandomFixo extends Random {
        @Override
        public int nextInt(int limite) {
            return 0;
        }
    }
}
