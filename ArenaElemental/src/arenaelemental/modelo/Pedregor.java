package arenaelemental.modelo;

public class Pedregor extends Monstrinho {
    public Pedregor() {
        super("Pedregor", "TERRA", 5, 125, 24, 25,
                new Ataque("Pedrada", "Ataca com uma pedra pesada."),
                new Ataque("Endurecer", "Aumenta a defesa do usuário."),
                new Ataque("Abalar", "Pode reduzir a defesa inimiga."),
                new Ataque("Tremor", "Um poderoso ataque terrestre."));
    }

    @Override
    protected double bonusEspecial(Monstrinho alvo) {
        return defesa >= alvo.defesa ? 1.10 : 1.0;
    }
}
