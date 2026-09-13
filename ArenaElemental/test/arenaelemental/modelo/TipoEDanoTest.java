package arenaelemental.modelo;

import arenaelemental.batalha.Batalha;
import java.util.Random;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TipoEDanoTest {
    @Test
    void vantagemElementalEAplicadaAoDano() {
        Braseiro atacante = new Braseiro();
        Folharal defensor = new Folharal();
        Batalha.ResultadoDano dano = Batalha.calcularDano(atacante, defensor, new RandomFixo());
        assertEquals(1.5, TipoElemental.FOGO.vantagemSobre(TipoElemental.PLANTA));
        assertTrue(dano.valor > atacante.ataque - defensor.defesa / 3);
    }

    private static final class RandomFixo extends Random {
        @Override
        public int nextInt(int limite) {
            return 0;
        }
    }
}
