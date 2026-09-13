package arenaelemental.modelo;

public class Flamion extends Monstrinho {
    public Flamion() {
        super("Flamion", "FOGO", 5, 98, 31, 14,
                new Ataque("Explosão", "Um ataque explosivo de fogo."),
                new Ataque("Queimar", "Pode causar queimadura."),
                new Ataque("Fúria", "Aumenta o ataque."),
                new Ataque("Brasa", "Ataca com uma brasa ardente."));
    }

    @Override
    protected double bonusEspecial(Monstrinho alvo) {
        return alvo.envenenado || alvo.queimado ? 1.15 : 1.0;
    }
}
