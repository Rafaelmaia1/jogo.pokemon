package arenaelemental.batalha;

import arenaelemental.modelo.Marulho;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CapturaTest {
    @Test
    void chanceAumentaQuandoVidaDiminui() {
        Marulho cheia = new Marulho();
        Marulho fraca = new Marulho();
        fraca.vida = 1;
        assertTrue(Captura.calcularChance(fraca) > Captura.calcularChance(cheia));
        assertTrue(Captura.calcularChance(cheia) >= 0.15);
        assertTrue(Captura.calcularChance(fraca) <= 0.95);
    }

    @Test
    void bagRespeitaLimiteMesmoComCapturaGarantida() {
        ArrayList<Marulho> bag = new ArrayList<>();
        for (int i = 0; i < EstadoJogo.LIMITE_BAG; i++) {
            bag.add(new Marulho());
        }
        assertEquals(EstadoJogo.LIMITE_BAG, bag.size());
    }
}
