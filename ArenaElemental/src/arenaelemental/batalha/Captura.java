package arenaelemental.batalha;

import arenaelemental.modelo.Monstrinho;
import java.util.List;
import java.util.Random;

public final class Captura {
    private Captura() {
    }

    public static double calcularChance(Monstrinho criatura) {
        double percentualVida = criatura.vidaMaxima <= 0
                ? 0.0 : (double) criatura.vida / criatura.vidaMaxima;
        double chance = 1.0 - percentualVida * 0.75;
        return Math.max(0.15, Math.min(0.95, chance));
    }

    public static boolean tentar(List<Monstrinho> bag, Monstrinho criatura) {
        return tentar(bag, criatura, new Random());
    }

    public static boolean tentar(List<Monstrinho> bag, Monstrinho criatura, Random random) {
        if (bag.size() >= EstadoJogo.LIMITE_BAG) {
            return false;
        }
        if (random.nextDouble() >= calcularChance(criatura)) {
            return false;
        }
        Monstrinho capturada = criatura.copiar();
        capturada.recuperar();
        bag.add(capturada);
        return true;
    }
}
