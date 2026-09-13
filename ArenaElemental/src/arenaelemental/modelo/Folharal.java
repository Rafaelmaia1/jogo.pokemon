package arenaelemental.modelo;

public class Folharal extends Monstrinho {
    public Folharal() {
        super("Folharal", "PLANTA", 5, 105, 23, 20,
                new Ataque("Folha Navalha", "Lança folhas afiadas contra o inimigo."),
                new Ataque("Veneno", "Pode envenenar o inimigo."),
                new Ataque("Crescer", "Aumenta o ataque do usuário."),
                new Ataque("Raiz", "Pode reduzir a defesa inimiga."));
    }

    @Override
    protected double bonusEspecial(Monstrinho alvo) {
        return alvo.envenenado ? 1.20 : 1.0;
    }

    @Override
    protected void efeitoPosAtaque(Monstrinho alvo, int dano) {
        vida = Math.min(vidaMaxima, vida + Math.max(1, dano / 5));
    }
}
