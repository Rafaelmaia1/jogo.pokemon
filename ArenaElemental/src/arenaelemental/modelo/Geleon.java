package arenaelemental.modelo;

public class Geleon extends Monstrinho {
    public Geleon() {
        super("Geleon", "GELO", 5, 108, 24, 22,
                new Ataque("Rajada de Gelo", "Ataca com energia congelante."),
                new Ataque("Congelar", "Pode paralisar o inimigo."),
                new Ataque("Concentração", "Aumenta o ataque."),
                new Ataque("Nevasca", "Um poderoso ataque de gelo."));
    }

    @Override
    protected double bonusEspecial(Monstrinho alvo) {
        return alvo.vida <= alvo.vidaMaxima * 0.50 ? 1.15 : 1.0;
    }
}
