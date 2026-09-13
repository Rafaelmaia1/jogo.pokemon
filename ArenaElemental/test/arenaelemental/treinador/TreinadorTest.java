package arenaelemental.treinador;

import arenaelemental.modelo.Marulho;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TreinadorTest {
    @Test
    void equipeNaoUltrapassaSeisCriaturas() {
        Treinador treinador = new Treinador("Ana");
        for (int i = 0; i < Treinador.getTamanhoMaxEquipe(); i++) {
            assertTrue(treinador.adicionarNaEquipe(new Marulho()));
        }
        assertFalse(treinador.adicionarNaEquipe(new Marulho()));
    }
}
