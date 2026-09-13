package arenaelemental.modelo;

public class Marulho extends Monstrinho {
    public Marulho() {
        super("Marulho", "ÁGUA", 5, 110, 22, 21,
                new Ataque("Jato d'Água", "Um poderoso jato de água."),
                new Ataque("Enfraquecer", "Reduz a defesa do inimigo."),
                new Ataque("Concentração", "Aumenta o ataque do usuário."),
                new Ataque("Bolha", "Pode deixar o inimigo mais lento."));
    }

    @Override
    protected double bonusEspecial(Monstrinho alvo) {
        return vida > vidaMaxima * 0.50 ? 1.15 : 1.0;
    }
}
