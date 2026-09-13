package arenaelemental.modelo;

public class Aquilon extends Monstrinho {
    public Aquilon() {
        super("Aquilon", "ÁGUA", 5, 115, 20, 24,
                new Ataque("Onda", "Uma onda atinge o adversário."),
                new Ataque("Reduzir Defesa", "Diminui a defesa do inimigo."),
                new Ataque("Aumentar Ataque", "Aumenta o ataque do usuário."),
                new Ataque("Jato Forte", "Um ataque de água concentrado."));
    }

    @Override
    protected double bonusEspecial(Monstrinho alvo) {
        return alvo.queimado ? 1.20 : 1.0;
    }
}
